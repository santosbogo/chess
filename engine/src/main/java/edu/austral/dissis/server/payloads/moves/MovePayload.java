package edu.austral.dissis.server.payloads.moves;

import edu.austral.dissis.chess.gui.Move;

public class MovePayload {
  private String clientId;
  Move move;

  public MovePayload() {}

  public MovePayload(Move move, String clientId) {
    this.move = move;
    this.clientId = clientId;
  }

  public String getClientId() {
    return this.clientId;
  }

  public Move getMove() {
    return this.move;
  }
}
