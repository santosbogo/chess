package edu.austral.dissis.chess.engine.boards;

import edu.austral.dissis.chess.engine.coordinates.Coordinate;

public interface Board {

    public Board move(Coordinate from, Coordinate to);

    public void setStartingPosition();

//    public void checkBounds(Coordinate coordinate); //Me gustaria que sea private pero no se puede en una interfaz
}
