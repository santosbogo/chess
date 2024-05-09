package edu.austral.dissis.chess.engine.buenos;

import edu.austral.dissis.chess.engine.board.Coordinates;
import edu.austral.dissis.chess.engine.enums.PieceColor;

public class Interactor {
  public void colorTurnMessage(PieceColor color) {
    if (color == PieceColor.WHITE) System.out.println("White plays");
    else System.out.println("Black plays");
  }

  public void invalidMoveMessage() {
    throw new IllegalStateException("Invalid move!");
  }

  public Coordinates getInputFromCoordinates() {
    return inputFromCoordinates();
  }

  public Coordinates getInputToCoordinates() {
    return inputToCoordinates();
  }

  public Coordinates FromCoordinates() {
    return inputFromCoordinates();
  }

  public Coordinates ToCoordinates() {
    return inputToCoordinates();
  }

  private Coordinates inputFromCoordinates() {
    System.out.print("\nInput x from: ");
    int x = Integer.parseInt(System.console().readLine());
    System.out.print("\nInput y from: ");
    int y = Integer.parseInt(System.console().readLine());

    return new Coordinates(x, y);
  }

  private Coordinates inputToCoordinates() {
    System.out.print("\nInput x to: ");
    int x = Integer.parseInt(System.console().readLine());
    System.out.print("\nInput y to: ");
    int y = Integer.parseInt(System.console().readLine());

    return new Coordinates(x, y);
  }
}
