package edu.austral.dissis.chess.engine.games;


import edu.austral.dissis.chess.engine.boards.Board;
import edu.austral.dissis.chess.engine.coordinates.Coordinate;
import edu.austral.dissis.chess.engine.coordinates.RectangularCoordinate;
import edu.austral.dissis.chess.engine.pieces.Piece;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

interface Game {

    final List<Board> boardHistory = new ArrayList<>();

    void start();

    void move(RectangularCoordinate from, RectangularCoordinate to);

    List<Board> getHistoryMoves();

    //RULES:

    HashMap<Coordinate, Piece> startingPosition();

    boolean isPossibleMove(Piece piece, Coordinate from, Coordinate to);

    boolean isLegalMove(Piece piece, Coordinate from, Coordinate to);
}