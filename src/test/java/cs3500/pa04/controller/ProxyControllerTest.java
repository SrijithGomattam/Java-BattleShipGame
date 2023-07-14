package cs3500.pa04.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.JsonNode;
import cs3500.pa04.json.EndJson;
import cs3500.pa04.json.JsonUtils;
import cs3500.pa04.json.MessageJson;
import cs3500.pa04.model.AiPlayer;
import cs3500.pa04.model.GameResult;
import cs3500.pa04.model.Peg;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for the ProxyController class
 */
public class ProxyControllerTest {

  Mocket socket;
  List<List<Peg>> board1;
  List<List<Peg>> board2;
  String expected;
  ArrayList<String> allVals;
  Controller pc;
  private ByteArrayOutputStream testLog;

  /**
   * Initializes variables used for testing
   */
  @BeforeEach
  public void setup() {
    this.testLog = new ByteArrayOutputStream();
    assertEquals("", logToString());
    board1 = new ArrayList<>();
    board2 = new ArrayList<>();

    allVals = new ArrayList<>(List.of("{\"method-name\":\"join\",\"arguments\":{}}",
        "{\"method-name\":\"setup\",\"arguments\":{\"height\":12,\"width\":7,\"fleet-s"
            + "pec\":{\"SUBMARINE\":1,\"BATTLESHIP\":1,\"DESTROYER\":2,\"CARRIER\":1}}}",
        "{\"method-name\":\"take-shots\",\"arguments\":{}}",
        "{\"method-name\":\"report-damage\",\"arguments\":{\"coordinates\":[{\"x\":1,\"y\":9"
            + "},{\"x\":5,\"y\":0},{\"x\":3,\"y\":7},{\"x\":3,\"y\":5},{\"x\":2,\"y\":1}]}}",
        "{\"method-name\":\"successful-hits\",\"arguments\":{\"coordinates\":[{\"x\":6,\"y\":"
            + "10},{\"x\":1,\"y\":10}]}}",
        "{\"method-name\":\"take-shots\",\"arguments\":{}}",
        "{\"method-name\":\"report-damage\",\"arguments\":{\"coordinates\":[{\"x\":1,\"y\":2"
            + "},{\"x\":3,\"y\":0},{\"x\":6,\"y\":8},{\"x\":3,\"y\":1},{\"x\":5,\"y\":2}]}}",
        "{\"method-name\":\"successful-hits\",\"arguments\":{\"coordinates\":[{\"x\":6,\"y\":"
            + "11},{\"x\":2,\"y\":10},{\"x\":3,\"y\":10},{\"x\":4,\"y\":10},{\"x\":5,\"y\":10}]}}",
        "{\"method-name\":\"take-shots\",\"arguments\":{}}",
        "{\"method-name\":\"report-damage\",\"arguments\":{\"coordinates\":[{\"x\":4,\"y\":"
            + "3},{\"x\":5,\"y\":8},{\"x\":2,\"y\":9},{\"x\":2,\"y\":7},{\"x\":1,\"y\":11}]}}",
        "{\"method-name\":\"successful-hits\",\"arguments\":{\"coordinates\":[]}}",
        "{\"method-name\":\"take-shots\",\"arguments\":{}}",
        "{\"method-name\":\"report-damage\",\"arguments\":{\"coordinates\":[{\"x\":0,\"y\":"
            + "8},{\"x\":5,\"y\":7},{\"x\":2,\"y\":5},{\"x\":2,\"y\":10},{\"x\":6,\"y\":6}]}}",
        "{\"method-name\":\"successful-hits\",\"arguments\":{\"coordinates\":[{\"x\":4,\"y\":"
            + "4},{\"x\":6,\"y\":4}]}}",
        "{\"method-name\":\"take-shots\",\"arguments\":{}}",
        "{\"method-name\":\"report-damage\",\"arguments\":{\"coordinates\":[{\"x\":2,\"y\":11"
            + "},{\"x\":6,\"y\":5},{\"x\":5,\"y\":5},{\"x\":2,\"y\":3},{\"x\":6,\"y\":10}]}}",
        "{\"method-name\":\"successful-hits\",\"arguments\":{\"coordinates\":[{\"x\":5,\"y\":4}"
            + "]}}",
        "{\"method-name\":\"take-shots\",\"arguments\":{}}",
        "{\"method-name\":\"report-damage\",\"arguments\":{\"coordinates\":[{\"x\":4,\"y\":0"
            + "},{\"x\":5,\"y\":11},{\"x\":0,\"y\":9},{\"x\":2,\"y\":0}]}}",
        "{\"method-name\":\"successful-hits\",\"arguments\":{\"coordinates\":[]}}",
        "{\"method-name\":\"take-shots\",\"arguments\":{}}",
        "{\"method-name\":\"report-damage\",\"arguments\":{\"coordinates\":[{\"x\":3,\"y\":9"
            + "},{\"x\":4,\"y\":5},{\"x\":0,\"y\":3},{\"x\":4,\"y\":1}]}}",
        "{\"method-name\":\"successful-hits\",\"arguments\":{\"coordinates\":[]}}",
        "{\"method-name\":\"take-shots\",\"arguments\":{}}",
        "{\"method-name\":\"report-damage\",\"arguments\":{\"coordinates\":[{\"x\":4,\"y\":4"
            + "},{\"x\":2,\"y\":2},{\"x\":3,\"y\":4},{\"x\":6,\"y\":3}]}}",
        "{\"method-name\":\"successful-hits\",\"arguments\":{\"coordinates\":[{\"x\":6,\"y\""
            + ":9},{\"x\":0,\"y\":2},{\"x\":0,\"y\":10}]}}",
        "{\"method-name\":\"take-shots\",\"arguments\":{}}",
        "{\"method-name\":\"report-damage\",\"arguments\":{\"coordinates\":[{\"x\":3,\"y\":1"
            + "1},{\"x\":1,\"y\":4},{\"x\":5,\"y\":9}]}}",
        "{\"method-name\":\"successful-hits\",\"arguments\":{\"coordinates\":[]}}",
        "{\"method-name\":\"take-shots\",\"arguments\":{}}",
        "{\"method-name\":\"report-damage\",\"arguments\":{\"coordinates\":[{\"x\":1,\"y\":1}"
            + ",{\"x\":1,\"y\":10},{\"x\":4,\"y\":2}]}}",
        "{\"method-name\":\"successful-hits\",\"arguments\":{\"coordinates\":[{\"x\":0,\"y\":1}"
            + "]}}",
        "{\"method-name\":\"take-shots\",\"arguments\":{}}",
        "{\"method-name\":\"report-damage\",\"arguments\":{\"coordinates\":[{\"x\":3,\"y\":6"
            + "},{\"x\":4,\"y\":7},{\"x\":1,\"y\":5}]}}",
        "{\"method-name\":\"successful-hits\",\"arguments\":{\"coordinates\":[{\"x\":3,\"y\":5}]"
            + "}}",
        "{\"method-name\":\"take-shots\",\"arguments\":{}}",
        "{\"method-name\":\"report-damage\",\"arguments\":{\"coordinates\":[{\"x\":6,\"y\":11}"
            + ",{\"x\":0,\"y\":2},{\"x\":0,\"y\":4}]}}",
        "{\"method-name\":\"successful-hits\",\"arguments\":{\"coordinates\":[{\"x\":3,\"y\":"
            + "6},{\"x\":3,\"y\":7}]}}",
        "{\"method-name\":\"take-shots\",\"arguments\":{}}",
        "{\"method-name\":\"report-damage\",\"arguments\":{\"coordinates\":[{\"x\":4,\"y\":6},"
            + "{\"x\":6,\"y\":1},{\"x\":0,\"y\":5}]}}",
        "{\"method-name\":\"successful-hits\",\"arguments\":{\"coordinates\":[{\"x\":3,\"y\":4}]}}",
        "{\"method-name\":\"take-shots\",\"arguments\":{}}",
        "{\"method-name\":\"report-damage\",\"arguments\":{\"coordinates\":[{\"x\":3,\"y\":8},{"
            + "\"x\":4,\"y\":10}]}}",
        "{\"method-name\":\"successful-hits\",\"arguments\":{\"coordinates\":[{\"x\":6,\"y\":7"
            + "},{\"x\":6,\"y\":8},{\"x\":0,\"y\":0}]}}",
        "{\"method-name\":\"take-shots\",\"arguments\":{}}",
        "{\"method-name\":\"report-damage\",\"arguments\":{\"coordinates\":[{\"x\":6,\"y\":7}"
            + "]}}",
        "{\"method-name\":\"successful-hits\",\"arguments\":{\"coordinates\":[]}}",
        "{\"method-name\":\"take-shots\",\"arguments\":{}}",
        "{\"method-name\":\"report-damage\",\"arguments\":{\"coordinates\":[{\"x\":6,\"y\":0}]}}",
        "{\"method-name\":\"successful-hits\",\"arguments\":{\"coordinates\":[{\"x\":0,\"y\":3}"
            + "]}}",
        "{\"method-name\":\"take-shots\",\"arguments\":{}}",
        "{\"method-name\":\"end-game\",\"arguments\":{\"result\":\"WIN\",\"reason\":\"You won!\"}"
            + "}"));

    expected = "{\"method-name\":\"join\",\"arguments\":{\"name\":\"brendanferguson4\",\"game"
        + "-type\":\"SINGLE\"}}\n"
        + "{\"method-name\":\"setup\",\"arguments\":{\"fleet\":[{\"coord\":{\"x\":6,\"y\":7}"
        + ",\"length\":3,\"direction\":\"VERTICAL\"},{\"coord\":{\"x\":2,\"y\":0},\"length\":"
        + "5,\"direction\":\"VERTICAL\"},{\"coord\":{\"x\":4,\"y\":7},\"length\":4,\"directio"
        + "n\":\"VERTICAL\"},{\"coord\":{\"x\":5,\"y\":1},\"length\":4,\"direction\":\"VERTIC"
        + "AL\"},{\"coord\":{\"x\":1,\"y\":11},\"length\":6,\"direction\":\"HORIZONTAL\"}]}}\n"
        + "{\"method-name\":\"take-shots\",\"arguments\":{\"coordinates\":[{\"x\":6,\"y\":10"
        + "},{\"x\":6,\"y\":1},{\"x\":2,\"y\":7},{\"x\":1,\"y\":8},{\"x\":1,\"y\":10}]}}\n"
        + "{\"method-name\":\"report-damage\",\"arguments\":{\"coordinates\":[{\"x\":2,\"y\":1}"
        + "]}}\n"
        + "{\"method-name\":\"successful-hits\",\"arguments\":\"void\"}\n"
        + "{\"method-name\":\"take-shots\",\"arguments\":{\"coordinates\":[{\"x\":6,\"y\":1"
        + "1},{\"x\":2,\"y\":10},{\"x\":3,\"y\":10},{\"x\":4,\"y\":10},{\"x\":5,\"y\":10}]}}\n"
        + "{\"method-name\":\"report-damage\",\"arguments\":{\"coordinates\":[{\"x\":6,\"y\":8"
        + "},{\"x\":5,\"y\":2}]}}\n"
        + "{\"method-name\":\"successful-hits\",\"arguments\":\"void\"}\n"
        + "{\"method-name\":\"take-shots\",\"arguments\":{\"coordinates\":[{\"x\":2,\"y\":11}"
        + ",{\"x\":3,\"y\":11},{\"x\":4,\"y\":11},{\"x\":5,\"y\":11},{\"x\":6,\"y\":2}]}}\n"
        + "{\"method-name\":\"report-damage\",\"arguments\":{\"coordinates\":[{\"x\":1,\"y\":"
        + "11}]}}\n"
        + "{\"method-name\":\"successful-hits\",\"arguments\":\"void\"}\n"
        + "{\"method-name\":\"take-shots\",\"arguments\":{\"coordinates\":[{\"x\":4,\"y\":0},"
        + "{\"x\":4,\"y\":4},{\"x\":0,\"y\":11},{\"x\":6,\"y\":3},{\"x\":6,\"y\":4}]}}\n"
        + "{\"method-name\":\"report-damage\",\"arguments\":{\"coordinates\":[]}}\n"
        + "{\"method-name\":\"successful-hits\",\"arguments\":\"void\"}\n"
        + "{\"method-name\":\"take-shots\",\"arguments\":{\"coordinates\":[{\"x\":5,\"y\":4},"
        + "{\"x\":4,\"y\":5},{\"x\":4,\"y\":6},{\"x\":4,\"y\":7},{\"x\":4,\"y\":8}]}}\n"
        + "{\"method-name\":\"report-damage\",\"arguments\":{\"coordinates\":[{\"x\":2,\"y\":11"
        + "},{\"x\":2,\"y\":3}]}}\n"
        + "{\"method-name\":\"successful-hits\",\"arguments\":\"void\"}\n"
        + "{\"method-name\":\"take-shots\",\"arguments\":{\"coordinates\":[{\"x\":5,\"y\":5"
        + "},{\"x\":5,\"y\":6},{\"x\":5,\"y\":7},{\"x\":5,\"y\":8},{\"x\":5,\"y\":9}]}}\n"
        + "{\"method-name\":\"report-damage\",\"arguments\":{\"coordinates\":[{\"x\":5,\"y\":1"
        + "1},{\"x\":2,\"y\":0}]}}\n"
        + "{\"method-name\":\"successful-hits\",\"arguments\":\"void\"}\n"
        + "{\"method-name\":\"take-shots\",\"arguments\":{\"coordinates\":[{\"x\":0,\"y\":7"
        + "},{\"x\":3,\"y\":1},{\"x\":5,\"y\":0},{\"x\":4,\"y\":9},{\"x\":0,\"y\":5}]}}\n"
        + "{\"method-name\":\"report-damage\",\"arguments\":{\"coordinates\":[]}}\n"
        + "{\"method-name\":\"successful-hits\",\"arguments\":\"void\"}\n"
        + "{\"method-name\":\"take-shots\",\"arguments\":{\"coordinates\":[{\"x\":1,\"y\":11}"
        + ",{\"x\":6,\"y\":9},{\"x\":1,\"y\":3},{\"x\":0,\"y\":2},{\"x\":0,\"y\":10}]}}\n"
        + "{\"method-name\":\"report-damage\",\"arguments\":{\"coordinates\":[{\"x\":2,\"y\":2}"
        + "]}}\n"
        + "{\"method-name\":\"successful-hits\",\"arguments\":\"void\"}\n"
        + "{\"method-name\":\"take-shots\",\"arguments\":{\"coordinates\":[{\"x\":1,\"y\":2},"
        + "{\"x\":2,\"y\":2},{\"x\":3,\"y\":2},{\"x\":4,\"y\":2},{\"x\":5,\"y\":2}]}}\n"
        + "{\"method-name\":\"report-damage\",\"arguments\":{\"coordinates\":[{\"x\":3,\"y\":1"
        + "1}]}}\n"
        + "{\"method-name\":\"successful-hits\",\"arguments\":\"void\"}\n"
        + "{\"method-name\":\"take-shots\",\"arguments\":{\"coordinates\":[{\"x\":0,\"y\":8}"
        + ",{\"x\":1,\"y\":9},{\"x\":2,\"y\":5},{\"x\":6,\"y\":6},{\"x\":0,\"y\":1}]}}\n"
        + "{\"method-name\":\"report-damage\",\"arguments\":{\"coordinates\":[]}}\n"
        + "{\"method-name\":\"successful-hits\",\"arguments\":\"void\"}\n"
        + "{\"method-name\":\"take-shots\",\"arguments\":{\"coordinates\":[{\"x\":1,\"y\":1},"
        + "{\"x\":2,\"y\":1},{\"x\":6,\"y\":5},{\"x\":3,\"y\":0},{\"x\":3,\"y\":5}]}}\n"
        + "{\"method-name\":\"report-damage\",\"arguments\":{\"coordinates\":[{\"x\":4,\"y\":7"
        + "}]}}\n"
        + "{\"method-name\":\"successful-hits\",\"arguments\":\"void\"}\n"
        + "{\"method-name\":\"take-shots\",\"arguments\":{\"coordinates\":[{\"x\":3,\"y\":6}"
        + ",{\"x\":3,\"y\":7},{\"x\":3,\"y\":8},{\"x\":3,\"y\":9},{\"x\":2,\"y\":4}]}}\n"
        + "{\"method-name\":\"report-damage\",\"arguments\":{\"coordinates\":[{\"x\":6,\"y\":1"
        + "1}]}}\n"
        + "{\"method-name\":\"successful-hits\",\"arguments\":\"void\"}\n"
        + "{\"method-name\":\"take-shots\",\"arguments\":{\"coordinates\":[{\"x\":6,\"y\":0}"
        + ",{\"x\":2,\"y\":8},{\"x\":3,\"y\":4},{\"x\":2,\"y\":3},{\"x\":2,\"y\":6}]}}\n"
        + "{\"method-name\":\"report-damage\",\"arguments\":{\"coordinates\":[]}}\n"
        + "{\"method-name\":\"successful-hits\",\"arguments\":\"void\"}\n"
        + "{\"method-name\":\"take-shots\",\"arguments\":{\"coordinates\":[{\"x\":3,\"y\":3},"
        + "{\"x\":6,\"y\":7},{\"x\":6,\"y\":8},{\"x\":0,\"y\":0},{\"x\":1,\"y\":7}]}}\n"
        + "{\"method-name\":\"report-damage\",\"arguments\":{\"coordinates\":[{\"x\":4,\"y\":"
        + "10}]}}\n"
        + "{\"method-name\":\"successful-hits\",\"arguments\":\"void\"}\n"
        + "{\"method-name\":\"take-shots\",\"arguments\":{\"coordinates\":[{\"x\":1,\"y\":0},"
        + "{\"x\":2,\"y\":0},{\"x\":4,\"y\":1},{\"x\":1,\"y\":5},{\"x\":0,\"y\":9}]}}\n"
        + "{\"method-name\":\"report-damage\",\"arguments\":{\"coordinates\":[{\"x\":6,\"y\":7}"
        + "]}}\n"
        + "{\"method-name\":\"successful-hits\",\"arguments\":\"void\"}\n"
        + "{\"method-name\":\"take-shots\",\"arguments\":{\"coordinates\":[{\"x\":0,\"y\":3}"
        + ",{\"x\":0,\"y\":6},{\"x\":0,\"y\":4},{\"x\":1,\"y\":4},{\"x\":4,\"y\":3}]}}\n"
        + "{\"method-name\":\"report-damage\",\"arguments\":{\"coordinates\":[]}}\n"
        + "{\"method-name\":\"successful-hits\",\"arguments\":\"void\"}\n"
        + "{\"method-name\":\"take-shots\",\"arguments\":{\"coordinates\":[{\"x\":5,\"y\":1}"
        + ",{\"x\":5,\"y\":3},{\"x\":2,\"y\":9},{\"x\":1,\"y\":6}]}}\n"
        + "{\"method-name\":\"end-game\",\"arguments\":\"void\"}\n";


    socket = new Mocket(testLog, allVals);

    this.pc = new ProxyController(new AiPlayer(new Random(1), board1, board2),
        socket, board1, board2);
  }

