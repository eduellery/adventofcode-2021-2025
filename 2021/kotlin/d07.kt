import java.io.File
import kotlin.math.abs

private fun String.ints(): List<Int> = File(this).readLines().single().split(",").map { it.toInt() }

private fun minFuel(positions: List<Int>, fuelFunction: (Int) -> Int): Int =
    (positions.minOrNull()!!..positions.maxOrNull()!!).minOf {
        positions.fold(0) { sum, item -> sum + fuelFunction(abs(item - it)) }
    }

fun main() {
    val numbers = "d07.in".ints()
    println("P1: ${minFuel(numbers) { x -> x }}")
    println("P2: ${minFuel(numbers) { x -> (x * (x + 1) / 2) }}")
}
