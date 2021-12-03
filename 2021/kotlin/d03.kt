import java.io.File
import kotlin.math.pow

fun String.lines(): List<String> = File(this).readLines()

fun Int.binaryColumnValue(): Int = 2.0.pow(this.toDouble()).toInt()

fun Int.maxBinaryValue(): Int = binaryColumnValue() - 1

fun List<String>.bitwiseFilter(keepMostCommon: Boolean, width: Int): String = (0 until width).fold(this) { inputs, column ->
    if (inputs.size == 1) inputs else {
        val split = inputs.partition { it[column] == '1' }
        if (keepMostCommon) split.longest() else split.shortest()
    }
}.first()

fun <T> Pair<List<T>, List<T>>.longest(): List<T> = if (first.size >= second.size) first else second

fun <T> Pair<List<T>, List<T>>.shortest(): List<T> = if (first.size < second.size) first else second

fun main() {
    val input = "d03.in".lines()
    val width = input.first().length
    val gamma = (0 until width).sumOf { column ->
        val columnValue = (width - column - 1).binaryColumnValue()
        if (input.count { it[column] == '1' } > input.size / 2) columnValue else 0
    }
    val epsilon = width.maxBinaryValue() - gamma
    val o2 = input.bitwiseFilter(true, width).toInt(2)
    val c02 = input.bitwiseFilter(false, width).toInt(2)
    println("P1: ${gamma * epsilon}")
    println("P2: ${o2 * c02}")
}
