class Day22(val input: List<String>) {

    private var instructions = input.map { Instruction(it) }

    fun solve1(): Long {
        val reactor = Reactor()
        return with(-50..50) {
            instructions.mapNotNull { it.limit(this, this, this) }
                .forEach { reactor.execute(it) }
        }.let { reactor.count }
    }

    fun solve2(): Long {
        val reactor = Reactor()
        return instructions.forEach { reactor.execute(it) }.let { reactor.count }
    }

    private data class Instruction(var x: IntRange, var y: IntRange, var z: IntRange, var mode: InstructionMode) {
        constructor(input: String) : this(0..0, 0..0, 0..0, InstructionMode.OFF) {
            this.mode = if (input.startsWith("on")) InstructionMode.ON else InstructionMode.OFF
            val tempInput = input.substringAfter(' ').split(',')
            val (xStart, xEnd) = tempInput[0].substringAfter('=').split("..").map { it.toInt() }
            val (yStart, yEnd) = tempInput[1].substringAfter('=').split("..").map { it.toInt() }
            val (zStart, zEnd) = tempInput[2].substringAfter('=').split("..").map { it.toInt() }
            this.x = minOf(xStart, xEnd)..maxOf(xStart, xEnd)
            this.y = minOf(yStart, yEnd)..maxOf(yStart, yEnd)
            this.z = minOf(zStart, zEnd)..maxOf(zStart, zEnd)
        }

        fun limit(x: IntRange, y: IntRange, z: IntRange): Instruction? {
            val newX = this.x.center(x)
            val newY = this.y.center(y)
            val newZ = this.z.center(z)

            if (newX.isEmpty() || newY.isEmpty() || newZ.isEmpty()) {
                return null
            }

            val xRange = IntRange(newX.first(), newX.last())
            val yRange = IntRange(newY.first(), newY.last())
            val zRange = IntRange(newZ.first(), newZ.last())

            return Instruction(xRange, yRange, zRange, mode)
        }

        fun cube(): Cube {
            return Cube(x, y, z)
        }
    }

    private enum class InstructionMode { ON, OFF }

    private data class Cube(var x: IntRange, var y: IntRange, var z: IntRange) {
        val volume: Long
            get() = x.count().toLong() * y.count().toLong() * z.count().toLong()

        fun contains(other: Cube): Boolean {
            return x.containsAll(other.x) && y.containsAll(other.y) && z.containsAll(other.z)
        }

        fun intersects(other: Cube): Boolean {
            return x.intersects(other.x) && y.intersects(other.y) && z.intersects(other.z)
        }

        operator fun minus(other: Cube): List<Cube> {
            val xSteps = x.split(other.x)
            val ySteps = y.split(other.y)
            val zSteps = z.split(other.z)

            val result = mutableListOf<Cube>()

            xSteps.forEach { newX ->
                ySteps.forEach { newY ->
                    zSteps.forEach { newZ ->
                        val newCube = Cube(newX, newY, newZ)
                        if (this.contains(newCube) && !newCube.intersects(other)) {
                            result.add(newCube)
                        }
                    }
                }
            }
            return result
        }
    }

    private class Reactor {
        private val cubes: HashSet<Cube> = hashSetOf()

        val count: Long get() = cubes.sumOf { it.volume }

        fun execute(instruction: Instruction) {
            if (instruction.mode == InstructionMode.ON) {
                add(instruction.cube())
            } else {
                remove(instruction.cube())
            }
        }

        private fun add(cube: Cube) {
            if (cubes.any { it.contains(cube) }) {
                return
            }

            cubes.filter { it.intersects(cube) }.forEach { existingCube ->
                val newCubes = existingCube - cube
                cubes.remove(existingCube)
                cubes.addAll(newCubes)
            }

            cubes.add(cube)
        }

        private fun remove(cube: Cube) {
            cubes.filter { it.intersects(cube) }.forEach { existingCube ->
                val newCubes = existingCube - cube
                cubes.remove(existingCube)
                cubes.addAll(newCubes)
            }
        }
    }
}