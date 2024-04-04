package edu.austral.dissis.chess.engine.interactor;

import edu.austral.dissis.chess.engine.coordinates.Coordinates;
import edu.austral.dissis.chess.engine.enums.PieceColor;

public class Interactor {
    public void colorTurnMessage(PieceColor color){
        if(color == PieceColor.WHITE)
            System.out.println("White plays");
        else
            System.out.println("Black plays");
    }

    public void invalidMoveMessage(){
        throw new IllegalStateException("Invalid move!");
    }

    public Coordinates getInputFromCoordinates(){
        return inputFromCoordinates();
    }

    public Coordinates getInputToCoordinates(){
        return inputToCoordinates();
    }

    private Coordinates inputFromCoordinates(){
        System.out.println("Input x from: ");
        int x = Integer.parseInt(System.console().readLine());
        System.out.println("\nInput y from: ");
        int y = Integer.parseInt(System.console().readLine());

        return new Coordinates(x, y);
    }

    private Coordinates inputToCoordinates(){
        System.out.println("Input x to: ");
        int x = Integer.parseInt(System.console().readLine());
        System.out.println("\nInput y to: ");
        int y = Integer.parseInt(System.console().readLine());

        return new Coordinates(x, y);
    }
}
