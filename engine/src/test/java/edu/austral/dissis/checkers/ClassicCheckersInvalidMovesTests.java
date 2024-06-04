package edu.austral.dissis.checkers;

import edu.austral.dissis.checkers.enums.CheckersPieceNames;
import edu.austral.dissis.checkers.games.ClassicCheckers;
import edu.austral.dissis.chess.enums.ChessStatusOptions;
import edu.austral.dissis.engine.Game;
import edu.austral.dissis.engine.components.Coordinates;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClassicCheckersInvalidMovesTests {

  @Test
  public void checkerInvalidMoveBackward() {
    Game game = new ClassicCheckers().generateGame();

    assertEquals(
            ChessStatusOptions.NORMAL, game.playTurn(new Coordinates('A', 3), new Coordinates('B', 4)));
    assertEquals(
            ChessStatusOptions.NORMAL, game.playTurn(new Coordinates('B', 6), new Coordinates('A', 5)));
    assertEquals(
            ChessStatusOptions.FAILURE, game.playTurn(new Coordinates('B', 4), new Coordinates('A', 3)));

    game.playTurn(new Coordinates('B', 4), new Coordinates('C', 5));

    assertEquals(
            ChessStatusOptions.FAILURE, game.playTurn(new Coordinates('A', 5), new Coordinates('B', 6)));

  }

  @Test
  public void checkerInvalidMoveOutOfBounds() {
    Game game = new ClassicCheckers().generateGame();

    assertEquals(
            ChessStatusOptions.FAILURE, game.playTurn(new Coordinates('H', 2), new Coordinates('I', 3)));

    assertEquals(
            CheckersPieceNames.PAWN, game.getBoard().getPieceAt(new Coordinates('H', 2)).getPieceName());

  }


}
