package edu.austral.dissis.engine.referees;

import edu.austral.dissis.engine.components.Board;
import edu.austral.dissis.engine.enums.PieceColor;
import edu.austral.dissis.engine.enums.StatusOptions;
import edu.austral.dissis.engine.validators.endofgamevalidators.EndOfGameValidator;
import java.util.List;

public class StatusReferee {

  public static boolean isEndOfGame(
      PieceColor colorTurn, Board board, List<EndOfGameValidator> endOfGameValidators) {
    for (EndOfGameValidator endOfGameValidator : endOfGameValidators) {
      if (endOfGameValidator.isEndOfGame(colorTurn, board)) {
        return true;
      }
    }
    return false;
  }

  public static StatusOptions getStatus(
      PieceColor colorTurn, Board board, List<EndOfGameValidator> endOfGameValidators) {
    if (isEndOfGame(colorTurn, board, endOfGameValidators)) {
      for (EndOfGameValidator endOfGameValidator : endOfGameValidators) {
        if (endOfGameValidator.isEndOfGame(colorTurn, board)) {
          return endOfGameValidator.getStatus(colorTurn, board);
        }
      }
    }
    return StatusOptions.NORMAL;
  }
}
