package edu.austral.dissis.chess.engine.generator.classicChessGenerator;

import edu.austral.dissis.chess.engine.components.Board;
import edu.austral.dissis.chess.engine.generator.BoardGenerator;
import edu.austral.dissis.chess.engine.generator.GameGenerator;

public class ClassicChess implements GameGenerator {

    @Override
    public Board generateBoard() {
        BoardGenerator boardGenerator = new ClassicChessBoardGenerator();
        return boardGenerator.generateBoard();
    }

    @Override
    public void generateRules() {
        
    }


}
