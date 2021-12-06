import java.io.File

private fun String.ints(): List<Int> = File(this).readLines().single().split(",").map { it.toInt() }

private fun simulateLanternFish(ages: List<Int>, days: Int): Long {
    var fish = MutableList<Long>(9) { index -> ages.count { it == index }.toLong() }
    repeat(days) {
        val spawns = fish.first()
        fish = fish.drop(1).toMutableList()
        fish[6] += spawns
        fish.add(spawns)
    }
    return fish.sum()
}

fun main() {
    val numbers = "d06.in".ints()
    println("P1: ${simulateLanternFish(numbers, 80)}")
    println("P2: ${simulateLanternFish(numbers, 256)}")
}