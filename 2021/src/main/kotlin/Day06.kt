class Day06(private val input: List<Int>) {

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

    fun solve1(): Long {
        var fishes = LongArray(9).apply { input.forEach { this[it] += 1L } }
        return simulateLanternFish(fishes, 80)
    }

    fun solve2(): Long {
        var fishes = LongArray(9).apply { input.forEach { this[it] += 1L } }
        return simulateLanternFish(fishes, 256)
    }
}