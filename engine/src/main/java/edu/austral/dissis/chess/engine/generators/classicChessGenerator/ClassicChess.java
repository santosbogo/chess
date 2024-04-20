package edu.austral.dissis.chess.engine.generators.classicChessGenerator;

import edu.austral.dissis.chess.engine.components.Board;
import edu.austral.dissis.chess.engine.coordinates.Rules;
import edu.austral.dissis.chess.engine.coordinates.Player;
import edu.austral.dissis.chess.engine.enums.PieceColor;
import edu.austral.dissis.chess.engine.components.Play;
import edu.austral.dissis.chess.engine.generators.BoardGenerator;
import edu.austral.dissis.chess.engine.generators.GameGenerator;

public class ClassicChess implements GameGenerator {
    private final Player whitePlayer;
    private final Player blackPlayer;

    public ClassicChess() {
        this.whitePlayer = new Player(PieceColor.WHITE);
        this.blackPlayer = new Player(PieceColor.BLACK);
    }

    @Override
    public void play(){
        Play play = new Play(generateBoard(), generateRules(), whitePlayer, blackPlayer);
    }

    @Override
    public Board generateBoard() {
        BoardGenerator boardGenerator = new ClassicChessBoardGenerator();
        return boardGenerator.generateBoard();
    }

    @Override
    public Rules generateRules() {
        return null;
    }


}
