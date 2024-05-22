package edu.austral.dissis.chess.engine.validators.endOfGameValidators;

import edu.austral.dissis.chess.engine.components.Board;
import edu.austral.dissis.chess.engine.enums.PieceColor;
import edu.austral.dissis.chess.engine.enums.StatusOptions;

public interface EndOfGameValidator {

  boolean isEndOfGame(PieceColor colorTurn, Board board);

  StatusOptions getStatus(PieceColor colorTurn, Board board);
}
