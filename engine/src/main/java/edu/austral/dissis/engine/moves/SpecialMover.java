package edu.austral.dissis.engine.moves;

import edu.austral.dissis.engine.components.Board;
import edu.austral.dissis.engine.components.Coordinates;

public interface SpecialMover {

    public boolean isSpecialMove(Board board, Coordinates from, Coordinates to);

    public Board modifyBoard(Board board, Coordinates from, Coordinates to);
}
