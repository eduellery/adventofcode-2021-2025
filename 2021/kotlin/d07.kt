import java.io.File
import kotlin.math.*

typealias FuelFunction = (Int, Int) -> Int

private fun String.ints(): List<Int> = File(this).readLines().single().split(",").map { it.toInt() }

private fun linearFuel(a: Int, b: Int): Int = abs(a - b)

private fun progressionFuel(a: Int, b: Int): Int = (abs(a - b) * (abs(a - b) + 1) / 2)

private fun minFuel(positions: List<Int>, fuelFunction: FuelFunction): Int = (positions.minOrNull()!!..positions.maxOrNull()!!).minOf { positions.fold(0) { total, item -> total + fuelFunction(item, it) } }

fun main() {
    val numbers = "d07.in".ints()
    println("P1: ${minFuel(numbers, ::linearFuel)}")
    println("P2: ${minFuel(numbers, ::progressionFuel)}")
}