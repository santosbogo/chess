package edu.austral.dissis.chess.engine;

import static org.junit.jupiter.api.Assertions.*;

import edu.austral.dissis.chess.engine.board.Coordinates;
import edu.austral.dissis.chess.engine.enums.PieceColor;
import edu.austral.dissis.chess.engine.enums.StatusOptions;
import edu.austral.dissis.chess.engine.testChess.TestChess;
import edu.austral.dissis.chess.engine.testChess.TestChessBoardGenerator;
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

    assertEquals(
        StatusOptions.WHITE_CHECKMATE,
        game.playTurn(new Coordinates('G', 3), new Coordinates('G', 7)));
    //        assertEquals(StatusOptions.WHITE_CHECKMATE, game.playTurn(new Coordinates('G',3), new
    // Coordinates('H',3)));
  }

  @Test
  public void testLeaveKingAttacked() {
    TestChessBoardGenerator testBoardGenerator = new TestChessBoardGenerator();
    testBoardGenerator.generateKing(new Coordinates('H', 8), PieceColor.BLACK);
    testBoardGenerator.generateQueen(new Coordinates('B', 7), PieceColor.BLACK);
    testBoardGenerator.generateKing(new Coordinates('A', 1), PieceColor.WHITE);

    Game game = new TestChess(testBoardGenerator.generateBoard()).generateGame();

    assertEquals(
        StatusOptions.FAILURE, game.playTurn(new Coordinates('A', 1), new Coordinates('B', 2)));
  }

  @Test
  public void testStalemate() {
    TestChessBoardGenerator testBoardGenerator = new TestChessBoardGenerator();
    testBoardGenerator.generateKing(new Coordinates('H', 8), PieceColor.BLACK);
    testBoardGenerator.generateQueen(new Coordinates('G', 2), PieceColor.WHITE);
    testBoardGenerator.generateKing(new Coordinates('A', 1), PieceColor.WHITE);

    Game game = new TestChess(testBoardGenerator.generateBoard()).generateGame();

    assertEquals(
        StatusOptions.STALEMATE, game.playTurn(new Coordinates('G', 2), new Coordinates('G', 6)));
  }
}
