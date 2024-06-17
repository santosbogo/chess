package edu.austral.dissis.engine.components;

import edu.austral.dissis.engine.enums.PieceColor;
import edu.austral.dissis.engine.enums.PieceName;
import edu.austral.dissis.engine.validators.movevalidators.MoveValidator;
import java.util.List;

public class Piece {
  private final List<MoveValidator> moveValidators;
  private final PieceColor pieceColor;
  private final PieceName pieceName;
  private final boolean isFirstMove;
  private static int ids = 1;
  private final int id;

  public Piece(PieceName pieceName, PieceColor pieceColor, List<MoveValidator> moveValidators) {
    this.pieceName = pieceName;
    this.pieceColor = pieceColor;
    this.moveValidators = moveValidators;
    this.isFirstMove = true;
    this.id = ids++;
  }

  // If i want to create a piece with a specific id, it means it is not the first move
  public Piece(
      PieceName pieceName, PieceColor pieceColor, List<MoveValidator> moveValidators, int id) {
    this.pieceName = pieceName;
    this.pieceColor = pieceColor;
    this.moveValidators = moveValidators;
    this.isFirstMove = false;
    this.id = id;
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

  public int getId() {
    return id;
  }
}
