package edu.austral.dissis.chess.engine.validators.endOfGameValidators.classicChess;

import edu.austral.dissis.chess.engine.components.Board;
import edu.austral.dissis.chess.engine.enums.PieceColor;
import edu.austral.dissis.chess.engine.enums.StatusOptions;
import edu.austral.dissis.chess.engine.referee.StatusReferee;
import edu.austral.dissis.chess.engine.validators.endOfGameValidators.EndOfGameValidator;

public class CheckmateEndGameValidator implements EndOfGameValidator {

  @Override
  public boolean isEndOfGame(PieceColor colorTurn, Board board) {
    if (board.isKingThreatened(colorTurn)) {
     boolean result = new StatusReferee().colorDoesntHaveValidMoves(board, colorTurn);
      return result;
    }
    return false;
  }

  @Override
  public StatusOptions getStatus(PieceColor colorTurn, Board board) {
    if (isEndOfGame(colorTurn, board)) {
      if (colorTurn.equals(PieceColor.WHITE)) return StatusOptions.BLACK_CHECKMATE;
      else return StatusOptions.WHITE_CHECKMATE;
    }
    return null;
  }
}
