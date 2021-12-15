import java.util.PriorityQueue

class Day15(val input: Grid) {

    private fun cost(grid: Grid): Int {
        val dist = Array(grid.size) { Array(grid.first().size) { Int.MAX_VALUE } }.apply { this[0][0] = 0 }
        val toVisit = PriorityQueue<Pair<Int, Int>> { (pY, pX), (rY, rX) -> dist[pY][pX].compareTo(dist[rY][rX]) }
        val visited = mutableSetOf(0 to 0)
        toVisit.add(0 to 0)

        while (toVisit.isNotEmpty()) {
            val (y, x) = toVisit.poll().also { visited.add(it) }
            neighbours(y, x, grid).forEach { (nY, nX) ->
                if (!visited.contains(nY to nX)) {
                    val newDistance = dist[y][x] + grid[nY][nX]
                    if (newDistance < dist[nY][nX]) {
                        dist[nY][nX] = newDistance
                        toVisit.add(nY to nX)
                    }
                }
            }
        }
        return dist[dist.lastIndex][dist.last().lastIndex]
    }

    private fun neighbours(y: Int, x: Int, grid: List<List<Int>>): List<Pair<Int, Int>> {
        return arrayOf((-1 to 0), (1 to 0), (0 to -1), (0 to 1)).map { (dy, dx) -> y + dy to x + dx }
            .filter { (y, x) -> y in grid.indices && x in grid.first().indices }
    }

    private fun List<List<Int>>.expand(times: Int): List<List<Int>> {
        val expandedRight = map { row -> (1 until times).fold(row) { acc, step -> acc + row.increasedAndCapped(step) } }
        return (1 until times).fold(expandedRight) { acc, step -> acc + expandedRight.increased(step) }
    }

    private fun Grid.increased(by: Int) = map { row -> row.increasedAndCapped(by) }

    private fun List<Int>.increasedAndCapped(by: Int) = map { level -> (level + by).let { if (it > 9) it - 9 else it } }

    fun solve1(): Int {
        return cost(input)
    }

    fun solve2(): Int {
        return cost(input.expand(5))
    }

}
