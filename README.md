# kotlin-enum-extensions
![CI](https://github.com/ChanTsune/kotlin-enum-extensions/workflows/CI/badge.svg)

Multiplatform kotlin library that extension functions for enum class.

![badge][badge-android]
![badge][badge-ios]
![badge][badge-js]
![badge][badge-jvm]
![badge][badge-linux]
![badge][badge-mac]
![badge][badge-tvos]
![badge][badge-wasm]
![badge][badge-watchos]
![badge][badge-windows]

## Setup
##### Add repository

```
repositories {
    jcenter() // Add repository
}

```

##### Provide the gradle dependency

###### Multi Platform Project

```kotlin
implementation("com.github.chantsune:kotlin-enum-extensions:${versions}")
```

###### JVM Project

```kotlin
implementation("com.github.chantsune:kotlin-enum-extensions-jvm:${versions}")
```

###### JS Project

```kotlin
implementation("com.github.chantsune:kotlin-enum-extensions-js:${versions}")
```

###### Native Project

```kotlin
implementation("com.github.chantsune:kotlin-enum-extensions-${your_platform}:${versions}")
```

`your_platform` like `iosarm64`,`iosx64`,`linuxarm64`,`linuxx64`,`macosx64`,`mingwx64`,`tvosarm64`,`tvosx64`,`wasm32` etc...

## Example

```kotlin
enum class MyEnum {
    ONE,
    TWO,
    OTHER
}

val myEnum = enumValueOf<MyEnum>("TWO", default = MyEnum.ONE) // will return MyEnum.TWO

val myEnumOrNull = enumValueOfOrNull<MyEnum>("not exist") // will return null
```

Can use it like a class method by inheriting the `EnumExtension` interface.

```kotlin

enum class MyEnum { 
    ONE,
    TWO,
    OTHER;

    companion object : EnumExtension<MyEnum>
}

MyEnum.valueOfOrNull("one", ignoreCase = true) // will return MyEnum.ONE
```

## Documentation

For more detail, see [Documentation](https://chantsune.github.io/kotlin-enum-extensions/).

## Licence

This project is licensed under the terms of the [MIT license](./LICENSE).


[badge-android]: http://img.shields.io/badge/platform-android-6EDB8D.svg?style=flat
[badge-ios]: http://img.shields.io/badge/platform-ios-CDCDCD.svg?style=flat
[badge-js]: http://img.shields.io/badge/platform-js-F8DB5D.svg?style=flat
[badge-jvm]: http://img.shields.io/badge/platform-jvm-DB413D.svg?style=flat
[badge-linux]: http://img.shields.io/badge/platform-linux-2D3F6C.svg?style=flat 
[badge-windows]: http://img.shields.io/badge/platform-windows-4D76CD.svg?style=flat
[badge-mac]: http://img.shields.io/badge/platform-macos-111111.svg?style=flat
[badge-watchos]: http://img.shields.io/badge/platform-watchos-C0C0C0.svg?style=flat
[badge-tvos]: http://img.shields.io/badge/platform-tvos-808080.svg?style=flat
[badge-wasm]: https://img.shields.io/badge/platform-wasm-624FE8.svg?style=flat
