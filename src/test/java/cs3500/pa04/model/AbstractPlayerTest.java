package cs3500.pa04.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents tests and examples for the AbstractPlayer class
 */
public class AbstractPlayerTest {

  private AbstractPlayer ap;

  /**
   * Sets up the AbstractPlayer for the AbstractPlayer tests;
   */
  @BeforeEach
  public void setup() {
    LinkedHashMap<ShipType, Integer> specs = new LinkedHashMap<>();
    specs.put(ShipType.CARRIER, 1);
    specs.put(ShipType.BATTLESHIP, 1);
    specs.put(ShipType.DESTROYER, 1);
    specs.put(ShipType.SUBMARINE, 1);
    ap = new AiPlayer(new Random(1));
    ap.setup(6, 6, specs);
  }

  /**
   * Tests the method setup
   */
  @Test
  public void testSetup() {
    Ship ship1 = new Ship(new ArrayList<>(Arrays.asList(new Coord(0, 3), new Coord(1, 3),
        new Coord(2, 3), new Coord(3, 3),
        new Coord(4, 3), new Coord(5, 3))));
    Ship ship2 = new Ship(new ArrayList<>(Arrays.asList(new Coord(0, 5),
        new Coord(1, 5), new Coord(2, 5),
        new Coord(3, 5), new Coord(4, 5))));
    Ship ship3 = new Ship(new ArrayList<>(Arrays.asList(new Coord(1, 2),
        new Coord(2, 2), new Coord(3, 2), new Coord(4, 2))));
    Ship ship4 = new Ship(new ArrayList<>(Arrays.asList(new Coord(1, 1),
        new Coord(2,  1), new Coord(3, 1))));
    assertEquals(new ArrayList<>(Arrays.asList(ship1, ship2, ship3, ship4)), ap.ships);
  }

  /**
   * Tests the method name
   */
  @Test
  public void testName() {
    assertEquals("brendanferguson4", new ManualPlayer(new Random()).name());
    assertEquals("brendanferguson4", ap.name());
  }

  /**
   * Tests the method reportDamage
   */
  @Test
  public void testReportDamage() {
    List<Coord> shots = new ArrayList<>(Arrays.asList(new Coord(1, 1),
        new Coord(1, 2), new Coord(1, 3), new Coord(0, 0)));
    assertEquals(new ArrayList<>(Arrays.asList(new Coord(1, 1),
        new Coord(1, 2), new Coord(1, 3))), ap.reportDamage(shots));
    assertEquals(Peg.HIT, ap.board.get(1).get(1));
    assertEquals(Peg.HIT, ap.board.get(2).get(1));
    assertEquals(Peg.HIT, ap.board.get(3).get(1));
    assertEquals(Peg.MISS, ap.board.get(0).get(0));
  }

  /**
   * Tests the method successfulHits
   */
  @Test
  public void testSuccessfulHits() {
    ap.previousShots = new ArrayList<>(Arrays.asList(new Coord(1, 1),
        new Coord(1, 2), new Coord(1, 3), new Coord(0, 0)));
    List<Coord> success = new ArrayList<>(Arrays.asList(new Coord(1, 1),
        new Coord(1, 2), new Coord(1, 3)));
    ap.successfulHits(success);
    assertEquals(Peg.HIT, ap.shootingBoard.get(1).get(1));
    assertEquals(Peg.HIT, ap.shootingBoard.get(2).get(1));
    assertEquals(Peg.HIT, ap.shootingBoard.get(3).get(1));
    assertEquals(Peg.MISS, ap.shootingBoard.get(0).get(0));
  }
}