class Day11(input: List<String>) {

    private val grid =
        input.map { row -> row.toCharArray().map { it.digitToInt() }.toTypedArray() }.toTypedArray().let { Grid(it) }

    fun solve1(): Int {
        return (1..100).sumOf {
            grid.increase()
            grid.flash().also { grid.reset() }
        }
    }

    fun solve2(): Int {
        return 1 + generateSequence {
            grid.increase()
            grid.flash()
            grid.reset()
        }.takeWhile { grid.notSynchronized() }.count()
    }
}

class Grid(private val arrays: Array<Array<Int>>) {

    fun increase() {
        arrays.forEachIndexed { y, row ->
            row.forEachIndexed { x, _ ->
                arrays[y][x]++
            }
        }
    }

    fun reset() {
        arrays.forEachIndexed { y, row ->
            row.forEachIndexed { x, _ ->
                arrays[y][x] = if (arrays[y][x] > 9) 0 else arrays[y][x]
            }
        }
    }

    fun flash(): Int {
        val visited = mutableSetOf<Pair<Int, Int>>()
        arrays.forEachIndexed { y, row ->
            row.forEachIndexed { x, energy ->
                if (energy > 9 && (y to x) !in visited) {
                    val toVisit = ArrayDeque(listOf(y to x))
                    visited.add(y to x)
                    while (toVisit.isNotEmpty()) {
                        val (nextY, nextX) = toVisit.removeLast()
                        neighbours(nextY, nextX).filter { it !in visited }.filter { (y, x) -> ++arrays[y][x] > 9 }
                            .forEach { (y, x) ->
                                toVisit.addLast(y to x)
                                visited.add(y to x)
                            }
                    }
                }
            }
        }
        return visited.size
    }

    fun notSynchronized() = arrays.any { row -> row.any { energy -> energy > 0 } }

    private fun neighbours(y: Int, x: Int): List<Pair<Int, Int>> {
        return (-1..1).flatMap { dy -> (-1..1).map { dx -> dy to dx } }.filterNot { (dy, dx) -> dy == 0 && dx == 0 }
            .map { (dy, dx) -> y + dy to x + dx }
            .filter { (y, x) -> y in arrays.indices && x in arrays.first().indices }
    }

}