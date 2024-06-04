package edu.austral.dissis.checkers;

import edu.austral.dissis.engine.components.Board;
import edu.austral.dissis.engine.components.Coordinates;
import edu.austral.dissis.engine.enums.PieceColor;

public class CheckersHelper {

    public boolean isOnlyOneOpponentPieceBetween(Coordinates from, Coordinates to, Board board){
        int piecesBetween = 0;
        PieceColor allyColor = board.getColorAt(from);
        int verticalDirection = (to.getX() - from.getX()) > 0 ? 1 : -1;
        int horizontalDirection = (to.getY() - from.getY()) > 0 ? 1 : -1;
        int distance = Math.abs(to.getX() - from.getX());

        for (int i = 1; i < distance; i++) {
            int x = from.getX() + i * verticalDirection;
            int y = from.getY() + i * horizontalDirection;
            if (!board.isEmptySquare(new Coordinates(x, y)) && board.getColorAt(new Coordinates(x, y)) != allyColor) {
                piecesBetween++;
            }
        }
        return piecesBetween == 1;
    }
}
