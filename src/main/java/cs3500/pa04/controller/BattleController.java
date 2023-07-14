package cs3500.pa04.controller;

import cs3500.pa04.model.Coord;
import cs3500.pa04.model.Peg;
import cs3500.pa04.model.Player;
import cs3500.pa04.model.ShipType;
import cs3500.pa04.view.BattleView;
import cs3500.pa04.view.View;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Represents the main controller for the BattleSalvo game
 */
public class BattleController implements Controller {

  private final Player player1;
  private final Player player2;
  private final List<List<Peg>> board1;
  private final List<List<Peg>> board2;
  private final View view;

  /**
   * Initializes the BattleModel and the BattleView from two Players and boards
   *
   * @param player1 The first given player
   * @param player2 The second given player
   *  @param board1 The board from player1
   * @param board2 The board from player2
   */
  public BattleController(Player player1, Player player2,
                          List<List<Peg>> board1, List<List<Peg>> board2) {
    this.player1 = player1;
    this.player2 = player2;
    this.board1 = board1;
    this.board2 = board2;
    view = new BattleView(new InputStreamReader(System.in), System.out);
  }

  /**
   * Initializes the BattleModel and the BattleView from two Players and boards
   * and input and output
   *
   * @param player1 The first given player
   * @param player2 The second given player
   * @param board1 The board from player1
   * @param board2 The board from player2
   * @param input Given Readable input
   * @param output Given Appendable output
   */
  public BattleController(Player player1, Player player2,
                          List<List<Peg>> board1, List<List<Peg>> board2,
                          Readable input, Appendable output) {
    this.player1 = player1;
    this.player2 = player2;
    this.board1 = board1;
    this.board2 = board2;
    view = new BattleView(input, output);
  }



  /**
   * Runs the BattleSalvo game
   */
  @Override
  public void run() {
    view.write("Welcome to BattleSalvo");
    start();
    displayBoard();
    while (true) {
      List<Coord> player1Shots = player1.takeShots();
      List<Coord> player2Shots = player2.takeShots();
      if (player1Shots.isEmpty() || player2Shots.isEmpty()) {
        break;
      }
      List<Coord> shotsThatHitPlayer1 = player1.reportDamage(player2Shots);
      List<Coord> shotsThatHitPlayer2 = player2.reportDamage(player1Shots);
      player1.successfulHits(shotsThatHitPlayer2);
      player2.successfulHits(shotsThatHitPlayer1);
      displayBoard();
    }
  }


  /**
   * Starts the BattleSalvo game by asking the user for the board size and fleet
   */
  private void start() {
    view.write("Enter a valid width and height");
    try {
      String widthAsString = view.getNext();
      String heightAsString = view.getNext();
      int height = Integer.parseInt(heightAsString);
      int width = Integer.parseInt(widthAsString);
      if (height < 6 || height > 15 || width < 6 || width > 15) {
        throw new NumberFormatException();
      }
      enterFleet(height, width);
    } catch (NumberFormatException e) {
      view.write("These dimensions are invalid");
      view.skipLine();
      start();
    }
  }

  /**
   * Enters the fleet of ships for the game with the given height and width
   *
   * @param height The given height
   * @param width The given width
   */
  private void enterFleet(int height, int width) {
    int maxShips = Math.min(height, width);
    LinkedHashMap<ShipType, Integer> specs = getShipSpecs(maxShips);
    player1.setup(height, width, specs);
    player2.setup(height, width, specs);
  }

  /**
   * Gets the specifications for the ship fleet
   *
   * @param maxShips The maximum number of ships in the ship fleet
   * @return The specifications of the fleet
   */
  private LinkedHashMap<ShipType, Integer> getShipSpecs(int maxShips) {
    view.write("Enter your fleet in the order [Carrier, Battleship, "
        + "Destroyer, Submarine] with a maximum of "
        + maxShips + " ships");
    LinkedHashMap<ShipType, Integer> specs = new LinkedHashMap<>();
    List<String> specsAsString = new ArrayList<>();
    ShipType[] shipTypes = ShipType.values();
    for (int i = 0; i < shipTypes.length; i++) {
      specsAsString.add(view.getNext());
    }
    try {
      List<Integer> specsValues = new ArrayList<>();
      for (String str : specsAsString) {
        specsValues.add(Integer.parseInt(str));
      }
      if (checkInvalidFleet(specsValues, maxShips)) {
        throw new NumberFormatException();
      }
      for (int i = 0; i < shipTypes.length; i++) {
        ShipType st = shipTypes[i];
        specs.put(st, specsValues.get(i));
      }
    }  catch (NumberFormatException e) {
      view.write("Invalid fleet");
      view.skipLine();
      return getShipSpecs(maxShips);
    }
    return specs;
  }

  /**
   * Cheeks if the fleet specifications are invalid
   *
   * @param specsValues The amount of each ship
   * @param maxShips The maximum total ships
   * @return If the fleet is invalid
   */
  private boolean checkInvalidFleet(List<Integer> specsValues, int maxShips) {
    int sum = 0;
    for (int i : specsValues) {
      sum += i;
      if (i <= 0) {
        return true;
      }
    }
    return sum > maxShips;
  }

  /**
   * Calls the view to display each board
   */
  private void displayBoard() {
    view.write("Opponents board: ");
    view.write(boardToString(board2));
    view.write("Your board: ");
    view.write(boardToString(board1));
  }

  /**
   * Returns a String representation of a board
   *
   * @param board A 2D list of Pegs representing a board
   * @return The String representation of the board
   */
  private static String boardToString(List<List<Peg>> board) {
    StringBuilder result = new StringBuilder();
    for (List<Peg> row : board) {
      for (Peg p : row) {
        result.append(p).append(" ");
      }
      result.append("\n");
    }
    return result.toString();
  }
}
