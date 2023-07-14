package cs3500.pa04.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents tests and examples for the Ship class
 */
public class ShipTest {

  private Ship ship1;
  private Ship ship2;
  private Ship ship3;

  /**
   * Sets up the examples for the Ship tests
   */
  @BeforeEach
  public void setup() {
    List<Coord> location1 = new ArrayList<>(Arrays.asList(new Coord(1, 2),
        new Coord(1, 3)));
    List<Coord> location2 = new ArrayList<>(List.of(new Coord(0, 0)));
    ship1 = new Ship(location1);
    ship3 = new Ship(location1);
    ship2 = new Ship(location2);
  }

  /**
   * Tests the method addShip
   */
  @Test
  public void testAddShip() {
    HashMap<ShipType, Integer> specs = new LinkedHashMap<>();
    specs.put(ShipType.CARRIER, 1);
    specs.put(ShipType.BATTLESHIP, 1);
    specs.put(ShipType.DESTROYER, 1);
    specs.put(ShipType.SUBMARINE, 1);
    AiPlayer ai = new AiPlayer(new Random());
    ai.setup(6, 6, specs);
    List<List<Peg>> board = ai.shootingBoard;
    ship1.addShip(board);
    assertEquals(Peg.SHIP, board.get(2).get(1));
    assertEquals(Peg.SHIP, board.get(2).get(1));
    ship2.addShip(board);
    assertEquals(Peg.SHIP, board.get(0).get(0));
  }

  /**
   * Tests the method isHit
   */
  @Test
  public void testIsHit() {
    assertTrue(ship1.isHit(new Coord(1, 2)));
    assertFalse(ship1.isHit(new Coord(1, 2)));
    assertTrue(ship1.isHit(new Coord(1, 3)));
    assertFalse(ship2.isHit(new Coord(1, 2)));
    assertTrue(ship2.isHit(new Coord(0, 0)));
    assertFalse(ship2.isHit(new Coord(0, 0)));
  }

  /**
   * Tests the method removeIfSunk
   */
  @Test
  public void testRemoveIfSunk() {
    List<Ship> ships = new ArrayList<>(Arrays.asList(ship1, ship2));
    Ship.removeIfSunk(ships);
    assertEquals(new ArrayList<>(Arrays.asList(ship1, ship2)), ships);
    ship2.isHit(new Coord(0, 0));
    Ship.removeIfSunk(ships);
    assertEquals(new ArrayList<>(Collections.singletonList(ship1)), ships);
    ship1.isHit(new Coord(1, 2));
    ship1.isHit(new Coord(1, 3));
    Ship.removeIfSunk(ships);
    assertEquals(new ArrayList<>(), ships);
  }

  /**
   * Tests the method equals
   */
  @Test
  public void testEquals() {
    assertEquals(ship1, ship3);
    assertNotEquals(ship1, ship2);
    ship1.isHit(new Coord(1, 2));
    ship1.isHit(new Coord(1, 3));
    assertNotEquals(ship1, ship3);
  }
}