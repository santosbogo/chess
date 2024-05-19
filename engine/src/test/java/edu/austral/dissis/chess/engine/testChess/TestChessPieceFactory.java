package edu.austral.dissis.chess.engine.testChess;

import edu.austral.dissis.chess.engine.buenos.Piece;
import edu.austral.dissis.chess.engine.enums.PieceColor;
import edu.austral.dissis.chess.engine.enums.PieceName;
import edu.austral.dissis.chess.engine.games.PieceFactory;
import edu.austral.dissis.chess.engine.validators.moveValidators.MoveValidator;
import edu.austral.dissis.chess.engine.validators.moveValidators.classicChess.CantLeaveTheKingThreatenedMoveValidator;
import edu.austral.dissis.chess.engine.validators.moveValidators.classicChess.InBoundsMoveValidator;
import java.util.List;

public class TestChessPieceFactory implements PieceFactory {
  private final List<MoveValidator> sharedMoveValidators =
      List.of(new InBoundsMoveValidator(), new CantLeaveTheKingThreatenedMoveValidator());

  @Override
  public Piece generatePiece(PieceName pieceName, PieceColor pieceColor) {
    return switch (pieceName) {
      case PAWN -> generatePawn(pieceColor);
      case ROOK -> generateRook(pieceColor);
      case KNIGHT -> generateKnight(pieceColor);
      case BISHOP -> generateBishop(pieceColor);
      case QUEEN -> generateQueen(pieceColor);
      case KING -> generateKing(pieceColor);
    };
  }

  private Piece generatePawn(PieceColor pieceColor) {
    Piece piece = new Piece(PieceName.PAWN, pieceColor);

    piece.addMoveValidators(sharedMoveValidators);

    return piece;
  }

  private Piece generateRook(PieceColor pieceColor) {
    Piece piece = new Piece(PieceName.ROOK, pieceColor);

    piece.addMoveValidators(sharedMoveValidators);

    return piece;
  }

  private Piece generateKnight(PieceColor pieceColor) {
    Piece piece = new Piece(PieceName.KNIGHT, pieceColor);

    piece.addMoveValidators(sharedMoveValidators);

    return piece;
  }

  private Piece generateBishop(PieceColor pieceColor) {
    Piece piece = new Piece(PieceName.BISHOP, pieceColor);

    piece.addMoveValidators(sharedMoveValidators);

    return piece;
  }

  private Piece generateQueen(PieceColor pieceColor) {
    Piece piece = new Piece(PieceName.QUEEN, pieceColor);

    piece.addMoveValidators(sharedMoveValidators);

    return piece;
  }

  private Piece generateKing(PieceColor pieceColor) {
    Piece piece = new Piece(PieceName.KING, pieceColor);

    piece.addMoveValidators(sharedMoveValidators);

    return piece;
  }
}
