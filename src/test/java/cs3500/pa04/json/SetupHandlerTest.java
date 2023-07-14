package cs3500.pa04.json;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.fasterxml.jackson.databind.JsonNode;
import cs3500.pa04.model.AbstractPlayer;
import cs3500.pa04.model.AiPlayer;
import cs3500.pa04.model.Coord;
import cs3500.pa04.model.Direction;
import cs3500.pa04.model.GameType;
import cs3500.pa04.model.Ship;
import cs3500.pa04.model.ShipAdapter;
import cs3500.pa04.model.ShipType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Random;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for the SetupHandler class
 */
public class SetupHandlerTest {

  /**
   * Tests the method getMessageJson
   */
  @Test
  public void testGetMessageJson() {
    Ship ship1 = new ShipAdapter(new Coord(0, 3), 6, Direction.HORIZONTAL);
    Ship ship2 = new ShipAdapter(new Coord(0,  5), 5, Direction.HORIZONTAL);
    Ship ship3 = new ShipAdapter(new Coord(1,  2), 4, Direction.HORIZONTAL);
    Ship ship4 = new ShipAdapter(new Coord(1,  1), 3, Direction.HORIZONTAL);
    FleetJson fleet = new FleetJson(new ArrayList<>(Arrays.asList(ship1, ship2, ship3, ship4)));
    LinkedHashMap<ShipType, Integer> specs = new LinkedHashMap<>();
    specs.put(ShipType.CARRIER, 1);
    specs.put(ShipType.BATTLESHIP, 1);
    specs.put(ShipType.DESTROYER, 1);
    specs.put(ShipType.SUBMARINE, 1);
    SetupJson setupJson = new SetupJson(6, 6, specs);
    JsonNode setupNode = JsonUtils.serializeRecord(setupJson);
    SetupHandler setupHandler = new SetupHandler();
    AbstractPlayer ai = new AiPlayer(new Random(1));
    JsonNode fleetNode = JsonUtils.serializeRecord(fleet);
    assertEquals(new MessageJson("setup", fleetNode),
        setupHandler.getMessageJson(setupNode, ai));
    JoinJson joinJson = new JoinJson("name", GameType.MULTI);
    JsonNode invalid = JsonUtils.serializeRecord(joinJson);
    IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
        () -> setupHandler.getMessageJson(invalid, ai));
    assertEquals("Mapper could not convert value", e.getMessage());
  }
}