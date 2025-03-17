package edu.austral.dissis.clientserver.payloads.game;

import edu.austral.dissis.chess.gui.PlayerColor;

public class GameOverPayload {
  private PlayerColor winner;

  public GameOverPayload(PlayerColor winner) {
    this.winner = winner;
  }

  public GameOverPayload() {}

  public PlayerColor getWinner() {
    return winner;
  }
}
