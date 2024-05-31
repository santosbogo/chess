package edu.austral.dissis.checkers;

import edu.austral.dissis.checkers.enums.CheckersPieceNames;
import edu.austral.dissis.checkers.games.ClassicCheckers;
import edu.austral.dissis.chess.enums.ChessStatusOptions;
import edu.austral.dissis.engine.Game;
import edu.austral.dissis.engine.components.Coordinates;
import edu.austral.dissis.engine.enums.PieceColor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClassicCheckersValidMovesTests {

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
        CheckersPieceNames.CHECKER, game.getBoard().getPieceAt(new Coordinates('B', 4)).getPieceName());
    assertEquals(PieceColor.WHITE, game.getBoard().getColorAt(new Coordinates('B', 4)));
    assertEquals(
        CheckersPieceNames.CHECKER, game.getBoard().getPieceAt(new Coordinates('A', 5)).getPieceName());
    assertEquals(PieceColor.BLACK, game.getBoard().getColorAt(new Coordinates('A', 5)));
  }

}
