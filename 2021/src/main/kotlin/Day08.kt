typealias Entry = Pair<List<Set<Char>>, List<Set<Char>>>

class Day08(private val input: List<String>) {

    private fun List<String>.entries(): List<Entry> = this.map { it.split(" | ") }.map { (patterns, output) ->
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
            put(3, patterns.filter { it.size == 5 }.first { it.containsAll(getValue(1)) })
            put(2, patterns.filter { it.size == 5 }.first { it.intersect(getValue(4)).size == 2 })
            put(5, patterns.filter { it.size == 5 }.first { it !in values })
            put(9, patterns.filter { it.size == 6 }.first { it.containsAll(getValue(4)) })
            put(6, patterns.filter { it.size == 6 }.first { it.intersect(getValue(1)).size == 1 })
            put(0, patterns.filter { it.size == 6 }.first { it !in values })
        }

        val mappedPatterns = mappedDigits.entries.associateBy({ it.value }) { it.key }
        output.joinToString("") { mappedPatterns.getValue(it).toString() }.toInt()
    }

    fun solve1(): Int {
        return countUnique(input.entries())
    }

    fun solve2(): Int {
        return countAll(input.entries())
    }
}