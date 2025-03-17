package edu.austral.dissis.clientserver.listeners;

import static edu.austral.dissis.clientserver.Client.clientId;

import edu.austral.ingsis.clientserver.Message;
import edu.austral.ingsis.clientserver.MessageListener;
import org.jetbrains.annotations.NotNull;

public class IdListener implements MessageListener<String> {
  @Override
  public void handleMessage(@NotNull Message<String> message) {
    clientId = message.getPayload();
  }
}
