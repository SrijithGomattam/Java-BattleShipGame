package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa04.model.GameResult;

/**
 * Represents a record that contains information regarding the result of an ended game and
 * the reason of why the game ended
 *
 * @param result : the result of the game
 * @param reason : reason of why the game ended
 */
public record EndJson(@JsonProperty("result")
    GameResult result, @JsonProperty("reason") String reason)  {

}
