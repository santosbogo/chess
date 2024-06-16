package edu.austral.dissis.engine.referee;

import edu.austral.dissis.engine.components.Board;
import edu.austral.dissis.engine.components.Coordinates;
import edu.austral.dissis.engine.components.Piece;
import edu.austral.dissis.engine.enums.PieceColor;
import edu.austral.dissis.chess.enums.ChessPieceNames;
import edu.austral.dissis.engine.enums.StatusOptions;
import edu.austral.dissis.engine.validators.endOfGameValidators.EndOfGameValidator;

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
        StatusOptions status = endOfGameValidator.getStatus(colorTurn, board);
        if (status == null) continue;
        return status;
      }
    }
    return StatusOptions.NORMAL;
  }

  public boolean colorDoesntHaveValidMoves(Board board, PieceColor colorTurn) {
    for (int fromX = 1; fromX <= board.getXSize(); fromX++) {
      for (int fromY = 1; fromY <= board.getYSize(); fromY++) {
        Coordinates tempFrom = new Coordinates(fromX, fromY);
        Piece fromPiece = board.getPieceAt(tempFrom);

        if (fromPiece == null || !board.getColorAt(tempFrom).equals(colorTurn)) continue;

        for (int toX = 1; toX <= board.getXSize(); toX++) {
          for (int toY = 1; toY <= board.getYSize(); toY++) {
            Coordinates tempTo = new Coordinates(toX, toY);
            Piece toPiece = board.getPieceAt(tempTo);

            if (toPiece != null
                && (toPiece.getPieceName() == ChessPieceNames.KING
                    || toPiece.getColor().equals(colorTurn))) continue;
            if (new MoveReferee(colorTurn, board).isValidMove(tempFrom, tempTo)) {
              return false;
            }
          }
        }
      }
    }

    return true;
  }
}
