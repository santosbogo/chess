package edu.austral.dissis.chess.engine.boards;

import edu.austral.dissis.chess.engine.coordinates.Coordinates;
import edu.austral.dissis.chess.engine.enums.PieceColor;
import edu.austral.dissis.chess.engine.enums.StatusInfo;
import edu.austral.dissis.chess.engine.game.Status;
import edu.austral.dissis.chess.engine.moves.Move;
import edu.austral.dissis.chess.engine.pieces.King;
import edu.austral.dissis.chess.engine.pieces.Pawn;
import edu.austral.dissis.chess.engine.pieces.Piece;
import edu.austral.dissis.chess.engine.pieces.Rook;

import java.util.HashMap;
import java.util.Map;

public class Board{
    private final Map<Coordinates, Piece> board;
    private final int xSize;
    private final int ySize;
    private final Status status;

    public Board(int xSize, int ySize, Map<Coordinates, Piece> board, Status status) {
        this.board = board;
        this.xSize = xSize;
        this.ySize = ySize;
        this.status = status;
    }

    public Board movePiece(Move move) {
        if (move.getPiece() instanceof King && ((King) move.getPiece()).isCastlingMove(move)) {
            // Move both the king and the rook
            // Update the board accordingly
        } else {
            Coordinates from = move.getFrom();
            Coordinates to = move.getTo();

            if (!isEmptySquare(to)) {
                getPieceAt(to).kill();
                setPiece(move.getPiece(), to);
            }

            setPiece(move.getPiece(), to);
            removePiece(from);
        }

        return this;
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
        for (int x = 1; x <= getXSize(); x++)
            for (int y = 1; y <= getYSize(); y++) {
                Coordinates tempEnemyCoordinates = new Coordinates(x, y);
                Piece enemyPiece = getPieceAt(tempEnemyCoordinates);

                if (!isEmptySquare(tempEnemyCoordinates)
                        && enemyPiece.getColor() != getColorAt(coordinates)
                        && new Move(this, tempEnemyCoordinates, coordinates).isMoveValid()) {

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

                if (tempPiece instanceof King && tempPiece.getColor() == color)
                    return tempCoordinates;
            }
        return null;
    }

    public PieceColor getColorAt(Coordinates coordinates) {
        return getPieceAt(coordinates).getColor();
    }

    public void setPiece(Piece piece, Coordinates coordinates) {
        if (piece instanceof Pawn)
            ((Pawn) piece).alreadyMoved();
        if (piece instanceof King)
            ((King) piece).alreadyMoved();
        if (piece instanceof Rook)
            ((Rook) piece).alreadyMoved();

        board.put(coordinates, piece);
    }

    public void removePiece(Coordinates coordinates) {
        board.remove(coordinates);
    }

    public int getXSize() {
        return xSize;
    }

    public int getYSize() {
        return ySize;
    }

    public Map<Coordinates, Piece> getBoard() {
        return board;
    }

    public Status getStatus() {
        return status;
    }

    public StatusInfo getStatusInfo() {
        return status.getStatusInfo();
    }
}