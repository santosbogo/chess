package edu.austral.dissis.chess.engine.validators.gameValidators;

import edu.austral.dissis.chess.engine.components.Board;
import edu.austral.dissis.chess.engine.coordinates.Player;
import edu.austral.dissis.chess.engine.coordinates.Coordinates;
import edu.austral.dissis.chess.engine.coordinates.referee.MoveReferee;

import java.util.HashMap;

public class CheckmateStatus implements GameStatus{
    @Override
    public boolean gameStatus(Player playerTurn, Board board){
        Coordinates kingCoordinates = board.getKingLocation(playerTurn.getColor());

        if (isCheck(playerTurn, board, kingCoordinates))
            if (isCheckmate(playerTurn, board, kingCoordinates))
                return true;

        return false;
    }

    private boolean isCheckmate(Player playerTurn, Board board, Coordinates kingCoordinates){
        for (int fromX = 1; fromX <= board.getXSize(); fromX++) {
            for (int fromY = 1; fromY <= board.getYSize(); fromY++) {

                Coordinates tempFrom = new Coordinates(fromX, fromY);

                if (!board.isEmptySquare(tempFrom)  && board.getPieceAt(tempFrom).getColor().equals(playerTurn.getColor())){
                    for (int toX = 1; toX <= board.getXSize(); toX++) {
                        for (int toY = 1; toY <= board.getYSize(); toY++) {

                            Coordinates tempTo = new Coordinates(toX, toY);

                            if (new MoveReferee(board, tempFrom, tempTo).isValid() && !isCheck(playerTurn, board, kingCoordinates)) {
                                Board newBoard = new Board(board.getXSize(), board.getYSize(), new HashMap<>(board.getPieceDistribution()));
                                newBoard.movePiece(tempFrom, tempTo);
                                return false;
                                //TODO: HAY QUE SIMULAR UN MOVIMIENTO PARA CADDA PIEZA Y VER SI CON ESE MOVIMIENTO SE SALE DEL JAQUE
                            }
                        }
                    }
                }
                if (new MoveReferee(board, kingCoordinates, next).isValid() && !isCheck(playerTurn, board, next)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isCheck(Player playerTurn, Board board, Coordinates kingCoordinates) {
        return board.isSquareThreatened(kingCoordinates);
    }

}
