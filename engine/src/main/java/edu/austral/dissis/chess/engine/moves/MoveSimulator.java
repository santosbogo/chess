package edu.austral.dissis.chess.engine.moves;

import edu.austral.dissis.chess.engine.board.Board;
import edu.austral.dissis.chess.engine.board.BoardModifier;
import edu.austral.dissis.chess.engine.board.Coordinates;

import java.util.HashMap;

public class MoveSimulator {
    private final Board simulatedBoard;

    public MoveSimulator(Board board) {
        this.simulatedBoard = new Board(board.getXSize(), board.getYSize(), new HashMap<>(board.getPieceDistribution()));
    }

    public Board simulateMove(Coordinates from, Coordinates to) {
        BoardModifier modifier = new BoardModifier(simulatedBoard);
        return modifier.move(from, to);
    }
}
