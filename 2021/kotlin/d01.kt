import java.io.File

fun String.intLines(): List<Int> {
    return File(this).readLines().map { it.toInt() }
}

fun main() {
    val input = "d01.in".intLines()
    val part1 = input.windowed(2).filter { (a, b) -> b > a }.count()
    val part2 = input.windowed(3).map { it.sum() }.windowed(2).filter { (a, b) -> b > a }.count()
    println("P1: " + part1)
    println("P2: " + part2)
}
