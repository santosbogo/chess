package edu.austral.dissis.chess.engine.components;

import edu.austral.dissis.chess.engine.coordinates.Coordinates;
import edu.austral.dissis.chess.engine.coordinates.referee.MoveReferee;
import edu.austral.dissis.chess.engine.coordinates.Piece;
import edu.austral.dissis.chess.engine.enums.PieceColor;
import edu.austral.dissis.chess.engine.enums.PieceName;

import java.util.Map;

public class Board{
    private final Map<Coordinates, Piece> pieceDistribution;
    private final int xSize;
    private final int ySize;

    public Board(int xSize, int ySize, Map<Coordinates, Piece> board) {
        this.pieceDistribution = board;
        this.xSize = xSize;
        this.ySize = ySize;
    }

    public Board movePiece(MoveReferee moveReferee) {
//        TODO: SETEAR EL PRIMER MOVIMIENTO SI ES QUE ESTA PIEZA NO SE MOVIO

        Coordinates from = moveReferee.getFrom();
        Coordinates to = moveReferee.getTo();
        setPiece(moveReferee.getPiece(), to);
        removePiece(from);

        return this;
    }

    public Piece getPieceAt(Coordinates coordinates) {
        return pieceDistribution.get(coordinates);
    }

    public boolean isInBounds(Coordinates coordinates) {
        return coordinates.getX() >= 1 && coordinates.getX() <= xSize && coordinates.getY() >= 1 && coordinates.getY() <= ySize;
    }

    public boolean isEmptySquare(Coordinates coordinates) {
        return !pieceDistribution.containsKey(coordinates);
    }

    public boolean isSquareThreatened(Coordinates coordinates) {
        for (int x = 1; x <= getXSize(); x++)
            for (int y = 1; y <= getYSize(); y++) {
                Coordinates tempEnemyCoordinates = new Coordinates(x, y);
                Piece enemyPiece = getPieceAt(tempEnemyCoordinates);

                if (!isEmptySquare(tempEnemyCoordinates)
                        && enemyPiece.getColor() != getColorAt(coordinates)
                        && new MoveReferee(this, tempEnemyCoordinates, coordinates).isValid()) {

                    return true;
                }
            }
        return false;
    }

    public Coordinates getKingLocation(PieceColor color) {
        for (int x = 1; x <= getXSize(); x++)
            for (int y = 1; y <= getYSize(); y++) {
                Coordinates tempCoordinates = new Coordinates(x, y);
                Piece tempPiece = getPieceAt(tempCoordinates);

                if (tempPiece.getPieceName() == PieceName.KING && tempPiece.getColor() == color)
                    return tempCoordinates;
            }
        return null;
    }

    public PieceColor getColorAt(Coordinates coordinates) {
        return getPieceAt(coordinates).getColor();
    }

    public void setPiece(Piece piece, Coordinates coordinates) {
        pieceDistribution.put(coordinates, piece);
    }

    public void removePiece(Coordinates coordinates) {
        pieceDistribution.remove(coordinates);
    }

    public int getXSize() {
        return xSize;
    }

    public int getYSize() {
        return ySize;
    }

    public Map<Coordinates, Piece> getPieceDistribution() {
        return pieceDistribution;
    }
}