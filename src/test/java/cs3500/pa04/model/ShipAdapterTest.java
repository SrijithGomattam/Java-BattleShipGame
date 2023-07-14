package cs3500.pa04.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents tests and examples for the ShipAdapter
 */
public class ShipAdapterTest {

  private ShipAdapter shipAdapter1;
  private ShipAdapter shipAdapter2;

  /**
   * Sets up the examples for ShipAdapter tests
   */
  @BeforeEach
  public void setup() {
    shipAdapter1 = new ShipAdapter(new Coord(0, 0), 3, Direction.VERTICAL);
    shipAdapter2 = new ShipAdapter(new Coord(1, 1), 3, Direction.HORIZONTAL);
  }

  /**
   * Tests the method locationToShipAdapter
   */
  @Test
  public void testLocationToShipAdapter() {
    List<Coord> location1 = new ArrayList<>(Arrays.asList(new Coord(0, 0),
        new Coord(0, 1), new Coord(0, 2)));
    assertEquals(shipAdapter1, ShipAdapter.locationToShipAdapter(location1));
    List<Coord> location2 = new ArrayList<>(Arrays.asList(new Coord(1, 1),
        new Coord(2, 1), new Coord(3, 1)));
    assertEquals(shipAdapter2, ShipAdapter.locationToShipAdapter(location2));
  }
}