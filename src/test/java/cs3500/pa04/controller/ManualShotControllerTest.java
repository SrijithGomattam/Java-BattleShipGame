package cs3500.pa04.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa04.model.Coord;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for the ManualShotController class
 */
public class ManualShotControllerTest {

  /**
   * Tests the method run
   */
  @Test
  public void testRun() {
    List<Coord> availableShots = new ArrayList<>(Arrays.asList(new Coord(0, 0),
        new Coord(0, 1), new Coord(0, 2), new Coord(0, 3)));
    Readable reader = new StringReader("1 1 \n 0 0 0 0 \n 0 0 0 1 0 2 0 3");
    List<Coord> shots = new ArrayList<>();
    Appendable output = new StringBuilder();
    ManualShotController msc = new ManualShotController(availableShots,
        shots, 4, reader, output);
    msc.run();
    assertEquals("Enter 4 shots: \nShot was invalid\nEnter 4 shots: "
            + "\nShot was invalid\nEnter 4 shots: \n",
        output.toString());
    assertEquals(availableShots, shots);
  }
}