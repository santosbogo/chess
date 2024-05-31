package edu.austral.dissis.checkers.games;

import edu.austral.dissis.checkers.enums.CheckersPieceNames;
import edu.austral.dissis.engine.components.Board;
import edu.austral.dissis.engine.components.Coordinates;
import edu.austral.dissis.engine.components.Piece;
import edu.austral.dissis.engine.enums.PieceColor;
import edu.austral.dissis.engine.games.BoardGenerator;
import edu.austral.dissis.engine.games.PieceFactory;

import java.util.HashMap;
import java.util.Map;

public class ClassicCheckersBoardGenerator implements BoardGenerator {

  @Override
  public Board generateBoard() {
    return new Board(8, 8, generateStartingPosition());
  }

  private Map<Coordinates, Piece> generateStartingPosition() {
    PieceFactory pieceGenerator = new ClassicCheckersPieceFactory();
    HashMap<Coordinates, Piece> pieces = new HashMap<>();
    generatePieces(PieceColor.WHITE, pieceGenerator, pieces);
    generatePieces(PieceColor.BLACK, pieceGenerator, pieces);

    return pieces;
  }

  private void generatePieces(
      PieceColor pieceColor, PieceFactory pieceGenerator, HashMap<Coordinates, Piece> pieces) {
    generateWhiteCheckers(pieceGenerator, pieces);
    generateBlackCheckers(pieceGenerator, pieces);
  }

  private void generateWhiteCheckers(PieceFactory pieceGenerator, HashMap<Coordinates, Piece> pieces) {
    for (int x = 1; x <= 8; x++) {
      for (int y = 1; y <= 3; y++) {
        if ((x + y) % 2 == 0) {
          pieces.put(new Coordinates(x, y), pieceGenerator.generatePiece(CheckersPieceNames.CHECKER, PieceColor.WHITE));
        }
      }
    }
  }

  private void generateBlackCheckers(PieceFactory pieceGenerator, HashMap<Coordinates, Piece> pieces) {
    for (int x = 1; x <= 8; x++) {
      for (int y = 6; y <= 8; y++) {
        if ((x + y) % 2 == 0) {
          pieces.put(new Coordinates(x, y), pieceGenerator.generatePiece(CheckersPieceNames.CHECKER, PieceColor.BLACK));
        }
      }
    }
  }
}
