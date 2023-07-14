package cs3500.pa04.json;

import com.fasterxml.jackson.databind.JsonNode;
import cs3500.pa04.model.Coord;
import cs3500.pa04.model.Player;
import java.util.List;

/**
 * Represents a class that handles the successful hits after a volley
 */
public class SuccessfulHitsHandler extends BattleHandler {

  /**
   * Constructor that initializes name parameter to successful-hits
   */
  public SuccessfulHitsHandler() {
    super("successful-hits");
  }

  /**
   * Method that handles and updates the successful hits after a volley of shots
   *
   * @param arguments : The arguments passed that to the method
   * @param player : The player in the BattleSalvo game
   * @return the method name and a jsonNode that updates the board for successful hits as a
   *        MessageJson
   */
  @Override
  public MessageJson getMessageJson(JsonNode arguments, Player player) {
    VolleyJson volleyThatHitServer;
    try {
      volleyThatHitServer = this.mapper.convertValue(arguments, VolleyJson.class);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Mapper could not convert value");
    }
    List<Coord> shotsThatHitServer = volleyThatHitServer.coordinates();
    player.successfulHits(shotsThatHitServer);
    return new MessageJson(name, BattleHandler.VOID_RESPONSE);
  }
}
