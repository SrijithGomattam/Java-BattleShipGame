package cs3500.pa04.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Represents tests for the Coord class
 */
public class CoordTest {

  /**
   * Tests the methods getX and getY
   */
  @Test
  public void testGet() {
    Coord coord1 = new Coord(0, 2);
    Coord coord2 = new Coord(1, 3);
    assertEquals(0, coord1.getX());
    assertEquals(1, coord2.getX());
    assertEquals(2, coord1.getY());
    assertEquals(3, coord2.getY());
  }
}