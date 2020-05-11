package extensions

import kotlin.test.Test
import kotlin.test.assertEquals

class EnumTests {
    enum class E(val rawValue: Int) {
        FIRST(1),
        SECOND(2),
        LAST(-1);

        companion object : EnumExtension<E>
    }

    @Test
    fun testValueOfStringWithDefault() {
        assertEquals(
            E.SECOND,
            E.valueOf("SECOND", E.LAST)
        )
        assertEquals(
            E.FIRST,
            E.valueOf("f", E.FIRST)
        )
    }

    @Test
    fun testValueOfIntWithDefault() {
        assertEquals(
            E.FIRST,
            E.valueOf(0, E.LAST)
        )
        assertEquals(
            E.LAST,
            E.valueOf(100, E.LAST)
        )
    }

    @Test
    fun testValueOfWithCondition() {
        assertEquals(
            E.SECOND,
            E.valueOfWithCondition {
                it.rawValue == 2
            }
        )
        assertEquals(
            null,
            E.valueOfWithCondition {
                it.rawValue == 100
            }
        )
    }

    @Test
    fun testValueOfWithConditionWithDefault() {
        assertEquals(
            E.FIRST,
            E.valueOfWithCondition({
                it.rawValue == 1
            }, E.LAST)
        )
        assertEquals(
            E.FIRST,
            E.valueOfWithCondition({
                it.rawValue == 100
            }, E.FIRST)
        )
    }
}
