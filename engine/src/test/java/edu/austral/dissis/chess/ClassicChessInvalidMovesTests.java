package edu.austral.dissis.chess;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.austral.dissis.engine.components.Game;
import edu.austral.dissis.engine.components.Coordinates;
import edu.austral.dissis.chess.enums.ChessPieceNames;
import edu.austral.dissis.engine.enums.StatusOptions;
import edu.austral.dissis.chess.games.classicChess.ClassicChess;
import org.junit.jupiter.api.Test;

public class  ClassicChessInvalidMovesTests {

  @Test
  public void pawnInvalidMovesTest2() {
    Game game = new ClassicChess().generateGame();

    assertEquals(
        StatusOptions.FAILURE, game.playTurn(new Coordinates('A', 2), new Coordinates('A', 5)).getStatus());
    assertEquals(
        StatusOptions.FAILURE, game.playTurn(new Coordinates('A', 2), new Coordinates('B', 3)).getStatus());
  }

  @Test
  public void pawnInvalidMovesTest() {
    Game game = new ClassicChess().generateGame();

    assertEquals(
        StatusOptions.FAILURE, game.playTurn(new Coordinates('A', 2), new Coordinates('A', 5)).getStatus());
    assertEquals(
        StatusOptions.FAILURE, game.playTurn(new Coordinates('A', 2), new Coordinates('B', 3)).getStatus());
  }

  @Test
  public void rookInvalidMovesTest() {
    Game game = new ClassicChess().generateGame();

    assertEquals(
        ChessPieceNames.ROOK, game.getBoard().getPieceAt(new Coordinates('A', 1)).getPieceName());

    assertEquals(
        StatusOptions.FAILURE, game.playTurn(new Coordinates('A', 1), new Coordinates('B', 1)).getStatus());
    assertEquals(
        StatusOptions.FAILURE, game.playTurn(new Coordinates('A', 1), new Coordinates('A', 2)).getStatus());
  }

  @Test
  public void knightInvalidMovesTest() {
    Game game = new ClassicChess().generateGame();

    assertEquals(
        StatusOptions.FAILURE, game.playTurn(new Coordinates('B', 1), new Coordinates('B', 3)).getStatus());
    assertEquals(
        StatusOptions.FAILURE, game.playTurn(new Coordinates('B', 1), new Coordinates('C', 2)).getStatus());
  }

  @Test
  public void bishopInvalidMovesTest() {
    Game game = new ClassicChess().generateGame();

    assertEquals(
        StatusOptions.FAILURE, game.playTurn(new Coordinates('C', 1), new Coordinates('C', 3)).getStatus());
    assertEquals(
        StatusOptions.FAILURE, game.playTurn(new Coordinates('C', 1), new Coordinates('D', 2)).getStatus());
  }
}
