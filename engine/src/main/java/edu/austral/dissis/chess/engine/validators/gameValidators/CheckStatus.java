package edu.austral.dissis.chess.engine.validators.gameValidators;

import edu.austral.dissis.chess.engine.components.Board;
import edu.austral.dissis.chess.engine.coordinates.Player;
import edu.austral.dissis.chess.engine.coordinates.Coordinates;

public class CheckStatus implements GameStatus{

    @Override
    public boolean gameStatus(Player playerTurn, Board board) {
        return isCheck(playerTurn, board);
    }

    private boolean isCheck(Player playerTurn, Board board) {
        Coordinates kingCoordinates = board.getKingLocation(playerTurn.getColor());
        return board.isSquareThreatened(kingCoordinates);
    }
}
