package edu.austral.dissis.chess.engine.coordinates.referee;

import edu.austral.dissis.chess.engine.components.Board;
import edu.austral.dissis.chess.engine.coordinates.Player;

public class RuleReferee {
    Board board;
    Player playerTurn;

    public RuleReferee(Board board, Player playerTurn) {
        this.board = board;
        this.playerTurn = playerTurn;
    }

    public boolean isValid() {
        return false;
    }

    public void iterateValidators() {
    }
}
