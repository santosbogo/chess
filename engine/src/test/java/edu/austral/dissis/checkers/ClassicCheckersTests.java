package edu.austral.dissis.checkers;

import edu.austral.dissis.checkers.enums.CheckersPieceNames;
import edu.austral.dissis.checkers.games.ClassicCheckers;
import edu.austral.dissis.chess.enums.ChessStatusOptions;
import edu.austral.dissis.engine.Game;
import edu.austral.dissis.engine.components.Board;
import edu.austral.dissis.engine.components.Coordinates;
import edu.austral.dissis.engine.enums.PieceColor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClassicCheckersTests {

    @Test
    public void testStartingBoard() {
        Game game = new ClassicCheckers().generateGame();
        Board board = game.getBoard();

//        for (int i = 1; i <= 8; i++) {
//            Assertions.assertEquals(PieceColor.BLACK, board.getColorAt(new Coordinates(i, 8)));
//            Assertions.assertEquals(PieceColor.BLACK, board.getColorAt(new Coordinates(i, 7)));
//        }
//
//        for (int i = 1; i <= 8; i++) {
//            Assertions.assertEquals(PieceColor.WHITE, board.getColorAt(new Coordinates(i, 2)));
//            Assertions.assertEquals(PieceColor.WHITE, board.getColorAt(new Coordinates(i, 1)));
//        }

//        Assertions.assertEquals(PieceName.CHECKER, board.getPieceAt(new Coordinates('A', 1)).getPieceName());

        game.playTurn(new Coordinates('A', 3), new Coordinates('B', 4));
        game.playTurn(new Coordinates('B', 6), new Coordinates('A', 5));
        game.playTurn(new Coordinates('B', 4), new Coordinates('C', 5));

        game.playTurn(new Coordinates('A', 5), new Coordinates('B', 6));

        System.out.println(game.getBoard());

    }

    @Test
    public void moveChecker() {
        Game game = new ClassicCheckers().generateGame();

        assertEquals(
                ChessStatusOptions.NORMAL, game.playTurn(new Coordinates('A', 3), new Coordinates('B', 4)));
        assertEquals(
                ChessStatusOptions.NORMAL, game.playTurn(new Coordinates('B', 6), new Coordinates('A', 5)));
        assertEquals(
                ChessStatusOptions.NORMAL, game.playTurn(new Coordinates('A', 3), new Coordinates('B', 4)));
        assertEquals(
                ChessStatusOptions.NORMAL, game.playTurn(new Coordinates('B', 6), new Coordinates('A', 5)));

        assertEquals(
                CheckersPieceNames.PAWN, game.getBoard().getPieceAt(new Coordinates('B', 4)).getPieceName());
        assertEquals(PieceColor.WHITE, game.getBoard().getColorAt(new Coordinates('B', 4)));
        assertEquals(
                CheckersPieceNames.PAWN, game.getBoard().getPieceAt(new Coordinates('A', 5)).getPieceName());
        assertEquals(PieceColor.BLACK, game.getBoard().getColorAt(new Coordinates('A', 5)));
    }
}
