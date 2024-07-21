package edu.austral.dissis.server.payloads.moves;

public class InvalidMovePayload {
  String reason;

  public InvalidMovePayload() {}

  public InvalidMovePayload(String reason) {
    this.reason = reason;
  }

  public String getReason() {
    return reason;
  }
}
