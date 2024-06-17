package edu.austral.dissis.chess;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.austral.dissis.chess.enums.ChessPieceNames;
import edu.austral.dissis.chess.game.TestChess;
import edu.austral.dissis.chess.game.TestChessBoardGenerator;
import edu.austral.dissis.engine.components.Coordinates;
import edu.austral.dissis.engine.components.Game;
import edu.austral.dissis.engine.enums.PieceColor;
import edu.austral.dissis.engine.enums.StatusOptions;
import org.junit.jupiter.api.Test;

public class PersonalizedChessTests {
  @Test
  public void testCheckmate() {
    TestChessBoardGenerator testBoardGenerator = new TestChessBoardGenerator();
    testBoardGenerator.generateKing(new Coordinates('H', 8), PieceColor.BLACK);
    testBoardGenerator.generateRook(new Coordinates('G', 1), PieceColor.WHITE);
    testBoardGenerator.generateQueen(new Coordinates('G', 3), PieceColor.WHITE);
    testBoardGenerator.generateKing(new Coordinates('A', 1), PieceColor.WHITE);
    Game game = new TestChess(testBoardGenerator.generateBoard()).generateGame();

    game = game.playTurn(new Coordinates('G', 3), new Coordinates('G', 7));

    assertEquals(StatusOptions.WHITE_WIN, game.getStatus());
  }

  @Test
  public void testStalemate() {
    TestChessBoardGenerator testBoardGenerator = new TestChessBoardGenerator();
    testBoardGenerator.generateKing(new Coordinates('H', 8), PieceColor.BLACK);
    testBoardGenerator.generateQueen(new Coordinates('G', 2), PieceColor.WHITE);
    testBoardGenerator.generateKing(new Coordinates('A', 1), PieceColor.WHITE);
    Game game = new TestChess(testBoardGenerator.generateBoard()).generateGame();

    assertEquals(
        StatusOptions.STALEMATE,
        game.playTurn(new Coordinates('G', 2), new Coordinates('G', 6)).getStatus());
  }

  @Test
  public void testDoubleStepPawn() {
    TestChessBoardGenerator testBoardGenerator = new TestChessBoardGenerator();
    testBoardGenerator.generateKing(new Coordinates('E', 8), PieceColor.BLACK);
    testBoardGenerator.generateKing(new Coordinates('E', 1), PieceColor.WHITE);
    testBoardGenerator.generatePawn(new Coordinates('A', 3), PieceColor.WHITE);
    testBoardGenerator.generatePawn(new Coordinates('A', 2), PieceColor.WHITE);
    testBoardGenerator.generatePawn(new Coordinates('B', 2), PieceColor.WHITE);
    Game game = new TestChess(testBoardGenerator.generateBoard()).generateGame();

    game = game.playTurn(new Coordinates('A', 2), new Coordinates('A', 4));
    assertEquals(StatusOptions.FAILURE, game.getStatus());

    game = game.playTurn(new Coordinates('A', 3), new Coordinates('A', 4));
    assertEquals(StatusOptions.NORMAL, game.getStatus());

    game = game.playTurn(new Coordinates('E', 8), new Coordinates('F', 8));

    game = game.playTurn(new Coordinates('B', 2), new Coordinates('B', 4));
    assertEquals(StatusOptions.NORMAL, game.getStatus());
  }

  @Test
  public void testLeaveKingAttacked() {
    TestChessBoardGenerator testBoardGenerator = new TestChessBoardGenerator();
    testBoardGenerator.generateKing(new Coordinates('H', 8), PieceColor.BLACK);
    testBoardGenerator.generateQueen(new Coordinates('B', 7), PieceColor.BLACK);
    testBoardGenerator.generateKing(new Coordinates('A', 1), PieceColor.WHITE);
    Game game = new TestChess(testBoardGenerator.generateBoard()).generateGame();

    assertEquals(
        StatusOptions.FAILURE,
        game.playTurn(new Coordinates('A', 1), new Coordinates('B', 2)).getStatus());
  }

  @Test
  public void testShortCastle() {
    TestChessBoardGenerator testBoardGenerator = new TestChessBoardGenerator();
    testBoardGenerator.generateKing(new Coordinates('E', 1), PieceColor.WHITE);
    testBoardGenerator.generateRook(new Coordinates('H', 1), PieceColor.WHITE);
    testBoardGenerator.generateKing(new Coordinates('E', 8), PieceColor.BLACK);
    Game game = new TestChess(testBoardGenerator.generateBoard()).generateGame();

    game = game.playTurn(new Coordinates('E', 1), new Coordinates('G', 1));
    assertEquals(StatusOptions.NORMAL, game.getStatus());
    assertEquals(
        ChessPieceNames.KING, game.getBoard().getPieceAt(new Coordinates('G', 1)).getPieceName());
    assertEquals(
        ChessPieceNames.ROOK, game.getBoard().getPieceAt(new Coordinates('F', 1)).getPieceName());
  }

