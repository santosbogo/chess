package edu.austral.dissis.chess;

import edu.austral.dissis.chess.enums.ChessPieceNames;
import edu.austral.dissis.engine.components.Board;
import edu.austral.dissis.engine.components.Coordinates;
import edu.austral.dissis.engine.components.Piece;
import edu.austral.dissis.engine.enums.PieceColor;
import edu.austral.dissis.engine.referees.MoveReferee;
import java.util.NoSuchElementException;

public class ChessHelper {

  public boolean isSquareThreatened(Board board, Coordinates coordinates) {
    PieceColor targetColor = board.getColorAt(coordinates);
    for (int x = 1; x <= board.getSizeX(); x++) {
      for (int y = 1; y <= board.getSizeY(); y++) {
        Coordinates from = new Coordinates(x, y);
        if (!board.getPieceDistribution().containsKey(from)
            || board.getColorAt(from).equals(targetColor)) {
          continue;
        }
        MoveReferee moveReferee = new MoveReferee(board.getColorAt(from), board);
        if (moveReferee.isValidMove(from, coordinates)) {
          return true;
        }
      }
    }
    return false;
  }

  public boolean isKingThreatened(Board board, PieceColor color) {
    return isSquareThreatened(board, getKingCoordinates(board, color));
  }

  public Coordinates getKingCoordinates(Board board, PieceColor kingColor) {
    for (int x = 1; x <= board.getSizeX(); x++) {
      for (int y = 1; y <= board.getSizeY(); y++) {
        Coordinates tempCoordinates = new Coordinates(x, y);
        Piece tempPiece = board.getPieceAt(tempCoordinates);

        if (tempPiece == null) {
          continue;
        }

        if (tempPiece.getPieceName().equals(ChessPieceNames.KING)
            && tempPiece.getColor().equals(kingColor)) {
          return tempCoordinates;
        }
      }
    }

    throw new NoSuchElementException("King not found");
  }

  public boolean colorDoesntHaveValidMoves(Board board, PieceColor colorTurn) {
    for (int fromX = 1; fromX <= board.getSizeX(); fromX++) {
      for (int fromY = 1; fromY <= board.getSizeY(); fromY++) {
        Coordinates tempFrom = new Coordinates(fromX, fromY);
        Piece fromPiece = board.getPieceAt(tempFrom);

        if (fromPiece == null || !board.getColorAt(tempFrom).equals(colorTurn)) {
          continue;
        }

        for (int toX = 1; toX <= board.getSizeX(); toX++) {
          for (int toY = 1; toY <= board.getSizeY(); toY++) {
            Coordinates tempTo = new Coordinates(toX, toY);
            Piece toPiece = board.getPieceAt(tempTo);

            if (toPiece != null
                && (toPiece.getPieceName() == ChessPieceNames.KING
                    || toPiece.getColor().equals(colorTurn))) {
              continue;
            }
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
