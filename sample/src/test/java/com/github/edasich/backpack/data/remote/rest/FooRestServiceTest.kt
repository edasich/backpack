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

package com.github.edasich.backpack.data.remote.rest

import com.github.edasich.backpack.data.remote.rest.model.FooApiResponse
import com.github.edasich.backpack.data.test.mockwebserver.ext.createRestService
import com.github.edasich.backpack.data.test.mockwebserver.ext.enqueueResponseFromResourceFile
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runners.MethodSorters
import retrofit2.converter.gson.GsonConverterFactory

@ExperimentalCoroutinesApi
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
class FooRestServiceTest {

    companion object {
        const val FOO_RESPONSE_FILE_PATH = "api-response/foo-200-api-response.json"
        const val FOO_EXPECTED = "This is just foo :)"
    }

    @Rule
    @JvmField
    val mockWebServerRule = MockWebServer()

    private lateinit var restService: FooRestService

    @Before
    fun setUp() {
        restService = mockWebServerRule.createRestService(
            converterFactory = GsonConverterFactory.create(),
            serviceClass = FooRestService::class.java
        )
    }

    @Test
    fun `the response of getFoo must match with the excepted`() =
        runTest {
            val expectedFooApiResponse = FooApiResponse(
                foo = FOO_EXPECTED
            )
            // given
            mockWebServerRule.enqueueResponseFromResourceFile(
                absoluteFilePath = FOO_RESPONSE_FILE_PATH,
                code = 200
            )
            // when
            val actualFooApiResponse = restService.getFoo().body()!!
            // then
            MatcherAssert.assertThat(
                actualFooApiResponse,
                Matchers.`is`(expectedFooApiResponse)
            )
        }

}