  @Test
  public void testInvalidShortCastleKingSide() {
    TestChessBoardGenerator testBoardGenerator = new TestChessBoardGenerator();
    testBoardGenerator.generateKing(new Coordinates('E', 1), PieceColor.WHITE);
    testBoardGenerator.generateRook(new Coordinates('H', 1), PieceColor.WHITE);
    testBoardGenerator.generateKing(new Coordinates('E', 8), PieceColor.BLACK);
    testBoardGenerator.generateRook(new Coordinates('F', 8), PieceColor.BLACK);
    testBoardGenerator.generateRook(new Coordinates('G', 8), PieceColor.BLACK);
    testBoardGenerator.generateRook(new Coordinates('E', 7), PieceColor.BLACK);

    Game game = new TestChess(testBoardGenerator.generateBoard()).generateGame();

    assertEquals(
        StatusOptions.FAILURE,
        game.playTurn(new Coordinates('E', 1), new Coordinates('G', 1)).getStatus());
  }

  @Test
  public void testLongCastle() {
    TestChessBoardGenerator testBoardGenerator = new TestChessBoardGenerator();
    testBoardGenerator.generateKing(new Coordinates('E', 1), PieceColor.WHITE);
    testBoardGenerator.generateRook(new Coordinates('A', 1), PieceColor.WHITE);
    testBoardGenerator.generateKing(new Coordinates('E', 8), PieceColor.BLACK);

    Game game = new TestChess(testBoardGenerator.generateBoard()).generateGame();

    assertEquals(
        StatusOptions.NORMAL,
        game.playTurn(new Coordinates('E', 1), new Coordinates('C', 1)).getStatus());
  }

  @Test
  public void testInvalidLongCastle() {
    TestChessBoardGenerator testBoardGenerator = new TestChessBoardGenerator();
    testBoardGenerator.generateKing(new Coordinates('E', 1), PieceColor.WHITE);
    testBoardGenerator.generateRook(new Coordinates('A', 1), PieceColor.WHITE);
    testBoardGenerator.generateKing(new Coordinates('E', 8), PieceColor.BLACK);
    testBoardGenerator.generateRook(new Coordinates('D', 8), PieceColor.BLACK);
    testBoardGenerator.generateRook(new Coordinates('C', 8), PieceColor.BLACK);
    testBoardGenerator.generateRook(new Coordinates('E', 7), PieceColor.BLACK);

    Game game = new TestChess(testBoardGenerator.generateBoard()).generateGame();

    assertEquals(
        StatusOptions.FAILURE,
        game.playTurn(new Coordinates('E', 1), new Coordinates('C', 1)).getStatus());
  }

  @Test
  public void testQueenMove() {
    TestChessBoardGenerator testBoardGenerator = new TestChessBoardGenerator();
    testBoardGenerator.generateQueen(new Coordinates('D', 1), PieceColor.WHITE);
    testBoardGenerator.generateKing(new Coordinates('E', 1), PieceColor.WHITE);
    testBoardGenerator.generateQueen(new Coordinates('D', 8), PieceColor.BLACK);
    testBoardGenerator.generateKing(new Coordinates('E', 8), PieceColor.BLACK);
    Game game = new TestChess(testBoardGenerator.generateBoard()).generateGame();

    game = game.playTurn(new Coordinates('D', 1), new Coordinates('D', 4));
    assertEquals(StatusOptions.NORMAL, game.getStatus());

    game = game.playTurn(new Coordinates('D', 8), new Coordinates('D', 5));
    assertEquals(StatusOptions.NORMAL, game.getStatus());

    game = game.playTurn(new Coordinates('D', 4), new Coordinates('G', 7));
    assertEquals(StatusOptions.NORMAL, game.getStatus());

    game = game.playTurn(new Coordinates('D', 5), new Coordinates('F', 7));
    assertEquals(StatusOptions.NORMAL, game.getStatus());

    game = game.playTurn(new Coordinates('G', 7), new Coordinates('D', 4));
    assertEquals(StatusOptions.NORMAL, game.getStatus());

    game = game.playTurn(new Coordinates('F', 7), new Coordinates('H', 5));
    assertEquals(StatusOptions.NORMAL, game.getStatus());
  }

  @Test
  public void testKnightMove() {
    TestChessBoardGenerator testBoardGenerator = new TestChessBoardGenerator();
    testBoardGenerator.generateKnight(new Coordinates('B', 1), PieceColor.WHITE);
    testBoardGenerator.generateKing(new Coordinates('E', 1), PieceColor.WHITE);
    testBoardGenerator.generateKing(new Coordinates('E', 8), PieceColor.BLACK);
    Game game = new TestChess(testBoardGenerator.generateBoard()).generateGame();

    game = game.playTurn(new Coordinates('B', 1), new Coordinates('C', 3));
    assertEquals(StatusOptions.NORMAL, game.getStatus());

    game = game.playTurn(new Coordinates('E', 8), new Coordinates('F', 8));
    assertEquals(StatusOptions.NORMAL, game.getStatus());

    game = game.playTurn(new Coordinates('C', 3), new Coordinates('E', 2));
    assertEquals(StatusOptions.NORMAL, game.getStatus());

    game = game.playTurn(new Coordinates('F', 8), new Coordinates('E', 8));
    assertEquals(StatusOptions.NORMAL, game.getStatus());

    game = game.playTurn(new Coordinates('E', 2), new Coordinates('C', 3));
    assertEquals(StatusOptions.NORMAL, game.getStatus());

    game = game.playTurn(new Coordinates('E', 8), new Coordinates('F', 8));
    assertEquals(StatusOptions.NORMAL, game.getStatus());

    game = game.playTurn(new Coordinates('C', 3), new Coordinates('B', 1));
    assertEquals(StatusOptions.NORMAL, game.getStatus());
  }

