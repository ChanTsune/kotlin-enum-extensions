package com.github.chantsune.kotlin.enumext

public interface EnumExtension<E : Enum<E>>

public inline fun <reified E : Enum<E>> EnumExtension<E>.valueOfOrNull(name: String, ignoreCase: Boolean = false): E? {
    return enumValueOfOrNull<E>(name, ignoreCase)
}

public inline fun <reified E : Enum<E>> EnumExtension<E>.valueOfOrNull(ordinal: Int): E? {
    return enumValueOfOrNull<E>(ordinal)
}

public inline fun <reified E : Enum<E>> EnumExtension<E>.valueOfOrNull(predicate: (E) -> Boolean): E? {
    return enumValueOfOrNull<E>(predicate)
}

public inline fun <reified E : Enum<E>> EnumExtension<E>.valueOf(
    name: String,
    default: E,
    ignoreCase: Boolean = false
): E {
    return enumValueOf(name, default, ignoreCase)
}

public inline fun <reified E : Enum<E>> EnumExtension<E>.valueOf(ordinal: Int, default: E): E {
    return enumValueOf(ordinal, default)
}

public inline fun <reified E : Enum<E>> EnumExtension<E>.valueOf(default: E, predicate: (E) -> Boolean): E {
    return enumValueOf(default, predicate)
}
