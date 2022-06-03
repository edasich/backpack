# Backpack
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

### For Data Layer
- Mock Web Server Extension
- Resource

### Mock Web Server Extension Library
Set of extension functions for testing rest service api interfaces.
Having mocked responses into resources folder and reading and passing them into Mock Web Server is hard and lots of boiler plate code must be written to setting it up. These extensions come to help.

### Dependency
```gradle
testImplementation 'io.github.edasich.backpack:data-test-mockwebserver-ext:LATEST_VERSION'
```

### How to use it ?
For instance we have a response file with json format in resources folder,
inside of `api-response` subfolder named `sample-200-api-response.json`.

Note : Having a subfolder is optional

```json
{
  "title": "This is a sample",
  "description": "Only for test"
}
```

Then in test method, we can pass the mocked json as file's path into Mock Web Server along with whatever http response code you choose by calling this extension function `enqueueResponseFromResourceFile`.

```kotlin
    @Test
    fun `test rest service`() =
        runTest {
            val expectedSampleApiResponse = SampleApiResponse(
                title = "This is a sample",
                description = "Only for test"
            )
            // given
            mockWebServerRule.enqueueResponseFromResourceFile(
                absoluteFilePath = "api-response/sample-200-api-response.json",
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
```

Also you can create your rest service interface by calling this extension too `createRestService`.

```kotlin
    @Before
    fun setUp() {
        restService = mockWebServerRule.createRestService(
            converterFactory = GsonConverterFactory.create(),
            serviceClass = SampleRestService::class.java
        )
    }
```

### Resource Library
Reading files from resources folder during test.

### Dependency
```gradle
testImplementation 'io.github.edasich.backpack:data-test-resource:LATEST_VERSION'
```

### How to use it ?

```kotlin
val fileContent: String = "folder/im-the-file-path.json".readFileFromResourceFolder()
```

