package cs3500.pa04.json;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.fasterxml.jackson.databind.JsonNode;
import cs3500.pa04.model.AiPlayer;
import cs3500.pa04.model.GameResult;
import cs3500.pa04.model.Player;
import java.util.ArrayList;
import java.util.Random;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for the EndGameHandler class
 */
public class EndGameHandlerTest {

  /**
   * Tests the method getMessageJson
   */
  @Test
  public void testGetMessageJson() {
    EndGameHandler endGameHandler = new EndGameHandler();
    EndJson endJson = new EndJson(GameResult.DRAW, "Game ended in a draw");
    JsonNode endNode = JsonUtils.serializeRecord(endJson);
    Player player = new AiPlayer(new Random());
    assertEquals(new MessageJson("end-game", BattleHandler.VOID_RESPONSE),
        endGameHandler.getMessageJson(endNode, player));
    VolleyJson volley = new VolleyJson(new ArrayList<>());
    JsonNode invalid  = JsonUtils.serializeRecord(volley);
    IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
        endGameHandler.getMessageJson(invalid, player));
    assertEquals("Mapper could not convert value", e.getMessage());
  }
}