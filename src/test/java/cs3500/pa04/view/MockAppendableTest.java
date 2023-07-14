package cs3500.pa04.view;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for the MockAppendable class
 */
public class MockAppendableTest {

  /**
   * Tests the append method
   */
  @Test
  public void testAppend() {
    MockAppendable ma = new MockAppendable();
    IOException e = assertThrows(IOException.class, () -> ma.append('a'));
    assertEquals("Mock throwing an error", e.getMessage());
    assertThrows(IOException.class, () -> ma.append("hello"));
    assertThrows(IOException.class, () -> ma.append("hello", 3, 4));
  }
}
