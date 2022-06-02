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
class MockWebServerExtKtTest {

    companion object {
        const val SAMPLE_FILE_PATH = "api-response/sample-200-api-response.json"
        const val SAMPLE_EXPECTED_TITLE = "This is a sample"
        const val SAMPLE_EXPECTED_DESCRIPTION = "Only for test"
    }

    @Rule
    @JvmField
    val mockWebServerRule = MockWebServer()

    private lateinit var restService: SampleRestService

    @Before
    fun setUp() {
        restService = mockWebServerRule.createRestService(
            converterFactory = GsonConverterFactory.create(),
            serviceClass = SampleRestService::class.java
        )
    }

    @Test
    fun `when api executed, the response must match with the given sample file via enqueueResponseFromResourceFile`() =
        runTest {
            val expectedSampleApiResponse = SampleApiResponse(
                title = SAMPLE_EXPECTED_TITLE,
                description = SAMPLE_EXPECTED_DESCRIPTION
            )
            // given
            mockWebServerRule.enqueueResponseFromResourceFile(
                absoluteFilePath = SAMPLE_FILE_PATH,
                code = 200
            )
            // when
            val actualSampleApiResponse = restService.getSample().body()!!
            // then
            MatcherAssert.assertThat(
                actualSampleApiResponse,
                Matchers.`is`(expectedSampleApiResponse)
            )
        }

}