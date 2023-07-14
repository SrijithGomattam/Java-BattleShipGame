package cs3500.pa04.json;

import com.fasterxml.jackson.databind.JsonNode;
import cs3500.pa04.model.Coord;
import cs3500.pa04.model.Player;
import java.util.List;

/**
 * Represents a class that handles the reporting of damage after every volley
 */
public class ReportDamageHandler extends BattleHandler {

  /**
   * Constructor initializes name to be report-damage
   */
  public ReportDamageHandler() {
    super("report-damage");
  }

  /**
   * Method that reports the damage after a volley of shots was fired
   *
   * @param arguments : The arguments passed that to the method
   * @param player : The player in the BattleSalvo game
   * @return the method name and a jsonNode that has the output of VolleyJson on the shots that the
   *        server or player shot as a MessageJson
   */
  @Override
  public MessageJson getMessageJson(JsonNode arguments, Player player) {
    VolleyJson serverVolley;
    try {
      serverVolley = this.mapper.convertValue(arguments, VolleyJson.class);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Mapper could not convert value");
    }
    List<Coord> serverShots = serverVolley.coordinates();
    List<Coord> shotsThatHitsPlayer  = player.reportDamage(serverShots);
    VolleyJson volleyJson = new VolleyJson(shotsThatHitsPlayer);
    return new MessageJson(name, JsonUtils.serializeRecord(volleyJson));
  }
}
