package edu.austral.dissis.chess.engine.boards;

import edu.austral.dissis.chess.engine.coordinates.Coordinate;
import edu.austral.dissis.chess.engine.coordinates.RectangularCoordinate;
import edu.austral.dissis.chess.engine.pieces.Piece;

import java.util.HashMap;
import java.util.Map;

public class RectangularBoard implements Board{
    private final Map<Coordinate, BoardSquare> board;
    private int xSize, ySize;

    public RectangularBoard(int xSize, int ySize) {
        this.board = new HashMap<>();
        this.xSize = xSize;
        this.ySize = ySize;
    }

    private RectangularBoard(HashMap<Coordinate, BoardSquare> board) {
        this.board = new HashMap<>(board);
    }

    public Piece getPieceAt(Coordinate coordinates) {
        return board.get(coordinates).getPiece();
    }

    @Override
    public Board move(Coordinate from, Coordinate to) {
        HashMap<Coordinate, BoardSquare> newBoard = new HashMap<>(this.board);
        Piece piece = getPieceAt(from);
        newBoard.put(from, new BoardSquare(null));
        newBoard.put(to, new BoardSquare(piece));

        return new RectangularBoard(newBoard);
    }

    @Override
    public void setStartingPosition() {

    }

    public void checkBounds(RectangularCoordinate coordinates){
        if(coordinates.getX() >= 1 && coordinates.getX() <= xSize && coordinates.getY() >= 1 && coordinates.getY() <= ySize) {
            return;
        }
        else throw new IllegalArgumentException("Coordinates out of bounds");
    }
}