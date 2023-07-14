package cs3500.pa04.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Represents tests for the Peg class
 */
public class PegTest {

  /**
   * Tests the method toString
   */
  @Test
  public void testToString() {
    assertEquals("0", Peg.EMPTY.toString());
    assertEquals("H", Peg.HIT.toString());
    assertEquals("M", Peg.MISS.toString());
    assertEquals("S", Peg.SHIP.toString());
  }
}