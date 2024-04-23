package edu.austral.dissis.chess.engine.components;

import edu.austral.dissis.chess.engine.buenos.Board;
import edu.austral.dissis.chess.engine.coordinates.Interactor;
import edu.austral.dissis.chess.engine.components.referee.MoveReferee;
import edu.austral.dissis.chess.engine.buenos.Player;
import edu.austral.dissis.chess.engine.enums.StatusOptions;
import edu.austral.dissis.chess.engine.enums.PieceColor;

import java.util.ArrayList;
import java.util.List;

public class Chess {
    private final Player whitePlayer;
    private final Player blackPlayer;
    private final List<Board> boardHistory = new ArrayList<>();
    private final Interactor interactor = new Interactor();

    public Chess(){
        this.whitePlayer = new Player(PieceColor.WHITE);
        this.blackPlayer = new Player(PieceColor.BLACK);

        boardHistory.add(setBoard(8, 8, new Status(peekLastBoard(), whitePlayer)));
        startingPosition();

    }

    public void play(){
        Board board = peekLastBoard();

        while(board.getStatusInfo() != StatusOptions.CHECKMATE || board.getStatusInfo() != StatusOptions.DRAW){
            board = peekLastBoard(); //Queda medio feo repetir. Buscar si existe una mejor forma de hacerlo
            Player playerTurn = board.getStatus().getPlayerTurn();

            interactor.colorTurnMessage(playerTurn.getColor());
            playTurn(playerTurn);
        }
    }

    private void playTurn(Player player){
        MoveReferee moveReferee = new MoveReferee(peekLastBoard(), interactor.getInputFromCoordinates(), interactor.getInputToCoordinates());
        Board board = peekLastBoard();

        if (!moveReferee.isValid()) {
            interactor.invalidMoveMessage();
            playTurn(player);
        } else {
            updateBoardHistory(board, board.movePiece(moveReferee));
        }
    }

    public Player switchPlayer(Player player) {
        if(player == whitePlayer) {
            return blackPlayer;
        } else {
            return whitePlayer;
        }
    }

    private Status generateNewStatus(Board board){
//        Player otherPlayer = switchPlayer(board.getStatus().getPlayerTurn());
//        return new Status(board, otherPlayer);
        return null;
    }

    private void updateBoardHistory(Board oldBoard, Board newBoard){
        boardHistory.add(new Board(oldBoard.getXSize(), oldBoard.getYSize(), newBoard.getPieceDistribution()));
    }

    private Board setBoard(int xSize, int ySize, Status status) {
        return new Board(xSize, ySize, startingPosition(), status);
    }

    private Board peekLastBoard(){
        return boardHistory.getLast();
    }

    public List<Board> getHistoryMoves() {
        return new ArrayList<>(boardHistory);
    }


}
