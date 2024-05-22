package edu.austral.dissis.chess.engine.games.classicChess;

import edu.austral.dissis.chess.engine.components.Piece;
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
      case HORSE -> generateKnight(pieceColor);
      case BISHOP -> generateBishop(pieceColor);
      case QUEEN -> generateQueen(pieceColor);
      case KING -> generateKing(pieceColor);
    };
  }

  private Piece generatePawn(PieceColor pieceColor) {
    List<MoveValidator> moveValidators = new ArrayList<>(sharedMoveValidators);
    List<MoveValidator> oneOfMoveValidators = new ArrayList<>();

    oneOfMoveValidators.add(new VerticalMoveValidator(1));
    oneOfMoveValidators.add(new FirstTwoStepsMoveValidator());
    oneOfMoveValidators.add(new DiagonalOneStepCaptureMoveValidator());
    //    oneOfMoveValidators.add(new EnPassantMoveValidator());

    moveValidators.add(new OneOfMoveValidators(oneOfMoveValidators));
    moveValidators.add(new ByClearPathMoveValidator());
    moveValidators.add(new JustForwardMoveValidator(pieceColor));
    moveValidators.add(new CantEatVerticalMoveValidator());

    return new Piece(PieceName.PAWN, pieceColor, moveValidators);
  }

  private Piece generateRook(PieceColor pieceColor) {
    List<MoveValidator> moveValidators = new ArrayList<>(sharedMoveValidators);
    List<MoveValidator> oneOfMoveValidators = new ArrayList<>();

    oneOfMoveValidators.add(new VerticalMoveValidator());
    oneOfMoveValidators.add(new HorizontalMoveValidator());

    moveValidators.add(new OneOfMoveValidators(oneOfMoveValidators));
    moveValidators.add(new ByClearPathMoveValidator());

    return new Piece(PieceName.ROOK, pieceColor, moveValidators);
  }

  private Piece generateKnight(PieceColor pieceColor) {
    List<MoveValidator> moveValidators = new ArrayList<>(sharedMoveValidators);

     moveValidators.add(new ClassicKnightMoveValidator());

    return new Piece(PieceName.HORSE, pieceColor, moveValidators);
  }

  private Piece generateBishop(PieceColor pieceColor) {
    List<MoveValidator> moveValidators = new ArrayList<>(sharedMoveValidators);

    moveValidators.add(new DiagonalMoveValidator());
    moveValidators.add(new ByClearPathMoveValidator());

    return new Piece(PieceName.BISHOP, pieceColor, moveValidators);
  }

  private Piece generateQueen(PieceColor pieceColor) {
    List<MoveValidator> moveValidators = new ArrayList<>(sharedMoveValidators);
    List<MoveValidator> oneOfMoveValidators = new ArrayList<>();

    oneOfMoveValidators.add(new VerticalMoveValidator());
    oneOfMoveValidators.add(new HorizontalMoveValidator());
    oneOfMoveValidators.add(new DiagonalMoveValidator());

     moveValidators.add(new OneOfMoveValidators(oneOfMoveValidators));
     moveValidators.add(new ByClearPathMoveValidator());

    return new Piece(PieceName.QUEEN, pieceColor, moveValidators);
  }

  private Piece generateKing(PieceColor pieceColor) {
    List<MoveValidator> moveValidators = new ArrayList<>(sharedMoveValidators);
    List<MoveValidator> oneOfMoveValidators = new ArrayList<>();

    oneOfMoveValidators.add(new VerticalMoveValidator(1));
    oneOfMoveValidators.add(new HorizontalMoveValidator(1));
    oneOfMoveValidators.add(new DiagonalMoveValidator(1));
    oneOfMoveValidators.add(new ClassicShortCastleMoveValidator());
    oneOfMoveValidators.add(new ClassicLongCastleMoveValidator());

     moveValidators.add(new OneOfMoveValidators(oneOfMoveValidators));
     moveValidators.add(new ByClearPathMoveValidator());

    return new Piece(PieceName.KING, pieceColor, moveValidators);
  }
}
