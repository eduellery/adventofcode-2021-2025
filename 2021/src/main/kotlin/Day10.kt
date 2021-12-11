class Day10(private val input: List<String>) {

    private val pairs = setOf("()", "[]", "<>", "{}")

    private val closing = setOf(')', ']', '}', '>')

    private val points = mapOf(
        ')' to 3L,
        ']' to 57L,
        '}' to 1197L,
        '>' to 25137L,
        '(' to 1L,
        '[' to 2L,
        '{' to 3L,
        '<' to 4L,
    )

    private fun String.removePairs(): String = generateSequence(this) { prev ->
        pairs.fold(prev) { acc, pair ->
            acc.replace(pair, "")
        }
    }.zipWithNext().takeWhile { it.first != it.second }.last().second

    private fun List<String>.charArrays(): List<CharArray> = this.map { it.removePairs().toCharArray() }

    private fun List<CharArray>.corruptedScore(): Long =
        this.mapNotNull { it.firstOrNull { char -> char in closing } }.sumOf { points[it]!! }

    private fun List<CharArray>.incompleteScore(): Long =
        this.filterNot { it.any { char -> char in closing } }.map { it.reversed() }
            .map { remaining -> remaining.fold(0L) { acc, char -> acc * 5 + points[char]!! } }.sorted()
            .let { it.midpoint() }

    fun solve1(): Long {
        return input.charArrays().corruptedScore()
    }

    fun solve2(): Long {
        return input.charArrays().incompleteScore()
    }
}
