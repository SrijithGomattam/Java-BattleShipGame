package cs3500.pa04.view;

import static java.lang.Integer.parseInt;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Represents the view used in the BattleSalvo game
 */
public class BattleView implements View {

  private final Scanner scan;
  private final Appendable output;

  /**
   * Initializes the scanner and Appendable from a given Readable and Appendable
   *
   * @param input The given Readable
   * @param output The given Appendable
   */
  public BattleView(Readable input, Appendable output) {
    this.output = output;
    scan = new Scanner(input);
  }

  /**
   * Writes a given message to the output
   *
   * @param message The given message
   */
  @Override
  public void write(String message) {
    try {
      output.append(message).append("\n");
    } catch (IOException e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  /**
   * Gets the next item from a Scanner
   *
   * @return The String from the next item
   */
  public String getNext() {
    if (scan.hasNext()) {
      return scan.next();
    }
    throw new NoSuchElementException("No next item found in Scanner");
  }

  /**
   * Skips the line in the Scanner
   */
  public void skipLine() {
    if (scan.hasNextLine()) {
      scan.nextLine();
    }
  }
}
