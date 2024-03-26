package edu.austral.dissis.chess.engine;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MessageProviderTest {

  // Dummy test because kover includes constructor in coverage
  @Test
  void testInstance() {
    MessageProvider messageProvider = new MessageProvider();
    assertEquals(messageProvider.getClass().getSimpleName(), "MessageProvider");
  }

  @Test
  void testGetMessage() {
    assertEquals("Hello      World!", MessageProvider.getMessage());
  }
}
