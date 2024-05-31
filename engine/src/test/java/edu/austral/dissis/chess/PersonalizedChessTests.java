package edu.austral.dissis.chess;

import static org.junit.jupiter.api.Assertions.*;

import edu.austral.dissis.engine.Game;
import edu.austral.dissis.engine.components.Coordinates;
import edu.austral.dissis.engine.enums.PieceColor;
import edu.austral.dissis.chess.enums.ChessPieceNames;
import edu.austral.dissis.chess.enums.ChessStatusOptions;
import edu.austral.dissis.chess.testChess.TestChess;
import edu.austral.dissis.chess.testChess.TestChessBoardGenerator;
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

    assertEquals( ChessStatusOptions.WHITE_CHECKMATE, game.playTurn(new Coordinates('G', 3), new Coordinates('G', 7)));
//        assertEquals(StatusOptions.WHITE_CHECKMATE, game.playTurn(new Coordinates('G',3), new Coordinates('H',3)));
  }

  @Test
  public void testDoubleStepPawn(){
    TestChessBoardGenerator testBoardGenerator = new TestChessBoardGenerator();
    testBoardGenerator.generateKing(new Coordinates('E', 8), PieceColor.BLACK);
    testBoardGenerator.generateKing(new Coordinates('E', 1), PieceColor.WHITE);
    testBoardGenerator.generatePawn(new Coordinates('A', 3), PieceColor.WHITE);
    testBoardGenerator.generatePawn(new Coordinates('A', 2), PieceColor.WHITE);
    testBoardGenerator.generatePawn(new Coordinates('B', 2), PieceColor.WHITE);

    Game game = new TestChess(testBoardGenerator.generateBoard()).generateGame();

    assertEquals(ChessStatusOptions.FAILURE, game.playTurn(new Coordinates('A', 2), new Coordinates('A', 4)));
    assertEquals(ChessStatusOptions.FAILURE, game.playTurn(new Coordinates('A', 2), new Coordinates('A', 3)));
    assertEquals(ChessStatusOptions.NORMAL, game.playTurn(new Coordinates('B', 2), new Coordinates('B', 4)));
  }

  @Test
  public void testLeaveKingAttacked() {
    TestChessBoardGenerator testBoardGenerator = new TestChessBoardGenerator();
    testBoardGenerator.generateKing(new Coordinates('H', 8), PieceColor.BLACK);
    testBoardGenerator.generateQueen(new Coordinates('B', 7), PieceColor.BLACK);
    testBoardGenerator.generateKing(new Coordinates('A', 1), PieceColor.WHITE);

    Game game = new TestChess(testBoardGenerator.generateBoard()).generateGame();

    assertEquals(
        ChessStatusOptions.FAILURE, game.playTurn(new Coordinates('A', 1), new Coordinates('B', 2)));
  }

  @Test
  public void testStalemate() {
    TestChessBoardGenerator testBoardGenerator = new TestChessBoardGenerator();
    testBoardGenerator.generateKing(new Coordinates('H', 8), PieceColor.BLACK);
    testBoardGenerator.generateQueen(new Coordinates('G', 2), PieceColor.WHITE);
    testBoardGenerator.generateKing(new Coordinates('A', 1), PieceColor.WHITE);

    Game game = new TestChess(testBoardGenerator.generateBoard()).generateGame();

    assertEquals(
        ChessStatusOptions.STALEMATE, game.playTurn(new Coordinates('G', 2), new Coordinates('G', 6)));
  }

  @Test
  public void testShortCastle() {
    TestChessBoardGenerator testBoardGenerator = new TestChessBoardGenerator();
    testBoardGenerator.generateKing(new Coordinates('E', 1), PieceColor.WHITE);
    testBoardGenerator.generateRook(new Coordinates('H', 1), PieceColor.WHITE);
    testBoardGenerator.generateKing(new Coordinates('E', 8), PieceColor.BLACK);

    Game game = new TestChess(testBoardGenerator.generateBoard()).generateGame();

    assertEquals(
        ChessStatusOptions.NORMAL, game.playTurn(new Coordinates('E', 1), new Coordinates('G', 1)));

    assertEquals(
        ChessPieceNames.KING, game.getBoard().getPieceAt(new Coordinates('G', 1)).getPieceName());
    assertFalse(game.getBoard().isEmptySquare(new Coordinates('F', 1)));
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
        ChessStatusOptions.FAILURE, game.playTurn(new Coordinates('E', 1), new Coordinates('G', 1)));
  }

  @Test
  public void testLongCastle() {
    TestChessBoardGenerator testBoardGenerator = new TestChessBoardGenerator();
    testBoardGenerator.generateKing(new Coordinates('E', 1), PieceColor.WHITE);
    testBoardGenerator.generateRook(new Coordinates('A', 1), PieceColor.WHITE);
    testBoardGenerator.generateKing(new Coordinates('E', 8), PieceColor.BLACK);

    Game game = new TestChess(testBoardGenerator.generateBoard()).generateGame();

    assertEquals(
        ChessStatusOptions.NORMAL, game.playTurn(new Coordinates('E', 1), new Coordinates('C', 1)));
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
        ChessStatusOptions.FAILURE, game.playTurn(new Coordinates('E', 1), new Coordinates('C', 1)));
  }

  @Test
  public void testQueenMove() {
    TestChessBoardGenerator testBoardGenerator = new TestChessBoardGenerator();

    testBoardGenerator.generateRook(new Coordinates('A', 1), PieceColor.WHITE);
    testBoardGenerator.generateKnight(new Coordinates('B', 1), PieceColor.WHITE);
    testBoardGenerator.generateBishop(new Coordinates('C', 1), PieceColor.WHITE);
    testBoardGenerator.generateQueen(new Coordinates('D', 1), PieceColor.WHITE);
    testBoardGenerator.generateKing(new Coordinates('E', 1), PieceColor.WHITE);
    testBoardGenerator.generateBishop(new Coordinates('F', 1), PieceColor.WHITE);
    testBoardGenerator.generateKnight(new Coordinates('G', 1), PieceColor.WHITE);
    testBoardGenerator.generateRook(new Coordinates('H', 1), PieceColor.WHITE);

    testBoardGenerator.generateRook(new Coordinates('A', 8), PieceColor.BLACK);
    testBoardGenerator.generateKnight(new Coordinates('B', 8), PieceColor.BLACK);
    testBoardGenerator.generateBishop(new Coordinates('C', 8), PieceColor.BLACK);
    testBoardGenerator.generateQueen(new Coordinates('D', 8), PieceColor.BLACK);
    testBoardGenerator.generateKing(new Coordinates('E', 8), PieceColor.BLACK);
    testBoardGenerator.generateBishop(new Coordinates('F', 8), PieceColor.BLACK);
    testBoardGenerator.generateKnight(new Coordinates('G', 8), PieceColor.BLACK);
    testBoardGenerator.generateRook(new Coordinates('H', 8), PieceColor.BLACK);

    Game game = new TestChess(testBoardGenerator.generateBoard()).generateGame();

    assertEquals(
        ChessStatusOptions.NORMAL, game.playTurn(new Coordinates('D', 1), new Coordinates('G', 4)));
    assertEquals(
        ChessStatusOptions.NORMAL, game.playTurn(new Coordinates('D', 8), new Coordinates('D', 4)));
    assertEquals(
        ChessStatusOptions.NORMAL, game.playTurn(new Coordinates('G', 4), new Coordinates('D', 4)));

    assertEquals(
        ChessPieceNames.QUEEN, game.getBoard().getPieceAt(new Coordinates('D', 4)).getPieceName());
    assertEquals(PieceColor.WHITE, game.getBoard().getPieceAt(new Coordinates('D', 4)).getColor());
  }

  @Test
  public void testUndoPawnMovementStillBlackTurn(){
    TestChessBoardGenerator testBoardGenerator = new TestChessBoardGenerator();
    testBoardGenerator.generateKing(new Coordinates('E', 1), PieceColor.WHITE);
    testBoardGenerator.generateKing(new Coordinates('E', 8), PieceColor.BLACK);
    testBoardGenerator.generatePawn(new Coordinates('E', 2), PieceColor.WHITE);
    testBoardGenerator.generatePawn(new Coordinates('E', 7), PieceColor.BLACK);

    Game game = new TestChess(testBoardGenerator.generateBoard()).generateGame();

    game.playTurn(new Coordinates('E', 2), new Coordinates('E', 3));
    game.playTurn(new Coordinates('E', 7), new Coordinates('E', 6));
    game.undo();
    assertEquals(ChessPieceNames.PAWN, game.getBoard().getPieceAt(new Coordinates('E', 7)).getPieceName());

//    FIXME: Revisar
//    assertEquals(StatusOptions.FAILURE, game.playTurn(new Coordinates('E', 8), new Coordinates('D', 8)));
  }

  @Test
  public void testCrowningPawn(){
    TestChessBoardGenerator testBoardGenerator = new TestChessBoardGenerator();
    testBoardGenerator.generateKing(new Coordinates('E', 1), PieceColor.WHITE);
    testBoardGenerator.generateKing(new Coordinates('E', 8), PieceColor.BLACK);
    testBoardGenerator.generatePawn(new Coordinates('A', 7), PieceColor.WHITE);

    Game game = new TestChess(testBoardGenerator.generateBoard()).generateGame();

    game.playTurn(new Coordinates('A', 7), new Coordinates('A', 8));
    assertEquals(ChessPieceNames.QUEEN, game.getBoard().getPieceAt(new Coordinates('A', 8)).getPieceName());
  }

  @Test
  public void testCheckmateCrowning(){
    TestChessBoardGenerator testBoardGenerator = new TestChessBoardGenerator();
    testBoardGenerator.generateKing(new Coordinates('E', 1), PieceColor.WHITE);
    testBoardGenerator.generateKing(new Coordinates('E', 8), PieceColor.BLACK);
    testBoardGenerator.generateQueen(new Coordinates('A', 7), PieceColor.WHITE);
    testBoardGenerator.generateRook(new Coordinates('B', 7), PieceColor.WHITE);

    Game game = new TestChess(testBoardGenerator.generateBoard()).generateGame();

    assertEquals(ChessStatusOptions.WHITE_CHECKMATE, game.playTurn(new Coordinates('A', 7), new Coordinates('A', 8)));
  }
}
