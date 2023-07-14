package cs3500.pa04.json;

import com.fasterxml.jackson.databind.JsonNode;
import cs3500.pa04.model.Player;
import cs3500.pa04.model.Ship;
import java.util.List;

/**
 * Represents a class tha handles the setup of a BattleSalvo game
 */
public class SetupHandler extends BattleHandler {

  /**
   * Constructor that passes setup as the name parameter
   */
  public SetupHandler() {
    super("setup");
  }

  /**
   * Method that sets up a game of BattleSalvo and displays the output as a MessageJson
   *
   * @param arguments : The arguments passed that to the method
   * @param player : The player in the BattleSalvo game
   * @return the method name and a jsonNode includes a fleetJson of the initialized ships as a
   *        MessageJson
   */
  @Override
  public MessageJson getMessageJson(JsonNode arguments, Player player) {
    SetupJson setupJson;
    try {
      setupJson = mapper.convertValue(arguments, SetupJson.class);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Mapper could not convert value");
    }
    List<Ship> fleet = player.setup(setupJson.height(), setupJson.width(), setupJson.specs());
    FleetJson fleetJson = new FleetJson(fleet);
    return new MessageJson(this.name, JsonUtils.serializeRecord(fleetJson));
  }
}
