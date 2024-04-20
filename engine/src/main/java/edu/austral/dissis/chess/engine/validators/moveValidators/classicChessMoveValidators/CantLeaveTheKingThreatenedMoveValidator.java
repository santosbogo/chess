package edu.austral.dissis.chess.engine.validators.moveValidators.classicChessMoveValidators;

import edu.austral.dissis.chess.engine.components.Board;
import edu.austral.dissis.chess.engine.coordinates.Coordinates;
import edu.austral.dissis.chess.engine.coordinates.referee.MoveReferee;
import edu.austral.dissis.chess.engine.validators.moveValidators.MoveValidator;

import java.util.HashMap;

public class CantLeaveTheKingThreatenedMoveValidator implements MoveValidator {

    @Override
    public boolean validMove(Coordinates from, Coordinates to) {
        //TODO: REVISAR LOGICA

        //        Board board FIXME: De donde saco el board??
        Board board = new Board(8, 8, new HashMap<>()); //FIXME: Esto es solo para que no tire error, pero no esta bien

        Board newBoard = new Board(board.getXSize(), board.getYSize(), board.getPieceDistribution());
        newBoard.movePiece(new MoveReferee(newBoard, from, to));
        return newBoard.isSquareThreatened(newBoard.getKingLocation(board.getPieceAt(from).getColor()));
    }
}
