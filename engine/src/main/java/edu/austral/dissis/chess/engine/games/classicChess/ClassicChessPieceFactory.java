package edu.austral.dissis.chess.engine.games.classicChess;

import edu.austral.dissis.chess.engine.buenos.Piece;
import edu.austral.dissis.chess.engine.enums.PieceColor;
import edu.austral.dissis.chess.engine.enums.PieceName;
import edu.austral.dissis.chess.engine.games.PieceFactory;
import edu.austral.dissis.chess.engine.validators.moveValidators.MoveValidator;
import edu.austral.dissis.chess.engine.validators.moveValidators.OneOfMoveValidators;
import edu.austral.dissis.chess.engine.validators.moveValidators.classicChess.*;
import java.util.ArrayList;
import java.util.List;

public class ClassicChessPieceFactory implements PieceFactory {

  private final List<MoveValidator> sharedMoveValidators =
      List.of(
          new InBoundsMoveValidator(),
          new CantLeaveTheKingThreatenedMoveValidator(),
          new CantMoveFromEmptySquareMoveValidator(),
          new DiferentColorInTargetMoveValidator());

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
    List<MoveValidator> oneOfMoveValidators = new ArrayList<>();

    oneOfMoveValidators.add(new VerticalMoveValidator(1));
    //    oneOfMoveValidators.add(new FirstTwoStepsMoveValidator());
    //    oneOfMoveValidators.add(new EnPassantMoveValidator());

    piece.addMoveValidators(new OneOfMoveValidators(oneOfMoveValidators));
    piece.addMoveValidators(sharedMoveValidators);
    piece.addMoveValidators(new ByClearPathMoveValidator());
    piece.addMoveValidators(new JustForwardMoveValidator(pieceColor));

    return piece;
  }

  private Piece generateRook(PieceColor pieceColor) {
    Piece piece = new Piece(PieceName.ROOK, pieceColor);
    List<MoveValidator> oneOfMoveValidators = new ArrayList<>();

    oneOfMoveValidators.add(new VerticalMoveValidator());
    oneOfMoveValidators.add(new HorizontalMoveValidator());

    piece.addMoveValidators(new OneOfMoveValidators(oneOfMoveValidators));
    piece.addMoveValidators(sharedMoveValidators);
    piece.addMoveValidators(new ByClearPathMoveValidator());

    return piece;
  }

  private Piece generateKnight(PieceColor pieceColor) {
    Piece piece = new Piece(PieceName.KNIGHT, pieceColor);

    piece.addMoveValidators(sharedMoveValidators);
    piece.addMoveValidators(new ClassicKnightMoveValidator());

    return piece;
  }

  private Piece generateBishop(PieceColor pieceColor) {
    Piece piece = new Piece(PieceName.BISHOP, pieceColor);

    piece.addMoveValidators(sharedMoveValidators);
    piece.addMoveValidators(new DiagonalMoveValidator());
    piece.addMoveValidators(new ByClearPathMoveValidator());

    return piece;
  }

  private Piece generateQueen(PieceColor pieceColor) {
    Piece piece = new Piece(PieceName.QUEEN, pieceColor);
    List<MoveValidator> oneOfMoveValidators = new ArrayList<>();

    oneOfMoveValidators.add(new VerticalMoveValidator());
    oneOfMoveValidators.add(new HorizontalMoveValidator());
    oneOfMoveValidators.add(new DiagonalMoveValidator());

    piece.addMoveValidators(new OneOfMoveValidators(oneOfMoveValidators));
    piece.addMoveValidators(sharedMoveValidators);
    piece.addMoveValidators(new ByClearPathMoveValidator());

    return piece;
  }

  private Piece generateKing(PieceColor pieceColor) {
    Piece piece = new Piece(PieceName.KING, pieceColor);
    List<MoveValidator> oneOfMoveValidators = new ArrayList<>();

    oneOfMoveValidators.add(new VerticalMoveValidator(1));
    oneOfMoveValidators.add(new HorizontalMoveValidator(1));
    oneOfMoveValidators.add(new DiagonalMoveValidator(1));
    //    oneOfMoveValidators.add(new ClassicCastleMoveValidator(pieceColor));

    piece.addMoveValidators(new OneOfMoveValidators(oneOfMoveValidators));
    piece.addMoveValidators(sharedMoveValidators);
    piece.addMoveValidators(new ByClearPathMoveValidator());

    return piece;
  }
}
