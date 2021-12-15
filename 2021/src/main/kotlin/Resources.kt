import java.io.File
import java.net.URI

internal object Resources {

    fun resourceAsString(fileName: String): String = File(fileName.toURI()).readText()

    fun resourceAsListOfString(fileName: String): List<String> = File(fileName.toURI()).readLines()

    fun resourceAsListOfString(fileName: String, delimiter: String): List<String> =
        File(fileName.toURI()).readText().split(delimiter)

    fun resourceAsListOfInt(fileName: String): List<Int> = resourceAsListOfString(fileName).map { it.toInt() }

    fun resourceAsListOfInt(fileName: String, delimiter: String): List<Int> =
        resourceAsListOfString(fileName, delimiter).map { it.toInt() }

    fun resourceAsGrid(fileName: String): Grid =
        resourceAsListOfString(fileName).map { line -> line.map { it.digitToInt() } }

    private fun String.toURI(): URI = Resources.javaClass.classLoader.getResource(this)?.toURI()
        ?: throw IllegalArgumentException("Cannot find Resource: $this")
}
