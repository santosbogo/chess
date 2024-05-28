package edu.austral.dissis.engine

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MessageProviderKtTest {
    @Test
    fun `test getMessage`() {
        assertEquals("Hello      World!", MessageProviderKt.getMessage())
    }
}
