package edu.austral.dissis.checkers.games;

import edu.austral.dissis.checkers.enums.PieceName;
import edu.austral.dissis.engine.components.Piece;
import edu.austral.dissis.engine.enums.PieceColor;
import edu.austral.dissis.engine.enums.PieceNameInterface;
import edu.austral.dissis.engine.games.PieceFactory;
import edu.austral.dissis.engine.validators.moveValidators.AllOfMoveValidators;
import edu.austral.dissis.engine.validators.moveValidators.MoveValidator;
import edu.austral.dissis.engine.validators.moveValidators.OneOfMoveValidators;
import edu.austral.dissis.engine.validators.moveValidators.sharedMoveValidators.DiagonalMoveValidator;

import java.util.ArrayList;
import java.util.List;

public class ClassicCheckersPieceFactory implements PieceFactory {

  private final List<MoveValidator> sharedMoveValidators = new ArrayList<>();

  @Override
  public Piece generatePiece(PieceNameInterface pieceName, PieceColor pieceColor) {
    return switch ((PieceName) pieceName) {
      case CHECKER -> generateChecker(pieceColor);
      case KING -> generateKing(pieceColor);
    };
  }

  private Piece generateChecker(PieceColor pieceColor) {
    List<MoveValidator> moveValidators = new ArrayList<>(sharedMoveValidators);

//    TODO: implement checker move validators

    return new Piece(PieceName.CHECKER, pieceColor, moveValidators);
  }

  private Piece generateKing(PieceColor pieceColor) {
    List<MoveValidator> moveValidators = new ArrayList<>(sharedMoveValidators);

//    TODO: implement king move validators

    return new Piece(PieceName.KING, pieceColor, moveValidators);
  }
}
