package edu.austral.dissis.chess.games.classicChess;

import edu.austral.dissis.engine.components.Board;
import edu.austral.dissis.engine.components.Coordinates;
import edu.austral.dissis.engine.components.Piece;
import edu.austral.dissis.engine.enums.PieceColor;
import edu.austral.dissis.chess.enums.ChessPieceNames;
import edu.austral.dissis.engine.games.BoardGenerator;
import edu.austral.dissis.engine.games.PieceFactory;
import java.util.HashMap;
import java.util.Map;

public class ClassicChessBoardGenerator implements BoardGenerator {

  @Override
  public Board generateBoard() {
    return new Board(8, 8, generateStartingPosition());
  }

  private Map<Coordinates, Piece> generateStartingPosition() {
    PieceFactory pieceGenerator = new ClassicChessPieceFactory();
    HashMap<Coordinates, Piece> pieces = new HashMap<>();
    generatePieces(PieceColor.WHITE, pieceGenerator, pieces);
    generatePieces(PieceColor.BLACK, pieceGenerator, pieces);

    return pieces;
  }

  private void generatePieces(
      PieceColor pieceColor, PieceFactory pieceGenerator, HashMap<Coordinates, Piece> pieces) {
    generatePawns(pieceColor, pieceGenerator, pieces);
    generateRooks(pieceColor, pieceGenerator, pieces);
    generateKnights(pieceColor, pieceGenerator, pieces);
    generateBishops(pieceColor, pieceGenerator, pieces);
    generateQueen(pieceColor, pieceGenerator, pieces);
    generateKing(pieceColor, pieceGenerator, pieces);
  }

  private void generatePawns(
      PieceColor pieceColor, PieceFactory pieceGenerator, HashMap<Coordinates, Piece> pieces) {
    int row = pieceColor.equals(PieceColor.WHITE) ? 2 : 7;
    for (char i = 'A'; i <= 'H'; i++) {
      pieces.put(new Coordinates(i, row), pieceGenerator.generatePiece(ChessPieceNames.PAWN, pieceColor));
    }
  }

  private void generateRooks(
      PieceColor pieceColor, PieceFactory pieceGenerator, HashMap<Coordinates, Piece> pieces) {
    int row = pieceColor.equals(PieceColor.WHITE) ? 1 : 8;
    pieces.put(new Coordinates('A', row), pieceGenerator.generatePiece(ChessPieceNames.ROOK, pieceColor));
    pieces.put(new Coordinates('H', row), pieceGenerator.generatePiece(ChessPieceNames.ROOK, pieceColor));
  }

  private void generateKnights(
      PieceColor pieceColor, PieceFactory pieceGenerator, HashMap<Coordinates, Piece> pieces) {
    int row = pieceColor.equals(PieceColor.WHITE) ? 1 : 8;
    pieces.put(
        new Coordinates('B', row), pieceGenerator.generatePiece(ChessPieceNames.KNIGHT, pieceColor));
    pieces.put(
        new Coordinates('G', row), pieceGenerator.generatePiece(ChessPieceNames.KNIGHT, pieceColor));
  }

  private void generateBishops(
      PieceColor pieceColor, PieceFactory pieceGenerator, HashMap<Coordinates, Piece> pieces) {
    int row = pieceColor.equals(PieceColor.WHITE) ? 1 : 8;
    pieces.put(
        new Coordinates('C', row), pieceGenerator.generatePiece(ChessPieceNames.BISHOP, pieceColor));
    pieces.put(
        new Coordinates('F', row), pieceGenerator.generatePiece(ChessPieceNames.BISHOP, pieceColor));
  }

  private void generateQueen(
      PieceColor pieceColor, PieceFactory pieceGenerator, HashMap<Coordinates, Piece> pieces) {
    int row = pieceColor.equals(PieceColor.WHITE) ? 1 : 8;
    pieces.put(
        new Coordinates('D', row), pieceGenerator.generatePiece(ChessPieceNames.QUEEN, pieceColor));
  }

  private void generateKing(
      PieceColor pieceColor, PieceFactory pieceGenerator, HashMap<Coordinates, Piece> pieces) {
    int row = pieceColor.equals(PieceColor.WHITE) ? 1 : 8;
    pieces.put(new Coordinates('E', row), pieceGenerator.generatePiece(ChessPieceNames.KING, pieceColor));
  }
}
