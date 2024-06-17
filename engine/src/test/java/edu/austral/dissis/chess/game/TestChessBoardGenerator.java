package edu.austral.dissis.chess.game;

import edu.austral.dissis.chess.enums.ChessPieceNames;
import edu.austral.dissis.chess.games.classic.ClassicChessPieceFactory;
import edu.austral.dissis.engine.components.Board;
import edu.austral.dissis.engine.components.Coordinates;
import edu.austral.dissis.engine.components.Piece;
import edu.austral.dissis.engine.enums.PieceColor;
import edu.austral.dissis.engine.games.BoardGenerator;
import edu.austral.dissis.engine.games.PieceFactory;
import java.util.HashMap;
import java.util.Map;

public class TestChessBoardGenerator implements BoardGenerator {
  private final PieceFactory pieceGenerator;
  private final Map<Coordinates, Piece> pieces;

  public TestChessBoardGenerator() {
    this.pieceGenerator = new ClassicChessPieceFactory();
    this.pieces = new HashMap<>();
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
