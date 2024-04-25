package edu.austral.dissis.chess.engine.validators.moveValidators.classicChess;

import edu.austral.dissis.chess.engine.buenos.Board;
import edu.austral.dissis.chess.engine.buenos.Coordinates;
import edu.austral.dissis.chess.engine.buenos.Piece;
import edu.austral.dissis.chess.engine.enums.PieceColor;
import edu.austral.dissis.chess.engine.validators.moveValidators.MoveValidator;

public class ClassicCastleMoveValidator implements MoveValidator {
  private final PieceColor pieceColor;

  public ClassicCastleMoveValidator(PieceColor pieceColor) {
    this.pieceColor = pieceColor;
  }

  @Override
  public boolean validMove(Coordinates from, Coordinates to, Board board) {
    int row = pieceColor == PieceColor.WHITE ? 1 : 8;

    if (to.getX() == 7 && to.getY() == row) return canCastleKingSide(from, to, board);
    else if (to.getX() == 3 && to.getY() == row) return canCastleQueenSide(from, to, board);

    return false;
  }

  private boolean canCastleKingSide(Coordinates from, Coordinates to, Board board) {

    if (board.isSquareThreatened(new Coordinates(6, to.getY()))) return false;

    Piece kingRook = board.getPieceAt(new Coordinates(to.getX(), to.getY() + 1));
    return canCastle(from, to, kingRook, board);
  }

  private boolean canCastleQueenSide(Coordinates from, Coordinates to, Board board) {

    if (board.isSquareThreatened(new Coordinates(4, to.getY()))) return false;

    ClearPathValidator clearPathValidator = new ClearPathValidator();
    Piece queenRook = board.getPieceAt(new Coordinates(to.getX(), to.getY() - 2));
    return canCastle(from, to, queenRook, board);
  }

  private boolean canCastle(Coordinates from, Coordinates to, Piece kingRook, Board board) {
    ClearPathValidator clearPathValidator = new ClearPathValidator();
    Piece king = board.getPieceAt(from);

    if (kingRook == null) return false;
    if (!kingRook.isFirstMove() && !king.isFirstMove()) return false;
    if (!clearPathValidator.validMove(from, to, board)) return false;

    return true;
  }
}
