import java.io.File

fun String.pairs(): List<Pair<String, Int>> =
    File(this).readLines().map { Pair(it.substringBefore(" "), it.substringAfter(" ").toInt()) }

fun main() {
    val input = "d02.in".pairs()
    var (depth1, depth2, horizontal, aim) = arrayOf(0, 0, 0, 0)
    input.forEach {
        when (it.first) {
            "forward" -> {
                horizontal += it.second
                depth2 += aim * it.second
            }
            "down" -> {
                depth1 += it.second
                aim += it.second
            }
            "up" -> {
                depth1 -= it.second
                aim -= it.second
            }
        }
    }
    println("P1: ${depth1 * horizontal}")
    println("P2: ${depth2 * horizontal}")
}
