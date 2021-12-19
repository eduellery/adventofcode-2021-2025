class Day19(val input: List<String>) {

    private val xFacings = arrayOf(
        intArrayOf(1, 0, 0),
        intArrayOf(-1, 0, 0),
        intArrayOf(0, 1, 0),
        intArrayOf(0, -1, 0),
        intArrayOf(0, 0, 1),
        intArrayOf(0, 0, -1)
    )

    private val upDirections = arrayOf(
        intArrayOf(1, 0), intArrayOf(0, 1), intArrayOf(-1, 0), intArrayOf(0, -1)
    )

    private val transformations = xFacings.flatMap { x ->
        upDirections.map { up ->
            transformation(x, up)
        }
    }

    private fun yFacing(x: IntArray, up: IntArray): IntArray {
        val coordinates = mutableListOf<Int>()
        var j = 0
        for (i in 0..2) {
            if (x[i] != 0) {
                coordinates.add(0)
            } else {
                coordinates.add(up[j])
                j++
            }
        }
        return coordinates.toIntArray()
    }

    private fun zFacing(x: IntArray, y: IntArray): IntArray {
        val z = IntArray(3) { 0 }
        z[0] = x[1] * y[2] - x[2] * y[1]
        z[1] = x[2] * y[0] - x[0] * y[2]
        z[2] = x[0] * y[1] - x[1] * y[0]
        return z
    }

    private fun transformation(x: IntArray, up: IntArray): Transformation {
        val ret = Array(3) { IntArray(3) }
        val y = yFacing(x, up)
        val z = zFacing(x, y)
        for (i in 0..2) {
            ret[i][0] = x[i]
            ret[i][1] = y[i]
            ret[i][2] = z[i]
        }
        return ret
    }

    private val scanners = input.map { ScannerProbe.of(it.split("\n"), transformations) }

    fun solve1(): Int {
        val located = mutableListOf<ScannerProbe>()
        val locatedIndices = mutableSetOf<Int>()
        located.add(scanners[0])
        locatedIndices.add(0)
        var locatedIdx = 0

        while (locatedIdx < located.size) {
            val a = located[locatedIdx]

            for ((i, b) in scanners.withIndex()) {
                if (i in locatedIndices) {
                    continue
                }
                val overlap = a.findOverlap(b)
                if (overlap != null) {
                    locatedIndices.add(i)
                    located.add(overlap.partner)
                }
            }

            locatedIdx++
        }

        val allBeacons = mutableSetOf<Position>()
        for (scanner in located) {
            allBeacons.addAll(scanner.beacons)
        }

        return allBeacons.size
    }

    fun solve2(): Int {
        val located = mutableListOf<Pair<ScannerProbe, Position>>()
        val locatedIndices = mutableSetOf<Int>()
        located.add(Pair(scanners[0], Position(0, 0, 0)))
        locatedIndices.add(0)
        var locatedIdx = 0

        while (locatedIdx < located.size) {
            val (a, _) = located[locatedIdx]

            for ((i, b) in scanners.withIndex()) {
                if (i in locatedIndices) {
                    continue
                }
                val overlap = a.findOverlap(b)
                if (overlap != null) {
                    locatedIndices.add(i)
                    located.add(Pair(overlap.partner, overlap.dr))
                }
            }

            locatedIdx++
        }

        var maxDistance = 0
        for (i in located.indices) {
            val (_, r1) = located[i]
            for (j in i until located.size) {
                val (_, r2) = located[j]
                val distance = r1.manhattanDistance(r2)
                if (distance > maxDistance) {
                    maxDistance = distance
                }
            }
        }

        return maxDistance
    }

    data class Position(
        val x: Int,
        val y: Int,
        val z: Int
    ) {

        operator fun plus(other: Position) = Position(x + other.x, y + other.y, z + other.z)

        operator fun minus(other: Position) = Position(x - other.x, y - other.y, z - other.z)

        fun manhattanDistance(other: Position): Int {
            var ret = 0
            val dr = this - other

            ret += if (dr.x >= 0) dr.x else -dr.x
            ret += if (dr.y >= 0) dr.y else -dr.y
            ret += if (dr.z >= 0) dr.z else -dr.z

            return ret
        }
    }

    class Overlap(
        val partner: ScannerProbe, // other transformed and moved
        val dr: Position, // position of scanner b relative to a
        val positions: Set<Position>
    )

    class ScannerProbe(val beacons: List<Position>, private val transformations: List<Transformation>) {

        fun findOverlap(other: ScannerProbe): Overlap? {

            for (t in transformations) {
                for (i in beacons.indices) {
                    for (j in other.beacons.indices) {
                        val overlap = calcOverlap(other, t, i, j)
                        if (overlap.positions.size >= 12) {
                            return overlap
                        }
                    }
                }
            }

            return null
        }

        private fun calcOverlap(other: ScannerProbe, t: Transformation, i: Int, j: Int): Overlap {
            var s = other.transform(t)
            val dr = beacons[i] - s.beacons[j]
            s.move(dr).also { s = it }
            val overlapPositions = beacons.toSet() intersect s.beacons.toSet()

            return Overlap(s, dr, overlapPositions)
        }

        private fun transform(t: Transformation): ScannerProbe {
            val transformed = mutableListOf<Position>()

            for (beacon in beacons) {
                val r = arrayOf(beacon.x, beacon.y, beacon.z)
                val x = (0..2).fold(0) { acc, i ->
                    acc + t[0][i] * r[i]
                }
                val y = (0..2).fold(0) { acc, i ->
                    acc + t[1][i] * r[i]
                }
                val z = (0..2).fold(0) { acc, i ->
                    acc + t[2][i] * r[i]
                }
                transformed.add(Position(x, y, z))
            }

            return ScannerProbe(transformed, transformations)
        }

        private fun move(dr: Position) = ScannerProbe(beacons.map { it + dr }, transformations)

        companion object {
            fun of(block: List<String>, transformations: List<Transformation>): ScannerProbe {
                val positions = block.drop(1).map { line -> line.split(",").map { it.toInt() } }
                val beacons = mutableListOf<Position>()
                positions.forEach {
                    val (x, y, z) = it
                    beacons.add(Position(x, y, z))
                }
                return ScannerProbe(beacons, transformations)
            }
        }
    }
}
