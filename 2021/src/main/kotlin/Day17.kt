class Day17(val input: String) {

    private fun String.prepareInput(): List<Int> =
        "target area: x=([-\\d]+)..([-\\d]+), y=([-\\d]+)..([-\\d]+)".toRegex()
            .matchEntire(this)!!.destructured.toList().map { it.toInt() }

    private val area = input.prepareInput()

    private var minY = minOf(area[2], area[3])
    private var maxY = -minY - 1
    private val minX = (1..area[0]).first { it.sequenceSum() >= area[0] }
    private val maxX = area[1]

    private fun isInRange(point: Pair<Int, Int>): Boolean {
        return point.first in area[0]..area[1] && point.second in area[2]..area[3]
    }

    private fun isInRange(x: Int, y: Int): Boolean {
        val seqX = generateSequence(0 to x) { (posX, velX) -> posX + velX to maxOf(0, velX - 1) }
        val seqY = generateSequence(0 to y) { (posY, velY) -> posY + velY to velY - 1 }
        val validX = seqX.takeWhile { (posX, _) -> posX <= maxX }.map { it.first }
        val validY = seqY.takeWhile { (posY, _) -> posY >= minY }.map { it.first }
        return (validX zip validY).any { isInRange(it) }
    }

    fun solve1(): Int {
        return minY.sequenceSum()
    }

    fun solve2(): Int {
        return (minX..maxX).sumOf { x ->
            (minY..maxY).count { y ->
                isInRange(x, y)
            }
        }
    }
}
