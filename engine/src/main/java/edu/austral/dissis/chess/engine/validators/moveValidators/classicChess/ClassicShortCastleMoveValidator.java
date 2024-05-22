package edu.austral.dissis.chess.engine.validators.moveValidators.classicChess;

import edu.austral.dissis.chess.engine.components.Board;
import edu.austral.dissis.chess.engine.components.BoardModifier;
import edu.austral.dissis.chess.engine.components.Coordinates;
import edu.austral.dissis.chess.engine.components.Piece;
import edu.austral.dissis.chess.engine.enums.PieceColor;
import edu.austral.dissis.chess.engine.enums.PieceName;
import edu.austral.dissis.chess.engine.referee.MoveReferee;
import edu.austral.dissis.chess.engine.validators.moveValidators.MoveValidator;

public class ClassicShortCastleMoveValidator implements MoveValidator {
  @Override
  public boolean validMove(Coordinates from, Coordinates to, Board board) {

    if (from.getY() != to.getY() || to.getX() != 7) return false;

    // From is a king, never moved, not in check
    Piece king = board.getPieceAt(from);
    if (king == null
        || king.getPieceName() != PieceName.KING
        || !king.isFirstMove()
        || board.isSquareThreatened(from)) return false;

    PieceColor color = king.getColor();

    // In column F there is a rook, never moved
    Piece rook = board.getPieceAt(new Coordinates('H', from.getY()));
    if (rook == null || rook.getPieceName() != PieceName.ROOK || !rook.isFirstMove()) return false;

    // Can King move two valid steps to the right?
    if (new MoveReferee(color, board).isValidMove(from, new Coordinates('F', from.getY()))) {
      BoardModifier boardModifier = new BoardModifier(board);
      board = boardModifier.move(from, new Coordinates('F', from.getY()));
      from = new Coordinates('F', from.getY()); // Update king coordinates
      return new MoveReferee(color, board).isValidMove(from, new Coordinates('G', from.getY()));
    }
    return false;
  }
}
