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

import okio.buffer
import okio.source
import java.nio.charset.StandardCharsets

/**
 * Read the content of file as [String] from resources folder.
 *
 * @param classLoader an instance of [ClassLoader] of call-site
 * @param absoluteFilePath path of file from resources folder like, folder/.../file.json
 * @throws IllegalArgumentException when file path is wrong
 * @return the content of file as string
 *
 * @see String.readFileFromResourceFolder
 */
@Throws(IllegalArgumentException::class)
fun readFileFromResourceFolder(
    classLoader: ClassLoader,
    absoluteFilePath: String
): String {
    require(absoluteFilePath.isNotBlank()) {
        "Wrong file path, make sure your path is correct."
    }
    val inputStream =
        classLoader.getResourceAsStream(absoluteFilePath)
    requireNotNull(value = inputStream) {
        "Wrong file path, make sure your path is correct."
    }
    return inputStream
        .source()
        .buffer()
        .readString(
            charset = StandardCharsets.UTF_8
        )
}

/**
 * An extension function that read the content of file as [String] from resources folder.
 *
 * @receiver the absolute file Path
 * @throws IllegalArgumentException when file path is wrong
 * @return the content of file as string
 *
 * @see readFileFromResourceFolder
 */
@Throws(IllegalArgumentException::class)
fun String.readFileFromResourceFolder(): String {
    val classLoader = String::javaClass.javaClass.classLoader
    val absoluteFilePath = this
    return readFileFromResourceFolder(
        classLoader = classLoader,
        absoluteFilePath = absoluteFilePath
    )
}