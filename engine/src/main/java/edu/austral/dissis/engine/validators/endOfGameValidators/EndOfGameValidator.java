package edu.austral.dissis.engine.validators.endOfGameValidators;

import edu.austral.dissis.engine.components.Board;
import edu.austral.dissis.engine.enums.PieceColor;
import edu.austral.dissis.chess.enums.StatusOptions;

public interface EndOfGameValidator {

  boolean isEndOfGame(PieceColor colorTurn, Board board);

  StatusOptions getStatus(PieceColor colorTurn, Board board);
}
