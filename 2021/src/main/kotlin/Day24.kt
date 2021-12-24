class Day24(val input: List<String>) {

    private fun calculate(minInsteadOfMax: Boolean): Long {
        val blocks = input.chunked(18)
        val result = MutableList(14) { -1 }
        val buffer = ArrayDeque<Pair<Int, Int>>()
        fun List<String>.lastOf(command: String) = last { it.startsWith(command) }.split(" ").last().toInt()
        val best = if (minInsteadOfMax) 1 else 9
        blocks.forEachIndexed { index, instructions ->
            if ("div z 26" in instructions) {
                val offset = instructions.lastOf("add x")
                val (lastIndex, lastOffset) = buffer.removeFirst()
                setResult(result, minInsteadOfMax, index, lastIndex, best, offset + lastOffset)
            } else buffer.addFirst(index to instructions.lastOf("add y"))
        }
        return result.joinToString("").toLong()
    }

    private fun setResult(
        result: MutableList<Int>,
        minInsteadOfMax: Boolean,
        index: Int,
        lastIndex: Int,
        best: Int,
        diff: Int
    ) {
        result[lastIndex] = if (!minInsteadOfMax.xor(diff >= 0)) best else best - diff
        result[index] = if (!minInsteadOfMax.xor(diff >= 0)) best + diff else best
    }

    fun solve1(): Long {
        return calculate(false)
    }

    fun solve2(): Long {
        return calculate(true)
    }
}