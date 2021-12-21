package alternative

class Day04(private val input: List<String>) {

    private fun prepareBoards(): Pair<List<Int>, List<Board>> {
        val numbers = input.first().trim().split(",").map(String::toInt)
        val grids = input.drop(1)
        val boards = grids.map(Board.Companion::parse)
        return Pair(numbers, boards)
    }

    fun solve1(): Int {
        val (numbers, boards) = prepareBoards()
        numbers.forEach { n ->
            boards.forEach { board ->
                board.markNumber(n)
                if (board.won()) {
                    return board.unmarkedNumbers().sum() * n
                }
            }
        }
        return 0
    }

    fun solve2(): Int {
        val (numbers, boards) = prepareBoards()
        val winningBoards = mutableSetOf<Board>()
        numbers.forEach { n ->
            boards.forEach { board ->
                board.markNumber(n)
                if (board.won() && board !in winningBoards) {
                    winningBoards += board
                    if (winningBoards.size == boards.size) {
                        return board.unmarkedNumbers().sum() * n
                    }
                }
            }
        }
        return 0
    }

    private class Board(private val grid: List<List<Int>>) {

        private val seen = mutableSetOf<Int>()

        fun markNumber(n: Int) {
            seen += n
        }

        fun unmarkedNumbers(): List<Int> {
            return grid.flatMap { row -> row.mapNotNull { field -> if (field !in seen) field else null } }
        }

        fun won(): Boolean {
            val wonRow = grid.any { row -> row.all { it in seen } }
            val wonColumn = grid.first().indices.any { column ->
                grid.indices.all { row ->
                    grid[row][column] in seen
                }
            }
            return wonRow || wonColumn
        }

        companion object {
            fun parse(grid: String): Board =
                grid.trim().split("\n").map { it.trim().split("\\s+".toRegex()).map(String::toInt) }.let { Board(it) }
        }
    }
}
