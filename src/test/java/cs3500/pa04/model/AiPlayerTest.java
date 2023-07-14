package cs3500.pa04.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Random;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for the AI class
 */
public class AiPlayerTest {

  /**
   * Tests the method takeShots
   */
  @Test
  public void testTakeShots() {
    LinkedHashMap<ShipType, Integer> specs = new LinkedHashMap<>();
    specs.put(ShipType.CARRIER, 1);
    specs.put(ShipType.BATTLESHIP, 1);
    specs.put(ShipType.DESTROYER, 1);
    specs.put(ShipType.SUBMARINE, 1);
    AiPlayer ai = new AiPlayer(new Random(1));
    ai.setup(6, 6, specs);
    ai.previousShots = new ArrayList<>(Arrays.asList(new Coord(2, 0),
        new Coord(2, 1)));
    ai.successfulHits(ai.previousShots);
    assertEquals(new ArrayList<>(Arrays.asList(new Coord(3, 0), new Coord(4, 0),
        new Coord(5, 0), new Coord(3, 1))), ai.takeShots());
  }
}