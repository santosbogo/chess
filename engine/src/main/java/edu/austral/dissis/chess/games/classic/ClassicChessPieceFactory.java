package edu.austral.dissis.chess.games.classic;

import edu.austral.dissis.chess.enums.ChessPieceNames;
import edu.austral.dissis.chess.validators.movevalidators.classic.CanOnlyEatMoveValidator;
import edu.austral.dissis.chess.validators.movevalidators.classic.CantLeaveTheKingThreatenedMoveValidator;
import edu.austral.dissis.chess.validators.movevalidators.classic.ClassicKnightMoveValidator;
import edu.austral.dissis.chess.validators.movevalidators.classic.HorizontalMoveValidator;
import edu.austral.dissis.engine.validators.movevalidators.shared.JustForwardMoveValidator;
import edu.austral.dissis.chess.validators.movevalidators.classic.LongCastleMoveValidator;
import edu.austral.dissis.chess.validators.movevalidators.classic.ShortCastleMoveValidator;
import edu.austral.dissis.chess.validators.movevalidators.classic.VerticalMoveValidator;
import edu.austral.dissis.engine.components.Piece;
import edu.austral.dissis.engine.enums.PieceColor;
import edu.austral.dissis.engine.enums.PieceName;
import edu.austral.dissis.engine.games.PieceFactory;
import edu.austral.dissis.engine.validators.movevalidators.AllOfMoveValidators;
import edu.austral.dissis.engine.validators.movevalidators.MoveValidator;
import edu.austral.dissis.engine.validators.movevalidators.OneOfMoveValidators;
import edu.austral.dissis.engine.validators.movevalidators.shared.ByClearPathMoveValidator;
import edu.austral.dissis.engine.validators.movevalidators.shared.CantGoToAlliesSquareMoveValidator;
import edu.austral.dissis.engine.validators.movevalidators.shared.CantGoToOpponentsSquareMoveValidator;
import edu.austral.dissis.engine.validators.movevalidators.shared.DiagonalMoveValidator;
import edu.austral.dissis.engine.validators.movevalidators.shared.InBoundsMoveValidator;
import java.util.ArrayList;
import java.util.List;

public class ClassicChessPieceFactory implements PieceFactory {

  private final List<MoveValidator> sharedMoveValidators =
      List.of(
          new InBoundsMoveValidator(),
          new CantLeaveTheKingThreatenedMoveValidator(),
          new CantGoToAlliesSquareMoveValidator());

  @Override
  public Piece generatePiece(PieceName pieceName, PieceColor pieceColor) {
    return switch ((ChessPieceNames) pieceName) {
      case PAWN -> generatePawn(pieceColor);
      case ROOK -> generateRook(pieceColor);
      case KNIGHT -> generateKnight(pieceColor);
      case BISHOP -> generateBishop(pieceColor);
      case QUEEN -> generateQueen(pieceColor);
      case KING -> generateKing(pieceColor);
    };
  }

  private Piece generatePawn(PieceColor pieceColor) {
    List<MoveValidator> oneOfMoveValidators = new ArrayList<>();

    // One step forward move
    List<MoveValidator> allOfMoveValidators1 = new ArrayList<>();
    allOfMoveValidators1.add(new VerticalMoveValidator(1));
    allOfMoveValidators1.add(new CantGoToOpponentsSquareMoveValidator());
    oneOfMoveValidators.add(new AllOfMoveValidators(allOfMoveValidators1));

    // Two steps forward first move
    List<MoveValidator> allOfMoveValidators2 = new ArrayList<>();
    allOfMoveValidators2.add(new VerticalMoveValidator(2, true));
    allOfMoveValidators2.add(new CantGoToOpponentsSquareMoveValidator());
    oneOfMoveValidators.add(new AllOfMoveValidators(allOfMoveValidators2));

    // One step forward eating move
    List<MoveValidator> allOfMoveValidators3 = new ArrayList<>();
    allOfMoveValidators3.add(new DiagonalMoveValidator(1));
    allOfMoveValidators3.add(new CanOnlyEatMoveValidator());
    oneOfMoveValidators.add(new AllOfMoveValidators(allOfMoveValidators3));

    //    oneOfMoveValidators.add(new EnPassantMoveValidator());

    List<MoveValidator> moveValidators = new ArrayList<>(sharedMoveValidators);
    moveValidators.add(new OneOfMoveValidators(oneOfMoveValidators));
    moveValidators.add(new ByClearPathMoveValidator());
    moveValidators.add(new JustForwardMoveValidator(pieceColor));

    return new Piece(ChessPieceNames.PAWN, pieceColor, moveValidators);
  }

  private Piece generateRook(PieceColor pieceColor) {
    List<MoveValidator> oneOfMoveValidators = new ArrayList<>();

    oneOfMoveValidators.add(new VerticalMoveValidator());
    oneOfMoveValidators.add(new HorizontalMoveValidator());

    List<MoveValidator> moveValidators = new ArrayList<>(sharedMoveValidators);
    moveValidators.add(new OneOfMoveValidators(oneOfMoveValidators));
    moveValidators.add(new ByClearPathMoveValidator());

    return new Piece(ChessPieceNames.ROOK, pieceColor, moveValidators);
  }

  private Piece generateKnight(PieceColor pieceColor) {
    List<MoveValidator> moveValidators = new ArrayList<>(sharedMoveValidators);

    moveValidators.add(new ClassicKnightMoveValidator());

    return new Piece(ChessPieceNames.KNIGHT, pieceColor, moveValidators);
  }

  private Piece generateBishop(PieceColor pieceColor) {
    List<MoveValidator> moveValidators = new ArrayList<>(sharedMoveValidators);

    moveValidators.add(new DiagonalMoveValidator());
    moveValidators.add(new ByClearPathMoveValidator());

    return new Piece(ChessPieceNames.BISHOP, pieceColor, moveValidators);
  }

  private Piece generateQueen(PieceColor pieceColor) {
    List<MoveValidator> oneOfMoveValidators = new ArrayList<>();

    oneOfMoveValidators.add(new VerticalMoveValidator());
    oneOfMoveValidators.add(new HorizontalMoveValidator());
    oneOfMoveValidators.add(new DiagonalMoveValidator());

    List<MoveValidator> moveValidators = new ArrayList<>(sharedMoveValidators);
    moveValidators.add(new OneOfMoveValidators(oneOfMoveValidators));
    moveValidators.add(new ByClearPathMoveValidator());

    return new Piece(ChessPieceNames.QUEEN, pieceColor, moveValidators);
  }

  private Piece generateKing(PieceColor pieceColor) {
    List<MoveValidator> oneOfMoveValidators = new ArrayList<>();

    oneOfMoveValidators.add(new VerticalMoveValidator(1));
    oneOfMoveValidators.add(new HorizontalMoveValidator(1));
    oneOfMoveValidators.add(new DiagonalMoveValidator(1));
    oneOfMoveValidators.add(new ShortCastleMoveValidator());
    oneOfMoveValidators.add(new LongCastleMoveValidator());

    List<MoveValidator> moveValidators = new ArrayList<>(sharedMoveValidators);
    moveValidators.add(new OneOfMoveValidators(oneOfMoveValidators));
    moveValidators.add(new ByClearPathMoveValidator());

    return new Piece(ChessPieceNames.KING, pieceColor, moveValidators);
  }
}
