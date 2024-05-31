package edu.austral.dissis.checkers.games;

import edu.austral.dissis.checkers.enums.CheckersPieceNames;
import edu.austral.dissis.checkers.validators.moveValidators.ObligationToEatMoveValidator;
import edu.austral.dissis.chess.validators.moveValidators.classicChess.JustForwardMoveValidator;
import edu.austral.dissis.engine.components.Piece;
import edu.austral.dissis.engine.enums.PieceColor;
import edu.austral.dissis.engine.enums.PieceName;
import edu.austral.dissis.engine.games.PieceFactory;
import edu.austral.dissis.engine.validators.moveValidators.MoveValidator;
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
      case CHECKER -> generateChecker(pieceColor);
      case KING -> generateKing(pieceColor);
    };
  }

  private Piece generateChecker(PieceColor pieceColor) {
    List<MoveValidator> moveValidators = new ArrayList<>(sharedMoveValidators);

    moveValidators.add(new JustForwardMoveValidator(pieceColor));
    moveValidators.add(new DiagonalMoveValidator(1));

    return new Piece(CheckersPieceNames.CHECKER, pieceColor, moveValidators);
  }

  private Piece generateKing(PieceColor pieceColor) {
    List<MoveValidator> moveValidators = new ArrayList<>(sharedMoveValidators);

//    TODO: implement king move validators

    return new Piece(CheckersPieceNames.KING, pieceColor, moveValidators);
  }
}
