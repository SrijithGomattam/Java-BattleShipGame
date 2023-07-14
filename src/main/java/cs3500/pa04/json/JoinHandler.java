package cs3500.pa04.json;

import com.fasterxml.jackson.databind.JsonNode;
import cs3500.pa04.model.GameType;
import cs3500.pa04.model.Player;


/**
 * Represents a class that handles when a player joins the queue in the server
 */
public class JoinHandler extends BattleHandler {

  /**
   * Constructor that passes join as the name parameter
   */
  public JoinHandler() {
    super("join");
  }

  /**
   * Method that handles arguments when a player joins the game
   *
   * @param arguments : The arguments passed that to the method
   * @param player : The player in the BattleSalvo game
   * @return the method name and a jsonNode that has the output of JoinJson as a MessageJson
   */
  @Override
  public MessageJson getMessageJson(JsonNode arguments, Player player) {
    JoinJson joinJson = new JoinJson(player.name(), GameType.SINGLE);
    JsonNode jsonNode = JsonUtils.serializeRecord(joinJson);
    return new MessageJson(name, jsonNode);
  }
}
