package edu.austral.dissis.chess;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.austral.dissis.chess.enums.ChessPieceNames;
import edu.austral.dissis.chess.games.classic.ClassicChess;
import edu.austral.dissis.engine.components.Board;
import edu.austral.dissis.engine.components.Coordinates;
import edu.austral.dissis.engine.components.Game;
import edu.austral.dissis.engine.enums.PieceColor;
import edu.austral.dissis.engine.enums.StatusOptions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ClassicChessTests {

  @Test
  public void testStartingBoard() {
    Game game = new ClassicChess().generateGame();
    Board board = game.getBoard();

    for (int i = 1; i <= 8; i++) {
      Assertions.assertEquals(PieceColor.BLACK, board.getColorAt(new Coordinates(i, 8)));
      Assertions.assertEquals(PieceColor.BLACK, board.getColorAt(new Coordinates(i, 7)));
    }

    for (int i = 1; i <= 8; i++) {
      Assertions.assertEquals(PieceColor.WHITE, board.getColorAt(new Coordinates(i, 2)));
      Assertions.assertEquals(PieceColor.WHITE, board.getColorAt(new Coordinates(i, 1)));
    }

    for (int y = 2; y <= 8; y = y + 5) {
      for (int x = 1; x <= 8; x++) {
        Assertions.assertEquals(
            ChessPieceNames.PAWN, board.getPieceAt(new Coordinates(x, y)).getPieceName());
      }
    }

    for (int y = 1; y <= 8; y = y + 7) {
      Assertions.assertEquals(
          ChessPieceNames.ROOK, board.getPieceAt(new Coordinates(1, y)).getPieceName());
      Assertions.assertEquals(
          ChessPieceNames.ROOK, board.getPieceAt(new Coordinates(8, y)).getPieceName());
      Assertions.assertEquals(
          ChessPieceNames.KNIGHT, board.getPieceAt(new Coordinates(2, y)).getPieceName());
      Assertions.assertEquals(
          ChessPieceNames.KNIGHT, board.getPieceAt(new Coordinates(7, y)).getPieceName());
      Assertions.assertEquals(
          ChessPieceNames.BISHOP, board.getPieceAt(new Coordinates(3, y)).getPieceName());
      Assertions.assertEquals(
          ChessPieceNames.BISHOP, board.getPieceAt(new Coordinates(6, y)).getPieceName());
      Assertions.assertEquals(
          ChessPieceNames.QUEEN, board.getPieceAt(new Coordinates(4, y)).getPieceName());
      Assertions.assertEquals(
          ChessPieceNames.KING, board.getPieceAt(new Coordinates(5, y)).getPieceName());
    }
  }

  @Test
  public void testTurns() {
    Game game = new ClassicChess().generateGame();

    game = game.playTurn(new Coordinates('A', 2), new Coordinates('A', 3));
    Assertions.assertEquals(StatusOptions.NORMAL, game.getStatus());

    game = game.playTurn(new Coordinates('A', 3), new Coordinates('A', 4));
    Assertions.assertEquals(StatusOptions.FAILURE, game.getStatus());

    game = game.playTurn(new Coordinates('A', 7), new Coordinates('A', 6));
    Assertions.assertEquals(StatusOptions.NORMAL, game.getStatus());

    game = game.playTurn(new Coordinates('A', 6), new Coordinates('A', 5));
    assertEquals(StatusOptions.FAILURE, game.getStatus());
  }

  @Test
  public void testCheck() {
    Game game = new ClassicChess().generateGame();

    game = game.playTurn(new Coordinates('E', 2), new Coordinates('E', 3));
    game = game.playTurn(new Coordinates('F', 7), new Coordinates('F', 6));
    game = game.playTurn(new Coordinates('D', 1), new Coordinates('H', 5));

    assertTrue(new ChessHelper().isKingThreatened(game.getBoard(), PieceColor.BLACK));
  }

  @Test
  public void testWhiteCheckmate() {
    Game game = new ClassicChess().generateGame();

    game = game.playTurn(new Coordinates('E', 2), new Coordinates('E', 3));
    game = game.playTurn(new Coordinates('F', 7), new Coordinates('F', 6));
    game = game.playTurn(new Coordinates('A', 2), new Coordinates('A', 3));
    game = game.playTurn(new Coordinates('G', 7), new Coordinates('G', 6));
    game = game.playTurn(new Coordinates('A', 3), new Coordinates('A', 4));
    game = game.playTurn(new Coordinates('G', 6), new Coordinates('G', 5));
    game = game.playTurn(new Coordinates('D', 1), new Coordinates('H', 5));

    assertEquals(StatusOptions.WHITE_WIN, game.getStatus());
  }

  @Test
  public void testBlackCheckmate() {
    Game game = new ClassicChess().generateGame();

    game = game.playTurn(new Coordinates('B', 2), new Coordinates('B', 3));
    game = game.playTurn(new Coordinates('E', 7), new Coordinates('E', 6));
    game = game.playTurn(new Coordinates('A', 2), new Coordinates('A', 3));
    game = game.playTurn(new Coordinates('F', 8), new Coordinates('C', 5));
    game = game.playTurn(new Coordinates('B', 3), new Coordinates('B', 4));
    game = game.playTurn(new Coordinates('D', 8), new Coordinates('H', 4));
    game = game.playTurn(new Coordinates('A', 3), new Coordinates('A', 4));
    game = game.playTurn(new Coordinates('H', 4), new Coordinates('F', 2));

    assertEquals(StatusOptions.BLACK_WIN, game.getStatus());
  }

  @Test
  public void testMoveRook() {
    Game game = new ClassicChess().generateGame();

    game = game.playTurn(new Coordinates('A', 2), new Coordinates('A', 3));
    game = game.playTurn(new Coordinates('A', 7), new Coordinates('A', 6));
    game = game.playTurn(new Coordinates('A', 3), new Coordinates('A', 4));
    game = game.playTurn(new Coordinates('A', 6), new Coordinates('A', 5));
    game = game.playTurn(new Coordinates('A', 1), new Coordinates('A', 3));

    assertEquals(
        ChessPieceNames.ROOK, game.getBoard().getPieceAt(new Coordinates('A', 3)).getPieceName());
  }

  @Test
  public void testMoveQueen() {
    Game game = new ClassicChess().generateGame();

    game = game.playTurn(new Coordinates('E', 2), new Coordinates('E', 3));
    game = game.playTurn(new Coordinates('A', 7), new Coordinates('A', 6));
    game = game.playTurn(new Coordinates('D', 1), new Coordinates('F', 3));

    assertEquals(
        ChessPieceNames.QUEEN, game.getBoard().getPieceAt(new Coordinates('F', 3)).getPieceName());

    game = game.playTurn(new Coordinates('A', 6), new Coordinates('A', 5));
    game = game.playTurn(new Coordinates('F', 3), new Coordinates('D', 5));
    assertEquals(
        ChessPieceNames.QUEEN, game.getBoard().getPieceAt(new Coordinates('D', 5)).getPieceName());
  }
}
