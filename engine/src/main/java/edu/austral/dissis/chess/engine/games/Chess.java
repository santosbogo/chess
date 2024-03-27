package edu.austral.dissis.chess.engine.games;

import edu.austral.dissis.chess.engine.boards.Board;
import edu.austral.dissis.chess.engine.coordinates.Coordinate;
import edu.austral.dissis.chess.engine.coordinates.RectangularCoordinate;
import edu.austral.dissis.chess.engine.boards.RectangularBoard;
import edu.austral.dissis.chess.engine.pieces.Piece;
import edu.austral.dissis.chess.engine.pieces.Pieces;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Chess implements Game {

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
        HashMap<Coordinate, Piece> startingPosition = new HashMap<>();

        //White pieces
        for(int i = 1; i <= 8; i++){
            startingPosition.put(new RectangularCoordinate(i, 2), new Piece(Pieces.PAWN, true));
        }
        startingPosition.put(new RectangularCoordinate(1, 2), new Piece(Pieces.ROOK, true));
        startingPosition.put(new RectangularCoordinate(8, 2), new Piece(Pieces.ROOK, true));
        startingPosition.put(new RectangularCoordinate(2, 2), new Piece(Pieces.KNIGHT, true));
        startingPosition.put(new RectangularCoordinate(7, 2), new Piece(Pieces.KNIGHT, true));
        startingPosition.put(new RectangularCoordinate(3, 2), new Piece(Pieces.BISHOP, true));
        startingPosition.put(new RectangularCoordinate(6, 2), new Piece(Pieces.BISHOP, true));
        startingPosition.put(new RectangularCoordinate(4, 2), new Piece(Pieces.QUEEN, true));
        startingPosition.put(new RectangularCoordinate(5, 2), new Piece(Pieces.KING, true));
        startingPosition.put(new RectangularCoordinate(5, 7), new Piece(Pieces.KING, false));

        //Black pieces
        for(int i = 1; i <= 8; i++){
            startingPosition.put(new RectangularCoordinate(i, 7), new Piece(Pieces.PAWN, false));
        }
        startingPosition.put(new RectangularCoordinate(1, 7), new Piece(Pieces.ROOK, false));
        startingPosition.put(new RectangularCoordinate(8, 7), new Piece(Pieces.ROOK, false));
        startingPosition.put(new RectangularCoordinate(2, 7), new Piece(Pieces.KNIGHT, false));
        startingPosition.put(new RectangularCoordinate(7, 7), new Piece(Pieces.KNIGHT, false));
        startingPosition.put(new RectangularCoordinate(3, 7), new Piece(Pieces.BISHOP, false));
        startingPosition.put(new RectangularCoordinate(6, 7), new Piece(Pieces.BISHOP, false));
        startingPosition.put(new RectangularCoordinate(4, 7), new Piece(Pieces.QUEEN, false));
        startingPosition.put(new RectangularCoordinate(5, 7), new Piece(Pieces.KING, false));

        return startingPosition;
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
