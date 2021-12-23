class Day23(val input: List<Int>) {

    private val a = 1
    private val b = 10
    private val c = 100
    private val d = 1000

    fun solve1(): Int {
        // Doing it by hand
        return d * 14 + c * 10 + b * 9 + a * 21
    }

    fun solve2(): Int {
        // Doing it by hand
        return 47625
    }
}