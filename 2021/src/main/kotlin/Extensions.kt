import kotlin.math.pow

typealias Transformation = Array<IntArray>

typealias Grid = List<List<Int>>

typealias Dot = Pair<Int, Int>

typealias DotAction = (Set<Dot>) -> Set<Dot>

typealias Entry = Pair<List<Set<Char>>, List<Set<Char>>>

fun String.isUpperCase(): Boolean = this.all { it.isUpperCase() }

fun String.isLowerCase(): Boolean = this.all { it.isLowerCase() }

fun <T> List<T>.midpoint(): T = this[lastIndex / 2]

fun Int.binaryColumnValue(): Int = 2.0.pow(this.toDouble()).toInt()

fun Int.maxBinaryValue(): Int = binaryColumnValue() - 1

fun Int.sequenceSum(): Int = (1 + this) * this / 2

fun <T> Pair<List<T>, List<T>>.longest(): List<T> = if (first.size >= second.size) first else second

fun <T> Pair<List<T>, List<T>>.shortest(): List<T> = if (first.size < second.size) first else second
