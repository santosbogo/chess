package edu.austral.dissis.server.payloads.moves;

public class UndoPayload {
  private String clientId;

  public UndoPayload() {}

  public UndoPayload(String clientId) {
    this.clientId = clientId;
  }

  public String getClientId() {
    return this.clientId;
  }
}
