package edu.austral.dissis.chess.engine.buenos;

import edu.austral.dissis.chess.engine.enums.PieceColor;
import edu.austral.dissis.chess.engine.enums.PieceName;
import edu.austral.dissis.chess.engine.validators.moveValidators.MoveValidator;
import java.util.ArrayList;
import java.util.List;

public class Piece {
  private final List<MoveValidator> moveValidators = new ArrayList<>();
  private final PieceColor pieceColor;
  private final PieceName pieceName;
  boolean isFirstMove = true;

  public Piece(PieceName pieceName, PieceColor pieceColor) {
    this.pieceName = pieceName;
    this.pieceColor = pieceColor;
  }

  public void addMoveValidators(MoveValidator moveValidator) {
    moveValidators.add(moveValidator);
  }

  public void addMoveValidators(List<MoveValidator> moveValidator) {
    moveValidators.addAll(moveValidator);
  }


  public void setFirstMove() {
    isFirstMove = false;
  }

  public boolean isFirstMove() {
    return isFirstMove;
  }

  public PieceColor getColor() {
    return pieceColor;
  }

  public PieceName getPieceName() {
    return pieceName;
  }

  public List<MoveValidator> getMoveValidators() {
    return moveValidators;
  }
}
