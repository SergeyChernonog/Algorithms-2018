package lesson1

import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertTrue

abstract class AbstractFileTests {
    protected fun assertFileContent(name: String, expectedContent: String) {
        val file = File(name)
        val content = file.readLines().joinToString("\n")
        assertEquals(expectedContent.trim(), content.trim())
    }


    fun assertBigFiles(file: String, expectedFile: String) {
        val first = File(file).readBytes()
        val second = File(expectedFile).readBytes()
        assertTrue { first.contentEquals(second) }
    }

}