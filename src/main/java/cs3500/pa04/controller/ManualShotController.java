package cs3500.pa04.controller;

import cs3500.pa04.model.Coord;
import cs3500.pa04.view.BattleView;
import cs3500.pa04.view.View;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Controller that takes shots for ManualPlayer
 */
public class ManualShotController implements Controller {

  private final int numShots;
  private final List<Coord> availableShots;
  private final List<Coord> currShots;
  private final View view;

  /**
   * Initializes the fields of a ManualShotController
   * with available shots, the currentShots, and the number of shots to take
   *
   * @param availableShots The list of Coord for available shots
   * @param currShots The list of Coord for the current shots
   * @param numShots The total number of shots
   * @param input The given Readable
   * @param output given Appendable
   */
  public ManualShotController(List<Coord> availableShots,
                              List<Coord> currShots, int numShots,
                              Readable input, Appendable output) {
    this.currShots = currShots;
    this.availableShots = availableShots;
    this.numShots = numShots;
    this.view = new BattleView(input, output);
  }


  /**
   * Runs the application for the controller
   */
  @Override
  public void run() {
    List<Coord> shots = new ArrayList<>();
    try {
      view.write("Enter " + numShots + " shots: ");
      for (int i = 0; i < numShots; i++) {
        int shotX = Integer.parseInt(view.getNext());
        int shotY = Integer.parseInt(view.getNext());
        Coord shot = new Coord(shotX, shotY);
        if (!availableShots.contains(shot) || shots.contains(shot)) {
          throw new NumberFormatException();
        }
        shots.add(shot);
      }
    } catch (NumberFormatException e) {
      view.write("Shot was invalid");
      view.skipLine();
      run();
    }
    if (shots.size() == numShots) {
      currShots.addAll(shots);
    }
  }



}
