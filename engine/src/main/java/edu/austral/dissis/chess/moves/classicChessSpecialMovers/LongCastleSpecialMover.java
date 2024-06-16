package edu.austral.dissis.chess.moves.classicChessSpecialMovers;

import edu.austral.dissis.chess.enums.ChessPieceNames;
import edu.austral.dissis.engine.components.Board;
import edu.austral.dissis.engine.components.Coordinates;
import edu.austral.dissis.engine.components.Piece;
import edu.austral.dissis.engine.enums.PieceColor;
import edu.austral.dissis.engine.move.SpecialMover;

import java.util.HashMap;
import java.util.Map;

public class LongCastleSpecialMover implements SpecialMover {
    @Override
    public boolean isSpecialMove(Board board, Coordinates from, Coordinates to) {
        boolean result = board.getPieceAt(from).getPieceName().equals(ChessPieceNames.KING)
                && to.getX() - from.getX() == -2;
        return result;
    }

    @Override
    public Board modifyBoard(Board board, Coordinates from, Coordinates to) {
        Map<Coordinates, Piece> pieceDistribution = new HashMap<>(board.getPieceDistribution());

        Piece piece = pieceDistribution.remove(from);
        pieceDistribution.put(to, piece);

        Piece rook = pieceDistribution.remove(new Coordinates(to.getX() - 2, to.getY()));
        pieceDistribution.put(new Coordinates(to.getX() + 1, to.getY()), new Piece(ChessPieceNames.ROOK, rook.getColor(), rook.getMoveValidators(), rook.getId()));

        return new Board(board.getXSize(), board.getYSize(), new HashMap<>(pieceDistribution));
    }

    @Override
    public PieceColor getNextTurnPieceColor(PieceColor currentTurn, Board nextBoard, Coordinates to) {
        return currentTurn.equals(PieceColor.WHITE) ? PieceColor.BLACK : PieceColor.WHITE;
    }
}
