package edu.austral.dissis.clientserver.listeners;

public class ClientConnectionListener
    implements edu.austral.ingsis.clientserver.ClientConnectionListener {
  @Override
  public void handleConnection() {
    System.out.println("Client connected!");
  }

  @Override
  public void handleConnectionClosed() {
    System.out.println("Client disconnected!");
  }
}
