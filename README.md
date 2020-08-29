# kotlin-enum-extensions
![CI](https://github.com/ChanTsune/kotlin-enum-extensions/workflows/CI/badge.svg)

Provide extension functions for kotlin enum class.

## Setup

##### Provide the gradle dependency

###### Multi Platform

```kotlin
implementation("com.github.chantsune:kotlin-enum-extensions:${versions}")
```

###### JVM

```kotlin
implementation("com.github.chantsune:kotlin-enum-extensions-jvm:${versions}")
```

###### JS

```kotlin
implementation("com.github.chantsune:kotlin-enum-extensions-js:${versions}")
```

###### Native

```kotlin
implementation("com.github.chantsune:kotlin-enum-extensions-native:${versions}")
```

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
