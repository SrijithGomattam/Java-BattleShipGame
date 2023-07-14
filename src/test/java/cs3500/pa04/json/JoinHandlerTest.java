package cs3500.pa04.json;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.fasterxml.jackson.databind.JsonNode;
import cs3500.pa04.model.AiPlayer;
import cs3500.pa04.model.GameResult;
import cs3500.pa04.model.GameType;
import cs3500.pa04.model.ManualPlayer;
import cs3500.pa04.model.Player;
import java.util.ArrayList;
import java.util.Random;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for the JoinHandler class
 */
public class JoinHandlerTest {

  /**
   * Tests the method getMessageJson
   */
  @Test
  public void testGetMessageJson() {
    JoinHandler joinHandler = new JoinHandler();
    JoinJson joinJson = new JoinJson("brendanferguson4", GameType.SINGLE);
    JsonNode joinNode = JsonUtils.serializeRecord(joinJson);
    VolleyJson volley = new VolleyJson(new ArrayList<>());
    JsonNode volleyNode = JsonUtils.serializeRecord(volley);
    Player player = new ManualPlayer(new Random());
    assertEquals(new MessageJson("join", joinNode),
        joinHandler.getMessageJson(joinNode, player));
    assertEquals(new MessageJson("join", joinNode),
        joinHandler.getMessageJson(volleyNode, player));
  }
}