  /**
   * Test to see if the game is ending properly
   */
  @Test
  public void testEndGame() {

    // ProxyController should end with the game ending
    EndJson endJson = new EndJson(GameResult.WIN, "You win");
    JsonNode jsonNode1 = createSampleMessage("end-game", endJson);

    socket = new Mocket(this.testLog, List.of(jsonNode1.toString()));

    this.pc = new ProxyController(new AiPlayer(new Random(), board1, board2),
        socket, board1, board2);

    this.pc.run();
    assertEquals("{\"method-name\":\"end-game\",\"arguments\":\"void\"}" +
        System.lineSeparator(), logToString());
  }

  /**
   * Tests the run sequence of the ProxyController
   */
  @Test
  public void testRun() {
    pc.run();
    assertEquals(expected, testLog.toString());
  }


  /**
   * Converts the ByteArrayOutputStream log to a string in UTF_8 format
   *
   * @return String representing the current log buffer
   */
  private String logToString() {
    return testLog.toString(StandardCharsets.UTF_8);
  }


  /**
   * Creates a JsonNode using the values passed in as parameters
   *
   * @param messageName   : a String name of the message
   * @param messageObject : A Json Record of the content of the message
   * @return a JsonNode of a MessageJson created using the given values
   */
  private JsonNode createSampleMessage(String messageName, Record messageObject) {
    MessageJson messageJson =
        new MessageJson(messageName, JsonUtils.serializeRecord(messageObject));
    return JsonUtils.serializeRecord(messageJson);
  }
}
