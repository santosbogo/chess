package edu.austral.dissis.chess.engine;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.austral.dissis.chess.engine.board.Coordinates;
import edu.austral.dissis.chess.engine.enums.PieceName;
import edu.austral.dissis.chess.engine.games.classicChess.ClassicChess;
import org.junit.jupiter.api.Test;

public class ClassicChessValidMovesTests {

  @Test
  public void movePawn() {
    Game game = new ClassicChess().generateGame();

    game.playTurn(new Coordinates('A', 2), new Coordinates('A', 3));

    assertEquals(
        PieceName.PAWN, game.peekBoard().getPieceAt(new Coordinates('A', 3)).getPieceName());
  }

  @Test
  public void moveRook() {
    Game game = new ClassicChess().generateGame();

    game.playTurn(new Coordinates('A', 2), new Coordinates('A', 3));
    game.playTurn(new Coordinates('A', 7), new Coordinates('A', 6));
    game.playTurn(new Coordinates('A', 3), new Coordinates('A', 4));
    game.playTurn(new Coordinates('A', 6), new Coordinates('A', 5));
    game.playTurn(new Coordinates('A', 1), new Coordinates('A', 3));

    assertEquals(
        PieceName.ROOK, game.peekBoard().getPieceAt(new Coordinates('A', 3)).getPieceName());
  }

  @Test
  public void moveQueen() {
    Game game = new ClassicChess().generateGame();

    game.playTurn(new Coordinates('E', 2), new Coordinates('E', 3)); // Este movimiento esta ok
    game.playTurn(
        new Coordinates('A', 7),
        new Coordinates(
            'A',
            6)); // Este movimiento cuando evalua el status no se por que hace desaparecer al rey
    // negro
    game.playTurn(new Coordinates('D', 1), new Coordinates('F', 3));
    assertEquals(
        PieceName.QUEEN, game.peekBoard().getPieceAt(new Coordinates('F', 3)).getPieceName());
    game.playTurn(new Coordinates('A', 6), new Coordinates('A', 5));
    game.playTurn(new Coordinates('F', 3), new Coordinates('D', 5));
    assertEquals(
        PieceName.QUEEN, game.peekBoard().getPieceAt(new Coordinates('D', 5)).getPieceName());
  }
}
