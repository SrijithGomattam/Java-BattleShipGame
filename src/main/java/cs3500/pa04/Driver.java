package cs3500.pa04;

import cs3500.pa04.controller.BattleController;
import cs3500.pa04.controller.Controller;
import cs3500.pa04.controller.ProxyController;
import cs3500.pa04.model.AiPlayer;
import cs3500.pa04.model.ManualPlayer;
import cs3500.pa04.model.Peg;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This is the main driver of this project.
 */
public class Driver {


  /**
   * Sets up the Socket for the ProxyController
   *
   * @param host The host of the Server
   * @param portStr The String representing the port
   */
  private static Socket getSocket(String host, String portStr) {
    int port;
    try {
      port = Integer.parseInt(portStr);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Port not an Integer");
    }
    try {
      return new Socket(host, port);
    } catch (IOException e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  /**
   * Runs a game of BattleSalvo
   *
   * @param args - no command line args required
   */
  public static void main(String[] args) {
    List<List<Peg>> board1 = new ArrayList<>();
    List<List<Peg>> board2 = new ArrayList<>();
    if (args.length == 0) {
      Controller bc = new BattleController(new ManualPlayer(new Random(1), board1, board2),
          new AiPlayer(new Random()), board1, board2);
      bc.run();
    } else if (args.length == 2) {
      Socket server = getSocket(args[0], args[1]);
      Controller pc = new ProxyController(new AiPlayer(new Random(1), board1, board2),
          server, board1, board2);
      pc.run();
    }
  }
}