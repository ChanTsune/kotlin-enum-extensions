package com.github.chantsune.kotlin.enumext

/**
 * Inherited by the companion object of Enum class to provide an extended function.
 */
public interface EnumExtension<E : Enum<E>>

/**
 * Returns an enum entry with specified [name], or `null` if entry was not found.
 */
public inline fun <reified E : Enum<E>> EnumExtension<E>.valueOfOrNull(name: String, ignoreCase: Boolean = false): E? {
    return enumValueOfOrNull<E>(name, ignoreCase)
}

/**
 * Returns an enum entry with specified [ordinal], or `null` if entry was not found.
 */
public inline fun <reified E : Enum<E>> EnumExtension<E>.valueOfOrNull(ordinal: Int): E? {
    return enumValueOfOrNull<E>(ordinal)
}

/**
 * Returns an enum entry matching the given [predicate], or `null` if entry was not found.
 */
public inline fun <reified E : Enum<E>> EnumExtension<E>.valueOfOrNull(predicate: (E) -> Boolean): E? {
    return enumValueOfOrNull<E>(predicate)
}

/**
 * Returns an enum entry with specified [name], or [default] if entry was not found.
 */
public inline fun <reified E : Enum<E>> EnumExtension<E>.valueOf(
    name: String,
    default: E,
    ignoreCase: Boolean = false
): E {
    return enumValueOf(name, default, ignoreCase)
}

/**
 * Returns an enum entry with specified [ordinal], or [default] if entry was not found.
 */
public inline fun <reified E : Enum<E>> EnumExtension<E>.valueOf(ordinal: Int, default: E): E {
    return enumValueOf(ordinal, default)
}

/**
 * Returns an enum entry matching the given [predicate], or [default] if entry was not found.
 */
public inline fun <reified E : Enum<E>> EnumExtension<E>.valueOf(default: E, predicate: (E) -> Boolean): E {
    return enumValueOf(default, predicate)
}
