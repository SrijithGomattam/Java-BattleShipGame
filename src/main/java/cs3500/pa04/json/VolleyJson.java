package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa04.model.Coord;
import java.util.List;

/**
 * Represents a record that contains the coordinates that are fired on in a volley
 *
 * @param coordinates : a list of Coords that have been fired on
 */
public record VolleyJson(@JsonProperty("coordinates") List<Coord> coordinates) {

}
