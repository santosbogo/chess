package edu.austral.dissis.clientserver.payloads.moves;

public class RedoPayload {
  private String clientId;

  public RedoPayload() {}

  public RedoPayload(String clientId) {
    this.clientId = clientId;
  }

  public String getClientId() {
    return this.clientId;
  }
}
