import java.io.File

fun String.pairLines(): List<Pair<String, Int>> {
    return File(this).readLines().map { Pair(it.substringBefore(" "), it.substringAfter(" ").toInt()) }
}

fun main() {
    val input = "d02.in".pairLines()
    var depth1 = 0
    var depth2 = 0
    var horizontal = 0
    var aim = 0
    input
            .forEach {
                when (it.first) {
                    "forward" -> {
                        horizontal += it.second;
                        depth2 += aim * it.second;
                    }
                    "down" -> {
                        depth1 += it.second;
                        aim += it.second;
                    }
                    "up" -> {
                        depth1 -= it.second;
                        aim -= it.second;
                    }
                }
            }
    println("P1: " + depth1 * horizontal)
    println("P2: " + depth2 * horizontal)
}
