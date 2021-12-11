class Day03(private val input: List<String>) {

    private fun List<String>.bitwiseFilter(keepMostCommon: Boolean, width: Int): String =
        (0 until width).fold(this) { inputs, column ->
            if (inputs.size == 1) inputs else {
                val split = inputs.partition { it[column] == '1' }
                if (keepMostCommon) split.longest() else split.shortest()
            }
        }.first()

    fun solve1(): Int {
        val width = input.first().length
        val gamma = (0 until width).sumOf { column ->
            val columnValue = (width - column - 1).binaryColumnValue()
            if (input.count { it[column] == '1' } > input.size / 2) columnValue else 0
        }
        val epsilon = width.maxBinaryValue() - gamma
        return gamma * epsilon
    }

    fun solve2(): Int {
        val width = input.first().length
        val o2 = input.bitwiseFilter(true, width).toInt(2)
        val c02 = input.bitwiseFilter(false, width).toInt(2)
        return o2 * c02
    }

}
