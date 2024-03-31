package edu.austral.dissis.chess.engine.boards;

import edu.austral.dissis.chess.engine.coordinates.Coordinates;
import edu.austral.dissis.chess.engine.enums.PieceColor;
import edu.austral.dissis.chess.engine.moves.Move;
import edu.austral.dissis.chess.engine.pieces.King;
import edu.austral.dissis.chess.engine.pieces.Piece;

import java.util.HashMap;
import java.util.Map;

public class Board{
    private final Map<Coordinates, Piece> board;
    private final int xSize;
    private final int ySize;

    public Board(int xSize, int ySize, HashMap<Coordinates, Piece> startingPosition) {
        this.board = startingPosition;
        this.xSize = xSize;
        this.ySize = ySize;
    }

    public void movePiece(Move move) {
        Coordinates from = move.getFrom();
        Coordinates to = move.getTo();

        if(!move.isMoveValid())
            throw new UnsupportedOperationException("Invalid move");

        if (!isEmptySquare(to))
            capturePiece(to);

        setPiece(move.getPiece(), to);

        removeFromSquare(from);
    }

    public Piece getPieceAt(Coordinates coordinates) {
        return board.get(coordinates);
    }

    public boolean isInBounds(Coordinates coordinates) {
        return coordinates.getX() >= 1 && coordinates.getX() <= xSize && coordinates.getY() >= 1 && coordinates.getY() <= ySize;
    }

    public boolean isEmptySquare(Coordinates coordinates) {
        return !board.containsKey(coordinates);
    }

    public boolean isSquareThreatened(Coordinates coordinates) {
        for (int i = 0; i < getXSize(); i++)
            for (int j = 0; j < getYSize(); j++) {
                Coordinates tempEnemyCoordinates = new Coordinates(i, j);
                Piece enemyPiece = getPieceAt(tempEnemyCoordinates);

                if (!isEmptySquare(tempEnemyCoordinates)
                        && enemyPiece.getColor() != getColor(coordinates)
                        && new Move(this, enemyPiece, tempEnemyCoordinates, coordinates).isMoveValid()) {

                    return true; //Chequea amenaza desde casilla NO vacia, con pieza de otro color y movimiento valido
                }
            }
        return false;
    }

    public Coordinates getKingLocation(PieceColor color) {
        for (int i = 0; i < getXSize(); i++)
            for (int j = 0; j < getYSize(); j++) {
                Coordinates tempCoordinates = new Coordinates(i, j);
                Piece tempPiece = getPieceAt(tempCoordinates);

                if (tempPiece instanceof King && tempPiece.getColor() == color)
                    return tempCoordinates;
            }
        return null;
    }

    public PieceColor getColor(Coordinates coordinates) {
        return getPieceAt(coordinates).getColor();
    }

    private void capturePiece(Coordinates coordinates) {
        getPieceAt(coordinates).kill();
        board.remove(coordinates);
    }

    private void setPiece(Piece piece, Coordinates coordinates) {
        board.put(coordinates, piece);
    }

    private void removeFromSquare(Coordinates from) {
        board.remove(from);
    }

    public int getXSize() {
        return xSize;
    }

    public int getYSize() {
        return ySize;
    }
}