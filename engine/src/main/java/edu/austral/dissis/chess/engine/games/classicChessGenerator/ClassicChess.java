package edu.austral.dissis.chess.engine.games.classicChessGenerator;

import edu.austral.dissis.chess.engine.buenos.Board;
import edu.austral.dissis.chess.engine.buenos.Player;
import edu.austral.dissis.chess.engine.enums.PieceColor;
import edu.austral.dissis.chess.engine.components.Play;
import edu.austral.dissis.chess.engine.games.BoardGenerator;
import edu.austral.dissis.chess.engine.games.GameGenerator;

public class ClassicChess implements GameGenerator {
    private final Player whitePlayer;
    private final Player blackPlayer;

    public ClassicChess() {
        this.whitePlayer = new Player(PieceColor.WHITE);
        this.blackPlayer = new Player(PieceColor.BLACK);
    }

    @Override
    public void play(){
        Play play = new Play(generateBoard(), whitePlayer, blackPlayer);
    }

    @Override
    public Board generateBoard() {
        BoardGenerator boardGenerator = new ClassicChessBoardGenerator();
        return boardGenerator.generateBoard();
    }


}
