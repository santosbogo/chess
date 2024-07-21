package edu.austral.dissis.server;

import com.fasterxml.jackson.core.type.TypeReference;
import edu.austral.dissis.chess.gui.GameOver;
import edu.austral.dissis.chess.gui.InitialState;
import edu.austral.dissis.chess.gui.InvalidMove;
import edu.austral.dissis.chess.gui.Move;
import edu.austral.dissis.chess.gui.MoveResult;
import edu.austral.dissis.chess.gui.NewGameState;
import edu.austral.dissis.chess.gui.PlayerColor;
import edu.austral.dissis.ui.MySimpleGameEngine;
import edu.austral.dissis.server.listeners.ServerConnectionListener;
import edu.austral.dissis.server.listeners.moves.MoveListener;
import edu.austral.dissis.server.listeners.moves.RedoListener;
import edu.austral.dissis.server.listeners.moves.UndoListener;
import edu.austral.dissis.server.payloads.game.StartingGamePayload;
import edu.austral.ingsis.clientserver.Message;
import edu.austral.ingsis.clientserver.Server;
import edu.austral.ingsis.clientserver.netty.server.NettyServerBuilder;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class ServerEngine {

  public static final MySimpleGameEngine gameEngine = new MySimpleGameEngine();
  public static Server server;
  public static List<String> players = new CopyOnWriteArrayList<>();
  public static Map<String, String> clientRoles = new ConcurrentHashMap<>();
  public static Map<String, String> clientIdMap = new ConcurrentHashMap<>();
  public static boolean gameStarted = false;
  static InitialState initialState;

  public static void main(String[] args) throws InterruptedException {
    server =
        NettyServerBuilder.Companion.createDefault()
            .withPort(8095)
            .withConnectionListener(new ServerConnectionListener())
            .addMessageListener("move", new TypeReference<>() {}, new MoveListener())
            .addMessageListener("undo", new TypeReference<>() {}, new UndoListener())
            .addMessageListener("redo", new TypeReference<>() {}, new RedoListener())
            .build();

    server.start();
  }

  public static void addPlayer(String connectionId) {
    String clientId = UUID.randomUUID().toString();
    clientIdMap.put(connectionId, clientId);

    if (!clientRoles.containsValue("white")) {
      clientRoles.put(clientId, "white");
      players.add(clientId);
    } else if ((!clientRoles.containsValue("black"))) {
      clientRoles.put(clientId, "black");
      players.add(clientId);
    } else {
      addViewer(clientId, connectionId);
    }

    System.out.println("Player added: " + clientId + " as " + clientRoles.get(clientId));

    if (players.size() == 2) {
      startGame();
    }

    server.sendMessage(connectionId, new Message<>("id", clientId));
  }

  private static void addViewer(String clientId, String connectionId) {
    clientRoles.put(clientId, "viewer");

    if (gameStarted) {
      NewGameState currentGameState = gameEngine.getCurrentState();
      InitialState currentState =
          new InitialState(
              initialState.getBoardSize(),
              currentGameState.getPieces(),
              currentGameState.getCurrentPlayer());
      server.sendMessage(
          connectionId, new Message<>("initialState", new StartingGamePayload(currentState)));
    }
  }

  private static void startGame() {
    gameStarted = true;
    initialState = gameEngine.init();
    server.broadcast(new Message<>("initialState", new StartingGamePayload(initialState)));
  }

  public static void removePlayer(String connectionId) {
    String clientId = clientIdMap.get(connectionId);
    if (clientRoles.get(clientId).equals("white") || clientRoles.get(clientId).equals("black")) {
      gameStarted = false;
    }
    players.remove(clientId);
    clientRoles.remove(clientId);
    clientIdMap.remove(connectionId);
  }

  public static void handleMove(Move move, String clientId) {
    if (!gameStarted) {
      sendInvalidMoveMessage(
          clientId, new InvalidMove("One player has disconnected. Please connect again."));
      return;
    }
    if (!isClientTurn(clientId)) {
      sendInvalidMoveMessage(clientId, new InvalidMove("It's not your turn!"));
      return;
    }

    MoveResult result = gameEngine.applyMove(move);

    switch (result) {
      case NewGameState newGameState -> server.broadcast(new Message<>("gameState", newGameState));
      case InvalidMove invalidMove -> sendInvalidMoveMessage(clientId, invalidMove);
      case GameOver gameOver -> server.broadcast(new Message<>("gameOver", gameOver));
      default -> {
        /* Do nothing */
      }
    }
  }

  public static void handleUndo(String clientId) {
    if (!gameStarted) {
      sendInvalidMoveMessage(
          clientId, new InvalidMove("One player has disconnected. Please connect again."));
      return;
    }
    if (isClientTurn(clientId)) {
      sendInvalidMoveMessage(clientId, new InvalidMove("It's not your turn!"));
      return;
    }

    NewGameState newGameState = gameEngine.undo();
    server.broadcast(new Message<>("gameState", newGameState));
  }

  public static void handleRedo(String clientId) {
    if (!gameStarted) {
      sendInvalidMoveMessage(
          clientId, new InvalidMove("One player has disconnected. Please connect again."));
      return;
    }
    if (!isClientTurn(clientId)) {
      sendInvalidMoveMessage(clientId, new InvalidMove("It's not your turn!"));
      return;
    }

    NewGameState newGameState = gameEngine.redo();
    server.broadcast(new Message<>("gameState", newGameState));
  }

  private static boolean isClientTurn(String clientId) {
    String role = clientRoles.get(clientId);
    boolean isWhiteTurn = gameEngine.getPlayerTurnColor().equals(PlayerColor.WHITE);
    return (role.equals("white") && isWhiteTurn) || (role.equals("black") && !isWhiteTurn);
  }

  private static void sendInvalidMoveMessage(String clientId, InvalidMove invalidMove) {
    String connectionId = getConnectionIdFromClientId(clientId);
    assert connectionId != null;
    server.sendMessage(connectionId, new Message<>("invalidMove", invalidMove));
  }

  private static String getConnectionIdFromClientId(String clientId) {
    for (Map.Entry<String, String> entry : clientIdMap.entrySet()) {
      if (entry.getValue().equals(clientId)) {
        return entry.getKey();
      }
    }
    return null;
  }
}
