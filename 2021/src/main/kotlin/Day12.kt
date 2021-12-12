class Day12(input: List<String>) {

    private val connections = input.map { it.split("-") }.flatMap { (begin, end) -> listOf(begin to end, end to begin) }
        .filterNot { (_, end) -> end == "start" }.groupBy({ it.first }, { it.second })

    private fun countPaths(
        cave: String = "start",
        path: List<String> = listOf(),
        smallCaveAllowance: (cave: String, currentPath: List<String>) -> Boolean
    ): Int {
        return when {
            cave == "end" -> 1
            cave.isLowerCase() && smallCaveAllowance(cave, path) -> 0
            else -> connections.getValue(cave).sumOf { countPaths(it, path + cave, smallCaveAllowance) }
        }
    }

    fun solve1(): Int {
        return countPaths { cave, currentPath ->
            cave in currentPath
        }
    }

    fun solve2(): Int {
        return countPaths { cave, currentPath ->
            val counts = currentPath.filter { it.isLowerCase() }.groupingBy { it }.eachCount()
            cave in counts.keys && counts.any { it.value > 1 }
        }
    }
}
