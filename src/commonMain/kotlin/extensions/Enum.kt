package extensions

interface EnumExtension<E : Enum<E>> {
    companion object
}

inline fun <reified E : Enum<E>> EnumExtension<E>.valueOf(name: String, default: E) =
    enumValues<E>().firstOrNull { it.name == name } ?: default

inline fun <reified E : Enum<E>> EnumExtension<E>.valueOf(ordinal: Int, default: E) =
    enumValues<E>().firstOrNull { it.ordinal == ordinal } ?: default

inline fun <reified E : Enum<E>> EnumExtension<E>.valueOfWithCondition(condition: (E) -> Boolean) =
    enumValues<E>().firstOrNull(condition)

inline fun <reified E : Enum<E>> EnumExtension<E>.valueOfWithCondition(condition: (E) -> Boolean, default: E) =
    enumValues<E>().firstOrNull(condition) ?: default

inline fun <reified E : Enum<E>> EnumExtension<E>.valueOfIgnoreCase(name: String) =
    enumValues<E>().firstOrNull { it.name.toLowerCase() == name.toLowerCase() }

inline fun <reified E: Enum<E>> Enum.Companion.a() {
    enumValues<E>()[1]
}
