package cs3500.pa04.json;

import com.fasterxml.jackson.databind.JsonNode;
import cs3500.pa04.model.Player;

/**
 * Represents an enumeration for the different server methods. This enumeration aids in abstraction
 * and each enum has a handler class which are subclasses of the BattleHandler abstract class
 */
public enum ServerMethods {
  JOIN(new JoinHandler()), SETUP(new SetupHandler()), TAKE_SHOTS(new TakeShotsHandler()),
  REPORT_DAMAGE(new ReportDamageHandler()), SUCCESSFUL_HITS(new SuccessfulHitsHandler()),
  END_GAME(new EndGameHandler());

  private final BattleHandler battleHandler;

  /**
   * Constructor initializes the battleHandler for each enum
   *
   * @param battleJsons : Given BattleHandler
   */
  private ServerMethods(BattleHandler battleJsons) {
    this.battleHandler =  battleJsons;
  }

  /**
   * Gets the corresponding battle message in the form of a MessageJson having passed a JsonNode
   * and Player in as arguments
   *
   * @param arguments : the JsonNode that is processed to a MessageJson
   * @param player : the player that is playing the game
   * @return the output that is a MessageJson
   */
  public MessageJson getBattleMessage(JsonNode arguments, Player player) {
    return battleHandler.getMessageJson(arguments, player);
  }
}
