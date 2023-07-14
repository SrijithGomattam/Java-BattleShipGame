package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa04.model.Ship;
import java.util.List;

/**
 * Represents a record that contains the fleet which is a list of ships
 *
 * @param fleet : a list of ships
 */
public record FleetJson(@JsonProperty("fleet") List<Ship> fleet) {


}
