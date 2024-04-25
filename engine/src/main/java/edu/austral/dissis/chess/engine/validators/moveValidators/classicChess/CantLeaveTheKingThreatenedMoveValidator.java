package edu.austral.dissis.chess.engine.validators.moveValidators.classicChess;

import edu.austral.dissis.chess.engine.buenos.Board;
import edu.austral.dissis.chess.engine.buenos.Coordinates;
import edu.austral.dissis.chess.engine.validators.moveValidators.MoveValidator;

public class CantLeaveTheKingThreatenedMoveValidator implements MoveValidator {

  @Override
  public boolean validMove(Coordinates from, Coordinates to, Board board) {
    Board newBoard = board.getCopy();

    //        newBoard.movePiece(new MoveReferee(newBoard, from, to)); // TODO: Simular movimiento

    return !newBoard.isKingThreatened(newBoard.getPieceAt(from).getColor());
  }
}
