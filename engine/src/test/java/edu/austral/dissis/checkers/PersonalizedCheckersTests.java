package edu.austral.dissis.checkers;

import edu.austral.dissis.checkers.enums.CheckersPieceNames;
import edu.austral.dissis.checkers.testCheckers.TestCheckers;
import edu.austral.dissis.checkers.testCheckers.TestCheckersBoardGenerator;
import edu.austral.dissis.engine.enums.StatusOptions;
import edu.austral.dissis.engine.components.Game;
import edu.austral.dissis.engine.components.Coordinates;
import edu.austral.dissis.engine.enums.PieceColor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonalizedCheckersTests {
  @Test
  public void testCheckmateBecauseEnemyDoesntHavePieces() {
    TestCheckersBoardGenerator testBoardGenerator = new TestCheckersBoardGenerator();
    testBoardGenerator.generateChecker(new Coordinates('A', 1), PieceColor.WHITE);
    testBoardGenerator.generateChecker(new Coordinates('B', 2), PieceColor.BLACK);
    Game game = new TestCheckers(testBoardGenerator.generateBoard()).generateGame();

    assertEquals( StatusOptions.WHITE_WIN, game.playTurn(new Coordinates('A', 1), new Coordinates('C', 3)).getStatus());
  }

  @Test
  public void testCheckmateBecauseEnemyCantMove() {
    TestCheckersBoardGenerator testBoardGenerator = new TestCheckersBoardGenerator();
    testBoardGenerator.generateChecker(new Coordinates('A', 1), PieceColor.WHITE);
    testBoardGenerator.generateChecker(new Coordinates('C', 1), PieceColor.WHITE);
    testBoardGenerator.generateChecker(new Coordinates('A', 3), PieceColor.BLACK);
    Game game = new TestCheckers(testBoardGenerator.generateBoard()).generateGame();

    game = game.playTurn(new Coordinates('A', 1), new Coordinates('B', 2));

    assertEquals( StatusOptions.WHITE_WIN, game.getStatus());
  }

  @Test
  public void testStalemate() {
    TestCheckersBoardGenerator testBoardGenerator = new TestCheckersBoardGenerator();
    testBoardGenerator.generateChecker(new Coordinates('A', 8), PieceColor.BLACK);
    testBoardGenerator.generateChecker(new Coordinates('C', 8), PieceColor.BLACK);
    testBoardGenerator.generateChecker(new Coordinates('E', 8), PieceColor.BLACK);
    testBoardGenerator.generateChecker(new Coordinates('G', 8), PieceColor.BLACK);
    testBoardGenerator.generateChecker(new Coordinates('B', 7), PieceColor.WHITE);
    testBoardGenerator.generateChecker(new Coordinates('D', 7), PieceColor.WHITE);
    testBoardGenerator.generateChecker(new Coordinates('F', 7), PieceColor.WHITE);
    testBoardGenerator.generateChecker(new Coordinates('H', 7), PieceColor.WHITE);
    testBoardGenerator.generateChecker(new Coordinates('A', 6), PieceColor.WHITE);
    testBoardGenerator.generateChecker(new Coordinates('C', 6), PieceColor.WHITE);
    testBoardGenerator.generateChecker(new Coordinates('E', 6), PieceColor.WHITE);
    testBoardGenerator.generateChecker(new Coordinates('F', 5), PieceColor.WHITE);
    Game game = new TestCheckers(testBoardGenerator.generateBoard()).generateGame();

    assertEquals(
        StatusOptions.STALEMATE, game.playTurn(new Coordinates('F', 5), new Coordinates('G', 6)).getStatus());
  }

  @Test
  public void testKingMove() {
    TestCheckersBoardGenerator testBoardGenerator = new TestCheckersBoardGenerator();
    testBoardGenerator.generateKing(new Coordinates('D', 4), PieceColor.WHITE);
    testBoardGenerator.generateChecker(new Coordinates('B', 8), PieceColor.BLACK);
    Game game = new TestCheckers(testBoardGenerator.generateBoard()).generateGame();

    game = game.playTurn(new Coordinates('D', 4), new Coordinates('F', 6));
    assertEquals(StatusOptions.NORMAL, game.getStatus());

    game = game.playTurn(new Coordinates('B', 8), new Coordinates('A', 7));

    game = game.playTurn(new Coordinates('F', 6), new Coordinates('H', 4));
    assertEquals(StatusOptions.NORMAL, game.getStatus());

    game = game.playTurn(new Coordinates('A', 7), new Coordinates('B', 6));

    game = game.playTurn(new Coordinates('H', 4), new Coordinates('F', 2));
    assertEquals(StatusOptions.NORMAL, game.getStatus());

    game = game.playTurn(new Coordinates('B', 6), new Coordinates('A', 5));

    game = game.playTurn(new Coordinates('F', 2), new Coordinates('B', 6));
    assertEquals(StatusOptions.NORMAL, game.getStatus());
  }

  @Test
  public void testUndoPawnMovementStillBlackTurn(){
    TestCheckersBoardGenerator testBoardGenerator = new TestCheckersBoardGenerator();
    testBoardGenerator.generateChecker(new Coordinates('A', 1), PieceColor.WHITE);
    testBoardGenerator.generateChecker(new Coordinates('B', 8), PieceColor.BLACK);
    Game game = new TestCheckers(testBoardGenerator.generateBoard()).generateGame();

    game = game.playTurn(new Coordinates('A', 1), new Coordinates('B', 2));
    game = game.playTurn(new Coordinates('B', 8), new Coordinates('C', 7));
    game = game.undo();

    assertEquals(StatusOptions.FAILURE, game.playTurn(new Coordinates('B', 2), new Coordinates('C', 3)).getStatus());
  }

  @Test
  public void testCrowningChecker(){
    TestCheckersBoardGenerator testBoardGenerator = new TestCheckersBoardGenerator();
    testBoardGenerator.generateChecker(new Coordinates('G', 7), PieceColor.WHITE);
    testBoardGenerator.generateChecker(new Coordinates('B', 8), PieceColor.BLACK);
    Game game = new TestCheckers(testBoardGenerator.generateBoard()).generateGame();

    game = game.playTurn(new Coordinates('G', 7), new Coordinates('H', 8));
    assertEquals(CheckersPieceNames.KING, game.getBoard().getPieceAt(new Coordinates('H', 8)).getPieceName());
  }
}
