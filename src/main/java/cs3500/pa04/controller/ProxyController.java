package cs3500.pa04.controller;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa04.json.JsonUtils;
import cs3500.pa04.json.MessageJson;
import cs3500.pa04.json.ServerMethods;
import cs3500.pa04.model.Peg;
import cs3500.pa04.model.Player;
import cs3500.pa04.view.BattleView;
import cs3500.pa04.view.View;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.List;

/**
 * Represents a controller for communicating the BattleSalvo classes with the server
 */
public class ProxyController implements Controller {

  private final Player player;
  private final List<List<Peg>> board1;
  private final List<List<Peg>> board2;
  private final View view;
  private final Socket server;
  private final PrintStream out;
  private final JsonParser parser;

  private final ObjectMapper mapper = new ObjectMapper();

  /**
   * Controller initializes player, socket, and both boards
   *
   * @param player : AiPlayer that plays against the server
   * @param server : Server that is used
   * @param board1 : first board for Player
   * @param board2 : second board for Player
   */
  public ProxyController(Player player, Socket server,
                         List<List<Peg>> board1, List<List<Peg>> board2) {
    this.player = player;
    this.server = server;
    this.board1 = board1;
    this.board2 = board2;
    view = new BattleView(new InputStreamReader(System.in), System.out);
    try {
      this.out = new PrintStream(server.getOutputStream());
      this.parser = mapper.getFactory().createParser(this.server.getInputStream());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }

  /**
   * Runs the server; handles messages and displays the board accordingly
   */
  public void run() {
    while (!this.server.isClosed()) {
      handleMessage();
      //displayBoard();
    }
  }

  /**
   * Handles the messageJson passed by the server and executes the corresponding method
   */
  private void handleMessage() {
    // Create a messageJson of the message using the getMessage helper
    MessageJson messageJson = getMessage();

    // Split the messageJson into the method name and arguments
    String methodName = messageJson.messageName();
    JsonNode arguments = messageJson.arguments();

    // Create a serverMethod enum based on the method name using the getMethod helper
    ServerMethods serverMethod = getMethod(methodName);

    // Create a messageJson based on the output of getBattleMessage called on the serverMethod
    MessageJson messageToServer = serverMethod.getBattleMessage(arguments, player);

    // Display the previously created messageJson
    JsonNode messageNode = JsonUtils.serializeRecord(messageToServer);
    this.out.println(messageNode);

    // If the message indicates that the game is over, close the server
    if (messageToServer.messageName().equals("end-game")) {
      try {
        server.close();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }

  /**
   * Returns runs the method which is received as a string
   *
   * @param methodName : String name of the method
   * @return the method which corresponds to the string name
   */
  public ServerMethods getMethod(String methodName) {
    if (methodName.contains("-")) {
      String[] split = methodName.split("-");
      StringBuilder result = new StringBuilder();
      for (int i = 0; i < split.length - 1; i++) {
        String stringAsEnum = split[i].toUpperCase();
        result.append(stringAsEnum).append("_");
      }
      String end = split[split.length - 1].toUpperCase();
      result.append(end);
      return ServerMethods.valueOf(result.toString());
    }
    return ServerMethods.valueOf(methodName.toUpperCase());
  }

  /**
   * Gets the messageJson
   *
   * @return messageJson
   */
  private MessageJson getMessage() {
    try {
      return parser.readValueAs(MessageJson.class);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  // DISPLAYS BOARD (USED BY US FOR CREATING CONTROLLER)
  //  /**
  //   * Calls the view to display each board
  //   */
  //  private void displayBoard() {
  //    if (board1.size() > 0 && board2.size() > 0) {
  //      view.write("Opponents board: ");
  //      view.write(boardToString(board2));
  //      view.write("Your board: ");
  //      view.write(boardToString(board1));
  //    }
  //  }
  //
  //  /**
  //   * Returns a String representation of a board
  //   *
  //   * @param board A 2D list of Pegs representing a board
  //   * @return The String representation of the board
  //   */
  //  private static String boardToString(List<List<Peg>> board) {
  //    StringBuilder result = new StringBuilder();
  //    for (List<Peg> row : board) {
  //      for (Peg p : row) {
  //        result.append(p).append(" ");
  //      }
  //      result.append("\n");
  //    }
  //    return result.toString();
  //  }

}
