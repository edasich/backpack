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

package com.github.edasich.backpack.test.mockwebserver.ext

import com.github.edasich.backpack.test.resource.readFileFromResourceFolder
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Converter
import retrofit2.Retrofit

/**
 * Creates the rest service api, based on [MockWebServer].
 *
 * @param converterFactory the converter that is used to serializing and deserializing requests and responses
 * @param serviceClass the target rest service api as an interface with worked with [Retrofit]
 *
 * @return rest service api which is connected to [MockWebServer]
 */
fun <SERVICE> MockWebServer.createRestService(
    converterFactory: Converter.Factory,
    serviceClass: Class<SERVICE>
): SERVICE {
    return Retrofit
        .Builder()
        .baseUrl(this.url(path = "/"))
        .addConverterFactory(converterFactory)
        .build()
        .create(serviceClass)
}

/**
 * Setups the [MockWebServer] from incoming mock response via a file in resources folder.
 *
 * @param absoluteFilePath the response file path in resources folder
 * @param code actual http response code
 */
fun MockWebServer.enqueueResponseFromResourceFile(
    absoluteFilePath: String,
    code: Int
) {
    absoluteFilePath
        .readFileFromResourceFolder()
        .also { body ->
            enqueue(
                response = MockResponse()
                    .setBody(body)
                    .setResponseCode(code)
            )
        }
}