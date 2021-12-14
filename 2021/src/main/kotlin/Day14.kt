class Day14(val input: List<String>) {

    private val template = input[0]
    private val rules = input[1].split("\n").groupBy({ it.substringBefore(" -> ") }, { it.substringAfter(" -> ") })
        .mapValues { it.value.single() }

    private fun transform(steps: Int): Long {
        val initial = template.windowed(2).groupingBy { it }.eachCount().mapValues { it.value.toLong() }

        val pairsCount = (0 until steps).fold(initial) { current, _ ->
            buildMap {
                current.forEach { (pair, count) ->
                    with(pair[0] + rules.getValue(pair)) { put(this, getOrDefault(this, 0) + count) }
                    with(rules.getValue(pair) + pair[1]) { put(this, getOrDefault(this, 0) + count) }
                }
            }
        }

        val charsCount = buildMap<Char, Long> {
            put(template[0], 1)
            pairsCount.forEach { (pair, count) ->
                put(pair[1], getOrDefault(pair[1], 0) + count)
            }
        }

        return charsCount.maxOf { it.value } - charsCount.minOf { it.value }
    }

    fun solve1(): Long {
        return transform(10)
    }

    fun solve2(): Long {
        return transform(40)
    }

}
