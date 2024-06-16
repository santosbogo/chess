package edu.austral.dissis.chess.validators.moveValidators.classicChess;

import edu.austral.dissis.chess.ChessHelper;
import edu.austral.dissis.engine.components.Board;
import edu.austral.dissis.engine.move.Mover;
import edu.austral.dissis.engine.components.Coordinates;
import edu.austral.dissis.engine.components.Piece;
import edu.austral.dissis.engine.enums.PieceColor;
import edu.austral.dissis.chess.enums.ChessPieceNames;
import edu.austral.dissis.engine.referee.MoveReferee;
import edu.austral.dissis.engine.validators.moveValidators.MoveValidator;

import java.util.Collections;

public class ShortCastleMoveValidator implements MoveValidator {
  @Override
  public boolean validMove(Coordinates from, Coordinates to, Board board) {

    if (from.getY() != to.getY() || to.getX() != 7) return false;

    // From is a king, never moved, not in check
    Piece king = board.getPieceAt(from);
    if (king == null
        || king.getPieceName() != ChessPieceNames.KING
        || !king.isFirstMove()
        || new ChessHelper().isSquareThreatened(board, from)) return false;

    PieceColor color = king.getColor();

    // In column F there is a rook, never moved
    Piece rook = board.getPieceAt(new Coordinates('H', from.getY()));
    if (rook == null || rook.getPieceName() != ChessPieceNames.ROOK || !rook.isFirstMove()) return false;

    // Can King move two valid steps to the right?
    if (new MoveReferee(color, board).isValidMove(from, new Coordinates('F', from.getY()))) {
      Mover mover = new Mover(board, Collections.emptyList(), from, new Coordinates('F', from.getY()));
      board = mover.getNextBoard();
      from = new Coordinates('F', from.getY()); // Update king coordinates
      return new MoveReferee(color, board).isValidMove(from, new Coordinates('G', from.getY()));
    }
    return false;
  }
}
