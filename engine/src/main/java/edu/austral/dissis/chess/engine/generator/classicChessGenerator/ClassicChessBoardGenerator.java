package edu.austral.dissis.chess.engine.generator.classicChessGenerator;

import edu.austral.dissis.chess.engine.components.Board;
import edu.austral.dissis.chess.engine.coordinates.Coordinates;
import edu.austral.dissis.chess.engine.enums.PieceColor;
import edu.austral.dissis.chess.engine.enums.PieceName;
import edu.austral.dissis.chess.engine.generator.BoardGenerator;
import edu.austral.dissis.chess.engine.pieces.Piece;

import java.util.HashMap;

public class ClassicChessBoardGenerator implements BoardGenerator {
    ClassicChessPieceGenerator pieceGenerator = new ClassicChessPieceGenerator();

    @Override
    public Board generateBoard() {

        return null;
    }

    private void generateUpperPieces(PieceColor pieceColor, ClassicChessPieceGenerator pieceGenerator) {
        generatePawns(pieceColor, pieceGenerator);
        generateRooks(pieceColor, pieceGenerator);
        generateKnights(pieceColor, pieceGenerator);
        generateBishops(pieceColor, pieceGenerator);
        generateQueen(pieceColor, pieceGenerator);
        generateKing(pieceColor, pieceGenerator);
    }

    private void generatePawns(PieceColor pieceColor, ClassicChessPieceGenerator pieceGenerator) {
        for (char i = 'A'; i <= 'H'; i++) {
            pieces.put(new Coordinates(i, 1), pieceGenerator.generatePiece(PieceName.PAWN, pieceColor));
        }
    }

    private void generateRooks(PieceColor pieceColor, ClassicChessPieceGenerator pieceGenerator) {
        pieces.put(new Coordinates('A', 0), pieceGenerator.generatePiece(PieceName.ROOK, pieceColor));
        pieces.put(new Coordinates('H', 0), pieceGenerator.generatePiece(PieceName.ROOK, pieceColor));
    }

    private void generateKnights(PieceColor pieceColor, ClassicChessPieceGenerator pieceGenerator) {
        pieces.put(new Coordinates('B', 0), pieceGenerator.generatePiece(PieceName.KNIGHT, pieceColor));
        pieces.put(new Coordinates('G', 0), pieceGenerator.generatePiece(PieceName.KNIGHT, pieceColor));
    }

    private void generateBishops(PieceColor pieceColor, ClassicChessPieceGenerator pieceGenerator) {
        pieces.put(new Coordinates('C', 0), pieceGenerator.generatePiece(PieceName.BISHOP, pieceColor));
        pieces.put(new Coordinates('F', 0), pieceGenerator.generatePiece(PieceName.BISHOP, pieceColor));
    }

    private void generateQueen(PieceColor pieceColor, ClassicChessPieceGenerator pieceGenerator) {
        pieces.put(new Coordinates('D', 0), pieceGenerator.generatePiece(PieceName.QUEEN, pieceColor));
    }

    private void generateKing(PieceColor pieceColor, ClassicChessPieceGenerator pieceGenerator) {
        pieces.put(new Coordinates('E', 0), pieceGenerator.generatePiece(PieceName.KING, pieceColor));
    }


}
