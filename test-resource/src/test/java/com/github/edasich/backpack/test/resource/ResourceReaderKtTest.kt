package com.github.edasich.backpack.test.resource

import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
class ResourceReaderKtTest {

    companion object {
        const val FILE_PATH_BLANK = " "
        const val FILE_PATH_WRONG = "parent/child/wrong.json"
        const val FILE_PATH_CORRECT = "parent/child/file.json"
        val EXPECTED_FILE_CONTENT = StringBuilder()
            .appendLine("{")
            .appendLine("  \"title\": \"test-resource\"")
            .append("}")
            .toString()
    }

    @Test(expected = IllegalArgumentException::class)
    fun `with blank file path, throws exception`() {
        readFileFromResourceFolder(
            classLoader = javaClass.classLoader,
            absoluteFilePath = FILE_PATH_BLANK
        )
    }

    @Test(expected = IllegalArgumentException::class)
    fun `via extension, with blank file path, throws exception`() {
        FILE_PATH_BLANK.readFileFromResourceFolder()
    }

    @Test(expected = IllegalArgumentException::class)
    fun `with wrong file path, throws exception`() {
        readFileFromResourceFolder(
            classLoader = javaClass.classLoader,
            absoluteFilePath = FILE_PATH_WRONG
        )
    }

    @Test(expected = IllegalArgumentException::class)
    fun `via extension, with wrong file path, throws exception`() {
        FILE_PATH_WRONG.readFileFromResourceFolder()
    }

    @Test
    fun `with correct file path, must read the file content`() {
        // when
        val actualFileContent = readFileFromResourceFolder(
            classLoader = javaClass.classLoader,
            absoluteFilePath = FILE_PATH_CORRECT
        )
        // then
        MatcherAssert.assertThat(
            actualFileContent,
            Matchers.`is`(EXPECTED_FILE_CONTENT)
        )
    }

    @Test
    fun `via extension, with correct file path, must read the file content`() {
        // when
        val actualFileContent = FILE_PATH_CORRECT.readFileFromResourceFolder()
        // then
        MatcherAssert.assertThat(
            actualFileContent,
            Matchers.`is`(EXPECTED_FILE_CONTENT)
        )
    }

}