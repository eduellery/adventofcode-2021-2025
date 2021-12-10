import kotlin.math.sign

class Day05(private val input: List<String>) {

    private fun List<String>.prepareInput(): List<List<Int>> = this.map {
        "(\\d+),(\\d+) -> (\\d+),(\\d+)".toRegex().matchEntire(it)!!.destructured.toList().map { it.toInt() }
    }

    private fun countMatrix(input: List<List<Int>>, matrix: Array<IntArray>, includeDiagonal: Boolean): Int {
        input.forEach {
            var (x1, y1) = arrayOf(it[0], it[1])
            var (x2, y2) = arrayOf(it[2], it[3])
            if (includeDiagonal || x1 == x2 || y1 == y2) {
                val dx = (x2 - x1).sign
                val dy = (y2 - y1).sign
                matrix[y2][x2]++
                while (x1 != x2 || y1 != y2) {
                    matrix[y1][x1]++
                    x1 += dx
                    y1 += dy
                }
            }
        }
        return matrix.sumOf { it.count { it > 1 } }
    }

    private fun createMatrix(input: List<List<Int>>): Array<IntArray> {
        val matrix = Array(input.maxOf { maxOf(it[1], it[3]) } + 1) {
            IntArray(input.maxOf { maxOf(it[0], it[2]) } + 1)
        }
        return matrix
    }

    fun solve1(): Int {
        return countMatrix(input.prepareInput(), createMatrix(input.prepareInput()), false)
    }

    fun solve2(): Int {
        return countMatrix(input.prepareInput(), createMatrix(input.prepareInput()), true)
    }

}
