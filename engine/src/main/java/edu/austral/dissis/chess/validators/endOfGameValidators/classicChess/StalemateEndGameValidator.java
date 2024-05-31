package edu.austral.dissis.chess.validators.endOfGameValidators.classicChess;

import edu.austral.dissis.engine.components.Board;
import edu.austral.dissis.engine.enums.PieceColor;
import edu.austral.dissis.chess.enums.ChessStatusOptions;
import edu.austral.dissis.engine.referee.StatusReferee;
import edu.austral.dissis.engine.validators.endOfGameValidators.EndOfGameValidator;

public class StalemateEndGameValidator implements EndOfGameValidator {

  @Override
  public boolean isEndOfGame(PieceColor colorTurn, Board board) {
    return new StatusReferee().colorDoesntHaveValidMoves(board, colorTurn);
  }

  @Override
  public ChessStatusOptions getStatus(PieceColor colorTurn, Board board) {
    if (isEndOfGame(colorTurn, board)) {
      return ChessStatusOptions.STALEMATE;
    }
    return null;
  }
}
