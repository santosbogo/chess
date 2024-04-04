package edu.austral.dissis.chess.engine.game;

import edu.austral.dissis.chess.engine.boards.Board;
import edu.austral.dissis.chess.engine.coordinates.Coordinates;
import edu.austral.dissis.chess.engine.enums.StatusInfo;
import edu.austral.dissis.chess.engine.moves.Move;
import edu.austral.dissis.chess.engine.pieces.Piece;
import edu.austral.dissis.chess.engine.player.Player;

public class Status {
    private final Board board;;
    private final Player playerTurn;

    public Status(Board board, Player playerTurn){
        this.board = board;
        this.playerTurn = playerTurn;
    }

    public StatusInfo getStatusInfo(){
        if(isCheck()){
            return StatusInfo.CHECK;
        }
        if(isCheckMate()){
            return StatusInfo.CHECKMATE;
        }
        if(isDraw()){
            return StatusInfo.DRAW;
        }
        return StatusInfo.NORMAL;
    }

    public Player getPlayerTurn() {
        return playerTurn;
    }

    private boolean isCheckMate(){

        Coordinates kingCoordinates = board.getKingLocation(playerTurn.getColor());

        if (!isCheck())
            return false;

        for (int i = -1; i < 2; i++)
            for (int j = -1; j < 2; j++) {
                if (i == 0 && j == 0)
                    continue;
                Coordinates next = new Coordinates(kingCoordinates.getX() + i, kingCoordinates.getY() + j);
                if (new Move(board, kingCoordinates, next).isMoveValid()
                        && !board.isSquareThreatened(next)) {
                    return false;
                }
            }

        return true;
    }

    private boolean isCheck(){
        Coordinates kingCoordinates = board.getKingLocation(playerTurn.getColor());
        return board.isSquareThreatened(kingCoordinates);
    }

    private boolean isDraw(){
        if (isCheck())
            return false;

        for (int i = 0; i < board.getXSize(); i++) {
            for (int j = 0; j < board.getYSize(); j++) {

                Coordinates tempCoordinates = new Coordinates(i, j);
                Piece piece = board.getPieceAt(tempCoordinates);

                if (piece != null
                        && piece.getColor().equals(playerTurn.getColor())
                        && piece.canMove(board, tempCoordinates))
                    return false;
            }
        }
        return true;
    }

}
