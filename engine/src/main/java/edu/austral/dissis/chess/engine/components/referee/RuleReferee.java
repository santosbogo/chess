package edu.austral.dissis.chess.engine.components.referee;

import edu.austral.dissis.chess.engine.buenos.Board;
import edu.austral.dissis.chess.engine.buenos.Player;

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
