package edu.austral.dissis.server.listeners.moves;

import edu.austral.dissis.chess.gui.Move;
import edu.austral.dissis.server.ServerEngine;
import edu.austral.dissis.server.payloads.moves.MovePayload;
import edu.austral.ingsis.clientserver.Message;
import edu.austral.ingsis.clientserver.MessageListener;
import org.jetbrains.annotations.NotNull;

public class MoveListener implements MessageListener<MovePayload> {
  @Override
  public void handleMessage(@NotNull Message<MovePayload> message) {
    MovePayload movePayload = message.getPayload();
    String clientId = movePayload.getClientId();
    Move move = movePayload.getMove();
    ServerEngine.handleMove(move, clientId);
  }
}
