package edu.austral.dissis.chess.engine.generator;

import edu.austral.dissis.chess.engine.components.Board;
import edu.austral.dissis.chess.engine.coordinates.Coordinates;
import edu.austral.dissis.chess.engine.pieces.Piece;

import java.util.HashMap;

public interface BoardGenerator {
    HashMap<Coordinates, Piece> pieces = new HashMap<>();

    public Board generateBoard();
}
