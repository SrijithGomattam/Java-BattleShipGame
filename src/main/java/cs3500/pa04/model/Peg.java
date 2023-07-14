package cs3500.pa04.model;

/**
 * Represents a Peg on the board of a BattleSalvo game
 */
public enum Peg {
  SHIP("S"), MISS("M"),
  HIT("H"), EMPTY("0");

  private final String pegLetter;

  /**
   * Sets the letter for the peg
   *
   * @param letter A given letter
   */
  private Peg(String letter) {
    pegLetter = letter;
  }

  /**
   * Overrides toString to return the letter for representing a Peg
   *
   * @return The letter
   */
  @Override
  public String toString() {
    return pegLetter;
  }
}
