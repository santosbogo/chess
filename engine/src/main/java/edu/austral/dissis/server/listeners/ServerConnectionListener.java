package edu.austral.dissis.server.listeners;

import static edu.austral.dissis.server.ServerEngine.addPlayer;
import static edu.austral.dissis.server.ServerEngine.removePlayer;

import org.jetbrains.annotations.NotNull;

public class ServerConnectionListener
    implements edu.austral.ingsis.clientserver.ServerConnectionListener {
  @Override
  public void handleClientConnection(@NotNull String s) {
    addPlayer(s);
  }

  @Override
  public void handleClientConnectionClosed(@NotNull String s) {
    removePlayer(s);
  }
}
