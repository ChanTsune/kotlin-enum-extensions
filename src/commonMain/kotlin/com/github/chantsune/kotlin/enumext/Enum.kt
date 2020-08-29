package com.github.chantsune.kotlin.enumext

public inline fun <reified E : Enum<E>> enumValueOfOrNull(predicate: (E) -> Boolean): E? {
    return enumValues<E>().firstOrNull(predicate)
}

public inline fun <reified E : Enum<E>> enumValueOfOrNull(name: String, ignoreCase: Boolean = false): E? {
    return if (ignoreCase) enumValueOfOrNull<E> { it.name.toLowerCase() == name.toLowerCase() }
    else enumValueOfOrNull<E> { it.name == name }
}

public inline fun <reified E : Enum<E>> enumValueOfOrNull(ordinal: Int): E? {
    return enumValueOfOrNull<E> { it.ordinal == ordinal }
}

public inline fun <reified E : Enum<E>> enumValueOf(name: String, default: E, ignoreCase: Boolean): E {
    return enumValueOfOrNull<E>(name, ignoreCase) ?: default
}

public inline fun <reified E : Enum<E>> enumValueOf(ordinal: Int, default: E): E {
    return enumValueOfOrNull<E>(ordinal) ?: default
}

public inline fun <reified E : Enum<E>> enumValueOf(default: E, predicate: (E) -> Boolean): E {
    return enumValueOfOrNull<E>(predicate) ?: default
}
