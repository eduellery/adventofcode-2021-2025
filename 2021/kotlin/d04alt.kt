import java.io.File

typealias BingoBoard = List<List<Int>>

private fun parseInput(input: List<String>): Set<BingoBoard> =
    input.asSequence().drop(1).filter { it.isNotEmpty() }.chunked(5).map { parseSingleBoard(it) }.toSet()

private fun parseSingleBoard(input: List<String>): BingoBoard = input.map { row ->
    row.split(" ").filter { it.isNotEmpty() }.map { it.toInt() }
}

private fun BingoBoard.isWinner(draws: Set<Int>) =
    this.any { row -> row.all { it in draws } } || (0..4).any { col -> this.all { row -> row[col] in draws } }


private fun BingoBoard.sumUnmarked(draws: Set<Int>): Int = this.sumOf { row ->
    row.filterNot { it in draws }.sum()
}

private fun getFirstWinner(draws: List<Int>, boards: Set<BingoBoard>): Int {
    val drawn = draws.take(4).toMutableSet()
    return draws.drop(4).firstNotNullOf { draw ->
        drawn += draw
        boards.firstOrNull { it.isWinner(drawn) }?.let { winner ->
            draw * winner.sumUnmarked(drawn)
        }
    }
}

private fun getLastWinner(draws: List<Int>, boards: Set<BingoBoard>): Int {
    val all = draws.toMutableSet()
    return draws.reversed().firstNotNullOf { draw ->
        all -= draw
        boards.firstOrNull { !it.isWinner(all) }?.let { winner ->
            draw * (winner.sumUnmarked(all) - draw)
        }
    }
}

fun main() {
    var input = File("d04.in").readLines()
    val draws: List<Int> = input.first().split(",").map { it.toInt() }
    val boards: Set<BingoBoard> = parseInput(input)
    println("P1: ${getFirstWinner(draws, boards)}")
    println("P2: ${getLastWinner(draws, boards)}")
}