package cs3500.pa04.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.JsonNode;
import cs3500.pa04.model.AiPlayer;
import cs3500.pa04.model.Coord;
import cs3500.pa04.model.ShipType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for the TakeShotsHandler
 */
public class TakeShotsHandlerTest {

  /**
   * Tests the method getMessageJson
   */
  @Test
  public void testGetMessageJson() {
    LinkedHashMap<ShipType, Integer> specs = new LinkedHashMap<>();
    specs.put(ShipType.CARRIER, 1);
    specs.put(ShipType.BATTLESHIP, 1);
    specs.put(ShipType.DESTROYER, 1);
    specs.put(ShipType.SUBMARINE, 1);
    AiPlayer ai = new AiPlayer(new Random(1));
    ai.setup(6, 6, specs);
    VolleyJson volleyJson = new VolleyJson(new ArrayList<>());
    JsonNode volleyNode = JsonUtils.serializeRecord(volleyJson);
    TakeShotsHandler takeShotsHandler = new TakeShotsHandler();
    List<Coord> shots = new ArrayList<>(Arrays.asList(new Coord(2, 4), new Coord(5, 5),
        new Coord(4, 1), new Coord(3, 4)));
    VolleyJson shotsJson = new VolleyJson(shots);
    JsonNode shotsNode = JsonUtils.serializeRecord(shotsJson);
    assertEquals(new MessageJson("take-shots", shotsNode),
        takeShotsHandler.getMessageJson(volleyNode, ai));
    FleetJson fleetJson = new FleetJson(new ArrayList<>());
    JsonNode fleetNode = JsonUtils.serializeRecord(fleetJson);
    List<Coord> shots1 = new ArrayList<>(Arrays.asList(new Coord(4, 1), new Coord(2, 2),
        new Coord(1, 4), new Coord(0, 4)));
    VolleyJson shots1Json = new VolleyJson(shots1);
    JsonNode shots1Node = JsonUtils.serializeRecord(shots1Json);
    assertEquals(new MessageJson("take-shots", shots1Node),
        takeShotsHandler.getMessageJson(fleetNode, ai));
  }
}