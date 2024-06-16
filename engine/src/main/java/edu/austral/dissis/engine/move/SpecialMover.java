package edu.austral.dissis.engine.move;

import edu.austral.dissis.engine.components.Board;
import edu.austral.dissis.engine.components.Coordinates;
import edu.austral.dissis.engine.enums.PieceColor;

public interface SpecialMover {

    public boolean isSpecialMove(Board board, Coordinates from, Coordinates to);

    public Board modifyBoard(Board board, Coordinates from, Coordinates to);

    public PieceColor getNextTurnPieceColor(PieceColor currentTurn, Board nextBoard, Coordinates to);
}
