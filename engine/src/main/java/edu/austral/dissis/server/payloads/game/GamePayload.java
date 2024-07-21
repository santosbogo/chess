package edu.austral.dissis.server.payloads.game;

import edu.austral.dissis.chess.gui.ChessPiece;
import edu.austral.dissis.chess.gui.PlayerColor;
import edu.austral.dissis.chess.gui.UndoState;
import java.util.List;

public class GamePayload {
  private List<ChessPiece> pieces;
  private PlayerColor currentPlayer;
  private UndoState undoState;

  public GamePayload() {}

  public GamePayload(List<ChessPiece> pieces, PlayerColor currentPlayer, UndoState undoState) {
    this.pieces = pieces;
    this.currentPlayer = currentPlayer;
    this.undoState = undoState;
  }

  public List<ChessPiece> getPieces() {
    return pieces;
  }

  public PlayerColor getCurrentPlayer() {
    return currentPlayer;
  }

  public UndoState getUndoState() {
    return undoState;
  }
}
