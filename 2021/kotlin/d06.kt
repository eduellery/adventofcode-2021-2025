import java.io.File

private fun String.ints(): List<Int> = File(this).readLines().single().split(",").map { it.toInt() }

private fun LongArray.rotateLeftInPlace() {
    val leftMost = first()
    this.copyInto(this, startIndex = 1)
    this[this.lastIndex] = leftMost
}

private fun simulateLanternFish(fishes: LongArray, days: Int): Long {
    repeat(days) {
        fishes.rotateLeftInPlace()
        fishes[6] += fishes[8]
    }
    return fishes.sum()
}

fun main() {
    var fishes = LongArray(9).apply { "d06.in".ints().forEach { this[it] += 1L } }
    println("P1: ${simulateLanternFish(fishes, 80)}")
    println("P2: ${simulateLanternFish(fishes, 256 - 80)}")
}