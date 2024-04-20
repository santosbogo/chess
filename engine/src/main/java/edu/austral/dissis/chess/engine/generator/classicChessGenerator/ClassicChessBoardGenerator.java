package edu.austral.dissis.chess.engine.generator.classicChessGenerator;

import edu.austral.dissis.chess.engine.components.Board;
import edu.austral.dissis.chess.engine.coordinates.Coordinates;
import edu.austral.dissis.chess.engine.enums.PieceColor;
import edu.austral.dissis.chess.engine.enums.PieceName;
import edu.austral.dissis.chess.engine.generator.BoardGenerator;
import edu.austral.dissis.chess.engine.pieces.Piece;

import java.util.HashMap;


public class ClassicChessBoardGenerator implements BoardGenerator {
    @Override
    public Board generateBoard() {

        return null;
    }

    private HashMap<Coordinates, Piece> generateStartingPosition(){
        ClassicChessPieceGenerator pieceGenerator = new ClassicChessPieceGenerator();
        HashMap<Coordinates, Piece> pieces = new HashMap<>();
        generatePieces(PieceColor.WHITE, pieceGenerator, pieces);
        generatePieces(PieceColor.BLACK, pieceGenerator, pieces);

        return pieces;
    }

    private void generatePieces(PieceColor pieceColor, ClassicChessPieceGenerator pieceGenerator, HashMap<Coordinates, Piece> pieces) {
        generatePawns(pieceColor, pieceGenerator, pieces);
        generateRooks(pieceColor, pieceGenerator, pieces);
        generateKnights(pieceColor, pieceGenerator, pieces);
        generateBishops(pieceColor, pieceGenerator, pieces);
        generateQueen(pieceColor, pieceGenerator, pieces);
        generateKing(pieceColor, pieceGenerator, pieces);
    }

    private void generatePawns(PieceColor pieceColor, ClassicChessPieceGenerator pieceGenerator, HashMap<Coordinates, Piece> pieces) {
        int row = pieceColor.equals(PieceColor.WHITE) ? 2 : 7;
        for (char i = 'A'; i <= 'H'; i++) {
            pieces.put(new Coordinates(i, row), pieceGenerator.generatePiece(PieceName.PAWN, pieceColor));
        }
    }

    private void generateRooks(PieceColor pieceColor, ClassicChessPieceGenerator pieceGenerator, HashMap<Coordinates, Piece> pieces) {
        int row = pieceColor.equals(PieceColor.WHITE) ? 1 : 8;
        pieces.put(new Coordinates('A', row), pieceGenerator.generatePiece(PieceName.ROOK, pieceColor));
        pieces.put(new Coordinates('H', row), pieceGenerator.generatePiece(PieceName.ROOK, pieceColor));
    }

    private void generateKnights(PieceColor pieceColor, ClassicChessPieceGenerator pieceGenerator, HashMap<Coordinates, Piece> pieces) {
        int row = pieceColor.equals(PieceColor.WHITE) ? 1 : 8;
        pieces.put(new Coordinates('B', row), pieceGenerator.generatePiece(PieceName.KNIGHT, pieceColor));
        pieces.put(new Coordinates('G', row), pieceGenerator.generatePiece(PieceName.KNIGHT, pieceColor));
    }

    private void generateBishops(PieceColor pieceColor, ClassicChessPieceGenerator pieceGenerator, HashMap<Coordinates, Piece> pieces) {
        int row = pieceColor.equals(PieceColor.WHITE) ? 1 : 8;
        pieces.put(new Coordinates('C', row), pieceGenerator.generatePiece(PieceName.BISHOP, pieceColor));
        pieces.put(new Coordinates('F', row), pieceGenerator.generatePiece(PieceName.BISHOP, pieceColor));
    }

    private void generateQueen(PieceColor pieceColor, ClassicChessPieceGenerator pieceGenerator, HashMap<Coordinates, Piece> pieces) {
        int row = pieceColor.equals(PieceColor.WHITE) ? 1 : 8;
        pieces.put(new Coordinates('D', row), pieceGenerator.generatePiece(PieceName.QUEEN, pieceColor));
    }

    private void generateKing(PieceColor pieceColor, ClassicChessPieceGenerator pieceGenerator, HashMap<Coordinates, Piece> pieces) {
        int row = pieceColor.equals(PieceColor.WHITE) ? 1 : 8;
        pieces.put(new Coordinates('E', row), pieceGenerator.generatePiece(PieceName.KING, pieceColor));
    }
}
