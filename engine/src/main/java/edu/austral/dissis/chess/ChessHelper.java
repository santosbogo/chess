package edu.austral.dissis.chess;

import edu.austral.dissis.chess.enums.PieceName;
import edu.austral.dissis.engine.components.Board;
import edu.austral.dissis.engine.components.Coordinates;
import edu.austral.dissis.engine.components.Piece;
import edu.austral.dissis.engine.enums.PieceColor;
import edu.austral.dissis.engine.referee.MoveReferee;

import java.util.NoSuchElementException;

public class ChessHelper {

    public boolean isSquareThreatened(Board board, Coordinates coordinates) {
        PieceColor targetColor = board.getColorAt(coordinates);
        for (int x = 1; x <= board.getXSize(); x++) {
            for (int y = 1; y <= board.getYSize(); y++) {
                Coordinates from = new Coordinates(x, y);
                if (!board.getPieceDistribution().containsKey(from) || board.getColorAt(from).equals(targetColor)) continue;
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
        for (int x = 1; x <= board.getXSize(); x++)
            for (int y = 1; y <= board.getYSize(); y++) {
                Coordinates tempCoordinates = new Coordinates(x, y);
                Piece tempPiece = board.getPieceAt(tempCoordinates);

                if (tempPiece == null) continue;

                if (tempPiece.getPieceName().equals(PieceName.KING) && tempPiece.getColor().equals(kingColor))
                    return tempCoordinates;
            }

        throw new NoSuchElementException("King not found");
    }
}
