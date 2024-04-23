package edu.austral.dissis.chess.engine.games;

import edu.austral.dissis.chess.engine.buenos.Board;

public interface GameGenerator {

    public Board generateBoard();

    public void play();
}
