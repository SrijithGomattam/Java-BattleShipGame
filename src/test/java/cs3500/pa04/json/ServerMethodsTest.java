package cs3500.pa04.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.JsonNode;
import cs3500.pa04.model.AiPlayer;
import cs3500.pa04.model.Coord;
import cs3500.pa04.model.Direction;
import cs3500.pa04.model.GameResult;
import cs3500.pa04.model.GameType;
import cs3500.pa04.model.Player;
import cs3500.pa04.model.Ship;
import cs3500.pa04.model.ShipAdapter;
import cs3500.pa04.model.ShipType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for the ServerMethods enum
 */
public class ServerMethodsTest {

  /**
   * Tests the method getBattleMessage
   */
  @Test
  public void testGetBattleMessage() {
    //JOIN
    Player player = new AiPlayer(new Random(1));
    JoinJson joinJson = new JoinJson("brendanferguson4", GameType.SINGLE);
    JsonNode joinNode = JsonUtils.serializeRecord(joinJson);
    assertEquals(new MessageJson("join", joinNode),
        ServerMethods.JOIN.getBattleMessage(joinNode, player));
    //END_GAME
    EndJson endJson = new EndJson(GameResult.LOSE, "You lost");
    JsonNode endNode = JsonUtils.serializeRecord(endJson);
    assertEquals(new MessageJson("end-game", BattleHandler.VOID_RESPONSE),
        ServerMethods.END_GAME.getBattleMessage(endNode, player));
    //SETUP
    Ship ship1 = new ShipAdapter(new Coord(0, 3), 6, Direction.HORIZONTAL);
    Ship ship2 = new ShipAdapter(new Coord(0, 5), 5, Direction.HORIZONTAL);
    Ship ship3 = new ShipAdapter(new Coord(1, 2), 4, Direction.HORIZONTAL);
    Ship ship4 = new ShipAdapter(new Coord(1, 1), 3, Direction.HORIZONTAL);
    FleetJson fleet = new FleetJson(new ArrayList<>(Arrays.asList(ship1, ship2, ship3, ship4)));
    LinkedHashMap<ShipType, Integer> specs = new LinkedHashMap<>();
    specs.put(ShipType.CARRIER, 1);
    specs.put(ShipType.BATTLESHIP, 1);
    specs.put(ShipType.DESTROYER, 1);
    specs.put(ShipType.SUBMARINE, 1);
    SetupJson setupJson = new SetupJson(6, 6, specs);
    JsonNode setupNode = JsonUtils.serializeRecord(setupJson);
    JsonNode fleetNode = JsonUtils.serializeRecord(fleet);
    assertEquals(new MessageJson("setup", fleetNode),
        ServerMethods.SETUP.getBattleMessage(setupNode, player));
    //TAKE_SHOTS
    VolleyJson volleyJson = new VolleyJson(new ArrayList<>());
    JsonNode volleyNode = JsonUtils.serializeRecord(volleyJson);
    TakeShotsHandler takeShotsHandler = new TakeShotsHandler();
    List<Coord> shots = new ArrayList<>(Arrays.asList(new Coord(2, 4), new Coord(5, 5),
        new Coord(4, 1), new Coord(3, 4)));
    VolleyJson shotsJson = new VolleyJson(shots);
    JsonNode shotsNode = JsonUtils.serializeRecord(shotsJson);
    assertEquals(new MessageJson("take-shots", shotsNode),
        takeShotsHandler.getMessageJson(volleyNode, player));
    //REPORT_DAMAGE
    VolleyJson serverVolley1 = new VolleyJson(new ArrayList<>(Arrays.asList(new Coord(0, 3),
        new Coord(1, 2), new Coord(0, 0))));
    JsonNode serverNode1 = JsonUtils.serializeRecord(serverVolley1);
    VolleyJson resultVolley = new VolleyJson(new ArrayList<>(Arrays.asList(new Coord(0, 3),
        new Coord(1, 2))));
    JsonNode resultNode = JsonUtils.serializeRecord(resultVolley);
    ReportDamageHandler reportDamageHandler = new ReportDamageHandler();
    assertEquals(new MessageJson("report-damage", resultNode),
        reportDamageHandler.getMessageJson(serverNode1, player));
    //SUCCESSFUL_HITS
    VolleyJson serverVolley2 = new VolleyJson(new ArrayList<>(Arrays.asList(new Coord(0, 3),
        new Coord(1, 2), new Coord(0, 0))));
    JsonNode serverNode2 = JsonUtils.serializeRecord(serverVolley2);
    SuccessfulHitsHandler handler = new SuccessfulHitsHandler();
    assertEquals(new MessageJson("successful-hits", BattleHandler.VOID_RESPONSE),
        handler.getMessageJson(serverNode2, player));
  }
}