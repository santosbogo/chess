package edu.austral.dissis.chess.engine.board;

import edu.austral.dissis.chess.engine.buenos.Piece;

import java.util.HashMap;
import java.util.Map;

public class BoardModifier {
    Board board;

    public BoardModifier(Board board){
        this.board = board;
    }

    public Board move(Coordinates from, Coordinates to) {
        Map<Coordinates, Piece> pieceDistribution = new HashMap<>(board.getPieceDistribution());

        Piece piece = pieceDistribution.remove(from);
        pieceDistribution.put(to, piece);

        return new Board(board.getXSize(), board.getYSize(), pieceDistribution);
    }
}
