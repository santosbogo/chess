package edu.austral.dissis.chess.validators.endofgamevalidators.classic;

import edu.austral.dissis.chess.ChessHelper;
import edu.austral.dissis.engine.components.Board;
import edu.austral.dissis.engine.enums.PieceColor;
import edu.austral.dissis.engine.enums.StatusOptions;
import edu.austral.dissis.engine.validators.endofgamevalidators.EndOfGameValidator;

public class CheckmateEndGameValidator implements EndOfGameValidator {

  @Override
  public boolean isEndOfGame(PieceColor colorTurn, Board board) {
    if (new ChessHelper().isKingThreatened(board, colorTurn)) {
      return new ChessHelper().colorDoesntHaveValidMoves(board, colorTurn);
    }
    return false;
  }

  @Override
  public StatusOptions getStatus(PieceColor colorTurn, Board board) {
    if (colorTurn.equals(PieceColor.WHITE)) {
      return StatusOptions.BLACK_WIN;
    } else {
      return StatusOptions.WHITE_WIN;
    }
  }
}
