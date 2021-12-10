typealias HeightMap = List<List<Int>>

class Day09(private val input: List<String>) {

    private fun List<String>.heightMap(): HeightMap = this.map { it.toList().map { Character.getNumericValue(it) } }

    private fun HeightMap.lowPoints(): List<Pair<Int, Int>> = this.foldIndexed(emptyList()) { rowIdx, allPoints, row ->
        row.foldIndexed(allPoints) { colIdx, points, height ->
            this.neighbours(rowIdx, colIdx).all { (x, y) -> this[x][y] > height }
                .let { isLowest -> if (isLowest) points + (rowIdx to colIdx) else points }
        }
    }

    private fun HeightMap.neighbours(rowIdx: Int, colIdx: Int): List<Pair<Int, Int>> =
        arrayOf((-1 to 0), (1 to 0), (0 to -1), (0 to 1)).map { (dx, dy) -> rowIdx + dx to colIdx + dy }
            .filter { (x, y) -> x in this.indices && y in this.first().indices }

    private fun HeightMap.basin(rowIdx: Int, colIdx: Int): List<Pair<Int, Int>> =
        this.neighbours(rowIdx, colIdx).filterNot { (x, y) -> this[x][y] == 9 }
            .fold(listOf((rowIdx to colIdx))) { points, (x, y) ->
                points + if (this[x][y] - this[rowIdx][colIdx] >= 1) basin(x, y) else emptyList()
            }

    fun solve1(): Int {
        val heightMap = input.heightMap()
        return heightMap.lowPoints().sumOf { (x, y) -> heightMap[x][y] + 1 }
    }

    fun solve2(): Int {
        val heightMap = input.heightMap()
        return heightMap.lowPoints().map { (rowIdx, colIdx) -> heightMap.basin(rowIdx, colIdx).toSet().size }
            .sortedDescending().take(3).reduce { acc, i -> acc * i }
    }
}
