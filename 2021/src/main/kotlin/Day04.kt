class Day04(private val input: List<String>) {

    private fun parseInput(input: List<String>): Set<Grid> =
        input.asSequence().drop(1).filter { it.isNotEmpty() }.chunked(5).map { parseSingleBoard(it) }.toSet()

    private fun parseSingleBoard(input: List<String>): Grid = input.map { row ->
        row.split(" ").filter { it.isNotEmpty() }.map { it.toInt() }
    }

    private fun Grid.isWinner(draws: Set<Int>) =
        this.any { row -> row.all { it in draws } } || (0..4).any { col -> this.all { row -> row[col] in draws } }

    private fun Grid.sumUnmarked(draws: Set<Int>): Int = this.sumOf { row ->
        row.filterNot { it in draws }.sum()
    }

    private fun getFirstWinner(draws: List<Int>, boards: Set<Grid>): Int {
        val drawn = draws.take(4).toMutableSet()
        return draws.drop(4).firstNotNullOf { draw ->
            drawn += draw
            boards.firstOrNull { it.isWinner(drawn) }?.let { winner ->
                draw * winner.sumUnmarked(drawn)
            }
        }
    }

    private fun getLastWinner(draws: List<Int>, boards: Set<Grid>): Int {
        val all = draws.toMutableSet()
        return draws.reversed().firstNotNullOf { draw ->
            all -= draw
            boards.firstOrNull { !it.isWinner(all) }?.let { winner ->
                draw * (winner.sumUnmarked(all) - draw)
            }
        }
    }

    fun solve1(): Int {
        val draws: List<Int> = input.first().split(",").map { it.toInt() }
        val boards: Set<Grid> = parseInput(input)
        return getFirstWinner(draws, boards)
    }

    fun solve2(): Int {
        val draws: List<Int> = input.first().split(",").map { it.toInt() }
        val boards: Set<Grid> = parseInput(input)
        return getLastWinner(draws, boards)
    }
}
