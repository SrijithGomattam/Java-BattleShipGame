package cs3500.pa04.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa04.model.AiPlayer;
import cs3500.pa04.model.Peg;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for the BattleController class
 */
public class BattleControllerTest {

  /**
   * Tests the method run
   */
  @Test
  public void testRun() {
    List<List<Peg>> board1 = new ArrayList<>();
    List<List<Peg>> board2 = new ArrayList<>();
    AiPlayer player1 = new AiPlayer(new Random(1), board1, board2);
    AiPlayer player2 = new AiPlayer(new Random(1));
    Readable input =
        new StringReader("1 1\n10 1\n1 10\n20 20\n20 1\n1 20\n6 6\n0 0 0 0\n5 5 5 5\n1 1 1 1");
    Appendable output = new StringBuilder();
    BattleController bc = new BattleController(player1, player2, board1, board2, input, output);
    bc.run();
    String fleet = "Enter your fleet in the order [Carrier, Battleship, Destroyer, "
        + "Submarine] with a maximum of 6 ships\n"
        + "Invalid fleet\n"
        + "Enter your fleet in the order [Carrier, Battleship, Destroyer,"
        + " Submarine] with a maximum of 6 ships\n"
        + "Invalid fleet\n"
        + "Enter your fleet in the order [Carrier, Battleship, Destroyer, "
        + "Submarine] with a maximum of 6 ships\n";
    String result = """
        Welcome to BattleSalvo
        Enter a valid width and height
        These dimensions are invalid
        Enter a valid width and height
        These dimensions are invalid
        Enter a valid width and height
        These dimensions are invalid
        Enter a valid width and height
        These dimensions are invalid
        Enter a valid width and height
        These dimensions are invalid
        Enter a valid width and height
        These dimensions are invalid
        Enter a valid width and height
        """
        + fleet
        + """
        Opponents board:\s
        0 0 0 0 0 0\s
        0 0 0 0 0 0\s
        0 0 0 0 0 0\s
        0 0 0 0 0 0\s
        0 0 0 0 0 0\s
        0 0 0 0 0 0\s

        Your board:\s
        0 0 0 0 0 0\s
        0 S S S 0 0\s
        0 S S S S 0\s
        S S S S S S\s
        0 0 0 0 0 0\s
        S S S S S 0\s

        Opponents board:\s
        0 0 0 0 0 0\s
        0 0 0 0 M 0\s
        0 0 0 0 0 0\s
        0 0 0 0 0 0\s
        0 0 M M 0 0\s
        0 0 0 0 0 M\s

        Your board:\s
        0 0 0 0 0 0\s
        0 S S S M 0\s
        0 S S S S 0\s
        S S S S S S\s
        0 0 M M 0 0\s
        S S S S S M\s

        Opponents board:\s
        0 0 0 0 0 M\s
        0 0 H 0 M 0\s
        0 0 0 0 0 0\s
        0 0 0 H 0 0\s
        0 0 M M 0 0\s
        0 0 0 H 0 M\s

        Your board:\s
        0 0 0 0 0 M\s
        0 S H S M 0\s
        0 S S S S 0\s
        S S S H S S\s
        0 0 M M 0 0\s
        S S S H S M\s

        Opponents board:\s
        0 0 0 0 0 M\s
        0 0 H H M 0\s
        0 0 H 0 0 0\s
        0 0 H H 0 0\s
        0 0 M M 0 0\s
        0 0 0 H H M\s

        Your board:\s
        0 0 0 0 0 M\s
        0 S H H M 0\s
        0 S H S S 0\s
        S S H H S S\s
        0 0 M M 0 0\s
        S S S H H M\s

        Opponents board:\s
        0 0 0 0 0 M\s
        0 0 H H M 0\s
        0 H H H 0 0\s
        0 H H H 0 0\s
        0 0 M M M 0\s
        0 0 0 H H M\s

        Your board:\s
        0 0 0 0 0 M\s
        0 S H H M 0\s
        0 H H H S 0\s
        S H H H S S\s
        0 0 M M M 0\s
        S S S H H M\s

        Opponents board:\s
        0 0 0 0 0 M\s
        0 0 H H M 0\s
        0 H H H H M\s
        0 H H H 0 0\s
        0 M M M M 0\s
        0 H 0 H H M\s

        Your board:\s
        0 0 0 0 0 M\s
        0 S H H M 0\s
        0 H H H H M\s
        S H H H S S\s
        0 M M M M 0\s
        S H S H H M\s

        Opponents board:\s
        0 0 M 0 0 M\s
        0 0 H H M 0\s
        0 H H H H M\s
        0 H H H H 0\s
        0 M M M M 0\s
        0 H H H H M\s

        Your board:\s
        0 0 M 0 0 M\s
        0 S H H M 0\s
        0 H H H H M\s
        S H H H H S\s
        0 M M M M 0\s
        S H H H H M\s

        Opponents board:\s
        0 0 M 0 0 M\s
        0 0 H H M 0\s
        0 H H H H M\s
        H H H H H H\s
        0 M M M M M\s
        0 H H H H M\s

        Your board:\s
        0 0 M 0 0 M\s
        0 S H H M 0\s
        0 H H H H M\s
        H H H H H H\s
        0 M M M M M\s
        S H H H H M\s

        Opponents board:\s
        0 0 M 0 0 M\s
        0 0 H H M 0\s
        0 H H H H M\s
        H H H H H H\s
        M M M M M M\s
        H H H H H M\s

        Your board:\s
        0 0 M 0 0 M\s
        0 S H H M 0\s
        0 H H H H M\s
        H H H H H H\s
        M M M M M M\s
        H H H H H M\s

        Opponents board:\s
        0 0 M 0 0 M\s
        0 0 H H M 0\s
        M H H H H M\s
        H H H H H H\s
        M M M M M M\s
        H H H H H M\s

        Your board:\s
        0 0 M 0 0 M\s
        0 S H H M 0\s
        M H H H H M\s
        H H H H H H\s
        M M M M M M\s
        H H H H H M\s

        Opponents board:\s
        0 0 M 0 0 M\s
        0 H H H M 0\s
        M H H H H M\s
        H H H H H H\s
        M M M M M M\s
        H H H H H M\s

        Your board:\s
        0 0 M 0 0 M\s
        0 H H H M 0\s
        M H H H H M\s
        H H H H H H\s
        M M M M M M\s
        H H H H H M\s
        
        """;
    assertEquals(result, output.toString());

  }
}