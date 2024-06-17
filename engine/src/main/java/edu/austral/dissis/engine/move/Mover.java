package edu.austral.dissis.engine.move;

import edu.austral.dissis.engine.components.Board;
import edu.austral.dissis.engine.components.Coordinates;
import edu.austral.dissis.engine.components.Piece;
import edu.austral.dissis.engine.enums.PieceColor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mover {
  private final Board board;
  private final List<SpecialMover> specialMovers;
  private final Coordinates from;
  private final Coordinates to;

  public Mover(Board board, List<SpecialMover> specialMovers, Coordinates from, Coordinates to) {
    this.board = board;
    this.specialMovers = specialMovers;
    this.from = from;
    this.to = to;
  }

  public Board getNextBoard() {

    for (SpecialMover specialBoardModifier : specialMovers) {
      if (specialBoardModifier.isSpecialMove(board, from, to)) {
        return specialBoardModifier.modifyBoard(board, from, to);
      }
    }

    Map<Coordinates, Piece> pieceDistribution = new HashMap<>(board.getPieceDistribution());
    Piece piece = pieceDistribution.remove(from);
    pieceDistribution.put(
        to,
        new Piece(
            piece.getPieceName(), piece.getColor(), piece.getMoveValidators(), piece.getId()));

    return new Board(board.getSizeX(), board.getSizeY(), new HashMap<>(pieceDistribution));
  }

  public PieceColor getNextColorTurn(Board nextBoard) {
    PieceColor colorTurn = board.getColorAt(this.from);

    for (SpecialMover specialBoardModifier : specialMovers) {
      if (specialBoardModifier.isSpecialMove(board, from, to)) {
        return specialBoardModifier.getNextTurnPieceColor(colorTurn, nextBoard, to);
      }
    }
    return colorTurn.equals(PieceColor.WHITE) ? PieceColor.BLACK : PieceColor.WHITE;
  }
}
