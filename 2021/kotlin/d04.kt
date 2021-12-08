import java.io.File

private fun String.blocks(): List<String> = File(this).readText().split("\n\n")

fun main() {
    val input = "d04.in".blocks()
    val numbers = input.first().trim().split(",").map(String::toInt)
    val grids = input.drop(1)
    val boards = grids.map(Board::parse)
    val winningBoards = mutableSetOf<Board>()
    var (part1, part2) = arrayOf(0, 0)

    run scope@{
        numbers.forEach { n ->
            boards.forEach { board ->
                board.markNumber(n)
                if (board.won() && board !in winningBoards) {
                    winningBoards += board
                    if (winningBoards.size == 1) {
                        part1 = board.unmarkedNumbers().sum() * n
                    } else if (winningBoards.size == boards.size) {
                        part2 = board.unmarkedNumbers().sum() * n
                        return@scope
                    }
                }
            }
        }
    }

    println("P1: ${part1}")
    println("P2: ${part2}")
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
