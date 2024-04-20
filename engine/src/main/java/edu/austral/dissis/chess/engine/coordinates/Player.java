package edu.austral.dissis.chess.engine.coordinates;

import edu.austral.dissis.chess.engine.enums.PieceColor;

public class Player {

//    CONVIENE QUE ALMACENE UNA LISTA DE LAS PIEZAS QUE TIENE EN JUEGO????
//    Como las piezas no tienen informacion de la posicion en la que estan, no es tan util esta funcion
//    Cada pieza ademas tiene asignado un color por lo que no es necesario que el jugador tenga una lista de piezas
    private final PieceColor pieceColor;

    public Player(PieceColor pieceColor) {
        this.pieceColor = pieceColor;
    }

    public PieceColor getColor() {
        return pieceColor;
    }
}
