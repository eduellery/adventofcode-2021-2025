class Day01(private val input: List<Int>) {

    fun solve1(): Int = input.windowed(2).filter { (a, b) -> b > a }.count()

    fun solve2(): Int = input.asSequence().windowed(3).map { it.sum() }.windowed(2).filter { (a, b) -> b > a }.count()
}
