import kotlin.math.floor
import kotlin.math.ceil

class Day18(val input: List<String>) {

    private fun magnitude(num: SnailFishNumber): Long {
        return when (num) {
            is SnailFishNumber.Regular -> num.value.toLong()
            is SnailFishNumber.Number -> magnitude(num.left) * 3 + magnitude(num.right) * 2
        }
    }

    private fun reduce(num: SnailFishNumber): SnailFishNumber {
        while (true) {
            val canExplode = findExploding(num)
            if (canExplode != null) {
                explode(canExplode)
                continue
            }
            val canSplit = findSplitting(num)
            if (canSplit != null) {
                split(canSplit)
                continue
            }
            break
        }
        return num
    }

    private fun findExploding(num: SnailFishNumber, depth: Int = 1): SnailFishNumber.Number? {
        return when (num) {
            is SnailFishNumber.Regular -> null
            is SnailFishNumber.Number -> {
                if (depth == 5) {
                    num
                } else {
                    findExploding(num.left, depth + 1) ?: findExploding(num.right, depth + 1)
                }
            }
        }
    }

    private fun findSplitting(num: SnailFishNumber): SnailFishNumber.Regular? {
        return when (num) {
            is SnailFishNumber.Regular -> if (num.value >= 10) num else null
            is SnailFishNumber.Number -> {
                findSplitting(num.left) ?: findSplitting(num.right)
            }
        }
    }

    private fun explode(num: SnailFishNumber.Number) {
        firstNonSideParent(num, SnailFishNumber.Number::left)?.let { rightMost(it.left) }?.apply {
            value += (num.left as SnailFishNumber.Regular).value
        }
        firstNonSideParent(num, SnailFishNumber.Number::right)?.let { leftMost(it.right) }?.apply {
            value += (num.right as SnailFishNumber.Regular).value
        }
        num.parent?.replace(num, SnailFishNumber.Regular(0))
    }

    private fun firstNonSideParent(
        num: SnailFishNumber,
        side: SnailFishNumber.Number.() -> SnailFishNumber
    ): SnailFishNumber.Number? {
        var current = num
        while (current.parent != null) {
            if (current.parent!!.side() !== current) {
                return current.parent
            } else {
                current = current.parent!!
            }
        }
        return null
    }

    private fun rightMost(num: SnailFishNumber): SnailFishNumber.Regular {
        return when (num) {
            is SnailFishNumber.Regular -> num
            is SnailFishNumber.Number -> rightMost(num.right)
        }
    }

    private fun leftMost(num: SnailFishNumber): SnailFishNumber.Regular {
        return when (num) {
            is SnailFishNumber.Regular -> num
            is SnailFishNumber.Number -> leftMost(num.left)
        }
    }

    private fun split(num: SnailFishNumber.Regular) {
        val l = floor(num.value / 2.0).toInt()
        val r = ceil(num.value / 2.0).toInt()
        val newNum = SnailFishNumber.Number(SnailFishNumber.Regular(l), SnailFishNumber.Regular(r))

        num.parent?.replace(num, newNum)
    }

    private fun <E> permutations(list: List<E>, length: Int? = null): Sequence<List<E>> = sequence {
        val n = list.size
        val r = length ?: list.size

        val indices = list.indices.toMutableList()
        val cycles = (n downTo (n - r)).toMutableList()
        yield(indices.take(r).map { list[it] })

        run@ while (true) {
            for (i in (r - 1) downTo 0) {
                cycles[i]--
                if (cycles[i] == 0) {
                    val end = indices[i]
                    for (j in i until indices.size - 1) {
                        indices[j] = indices[j + 1]
                    }
                    indices[indices.size - 1] = end
                    cycles[i] = n - i
                } else {
                    val j = cycles[i]
                    val tmp = indices[i]
                    indices[i] = indices[-j + indices.size]
                    indices[-j + indices.size] = tmp
                    yield(indices.take(r).map { list[it] })
                    continue@run
                }
            }
            break@run
        }
    }

    fun solve1(): Long {
        return input.map { SnailFishNumber.of(it) }.reduce { acc, snailFishNumber -> reduce(acc + snailFishNumber) }
            .let { magnitude(it) }
    }

    fun solve2(): Long {
        return permutations(input, length = 2).maxOf {
            magnitude(reduce(SnailFishNumber.of(it[0]) + SnailFishNumber.of(it[1])))
        }
    }

    sealed class SnailFishNumber(var parent: Number? = null) {

        operator fun plus(other: SnailFishNumber): SnailFishNumber {
            return Number(this, other)
        }

        data class Number(var left: SnailFishNumber, var right: SnailFishNumber) : SnailFishNumber() {
            init {
                left.parent = this
                right.parent = this
            }

            fun replace(old: SnailFishNumber, new: SnailFishNumber) {
                if (left === old) {
                    left = new
                } else {
                    right = new
                }
                new.parent = this
            }
        }

        data class Regular(var value: Int) : SnailFishNumber()

        companion object {
            fun of(line: String): SnailFishNumber {
                return if (line.startsWith("[")) {
                    var bracketCount = 0
                    var commaIndex = 0
                    for ((i, c) in line.withIndex()) {
                        if (c == '[') bracketCount++
                        else if (c == ']') bracketCount--
                        else if (c == ',' && bracketCount == 1) {
                            commaIndex = i
                            break
                        }
                    }
                    Number(of(line.take(commaIndex).drop(1)), of(line.drop(commaIndex + 1).dropLast(1)))
                } else {
                    Regular(line.toInt())
                }
            }
        }
    }
}
