package com.github.chantsune.kotlin.enumext

/**
 * Returns an enum entry matching the given [predicate], or `null` if entry was not found.
 */
public inline fun <reified E : Enum<E>> enumValueOfOrNull(predicate: (E) -> Boolean): E? {
    return enumValues<E>().firstOrNull(predicate)
}

/**
 * Returns an enum entry with specified [name], or `null` if entry was not found.
 */
public inline fun <reified E : Enum<E>> enumValueOfOrNull(name: String, ignoreCase: Boolean = false): E? {
    return if (ignoreCase) enumValueOfOrNull<E> { it.name.toLowerCase() == name.toLowerCase() }
    else enumValueOfOrNull<E> { it.name == name }
}

/**
 * Returns an enum entry with specified [ordinal], or `null` if entry was not found.
 */
public inline fun <reified E : Enum<E>> enumValueOfOrNull(ordinal: Int): E? {
    return enumValueOfOrNull<E> { it.ordinal == ordinal }
}

/**
 * Returns an enum entry with specified [name], or [default] if entry was not found.
 */
public inline fun <reified E : Enum<E>> enumValueOf(name: String, default: E, ignoreCase: Boolean = false): E {
    return enumValueOfOrNull<E>(name, ignoreCase) ?: default
}

/**
 * Returns an enum entry with specified [ordinal], or [default] if entry was not found.
 */
public inline fun <reified E : Enum<E>> enumValueOf(ordinal: Int, default: E): E {
    return enumValueOfOrNull<E>(ordinal) ?: default
}

/**
 * Returns an enum entry matching the given [predicate], or [default] if entry was not found.
 */
public inline fun <reified E : Enum<E>> enumValueOf(default: E, predicate: (E) -> Boolean): E {
    return enumValueOfOrNull(predicate) ?: default
}

/**
 * Return an enum entry matching the given [predicate], or throw `Exception` if entry was not found.
 */
public inline fun <reified E : Enum<E>> enumValueOf(predicate: (E) -> Boolean): E {
    return enumValueOfOrNull(predicate) ?: throw Exception("No enum constant matching given predicate")
}

/**
 * Returns an enum entry with specified [name], or throw `Exception` if entry was not found.
 */
public inline fun <reified E : Enum<E>> enumValueOf(name: String, ignoreCase: Boolean = false): E {
    return enumValueOfOrNull<E>(name, ignoreCase) ?: throw  Exception("No enum constant name is $name")
}

/**
 * Returns an enum entry with specified [ordinal], or throw `Exception` if entry was not found.
 */
public inline fun <reified E : Enum<E>> enumValueOf(ordinal: Int): E {
    return enumValueOfOrNull<E>(ordinal) ?: throw Exception("No enum constant ordinal is $ordinal")
}
