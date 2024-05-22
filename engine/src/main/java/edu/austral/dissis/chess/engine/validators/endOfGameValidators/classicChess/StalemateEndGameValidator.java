package edu.austral.dissis.chess.engine.validators.endOfGameValidators.classicChess;

import edu.austral.dissis.chess.engine.components.Board;
import edu.austral.dissis.chess.engine.enums.PieceColor;
import edu.austral.dissis.chess.engine.enums.StatusOptions;
import edu.austral.dissis.chess.engine.referee.StatusReferee;
import edu.austral.dissis.chess.engine.validators.endOfGameValidators.EndOfGameValidator;

public class StalemateEndGameValidator implements EndOfGameValidator {

  @Override
  public boolean isEndOfGame(PieceColor colorTurn, Board board) {
    return new StatusReferee().colorDoesntHaveValidMoves(board, colorTurn);
  }

  @Override
  public StatusOptions getStatus(PieceColor colorTurn, Board board) {
    if (isEndOfGame(colorTurn, board)) {
      return StatusOptions.STALEMATE;
    }
    return null;
  }
}
