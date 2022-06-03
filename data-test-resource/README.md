# Backpack
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

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

