package edu.austral.dissis.server.listeners.moves;

import edu.austral.dissis.server.ServerEngine;
import edu.austral.dissis.server.payloads.moves.RedoPayload;
import edu.austral.ingsis.clientserver.Message;
import edu.austral.ingsis.clientserver.MessageListener;
import org.jetbrains.annotations.NotNull;

public class RedoListener implements MessageListener<RedoPayload> {
  @Override
  public void handleMessage(@NotNull Message<RedoPayload> message) {
    RedoPayload redoPayload = message.getPayload();
    String clientId = redoPayload.getClientId();
    ServerEngine.handleRedo(clientId);
  }
}
