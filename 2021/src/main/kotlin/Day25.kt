class Day25(val input: List<String>) {

    private val grid = input.map { it.toCharArray() }.toTypedArray()
    private val east = '>'
    private val south = 'v'
    private val blank = '.'

    private fun moveEast(): Boolean {
        var moved = false
        for (row in grid.indices) {
            var movedFirst = false
            var col = 0
            while (col < grid[0].size) {
                if (grid[row][col] == east) {
                    if (col <= grid[0].size - 2 && grid[row][col + 1] == blank) {
                        moved = true
                        movedFirst = movedFirst || (col == 0)
                        grid[row][col] = blank
                        grid[row][col + 1] = east
                        col++
                    } else if (col == grid[0].size - 1 && grid[row][0] == blank && !movedFirst) {
                        moved = true
                        grid[row][col] = blank
                        grid[row][0] = east
                    }
                }
                col++
            }
        }
        return moved
    }

    private fun moveSouth(): Boolean {
        var moved = false
        for (col in grid[0].indices) {
            var movedFirst = false
            var row = 0
            while (row < grid.size) {
                if (grid[row][col] == south) {
                    if (row <= grid.size - 2 && grid[row + 1][col] == blank) {
                        moved = true
                        movedFirst = movedFirst || (row == 0)
                        grid[row][col] = blank
                        grid[row + 1][col] = south
                        row++
                    } else if (row == grid.size - 1 && grid[0][col] == blank && !movedFirst) {
                        moved = true
                        grid[row][col] = blank
                        grid[0][col] = south
                    }
                }
                row++
            }
        }
        return moved
    }

    fun solve1(): Int {
        var steps = 0
        do {
            steps++
            var movedEast = moveEast()
            var movedSouth = moveSouth()
        } while (movedEast || movedSouth)
        return steps
    }
}