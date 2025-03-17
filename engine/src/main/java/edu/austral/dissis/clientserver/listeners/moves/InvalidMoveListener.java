package edu.austral.dissis.clientserver.listeners.moves;

import edu.austral.dissis.chess.gui.GameView;
import edu.austral.dissis.chess.gui.InvalidMove;
import edu.austral.dissis.clientserver.payloads.moves.InvalidMovePayload;
import edu.austral.ingsis.clientserver.Message;
import edu.austral.ingsis.clientserver.MessageListener;
import org.jetbrains.annotations.NotNull;

public class InvalidMoveListener implements MessageListener<InvalidMovePayload> {
  private final GameView root;

  public InvalidMoveListener(GameView root) {
    this.root = root;
  }

  @Override
  public void handleMessage(@NotNull Message<InvalidMovePayload> message) {
    InvalidMovePayload invalidMovePayload = message.getPayload();

    root.handleMoveResult(new InvalidMove(invalidMovePayload.getReason()));
  }
}
