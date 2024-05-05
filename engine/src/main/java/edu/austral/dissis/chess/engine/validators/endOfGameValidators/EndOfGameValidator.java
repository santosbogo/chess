package edu.austral.dissis.chess.engine.validators.endOfGameValidators;

import edu.austral.dissis.chess.engine.buenos.Board;
import edu.austral.dissis.chess.engine.enums.PieceColor;

public interface EndOfGameValidator {

  boolean isEndOfGame(PieceColor colorTurn, Board board);

}
