package edu.austral.dissis.chess.engine.referee;

import edu.austral.dissis.chess.engine.board.Board;
import edu.austral.dissis.chess.engine.board.Coordinates;
import edu.austral.dissis.chess.engine.enums.PieceColor;
import edu.austral.dissis.chess.engine.validators.moveValidators.MoveValidator;

import java.util.List;

public class MoveReferee {
    PieceColor colorTurn;
    Board board;

    public MoveReferee(PieceColor colorTurn, Board board){
        this.colorTurn = colorTurn;
        this.board = board;
    }

    public boolean isValidMove(Coordinates from, Coordinates to) {
        List<MoveValidator> validators = board.getPieceAt(from).getMoveValidators();

        for (MoveValidator validator : validators) {
            if (!validator.validMove(from, to, board)) {
                return false;
            }
        }
        return true;
    }

    public boolean colorHasNoValidMoves(){
        for (int fromX = 1; fromX <= board.getXSize(); fromX++) {
            for (int fromY = 1; fromY <= board.getYSize(); fromY++) {
                Coordinates tempFrom = new Coordinates(fromX, fromY);

                if (!board.isEmptySquare(tempFrom) && board.getColorAt(tempFrom).equals(colorTurn)) {

                    for (int toX = 1; toX <= board.getXSize(); toX++) {
                        for (int toY = 1; toY <= board.getYSize(); toY++) {
                            Coordinates tempTo = new Coordinates(toX, toY);

                            if (isValidMove(tempFrom, tempTo)) {
                                return false;
                            }
                        }
                    }
                }
            }
        }

        return true;
    }
}
