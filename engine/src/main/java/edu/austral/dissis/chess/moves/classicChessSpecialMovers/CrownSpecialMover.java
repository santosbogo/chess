package edu.austral.dissis.chess.moves.classicChessSpecialMovers;

import edu.austral.dissis.chess.enums.ChessPieceNames;
import edu.austral.dissis.engine.components.Board;
import edu.austral.dissis.engine.components.Coordinates;
import edu.austral.dissis.engine.components.Piece;
import edu.austral.dissis.engine.enums.PieceColor;
import edu.austral.dissis.engine.move.SpecialMover;

import java.util.HashMap;
import java.util.Map;

public class CrownSpecialMover implements SpecialMover {
    private final Piece piece;

    public CrownSpecialMover(Piece piece) {
        this.piece = piece;
    }

    @Override
    public boolean isSpecialMove(Board board, Coordinates from, Coordinates to) {
        return board.getPieceAt(from).getPieceName().equals(ChessPieceNames.PAWN) && (Math.abs(to.getY() - board.getYSize()) == 0);
    }

    @Override
    public Board modifyBoard(Board board, Coordinates from, Coordinates to) {
        Map<Coordinates, Piece> pieceDistribution = new HashMap<>(board.getPieceDistribution());

        Piece oldPiece = pieceDistribution.remove(from);
        pieceDistribution.put(to, new Piece(piece.getPieceName(), oldPiece.getColor(), piece.getMoveValidators(), oldPiece.getId()));

        return new Board(board.getXSize(), board.getYSize(), new HashMap<>(pieceDistribution));
    }

    @Override
    public PieceColor getNextTurnPieceColor(PieceColor currentTurn) {
        return currentTurn.equals(PieceColor.WHITE) ? PieceColor.BLACK : PieceColor.WHITE;
    }
}
