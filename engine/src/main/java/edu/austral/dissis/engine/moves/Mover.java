package edu.austral.dissis.engine.moves;

import edu.austral.dissis.engine.components.Board;
import edu.austral.dissis.engine.components.Coordinates;
import edu.austral.dissis.engine.components.Piece;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mover {
  Board board;
  List<SpecialMover> specialMovers;

  public Mover(Board board, List<SpecialMover> specialMovers){
    this.board = board;
    this.specialMovers = specialMovers;
  }

  public Board move(Coordinates from, Coordinates to) {

    for(SpecialMover specialBoardModifier : specialMovers){
      if(specialBoardModifier.isSpecialMove(board, from, to)){
        return specialBoardModifier.modifyBoard(board, from, to);
      }
    }

    Map<Coordinates, Piece> pieceDistribution = new HashMap<>(board.getPieceDistribution());
    Piece piece = pieceDistribution.remove(from);
    pieceDistribution.put(to, new Piece(piece.getPieceName(), piece.getColor(), piece.getMoveValidators(), piece.getId()));

    return new Board(board.getXSize(), board.getYSize(), new HashMap<>(pieceDistribution));
  }

}