  @Test
  public void testBishopMove() {
    TestChessBoardGenerator testBoardGenerator = new TestChessBoardGenerator();
    testBoardGenerator.generateBishop(new Coordinates('C', 1), PieceColor.WHITE);
    testBoardGenerator.generateKing(new Coordinates('E', 1), PieceColor.WHITE);
    testBoardGenerator.generateKing(new Coordinates('E', 8), PieceColor.BLACK);
    Game game = new TestChess(testBoardGenerator.generateBoard()).generateGame();

    game = game.playTurn(new Coordinates('C', 1), new Coordinates('G', 5));
    assertEquals(StatusOptions.NORMAL, game.getStatus());

    game = game.playTurn(new Coordinates('E', 8), new Coordinates('F', 8));
    assertEquals(StatusOptions.NORMAL, game.getStatus());

    game = game.playTurn(new Coordinates('G', 5), new Coordinates('D', 8));
    assertEquals(StatusOptions.NORMAL, game.getStatus());

    game = game.playTurn(new Coordinates('F', 8), new Coordinates('E', 8));
    assertEquals(StatusOptions.NORMAL, game.getStatus());

    game = game.playTurn(new Coordinates('D', 8), new Coordinates('A', 5));
    assertEquals(StatusOptions.NORMAL, game.getStatus());

    game = game.playTurn(new Coordinates('E', 8), new Coordinates('F', 8));
    assertEquals(StatusOptions.NORMAL, game.getStatus());

    game = game.playTurn(new Coordinates('A', 5), new Coordinates('D', 2));
    assertEquals(StatusOptions.NORMAL, game.getStatus());
  }

  @Test
  public void testUndoPawnMovementStillBlackTurn() {
    TestChessBoardGenerator testBoardGenerator = new TestChessBoardGenerator();
    testBoardGenerator.generateKing(new Coordinates('E', 1), PieceColor.WHITE);
    testBoardGenerator.generateKing(new Coordinates('E', 8), PieceColor.BLACK);
    testBoardGenerator.generatePawn(new Coordinates('E', 2), PieceColor.WHITE);
    testBoardGenerator.generatePawn(new Coordinates('E', 7), PieceColor.BLACK);
    Game game = new TestChess(testBoardGenerator.generateBoard()).generateGame();

    game.playTurn(new Coordinates('E', 2), new Coordinates('E', 3));
    game.playTurn(new Coordinates('E', 7), new Coordinates('E', 6));
    game.undo();
    assertEquals(
        ChessPieceNames.PAWN, game.getBoard().getPieceAt(new Coordinates('E', 7)).getPieceName());

    assertEquals(
        StatusOptions.FAILURE,
        game.playTurn(new Coordinates('E', 8), new Coordinates('D', 8)).getStatus());
  }

  @Test
  public void testCrowningPawn() {
    TestChessBoardGenerator testBoardGenerator = new TestChessBoardGenerator();
    testBoardGenerator.generateKing(new Coordinates('E', 1), PieceColor.WHITE);
    testBoardGenerator.generateKing(new Coordinates('E', 8), PieceColor.BLACK);
    testBoardGenerator.generatePawn(new Coordinates('A', 7), PieceColor.WHITE);
    Game game = new TestChess(testBoardGenerator.generateBoard()).generateGame();

    game = game.playTurn(new Coordinates('A', 7), new Coordinates('A', 8));
    assertEquals(
        ChessPieceNames.QUEEN, game.getBoard().getPieceAt(new Coordinates('A', 8)).getPieceName());
  }

  @Test
  public void testCheckmateCrowning() {
    TestChessBoardGenerator testBoardGenerator = new TestChessBoardGenerator();
    testBoardGenerator.generateKing(new Coordinates('E', 1), PieceColor.WHITE);
    testBoardGenerator.generateKing(new Coordinates('E', 8), PieceColor.BLACK);
    testBoardGenerator.generateQueen(new Coordinates('A', 7), PieceColor.WHITE);
    testBoardGenerator.generateRook(new Coordinates('B', 7), PieceColor.WHITE);

    Game game = new TestChess(testBoardGenerator.generateBoard()).generateGame();

    assertEquals(
        StatusOptions.WHITE_WIN,
        game.playTurn(new Coordinates('A', 7), new Coordinates('A', 8)).getStatus());
  }
}
