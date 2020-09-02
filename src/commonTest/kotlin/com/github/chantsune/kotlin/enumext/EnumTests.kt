package com.github.chantsune.kotlin.enumext

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNull

class EnumTests {
    enum class E(val rawValue: Int) {
        FIRST(1),
        SECOND(2),
        LAST(-1);

        companion object : EnumExtension<E>
    }

    @Test
    fun testValueOfNameWithDefault() {
        assertEquals(
            E.SECOND,
            E.valueOf("SECOND", default = E.LAST)
        )
        assertEquals(
            E.FIRST,
            E.valueOf("not exist", default = E.FIRST)
        )
    }

    @Test
    fun testValueOfNameWithIgnoreCase() {

        assertEquals(
            E.LAST,
            E.valueOf("last", default = E.FIRST, ignoreCase = true)
        )
    }

    @Test
    fun testValueOfOrdinalWithDefault() {
        assertEquals(
            E.FIRST,
            E.valueOf(0, default = E.LAST)
        )
        assertEquals(
            E.LAST,
            E.valueOf(100, default = E.LAST)
        )
    }

    @Test
    fun testValueOfPredicateWithDefault() {
        assertEquals(
            E.FIRST,
            E.valueOf(E.LAST) { it.rawValue == 1 }
        )
        assertEquals(
            E.FIRST,
            E.valueOf(E.FIRST) { it.rawValue == 100 }
        )
    }

    @Test
    fun testValueOfOrNullName() {
        assertNull(E.valueOfOrNull("not exist"))
    }

    @Test
    fun testValueOfOrNullOrdinal() {
        assertNull(E.valueOfOrNull(-100))
    }

    @Test
    fun testValueOfOrNullPredicate() {
        assertEquals(
            E.SECOND,
            E.valueOfOrNull { it.rawValue == 2 }
        )
        assertNull(E.valueOfOrNull { it.rawValue == 100 })
    }

    @Test
    fun testValueOfName() {
        assertFailsWith<Exception> {
            E.valueOf { it.name == "" }
        }
    }

    @Test
    fun testValueOfOrdinal() {
        assertFailsWith<Exception> {
            E.valueOf("a", ignoreCase = true)
        }
        assertFailsWith<Exception> {
            E.valueOf("a", ignoreCase = false)
        }
    }

    @Test
    fun testValueOfPredicate() {
        assertFailsWith<Exception> {
            E.valueOf(-100)
        }
    }
}
