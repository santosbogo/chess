package edu.austral.dissis.checkers;

import edu.austral.dissis.checkers.enums.CheckersPieceNames;
import edu.austral.dissis.checkers.games.ClassicCheckers;
import edu.austral.dissis.engine.enums.StatusOptions;
import edu.austral.dissis.engine.components.Game;
import edu.austral.dissis.engine.components.Coordinates;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClassicCheckersInvalidMovesTests {

  @Test
  public void checkerInvalidMoveBackward() {
    Game game = new ClassicCheckers().generateGame();

    assertEquals(
            StatusOptions.NORMAL, game.playTurn(new Coordinates('A', 3), new Coordinates('B', 4)).getStatus());
    assertEquals(
            StatusOptions.NORMAL, game.playTurn(new Coordinates('B', 6), new Coordinates('A', 5)).getStatus());
    assertEquals(
            StatusOptions.FAILURE, game.playTurn(new Coordinates('B', 4), new Coordinates('A', 3)).getStatus());

    game.playTurn(new Coordinates('B', 4), new Coordinates('C', 5));

    assertEquals(
            StatusOptions.FAILURE, game.playTurn(new Coordinates('A', 5), new Coordinates('B', 6)).getStatus());

  }

  @Test
  public void checkerInvalidMoveOutOfBounds() {
    Game game = new ClassicCheckers().generateGame();

    assertEquals(
            StatusOptions.FAILURE, game.playTurn(new Coordinates('H', 2), new Coordinates('I', 3)).getStatus());

    assertEquals(
            CheckersPieceNames.PAWN, game.getBoard().getPieceAt(new Coordinates('H', 2)).getPieceName());

  }


}
