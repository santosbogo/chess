package edu.austral.dissis.server.payloads.game;

import edu.austral.dissis.chess.gui.BoardSize;
import edu.austral.dissis.chess.gui.ChessPiece;
import edu.austral.dissis.chess.gui.InitialState;
import edu.austral.dissis.chess.gui.PlayerColor;
import java.util.List;

public class StartingGamePayload {
  private BoardSize boardSize;
  private List<ChessPiece> pieces;
  private PlayerColor currentPlayer;

  public StartingGamePayload(InitialState initialState) {
    this.boardSize = initialState.getBoardSize();
    this.pieces = initialState.getPieces();
    this.currentPlayer = initialState.getCurrentPlayer();
  }

  public StartingGamePayload() {}

  public BoardSize getBoardSize() {
    return boardSize;
  }

  public List<ChessPiece> getPieces() {
    return pieces;
  }

  public PlayerColor getCurrentPlayer() {
    return currentPlayer;
  }
}
