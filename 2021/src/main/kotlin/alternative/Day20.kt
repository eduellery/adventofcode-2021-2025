package alternative

class Day20(val input: List<String>) {

    private val algorithm = input[0].map { if (it == '#') '1' else '0' }.toTypedArray()

    private val originalImage = input[1].split("\n").map { row ->
        row.map { if (it == '#') '1' else '0' }.toTypedArray()
    }.toTypedArray()

    private fun enhance(steps: Int): Int {
        val processed = (0 until steps).fold(originalImage) { image, step ->
            val outside = when (algorithm.first() == '1') {
                true -> if (step % 2 == 0) algorithm.last() else algorithm.first()
                false -> '0'
            }
            (-1..image.size).map { y ->
                (-1..image.first().size).map { x ->
                    (-1..1).flatMap { dy -> (-1..1).map { dx -> dy to dx } }
                        .map { (dy, dx) -> y + dy to x + dx }
                        .joinToString("") { (y, x) -> (image.getOrNull(y)?.getOrNull(x) ?: outside).toString() }
                        .toInt(2).let { algorithm[it] }
                }.toTypedArray()
            }.toTypedArray()
        }
        return processed.sumOf { row -> row.count { it == '1' } }
    }

    fun solve1(): Int {
        return enhance(2)
    }

    fun solve2(): Int {
        return enhance(50)
    }
}
