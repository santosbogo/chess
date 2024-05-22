package edu.austral.dissis.chess.engine.components;

import edu.austral.dissis.chess.engine.enums.PieceName;
import java.util.HashMap;
import java.util.Map;

public class BoardModifier {
  Board board;

  public BoardModifier(Board board) {
    this.board = board;
  }

  public Board move(Coordinates from, Coordinates to) {
    Map<Coordinates, Piece> pieceDistribution = new HashMap<>(board.getPieceDistribution());

    if (board.getPieceAt(from).getPieceName().equals(PieceName.KING)
        && to.getX() - from.getX() == 2) {
      Piece king = pieceDistribution.remove(from);
      pieceDistribution.put(to, king);

      Piece rook = pieceDistribution.remove(new Coordinates(to.getX() + 1, to.getY()));
      pieceDistribution.put(new Coordinates(to.getX() - 1, to.getY()), new Piece(PieceName.ROOK, rook.getColor(), rook.getMoveValidators(), rook.getId()));
    }

    else if (board.getPieceAt(from).getPieceName().equals(PieceName.KING)
        && to.getX() - from.getX() == -2) {
      Piece piece = pieceDistribution.remove(from);
      pieceDistribution.put(to, piece);

      Piece rook = pieceDistribution.remove(new Coordinates(to.getX() - 2, to.getY()));
      pieceDistribution.put(new Coordinates(to.getX() + 1, to.getY()), new Piece(PieceName.ROOK, rook.getColor(), rook.getMoveValidators(), rook.getId()));
    } else {
      Piece piece = pieceDistribution.remove(from);
      pieceDistribution.put(to, new Piece(piece.getPieceName(), piece.getColor(), piece.getMoveValidators(), piece.getId()));
    }

    return new Board(board.getXSize(), board.getYSize(), new HashMap<>(pieceDistribution));
  }
}
