package edu.austral.dissis.clientserver.listeners.game;

import edu.austral.dissis.chess.gui.ChessPiece;
import edu.austral.dissis.chess.gui.GameView;
import edu.austral.dissis.chess.gui.NewGameState;
import edu.austral.dissis.chess.gui.PlayerColor;
import edu.austral.dissis.chess.gui.UndoState;
import edu.austral.dissis.clientserver.payloads.game.GamePayload;
import edu.austral.ingsis.clientserver.Message;
import edu.austral.ingsis.clientserver.MessageListener;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class GameListener implements MessageListener<GamePayload> {
  private final GameView root;

  public GameListener(GameView root) {
    this.root = root;
  }

  @Override
  public void handleMessage(@NotNull Message<GamePayload> message) {
    GamePayload gameState = message.getPayload();
    List<ChessPiece> pieces = gameState.getPieces();
    PlayerColor colorTurn = gameState.getCurrentPlayer();
    UndoState undoState = gameState.getUndoState();

    root.handleMoveResult(new NewGameState(pieces, colorTurn, undoState));
  }
}
