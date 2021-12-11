import kotlin.math.pow

typealias Grid = List<List<Int>>

typealias Entry = Pair<List<Set<Char>>, List<Set<Char>>>

fun <T> List<T>.midpoint(): T = this[lastIndex / 2]

fun Int.binaryColumnValue(): Int = 2.0.pow(this.toDouble()).toInt()

fun Int.maxBinaryValue(): Int = binaryColumnValue() - 1

fun <T> Pair<List<T>, List<T>>.longest(): List<T> = if (first.size >= second.size) first else second

fun <T> Pair<List<T>, List<T>>.shortest(): List<T> = if (first.size < second.size) first else second
