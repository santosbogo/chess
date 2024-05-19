package edu.austral.dissis.chess.engine.testChess;

import edu.austral.dissis.chess.engine.board.Board;
import edu.austral.dissis.chess.engine.board.Coordinates;
import edu.austral.dissis.chess.engine.buenos.Piece;
import edu.austral.dissis.chess.engine.enums.PieceColor;
import edu.austral.dissis.chess.engine.enums.PieceName;
import edu.austral.dissis.chess.engine.games.BoardGenerator;
import edu.austral.dissis.chess.engine.games.PieceFactory;
import edu.austral.dissis.chess.engine.games.classicChess.ClassicChessPieceFactory;
import java.util.HashMap;

public class TestChessBoardGenerator implements BoardGenerator {
  PieceFactory pieceGenerator = new ClassicChessPieceFactory();
  HashMap<Coordinates, Piece> pieces = new HashMap<>();

  @Override
  public Board generateBoard() {
    return new Board(8, 8, pieces);
  }

  public void generatePawn(Coordinates coordinates, PieceColor pieceColor) {
    pieces.put(coordinates, pieceGenerator.generatePiece(PieceName.PAWN, pieceColor));
  }

  public void generateRook(Coordinates coordinates, PieceColor pieceColor) {
    pieces.put(coordinates, pieceGenerator.generatePiece(PieceName.ROOK, pieceColor));
  }

  public void generateKnight(Coordinates coordinates, PieceColor pieceColor) {
    pieces.put(coordinates, pieceGenerator.generatePiece(PieceName.KNIGHT, pieceColor));
  }

  public void generateBishop(Coordinates coordinates, PieceColor pieceColor) {
    pieces.put(coordinates, pieceGenerator.generatePiece(PieceName.BISHOP, pieceColor));
  }

  public void generateQueen(Coordinates coordinates, PieceColor pieceColor) {
    pieces.put(coordinates, pieceGenerator.generatePiece(PieceName.QUEEN, pieceColor));
  }

  public void generateKing(Coordinates coordinates, PieceColor pieceColor) {
    pieces.put(coordinates, pieceGenerator.generatePiece(PieceName.KING, pieceColor));
  }
}
