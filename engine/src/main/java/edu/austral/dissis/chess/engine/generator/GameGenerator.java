package edu.austral.dissis.chess.engine.generator;

import edu.austral.dissis.chess.engine.components.Board;
import edu.austral.dissis.chess.engine.pieces.Piece;

import java.util.List;

public interface GameGenerator {

    public Board generateBoard();

    public List<Piece> generatePieces();

    public void generateRules();
}
