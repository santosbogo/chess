package edu.austral.dissis.chess.testChess;

import edu.austral.dissis.engine.components.Board;
import edu.austral.dissis.engine.components.Coordinates;
import edu.austral.dissis.engine.components.Piece;
import edu.austral.dissis.engine.enums.PieceColor;
import edu.austral.dissis.chess.enums.ChessPieceNames;
import edu.austral.dissis.engine.games.BoardGenerator;
import edu.austral.dissis.engine.games.PieceFactory;
import edu.austral.dissis.chess.games.classicChess.ClassicChessPieceFactory;
import java.util.HashMap;
import java.util.Map;

public class TestChessBoardGenerator implements BoardGenerator {
  PieceFactory pieceGenerator;
  Map<Coordinates, Piece> pieces;

  public TestChessBoardGenerator(){
    this.pieceGenerator = new ClassicChessPieceFactory();
    this.pieces = new HashMap<>();
  }

  public TestChessBoardGenerator(Board board){
    this.pieceGenerator = new ClassicChessPieceFactory();
    this.pieces = board.getPieceDistribution();
  }

  @Override
  public Board generateBoard() {
    return new Board(8, 8, pieces);
  }

  public void generatePawn(Coordinates coordinates, PieceColor pieceColor) {
    pieces.put(coordinates, pieceGenerator.generatePiece(ChessPieceNames.PAWN, pieceColor));
  }

  public void generateRook(Coordinates coordinates, PieceColor pieceColor) {
    pieces.put(coordinates, pieceGenerator.generatePiece(ChessPieceNames.ROOK, pieceColor));
  }

  public void generateKnight(Coordinates coordinates, PieceColor pieceColor) {
    pieces.put(coordinates, pieceGenerator.generatePiece(ChessPieceNames.KNIGHT, pieceColor));
  }

  public void generateBishop(Coordinates coordinates, PieceColor pieceColor) {
    pieces.put(coordinates, pieceGenerator.generatePiece(ChessPieceNames.BISHOP, pieceColor));
  }

  public void generateQueen(Coordinates coordinates, PieceColor pieceColor) {
    pieces.put(coordinates, pieceGenerator.generatePiece(ChessPieceNames.QUEEN, pieceColor));
  }

  public void generateKing(Coordinates coordinates, PieceColor pieceColor) {
    pieces.put(coordinates, pieceGenerator.generatePiece(ChessPieceNames.KING, pieceColor));
  }
}
