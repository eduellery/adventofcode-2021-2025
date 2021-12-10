class Day02(input: List<String>) {

    private val commands = input.map { Command.of(it) }

    fun solve1(): Int {
        var (depth1, horizontal) = arrayOf(0, 0)
        commands.forEach {
            when (it.name) {
                "forward" -> horizontal += it.amount
                "down" -> depth1 += it.amount
                "up" -> depth1 -= it.amount
            }
        }
        return depth1 * horizontal
    }

    fun solve2(): Int {
        var (depth2, horizontal, aim) = arrayOf(0, 0, 0)
        commands.forEach {
            when (it.name) {
                "forward" -> {
                    depth2 += aim * it.amount
                    horizontal += it.amount
                }
                "down" -> aim += it.amount
                "up" -> aim -= it.amount
            }
        }
        return depth2 * horizontal
    }

    private class Command(val name: String, val amount: Int) {
        companion object {
            fun of(input: String) = run { Command(input.substringBefore(" "), input.substringAfter(" ").toInt()) }
        }
    }
}
