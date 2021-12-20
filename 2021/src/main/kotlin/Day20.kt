class Day20(val input: List<String>) {

    private val algorithm = input[0].map { it == '#' }.toTypedArray()

    private val image = input[1].split("\n").map { line ->
        line.map { it == '#' }.toTypedArray()
    }.toTypedArray()

    private fun Array<Array<Boolean>>.getAt(x: Int, y: Int, unknown: Boolean): Boolean {
        return this.getOrNull(y)?.getOrNull(x) ?: unknown
    }

    private fun Array<Array<Boolean>>.getSurrounding(x: Int, y: Int, unknown: Boolean): Int {
        val replacement = if (algorithm[0]) unknown else false
        var result = 0
        if (this.getAt(x - 1, y - 1, replacement)) result += 256
        if (this.getAt(x, y - 1, replacement)) result += 128
        if (this.getAt(x + 1, y - 1, replacement)) result += 64
        if (this.getAt(x - 1, y, replacement)) result += 32
        if (this.getAt(x, y, replacement)) result += 16
        if (this.getAt(x + 1, y, replacement)) result += 8
        if (this.getAt(x - 1, y + 1, replacement)) result += 4
        if (this.getAt(x, y + 1, replacement)) result += 2
        if (this.getAt(x + 1, y + 1, replacement)) result += 1
        return result
    }

    private fun applyAlgorithm(map: Array<Array<Boolean>>, unknown: Boolean): Array<Array<Boolean>> {
        return (-2..map.size + 1).map { y ->
            (-2..map[0].size + 1).map { x ->
                val surrounding = map.getSurrounding(x, y, unknown)
                algorithm[surrounding]
            }.toTypedArray()
        }.toTypedArray()
    }

    private fun solve(n: Int): Int {
        var processed = image
        return repeat(n) {
            processed = applyAlgorithm(processed, it % 2 != 0)
        }.let {
            processed.sumOf { row ->
                row.count { it }
            }
        }
    }

    fun solve1(): Int {
        return solve(2)
    }

    fun solve2(): Int {
        return solve(50)
    }
}
