import java.io.File

typealias Entry = Pair<List<Set<Char>>, List<Set<Char>>>

private fun String.entries(): List<Entry> = File(this).readLines().map { it.split(" | ") }.map { (patterns, output) ->
    patterns.split(" ").map { it.toSet() } to output.split(" ").map { it.toSet() }
}

private fun countUnique(entries: List<Entry>): Int =
    entries.flatMap { it.second }.count { it.size in arrayOf(2, 3, 4, 7) }

private fun countAll(entries: List<Entry>): Int = entries.sumOf { (patterns, output) ->
    val mappedDigits = mutableMapOf(
        1 to patterns.first { it.size == 2 },
        4 to patterns.first { it.size == 4 },
        7 to patterns.first { it.size == 3 },
        8 to patterns.first { it.size == 7 },
    )

    with(mappedDigits) {
        put(3, patterns.filter { it.size == 5 }.first { it.intersect(getValue(1)).size == 2 })
        put(2, patterns.filter { it.size == 5 }.first { it.intersect(getValue(4)).size == 2 })
        put(5, patterns.filter { it.size == 5 }.first { it !in values })
        put(6, patterns.filter { it.size == 6 }.first { it.intersect(getValue(1)).size == 1 })
        put(9, patterns.filter { it.size == 6 }.first { it.intersect(getValue(4)).size == 4 })
        put(0, patterns.filter { it.size == 6 }.first { it !in values })
    }

    val mappedPatterns = mappedDigits.entries.associateBy({ it.value }) { it.key }
    output.joinToString("") { mappedPatterns.getValue(it).toString() }.toInt()
}

fun main() {
    val entries = "d08.in".entries()
    var part1 = countUnique(entries)
    var part2 = countAll(entries)
    println("P1: ${part1}")
    println("P2: ${part2}")
}