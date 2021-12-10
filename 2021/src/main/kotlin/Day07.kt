import kotlin.math.abs

class Day07(private val input: List<Int>) {

    private fun minFuel(positions: List<Int>, fuelFunction: (Int) -> Int): Int =
        (positions.minOrNull()!!..positions.maxOrNull()!!).minOf {
            positions.fold(0) { sum, item -> sum + fuelFunction(abs(item - it)) }
        }

    fun solve1(): Int {
        return minFuel(input) { x -> x }
    }

    fun solve2(): Int {
        return minFuel(input) { x -> (x * (x + 1) / 2) }
    }

}
