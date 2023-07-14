package cs3500.pa04.model;

import cs3500.pa04.controller.ManualShotController;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents a manual player of the BattleSalvo game
 */
public class ManualPlayer extends AbstractPlayer {

  private Readable input;
  private Appendable output;

  /**
   * Initializes the manual player with a given name and random
   *
   * @param rand The given random
   */
  public ManualPlayer(Random rand) {
    super(rand);
    input = new InputStreamReader(System.in);
    output = System.out;
  }


  /**
   * Initializes the manual player with a given name, random, and boards
   *
   * @param rand The given random
   * @param board The given board
   * @param shootingBoard The given board for the opponent
   */
  public ManualPlayer(Random rand,
                      List<List<Peg>> board, List<List<Peg>> shootingBoard) {
    super(rand, board, shootingBoard);
    this.input = new InputStreamReader(System.in);
    this.output = System.out;
  }

  /**
   * Initializes the manual player with a given name, random, readable, and appendable
   *
   * @param rand The given random
   * @param input The given Readable
   * @param output The given Appenda ble
   */
  public ManualPlayer(Random rand, Readable input, Appendable output) {
    super(rand);
    this.input = input;
    this.output = output;
  }

  /**
   * Returns this manual player's shots on the opponent's board. The number of shots returned should
   * equal the number of ships on this player's board that have not sunk.
   *
   * @return the locations of shots on the opponent's board
   */
  @Override
  public List<Coord> takeShots() {
    Ship.removeIfSunk(ships);
    List<Coord> availableShots = getRemainingShots();
    int totalAvailableShots = availableShots.size();
    int numShots =  Math.min(totalAvailableShots, ships.size());
    List<Coord> shots = new ArrayList<>();
    ManualShotController msc =
        new ManualShotController(availableShots, shots, numShots, input, output);
    msc.run();
    previousShots = shots;
    return shots;
  }

  private List<Coord> getRemainingShots() {
    List<Coord> remainingCoords = new ArrayList<>();
    for (int r = 0; r < shootingBoard.size(); r++) {
      for (int c = 0; c < shootingBoard.get(r).size(); c++) {
        Peg p = shootingBoard.get(r).get(c);
        if (p.equals(Peg.EMPTY)) {
          remainingCoords.add(new Coord(c, r));
        }
      }
    }
    return remainingCoords;

  }


}
