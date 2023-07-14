package cs3500.pa04.json;

import com.fasterxml.jackson.databind.JsonNode;
import cs3500.pa04.model.Player;

/**
 * Represents a class that handles when the game ends
 */
public class EndGameHandler extends BattleHandler {

  /**
   * Constructor that passes end-game as the name parameter
   */
  public EndGameHandler() {
    super("end-game");
  }


  /**
   * Method that handles when the game ends.
   *
   * @param arguments : The arguments passed that to the method
   * @param player : The player in the BattleSalvo game
   * @return the method name and a void response, signifying that the game has ended, as a
   *        MessageJson
   */
  @Override
  public MessageJson getMessageJson(JsonNode arguments, Player player) {
    EndJson endJson;
    try {
      endJson = this.mapper.convertValue(arguments, EndJson.class);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Mapper could not convert value");
    }
    player.endGame(endJson.result(), endJson.reason());
    return new MessageJson(name, BattleHandler.VOID_RESPONSE);
  }
}
