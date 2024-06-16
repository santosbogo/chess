package edu.austral.dissis.checkers.games;

import edu.austral.dissis.checkers.enums.CheckersPieceNames;
import edu.austral.dissis.checkers.validators.moveValidators.IsOneEnemyPieceBetweenMoveValidator;
import edu.austral.dissis.checkers.validators.moveValidators.ObligationToEatMoveValidator;
import edu.austral.dissis.chess.validators.moveValidators.classicChess.ByClearPathMoveValidator;
import edu.austral.dissis.chess.validators.moveValidators.classicChess.JustForwardMoveValidator;
import edu.austral.dissis.chess.validators.moveValidators.classicChess.VerticalMoveValidator;
import edu.austral.dissis.engine.components.Piece;
import edu.austral.dissis.engine.enums.PieceColor;
import edu.austral.dissis.engine.enums.PieceName;
import edu.austral.dissis.engine.games.PieceFactory;
import edu.austral.dissis.engine.validators.moveValidators.AllOfMoveValidators;
import edu.austral.dissis.engine.validators.moveValidators.MoveValidator;
import edu.austral.dissis.engine.validators.moveValidators.OneOfMoveValidators;
import edu.austral.dissis.engine.validators.moveValidators.sharedMoveValidators.*;

import java.util.ArrayList;
import java.util.List;

public class ClassicCheckersPieceFactory implements PieceFactory {

  private final List<MoveValidator> sharedMoveValidators = List.of(
          new ObligationToEatMoveValidator(),
          new InBoundsMoveValidator(),
          new CantGoToOpponentsSquareMoveValidator(),
          new CantGoToAlliesSquareMoveValidator()
  );

  @Override
  public Piece generatePiece(PieceName pieceName, PieceColor pieceColor) {
    return switch ((CheckersPieceNames) pieceName) {
      case PAWN -> generateChecker(pieceColor);
      case KING -> generateKing(pieceColor);
      default -> throw new IllegalArgumentException("Unexpected value: " + pieceName);
    };
  }

  private Piece generateChecker(PieceColor pieceColor) {
    List<MoveValidator> moveValidators = new ArrayList<>(sharedMoveValidators);
    List<MoveValidator> oneOfMoveValidators = new ArrayList<>();

    List<MoveValidator> allOfMoveValidators = new ArrayList<>();
    allOfMoveValidators.add(new DiagonalMoveValidator(2));
    allOfMoveValidators.add(new IsOneEnemyPieceBetweenMoveValidator());
    oneOfMoveValidators.add(new AllOfMoveValidators(allOfMoveValidators));

    oneOfMoveValidators.add(new DiagonalMoveValidator(1));
    moveValidators.add(new OneOfMoveValidators(oneOfMoveValidators));

    moveValidators.add(new JustForwardMoveValidator(pieceColor));

    return new Piece(CheckersPieceNames.PAWN, pieceColor, moveValidators);
  }

  private Piece generateKing(PieceColor pieceColor) {
    List<MoveValidator> moveValidators = new ArrayList<>(sharedMoveValidators);
    List<MoveValidator> oneOfMoveValidators = new ArrayList<>();

    List<MoveValidator> allOfMoveValidators1 = new ArrayList<>();
    allOfMoveValidators1.add(new IsOneEnemyPieceBetweenMoveValidator());
    allOfMoveValidators1.add(new DiagonalMoveValidator());
    oneOfMoveValidators.add(new AllOfMoveValidators(allOfMoveValidators1));

    List<MoveValidator> allOfMoveValidators2 = new ArrayList<>();
    allOfMoveValidators2.add(new ByClearPathMoveValidator());
    allOfMoveValidators2.add(new DiagonalMoveValidator());
    oneOfMoveValidators.add(new AllOfMoveValidators(allOfMoveValidators2));

    moveValidators.add(new OneOfMoveValidators(oneOfMoveValidators));

    return new Piece(CheckersPieceNames.KING, pieceColor, moveValidators);
  }
}
