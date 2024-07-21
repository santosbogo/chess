package edu.austral.dissis.server.listeners.moves;

import edu.austral.dissis.server.ServerEngine;
import edu.austral.dissis.server.payloads.moves.UndoPayload;
import edu.austral.ingsis.clientserver.Message;
import edu.austral.ingsis.clientserver.MessageListener;
import org.jetbrains.annotations.NotNull;

public class UndoListener implements MessageListener<UndoPayload> {
  @Override
  public void handleMessage(@NotNull Message<UndoPayload> message) {
    UndoPayload undoPayload = message.getPayload();
    String clientId = undoPayload.getClientId();
    ServerEngine.handleUndo(clientId);
  }
}
