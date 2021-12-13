class Day13(val input: List<String>) {

    private val instructions = parse(input)

    private fun makeUpAction(y: Int) = makeAction { if (it.second <= y) it else Dot(it.first, 2 * y - it.second) }

    private fun makeLeftAction(x: Int) = makeAction { if (it.first <= x) it else Dot(2 * x - it.first, it.second) }

    private fun makeAction(transformation: (Dot) -> Dot): DotAction {
        return {
            it.fold(mutableSetOf()) { acc, dot ->
                acc.add(transformation(dot))
                acc
            }
        }
    }

    private fun display(dots: Set<Dot>): String {
        val xMax = dots.maxByOrNull { (x, _) -> x }!!.first
        val yMax = dots.maxByOrNull { (_, y) -> y }!!.second
        var result = ""
        return (0..yMax).forEach { y ->
            (0..xMax).forEach { x ->
                result += if (Dot(x, y) in dots) '#' else '.'
            }.also { result += "\n" }
        }.let { result }
    }

    fun solve1(): Int {
        val dots = instructions.first
        val actions = instructions.second
        return actions.first()(dots).size
    }

    fun solve2(): String {
        val dots = instructions.first
        val actions = instructions.second
        val result = actions.fold(dots) { acc, action ->
            action(acc)
        }
        return display(result)
    }

    private fun parse(input: List<String>): Pair<Set<Dot>, List<DotAction>> {
        val dots = mutableSetOf<Dot>()
        input[0].split("\n").map { it.split(",") }.map { dots.add(Dot(it[0].toInt(), it[1].toInt())) }

        val actions = mutableListOf<DotAction>()
        input[1].split("\n").map { it.split("=") }
            .map { actions.add(if (it[0].last() == 'x') makeLeftAction(it[1].toInt()) else makeUpAction(it[1].toInt())) }

        return Pair(dots, actions)
    }
}
