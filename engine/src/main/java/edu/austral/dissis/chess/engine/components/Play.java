package edu.austral.dissis.chess.engine.components;

import edu.austral.dissis.chess.engine.coordinates.Interactor;
import edu.austral.dissis.chess.engine.coordinates.Player;
import edu.austral.dissis.chess.engine.coordinates.Rules;
import edu.austral.dissis.chess.engine.coordinates.referee.MoveReferee;
import edu.austral.dissis.chess.engine.coordinates.referee.RuleReferee;

import java.util.ArrayList;
import java.util.List;

public class Play {
    Board board;
    Player player1;
    Player player2;

    private final List<Board> boardHistory = new ArrayList<>();
    private final Interactor interactor = new Interactor();

    public Play(Board board, Rules rules, Player player1, Player player2){
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
    }

    public Player switchPlayer(Player player) {
        return player.equals(player1) ? player2 : player1;
    }

    public void play(){
        boardHistory.add(board);
        Player playerTurn = player1;

        while(RuleReferee.isGameNotOver(board)){
            board = peekLastBoard();
            playTurn(playerTurn);

            playerTurn = switchPlayer(playerTurn);
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

    private Board peekLastBoard(){
        return boardHistory.getLast();
    }

    public List<Board> getHistoryMoves() {
        return new ArrayList<>(boardHistory);
    }


}
