package cs3500.pa04.json;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.fasterxml.jackson.databind.JsonNode;
import cs3500.pa04.model.AiPlayer;
import cs3500.pa04.model.Coord;
import cs3500.pa04.model.GameResult;
import cs3500.pa04.model.ShipType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Random;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for the SuccessfulHitsHandler class
 */
public class SuccessfulHitsHandlerTest {

  /**
   * Tests the method getMessageJson
   */
  @Test
  public void testSuccessHitsHandlerTest() {
    LinkedHashMap<ShipType, Integer> specs = new LinkedHashMap<>();
    specs.put(ShipType.CARRIER, 1);
    specs.put(ShipType.BATTLESHIP, 1);
    specs.put(ShipType.DESTROYER, 1);
    specs.put(ShipType.SUBMARINE, 1);
    AiPlayer ap = new AiPlayer(new Random(1));
    ap.setup(6, 6, specs);
    VolleyJson serverVolley = new VolleyJson(new ArrayList<>(Arrays.asList(new Coord(0, 3),
        new Coord(1, 2), new Coord(0, 0))));
    JsonNode serverNode = JsonUtils.serializeRecord(serverVolley);
    SuccessfulHitsHandler handler = new SuccessfulHitsHandler();
    assertEquals(new MessageJson("successful-hits", BattleHandler.VOID_RESPONSE),
        handler.getMessageJson(serverNode, ap));
    EndJson endJson = new EndJson(GameResult.DRAW, "Game has drawn");
    JsonNode invalid = JsonUtils.serializeRecord(endJson);
    IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
        () -> handler.getMessageJson(invalid, ap));
    assertEquals("Mapper could not convert value", e.getMessage());
  }
}