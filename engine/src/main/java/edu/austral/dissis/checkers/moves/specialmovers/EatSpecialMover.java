package edu.austral.dissis.checkers.moves.specialmovers;

import edu.austral.dissis.checkers.CheckersHelper;
import edu.austral.dissis.engine.components.Board;
import edu.austral.dissis.engine.components.Coordinates;
import edu.austral.dissis.engine.components.Piece;
import edu.austral.dissis.engine.enums.PieceColor;
import edu.austral.dissis.engine.move.SpecialMover;
import java.util.HashMap;
import java.util.Map;

public class EatSpecialMover implements SpecialMover {

  @Override
  public boolean isSpecialMove(Board board, Coordinates from, Coordinates to) {
    return new CheckersHelper().canPieceFromCoordinateEat(from, board);
  }

  @Override
  public Board modifyBoard(Board board, Coordinates from, Coordinates to) {
    Map<Coordinates, Piece> pieceDistribution = new HashMap<>(board.getPieceDistribution());
    Piece piece = pieceDistribution.remove(from);
    pieceDistribution.remove(new CheckersHelper().getOnlyEnemyPieceBetween(from, to, board));
    pieceDistribution.put(
        to,
        new Piece(
            piece.getPieceName(), piece.getColor(), piece.getMoveValidators(), piece.getId()));

    return new Board(board.getSizeX(), board.getSizeY(), new HashMap<>(pieceDistribution));
  }

  @Override
  public PieceColor getNextTurnPieceColor(PieceColor currentTurn, Board nextBoard, Coordinates to) {
    if (new CheckersHelper().canPieceFromCoordinateEat(to, nextBoard)) {
      return currentTurn;
    }
    return currentTurn.equals(PieceColor.WHITE) ? PieceColor.BLACK : PieceColor.WHITE;
  }
}
