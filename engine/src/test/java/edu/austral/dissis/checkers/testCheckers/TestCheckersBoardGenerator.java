package edu.austral.dissis.checkers.testCheckers;

import edu.austral.dissis.checkers.games.ClassicCheckersPieceFactory;
import edu.austral.dissis.checkers.enums.CheckersPieceNames;
import edu.austral.dissis.engine.components.Board;
import edu.austral.dissis.engine.components.Coordinates;
import edu.austral.dissis.engine.components.Piece;
import edu.austral.dissis.engine.enums.PieceColor;
import edu.austral.dissis.engine.games.BoardGenerator;
import edu.austral.dissis.engine.games.PieceFactory;

import java.util.HashMap;
import java.util.Map;

public class TestCheckersBoardGenerator implements BoardGenerator {
  PieceFactory pieceGenerator;
  Map<Coordinates, Piece> pieces;

  public TestCheckersBoardGenerator(){
    this.pieceGenerator = new ClassicCheckersPieceFactory();
    this.pieces = new HashMap<>();
  }

  public TestCheckersBoardGenerator(Board board){
    this.pieceGenerator = new ClassicCheckersPieceFactory();
    this.pieces = board.getPieceDistribution();
  }

  @Override
  public Board generateBoard() {
    return new Board(8, 8, pieces);
  }

  public void generateChecker(Coordinates coordinates, PieceColor pieceColor) {
    pieces.put(coordinates, pieceGenerator.generatePiece(CheckersPieceNames.PAWN, pieceColor));
  }

  public void generateKing(Coordinates coordinates, PieceColor pieceColor) {
    pieces.put(coordinates, pieceGenerator.generatePiece(CheckersPieceNames.KING, pieceColor));
  }
}
