package edu.austral.dissis.server.listeners.game;

import edu.austral.dissis.chess.gui.GameOver;
import edu.austral.dissis.chess.gui.GameView;
import edu.austral.dissis.chess.gui.PlayerColor;
import edu.austral.dissis.server.payloads.game.GameOverPayload;
import edu.austral.ingsis.clientserver.Message;
import edu.austral.ingsis.clientserver.MessageListener;
import javafx.application.Platform;
import org.jetbrains.annotations.NotNull;

public class GameOverListener implements MessageListener<GameOverPayload> {
  private final GameView root;

  public GameOverListener(GameView root) {
    this.root = root;
  }

  @Override
  public void handleMessage(@NotNull Message<GameOverPayload> message) {
    GameOverPayload gameOver = message.getPayload();
    PlayerColor winner = gameOver.getWinner();

    Platform.runLater(() -> root.handleMoveResult(new GameOver(winner)));
  }
}
