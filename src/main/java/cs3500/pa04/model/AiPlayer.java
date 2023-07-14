package cs3500.pa04.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents an AI player for the BattleSalvo game
 */
public class AiPlayer extends AbstractPlayer {

  /**
   * Initializes the AI player with a given random
   *
   * @param rand The given Random
   */
  public AiPlayer(Random rand) {
    super(rand);
  }

  /**
   * Initializes the fields with a given name, random, and boards
   *
   * @param rand The given Random
   * @param board The given board for this player's board
   * @param shootingBoard The given board to be the board for the opponent
   */
  public AiPlayer(Random rand,
                        List<List<Peg>> board, List<List<Peg>> shootingBoard) {
    super(rand, board, shootingBoard);

  }

  /**
   * Returns this AI player's shots on the opponent's board. The number of shots returned should
   * equal the number of ships on this player's board that have not sunk.
   *
   * @return the locations of shots on the opponent's board
   */
  @Override
  public List<Coord> takeShots() {
    List<Coord> shots = new ArrayList<>();
    Ship.removeIfSunk(ships);
    List<Coord> availableShots = getRemainingShots();
    int totalAvailableShots = availableShots.size();
    int numShots =  Math.min(totalAvailableShots, ships.size());
    takeTacticalShots(availableShots, numShots, shots);
    int remainingShots = shots.size();
    for (int i = 0; i < numShots - remainingShots; i++) {
      int shotIndex = rand.nextInt(availableShots.size());
      Coord shot = availableShots.remove(shotIndex);
      shots.add(shot);
    }
    previousShots = shots;
    return shots;
  }

  /**
   * Takes shots in a strategic way
   *
   * @param availableShots The available shots to take
   * @param numShots The number of allowed shots
   * @param shots The list of shots
   */
  private void takeTacticalShots(List<Coord> availableShots,
                                 int numShots, List<Coord> shots) {
    for (Coord shot : previousShots) {
      if (shootingBoard.get(shot.getY()).get(shot.getX()).equals(Peg.HIT)) {
        Coord nextV = new Coord(shot.getX(), shot.getY() + 1);
        Coord nextH = new Coord(shot.getX() + 1, shot.getY());
        while (availableShots.contains(nextH) && shots.size() < numShots) {
          shots.add(nextH);
          availableShots.remove(nextH);
          nextH = new Coord(nextH.getX() + 1, nextH.getY());
        }
        while (availableShots.contains(nextV) && shots.size() < numShots) {
          shots.add(nextV);
          availableShots.remove(nextV);
          nextV = new Coord(nextV.getX(), nextV.getY() + 1);
        }
      }
    }
  }


  /**
   * Gets the shots that remaining for this salvo round
   *
   * @return The list of remaining Coords
   */
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
