import java.io.File

val PAIRS = setOf("()", "[]", "<>", "{}")

val CLOSING = setOf(')', ']', '}', '>')

val POINTS = mapOf(
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
    PAIRS.fold(prev) { acc, pair ->
        acc.replace(pair, "")
    }
}.zipWithNext().takeWhile { it.first != it.second }.last().second

private fun String.charArrays(): List<CharArray> = File(this).readLines().map { it.removePairs().toCharArray() }

private fun List<CharArray>.corruptedScore(): Long =
    this.mapNotNull { it.firstOrNull { char -> char in CLOSING } }.sumOf { POINTS[it]!! }

private fun List<CharArray>.incompleteScore(): Long =
    this.filterNot { it.any { char -> char in CLOSING } }.map { it.reversed() }
        .map { remaining -> remaining.fold(0L) { acc, char -> acc * 5 + POINTS[char]!! } }.sorted()
        .let { it[it.size / 2] }

fun main() {
    var input = "d10.in".charArrays()
    var part1 = input.corruptedScore()
    var part2 = input.incompleteScore()
    println("P1: ${part1}")
    println("P2: ${part2}")
}
