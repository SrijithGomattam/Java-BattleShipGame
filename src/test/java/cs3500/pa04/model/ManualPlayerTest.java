package cs3500.pa04.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Random;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for the ManualPlayer class
 */
public class ManualPlayerTest {

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
    Readable reader = new StringReader("0 0 1 1 2 2 3 3");
    Appendable output = new StringBuilder();
    ManualPlayer mp = new ManualPlayer(new Random(1), reader, output);
    mp.setup(6, 6, specs);
    mp.previousShots = new ArrayList<>(Arrays.asList(new Coord(2, 0), new Coord(2, 1)));
    mp.successfulHits(mp.previousShots);
    assertEquals(new ArrayList<>(Arrays.asList(new Coord(0, 0), new Coord(1, 1),
        new Coord(2, 2), new Coord(3, 3))), mp.takeShots());
  }
}