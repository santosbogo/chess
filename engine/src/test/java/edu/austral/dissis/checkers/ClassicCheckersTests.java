package edu.austral.dissis.checkers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.austral.dissis.checkers.enums.CheckersPieceNames;
import edu.austral.dissis.checkers.games.classic.ClassicCheckers;
import edu.austral.dissis.engine.components.Board;
import edu.austral.dissis.engine.components.Coordinates;
import edu.austral.dissis.engine.components.Game;
import edu.austral.dissis.engine.enums.PieceColor;
import edu.austral.dissis.engine.enums.StatusOptions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ClassicCheckersTests {

  @Test
  public void testStartingBoard() {
    Game game = new ClassicCheckers().generateGame();
    Board board = game.getBoard();

    for (int i = 1; i <= 8; i += 2) {
      Assertions.assertEquals(PieceColor.BLACK, board.getColorAt(new Coordinates(i + 1, 8)));
      Assertions.assertEquals(
          CheckersPieceNames.PAWN, board.getPieceAt(new Coordinates(i + 1, 8)).getPieceName());

      Assertions.assertEquals(PieceColor.BLACK, board.getColorAt(new Coordinates(i, 7)));
      Assertions.assertEquals(
          CheckersPieceNames.PAWN, board.getPieceAt(new Coordinates(i, 7)).getPieceName());

      Assertions.assertEquals(PieceColor.BLACK, board.getColorAt(new Coordinates(i + 1, 6)));
      Assertions.assertEquals(
          CheckersPieceNames.PAWN, board.getPieceAt(new Coordinates(i + 1, 6)).getPieceName());
    }

    for (int i = 1; i <= 8; i += 2) {
      Assertions.assertEquals(PieceColor.WHITE, board.getColorAt(new Coordinates(i, 1)));
      Assertions.assertEquals(
          CheckersPieceNames.PAWN, board.getPieceAt(new Coordinates(i, 1)).getPieceName());

      Assertions.assertEquals(PieceColor.WHITE, board.getColorAt(new Coordinates(i + 1, 2)));
      Assertions.assertEquals(
          CheckersPieceNames.PAWN, board.getPieceAt(new Coordinates(i + 1, 2)).getPieceName());

      Assertions.assertEquals(PieceColor.WHITE, board.getColorAt(new Coordinates(i, 3)));
      Assertions.assertEquals(
          CheckersPieceNames.PAWN, board.getPieceAt(new Coordinates(i, 3)).getPieceName());
    }
  }

  @Test
  public void checkerObligationToEat() {
    Game game = new ClassicCheckers().generateGame();

    game = game.playTurn(new Coordinates('A', 3), new Coordinates('B', 4));
    assertEquals(StatusOptions.NORMAL, game.getStatus());

    game = game.playTurn(new Coordinates('B', 6), new Coordinates('C', 5));
    assertEquals(StatusOptions.NORMAL, game.getStatus());

    game = game.playTurn(new Coordinates('G', 3), new Coordinates('H', 4));
    assertEquals(StatusOptions.NORMAL, game.getStatus());

    game = game.playTurn(new Coordinates('C', 5), new Coordinates('D', 4));
    assertEquals(StatusOptions.FAILURE, game.getStatus());

    game = game.playTurn(new Coordinates('C', 5), new Coordinates('A', 3));
    assertEquals(StatusOptions.NORMAL, game.getStatus());
  }
}
