package edu.austral.dissis.checkers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.austral.dissis.checkers.enums.CheckersPieceNames;
import edu.austral.dissis.checkers.games.classic.ClassicCheckers;
import edu.austral.dissis.engine.components.Coordinates;
import edu.austral.dissis.engine.components.Game;
import edu.austral.dissis.engine.enums.StatusOptions;
import org.junit.jupiter.api.Test;

public class ClassicCheckersInvalidMovesTests {

  @Test
  public void checkerInvalidMoveBackward() {
    Game game = new ClassicCheckers().generateGame();

    game = game.playTurn(new Coordinates('A', 3), new Coordinates('B', 4));
    assertEquals(StatusOptions.NORMAL, game.getStatus());

    game = game.playTurn(new Coordinates('B', 4), new Coordinates('A', 3));
    assertEquals(StatusOptions.FAILURE, game.getStatus());

    game = game.playTurn(new Coordinates('B', 6), new Coordinates('C', 5));
    assertEquals(StatusOptions.NORMAL, game.getStatus());

    game = game.playTurn(new Coordinates('B', 4), new Coordinates('A', 3));
    assertEquals(StatusOptions.FAILURE, game.getStatus());

    game = game.playTurn(new Coordinates('B', 4), new Coordinates('A', 5));
    assertEquals(StatusOptions.NORMAL, game.getStatus());

    game = game.playTurn(new Coordinates('A', 5), new Coordinates('B', 6));
    assertEquals(
        StatusOptions.FAILURE,
        game.playTurn(new Coordinates('C', 5), new Coordinates('B', 6)).getStatus());
  }

  @Test
  public void checkerInvalidMoveOutOfBounds() {
    Game game = new ClassicCheckers().generateGame();

    assertEquals(
        StatusOptions.FAILURE,
        game.playTurn(new Coordinates('H', 2), new Coordinates('I', 3)).getStatus());
    assertEquals(
        CheckersPieceNames.PAWN,
        game.getBoard().getPieceAt(new Coordinates('H', 2)).getPieceName());
  }

  @Test
  public void checkerInvalidMoveToOccupiedSquare() {
    Game game = new ClassicCheckers().generateGame();

    game = game.playTurn(new Coordinates('A', 3), new Coordinates('B', 4));
    assertEquals(StatusOptions.NORMAL, game.getStatus());

    game = game.playTurn(new Coordinates('B', 6), new Coordinates('C', 5));
    assertEquals(StatusOptions.NORMAL, game.getStatus());

    game = game.playTurn(new Coordinates('B', 4), new Coordinates('C', 5));
    assertEquals(StatusOptions.FAILURE, game.getStatus());
  }

  @Test
  public void checkerInvalidForObligationToEat() {
    Game game = new ClassicCheckers().generateGame();

    game = game.playTurn(new Coordinates('A', 3), new Coordinates('B', 4));
    assertEquals(StatusOptions.NORMAL, game.getStatus());

    game = game.playTurn(new Coordinates('B', 6), new Coordinates('C', 5));
    assertEquals(StatusOptions.NORMAL, game.getStatus());

    game = game.playTurn(new Coordinates('G', 3), new Coordinates('H', 4));
    assertEquals(StatusOptions.NORMAL, game.getStatus());

    game = game.playTurn(new Coordinates('C', 5), new Coordinates('D', 4));
    assertEquals(StatusOptions.FAILURE, game.getStatus());
  }
}
