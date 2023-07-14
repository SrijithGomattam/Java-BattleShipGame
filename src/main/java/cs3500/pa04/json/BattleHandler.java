package cs3500.pa04.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa04.model.Player;

/**
 * Represents an abstract class that is used by all the handler classes
 */
public abstract class BattleHandler  {

  protected String name;
  protected ObjectMapper mapper = new ObjectMapper();
  protected static final JsonNode VOID_RESPONSE =
      new ObjectMapper().getNodeFactory().textNode("void");

  /**
   * Constructor that initializes the name of the method
   *
   * @param name : the name of the method
   */
  public BattleHandler(String name) {
    this.name = name;
  }

  /**
   * Method that converts the name to a method and returns the corresponding values
   *
   * @param arguments : The arguments passed that to the method
   * @param player : The player in the BattleSalvo game
   * @return returns the output of the corresponding method in the BattleSalvo model as a
   *        MessageJson
   */
  public abstract MessageJson getMessageJson(JsonNode arguments, Player player);
}
