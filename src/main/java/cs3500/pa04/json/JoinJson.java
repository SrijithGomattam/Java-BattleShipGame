package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa04.model.GameType;

/**
 * Represents a record that contains JsonProperties of name and gametype
 *
 * @param name : a String name of the player
 * @param type : the GameType type of game which will be played
 */
public record JoinJson(@JsonProperty ("name") String name,
                       @JsonProperty("game-type") GameType type)  {

}
