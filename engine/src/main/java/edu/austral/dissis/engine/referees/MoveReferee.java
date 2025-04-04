package edu.austral.dissis.engine.referees;

import edu.austral.dissis.engine.components.Board;
import edu.austral.dissis.engine.components.Coordinates;
import edu.austral.dissis.engine.enums.PieceColor;
import edu.austral.dissis.engine.validators.movevalidators.MoveValidator;
import java.util.List;

public class MoveReferee {
  final PieceColor colorTurn;
  final Board board;

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
