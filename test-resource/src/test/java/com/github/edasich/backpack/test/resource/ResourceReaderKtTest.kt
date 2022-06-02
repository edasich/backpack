/*
 * MIT License
 *
 * Copyright (c) 2022 Mehdi Jahed Manesh
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

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