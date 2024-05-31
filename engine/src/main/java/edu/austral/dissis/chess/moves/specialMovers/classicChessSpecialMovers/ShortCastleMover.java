package edu.austral.dissis.chess.moves.specialMovers.classicChessSpecialMovers;

import edu.austral.dissis.chess.enums.ChessPieceNames;
import edu.austral.dissis.engine.components.Board;
import edu.austral.dissis.engine.components.Coordinates;
import edu.austral.dissis.engine.components.Piece;
import edu.austral.dissis.engine.move.sharedSpecialMovers.SpecialMover;

import java.util.HashMap;
import java.util.Map;

public class ShortCastleMover implements SpecialMover {

    @Override
    public boolean isSpecialMove(Board board, Coordinates from, Coordinates to) {
        return board.getPieceAt(from).getPieceName().equals(ChessPieceNames.KING)
                && to.getX() - from.getX() == 2;
    }

    @Override
    public Board modifyBoard(Board board, Coordinates from, Coordinates to) {
        Map<Coordinates, Piece> pieceDistribution = new HashMap<>(board.getPieceDistribution());

        Piece king = pieceDistribution.remove(from);
        pieceDistribution.put(to, king);

        Piece rook = pieceDistribution.remove(new Coordinates(to.getX() + 1, to.getY()));
        pieceDistribution.put(new Coordinates(to.getX() - 1, to.getY()), new Piece(ChessPieceNames.ROOK, rook.getColor(), rook.getMoveValidators(), rook.getId()));

        return new Board(board.getXSize(), board.getYSize(), new HashMap<>(pieceDistribution));
    }
}
