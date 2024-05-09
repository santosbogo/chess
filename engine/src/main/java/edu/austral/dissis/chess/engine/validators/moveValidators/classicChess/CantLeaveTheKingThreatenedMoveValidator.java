package edu.austral.dissis.chess.engine.validators.moveValidators.classicChess;

import edu.austral.dissis.chess.engine.board.Board;
import edu.austral.dissis.chess.engine.board.Coordinates;
import edu.austral.dissis.chess.engine.enums.PieceColor;
import edu.austral.dissis.chess.engine.moves.MoveSimulator;
import edu.austral.dissis.chess.engine.validators.moveValidators.MoveValidator;

public class CantLeaveTheKingThreatenedMoveValidator implements MoveValidator {

  @Override
  public boolean validMove(Coordinates from, Coordinates to, Board board) {
    PieceColor color = board.getColorAt(from);

    MoveSimulator moveSimulator = new MoveSimulator(board);
    return !moveSimulator.simulateMove(from, to).isKingThreatened(color);
  }
}
