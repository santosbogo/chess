package edu.austral.dissis.checkers.validators.endofgamevalidators.classic;

import edu.austral.dissis.engine.components.Board;
import edu.austral.dissis.engine.components.Coordinates;
import edu.austral.dissis.engine.enums.PieceColor;
import edu.austral.dissis.engine.enums.StatusOptions;
import edu.austral.dissis.engine.validators.endofgamevalidators.EndOfGameValidator;

public class EatAllPiecesCheckmateEndOfGameValidator implements EndOfGameValidator {
  @Override
  public boolean isEndOfGame(PieceColor colorTurn, Board board) {
    return colorDoesntHavePieces(colorTurn, board);
  }

  @Override
  public StatusOptions getStatus(PieceColor colorTurn, Board board) {
    if (colorTurn.equals(PieceColor.WHITE)) {
      return StatusOptions.BLACK_WIN;
    } else {
      return StatusOptions.WHITE_WIN;
    }
  }

  private boolean colorDoesntHavePieces(PieceColor color, Board board) {
    for (int x = 1; x <= board.getSizeX(); x++) {
      for (int y = 1; y <= board.getSizeY(); y++) {
        Coordinates coordinates = new Coordinates(x, y);
        if (board.isEmptySquare(coordinates)) {
          continue;
        }
        if (board.getColorAt(coordinates).equals(color)) {
          return false;
        }
      }
    }
    return true;
  }
}
