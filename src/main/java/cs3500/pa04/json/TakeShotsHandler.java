package cs3500.pa04.json;

import com.fasterxml.jackson.databind.JsonNode;
import cs3500.pa04.model.Coord;
import cs3500.pa04.model.Player;
import java.util.List;

/**
 * Represents a class that handles the creation of a volley of shots that is to be fired
 */
public class TakeShotsHandler extends BattleHandler {

  /**
   * Constructor initializes the name parameter to take-shots
   */
  public TakeShotsHandler() {
    super("take-shots");
  }

  /**
   * Method that creates a new volley of shots and returns this volley in the proper Json format
   *
   * @param arguments : The arguments passed that to the method
   * @param player : The player in the BattleSalvo game
   * @return the player name and the volley of created shots as a MessageJson
   */
  @Override
  public MessageJson getMessageJson(JsonNode arguments, Player player) {
    List<Coord> playerShots = player.takeShots();
    VolleyJson volleyJson = new VolleyJson(playerShots);
    return new MessageJson(name, JsonUtils.serializeRecord(volleyJson));
  }
}
