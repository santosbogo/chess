package edu.austral.dissis.clientserver.listeners.game;

import edu.austral.dissis.chess.gui.BoardSize;
import edu.austral.dissis.chess.gui.ChessPiece;
import edu.austral.dissis.chess.gui.GameView;
import edu.austral.dissis.chess.gui.InitialState;
import edu.austral.dissis.chess.gui.PlayerColor;
import edu.austral.dissis.clientserver.payloads.game.StartingGamePayload;
import edu.austral.ingsis.clientserver.Message;
import edu.austral.ingsis.clientserver.MessageListener;
import java.util.List;
import javafx.application.Platform;
import org.jetbrains.annotations.NotNull;

public class StartingGameListener implements MessageListener<StartingGamePayload> {
  private final GameView root;

  public StartingGameListener(GameView root) {
    this.root = root;
  }

  @Override
  public void handleMessage(@NotNull Message<StartingGamePayload> message) {
    StartingGamePayload initialStatePayload = message.getPayload();

    List<ChessPiece> pieces = initialStatePayload.getPieces();
    BoardSize boardSize = initialStatePayload.getBoardSize();
    PlayerColor currentPlayer = initialStatePayload.getCurrentPlayer();
    updateUi(new InitialState(boardSize, pieces, currentPlayer));
  }

  private void updateUi(InitialState initialState) {

    Platform.runLater(() -> root.handleInitialState(initialState));
  }
}
