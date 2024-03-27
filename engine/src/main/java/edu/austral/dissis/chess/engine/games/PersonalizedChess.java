package edu.austral.dissis.chess.engine.games;

import edu.austral.dissis.chess.engine.boards.Board;
import edu.austral.dissis.chess.engine.boards.RectangularBoard;
import edu.austral.dissis.chess.engine.coordinates.Coordinate;
import edu.austral.dissis.chess.engine.coordinates.RectangularCoordinate;
import edu.austral.dissis.chess.engine.pieces.Piece;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PersonalizedChess implements Game {

    @Override
    public void start() {
        boardHistory.add(new RectangularBoard(8, 8));
    }

    @Override
    public void move(RectangularCoordinate from, RectangularCoordinate to) {
        Board currentBoard = boardHistory.getLast();
        boardHistory.add(currentBoard.move(from, to));
    }

    @Override
    public List<Board> getHistoryMoves() {
        return new ArrayList<>(boardHistory);
    }

    @Override
    public HashMap<Coordinate, Piece> startingPosition() {
        return null;
    }

    @Override
    public boolean isPossibleMove(Piece piece, Coordinate from, Coordinate to) {
        return false;
    }

    @Override
    public boolean isLegalMove(Piece piece, Coordinate from, Coordinate to) {
        return false;
    }
}
