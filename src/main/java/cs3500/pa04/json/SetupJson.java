package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa04.model.ShipType;
import java.util.Map;

/**
 * Represents a record that is used for setting up a BattleSalvo game
 *
 * @param width : the width of the board
 * @param height : the height of the board
 * @param specs : the fleet of the BattleSalvo game
 */
public record SetupJson(@JsonProperty ("width") int width, @JsonProperty("height")
    int height, @JsonProperty("fleet-spec")
                        Map<ShipType, Integer> specs) {


}
