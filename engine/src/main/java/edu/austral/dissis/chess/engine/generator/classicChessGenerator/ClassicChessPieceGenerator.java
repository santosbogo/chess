package edu.austral.dissis.chess.engine.generator.classicChessGenerator;

import edu.austral.dissis.chess.engine.enums.PieceColor;
import edu.austral.dissis.chess.engine.enums.PieceName;
import edu.austral.dissis.chess.engine.generator.PieceGenerator;
import edu.austral.dissis.chess.engine.pieces.Piece;

public class ClassicChessPieceGenerator implements PieceGenerator {

    @Override
    public Piece generatePiece(PieceName pieceName, PieceColor pieceColor) {
        return new Piece(pieceName, pieceColor);
    }
}
