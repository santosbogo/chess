package edu.austral.dissis.chess.validators.endOfGameValidators.classicChess;

import edu.austral.dissis.chess.ChessHelper;
import edu.austral.dissis.engine.components.Board;
import edu.austral.dissis.engine.enums.PieceColor;
import edu.austral.dissis.chess.enums.ChessStatusOptions;
import edu.austral.dissis.engine.referee.StatusReferee;
import edu.austral.dissis.engine.validators.endOfGameValidators.EndOfGameValidator;

public class CheckmateEndGameValidator implements EndOfGameValidator {

  @Override
  public boolean isEndOfGame(PieceColor colorTurn, Board board) {
    if (new ChessHelper().isKingThreatened(board, colorTurn)) {
        return new StatusReferee().colorDoesntHaveValidMoves(board, colorTurn);
    }
    return false;
  }

  @Override
  public ChessStatusOptions getStatus(PieceColor colorTurn, Board board) {
    if (isEndOfGame(colorTurn, board)) {
      if (colorTurn.equals(PieceColor.WHITE)) return ChessStatusOptions.BLACK_CHECKMATE;
      else return ChessStatusOptions.WHITE_CHECKMATE;
    }
    return null;
  }
}
