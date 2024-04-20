package edu.austral.dissis.chess.engine.validators.classicChessMoveValidators;

import edu.austral.dissis.chess.engine.components.Board;
import edu.austral.dissis.chess.engine.coordinates.Coordinates;
import edu.austral.dissis.chess.engine.enums.PieceColor;
import edu.austral.dissis.chess.engine.pieces.Piece;
import edu.austral.dissis.chess.engine.validators.basicMovesValidators.ClearPathValidator;
import edu.austral.dissis.chess.engine.validators.MoveValidator;

public class ClassicChessCastleMoveValidator implements MoveValidator {
    private final PieceColor pieceColor;
    private Board board;

    public ClassicChessCastleMoveValidator(PieceColor pieceColor){
        this.pieceColor = pieceColor;
    }

    @Override
    public boolean validMove(Coordinates from, Coordinates to) {
//        Board board FIXME de donde saco el board?? Va aca o en el constructor???

        int kingRow = pieceColor == PieceColor.WHITE ? 1 : 8;

        if (to.getX() == 'G' && to.getY() == kingRow)
            return canCastleKingSide(from, to);
        else if (to.getX() == 'C' && to.getY() == kingRow)
            return canCastleQueenSide(from, to);

        return false;
    }

    private boolean canCastleKingSide(Coordinates from, Coordinates to){
//        TODO: Verificar que las casillas por las que pasa el rey no esten en amenazadas
        Piece kingRook = board.getPieceAt(new Coordinates(to.getX(), to.getY() + 1));
        return canCastle(from, to, kingRook);
    }

    private boolean canCastleQueenSide(Coordinates from, Coordinates to){
//        TODO: Verificar que las casillas por las que pasa el rey no esten en amenazadas
        ClearPathValidator clearPathValidator = new ClearPathValidator();
        Piece queenRook = board.getPieceAt(new Coordinates(to.getX(), to.getY() - 2));
        return canCastle(from, to, queenRook);
    }

    private boolean canCastle(Coordinates from, Coordinates to, Piece kingRook) {
        ClearPathValidator clearPathValidator = new ClearPathValidator();
        Piece king = board.getPieceAt(from);

        if(kingRook == null)
            return false;
        if (!kingRook.isFirstMove() && !king.isFirstMove())
            return false;
        if(!clearPathValidator.validMove(from, to))
            return false;

        return true;
    }



}
