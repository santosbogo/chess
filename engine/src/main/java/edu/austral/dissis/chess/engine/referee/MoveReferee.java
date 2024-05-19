package edu.austral.dissis.chess.engine.referee;

import edu.austral.dissis.chess.engine.board.Board;
import edu.austral.dissis.chess.engine.board.Coordinates;
import edu.austral.dissis.chess.engine.enums.PieceColor;
import edu.austral.dissis.chess.engine.validators.moveValidators.MoveValidator;
import java.util.List;

public class MoveReferee {
  PieceColor colorTurn;
  Board board;

  public MoveReferee(PieceColor colorTurn, Board board) {
    this.colorTurn = colorTurn;
    this.board = board;
  }

  public boolean isValidMove(Coordinates from, Coordinates to) {
    List<MoveValidator> validators = board.getPieceAt(from).getMoveValidators();

    for (MoveValidator validator : validators) {
      if (!validator.validMove(from, to, board)) {
        return false;
      }
    }
    return true;
  }
}
