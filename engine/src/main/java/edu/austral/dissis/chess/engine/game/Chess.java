package edu.austral.dissis.chess.engine.game;

import edu.austral.dissis.chess.engine.boards.Board;
import edu.austral.dissis.chess.engine.coordinates.Coordinates;
import edu.austral.dissis.chess.engine.enums.GameStatus;
import edu.austral.dissis.chess.engine.enums.PieceColor;
import edu.austral.dissis.chess.engine.moves.Move;
import edu.austral.dissis.chess.engine.pieces.*;
import edu.austral.dissis.chess.engine.player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Chess {
    private final Player whitePlayer;
    private final Player blackPlayer;
    private final List<Board> boardHistory = new ArrayList<>();
    private Status status;

    public Chess(){
        this.whitePlayer = new Player(PieceColor.WHITE);
        this.blackPlayer = new Player(PieceColor.BLACK);
        
        setBoard(8, 8);
        startingPosition();
        
        this.status = new Status(peekLastBoard(), whitePlayer);
    }
    
    public void play(){
        Player playerTurn = whitePlayer;
        
        while(status.getStatus() != GameStatus.CHECKMATE || status.getStatus() != GameStatus.DRAW){
            playTurn(playerTurn);
            changeTurn();
        }
    }
    
    private void playTurn(Player player){
    }

    public void changeTurn(){
        if(status.getPlayerTurn() == whitePlayer){
            status = new Status(peekLastBoard(), blackPlayer);
        } else {
            status = new Status(peekLastBoard(), whitePlayer);
        }
    }


    private void setBoard(int xSize, int ySize) {
        boardHistory.add(new Board(xSize, ySize, startingPosition()));
    }

    private Board peekLastBoard(){
        return boardHistory.getLast();
    }

    public List<Board> getHistoryMoves() {
        return new ArrayList<>(boardHistory);
    }

    public HashMap<Coordinates, Piece> startingPosition() {
        HashMap<Coordinates, Piece> startingPosition = new HashMap<>();

        //White pieces
        for(int i = 1; i <= 8; i++){
            startingPosition.put(new Coordinates(i, 2), new Pawn(PieceColor.WHITE));
        }
        startingPosition.put(new Coordinates(1, 2), new Rook(PieceColor.WHITE));
        startingPosition.put(new Coordinates(8, 2), new Rook(PieceColor.WHITE));
        startingPosition.put(new Coordinates(2, 2), new Horse(PieceColor.WHITE));
        startingPosition.put(new Coordinates(7, 2), new Horse(PieceColor.WHITE));
        startingPosition.put(new Coordinates(3, 2), new Bishop(PieceColor.WHITE));
        startingPosition.put(new Coordinates(6, 2), new Bishop(PieceColor.WHITE));
        startingPosition.put(new Coordinates(4, 2), new Queen(PieceColor.WHITE));
        startingPosition.put(new Coordinates(5, 2), new King(PieceColor.WHITE));

        //Black pieces
        for(int i = 1; i <= 8; i++){
            startingPosition.put(new Coordinates(i, 7), new Pawn(PieceColor.BLACK));
        }
        startingPosition.put(new Coordinates(1, 7), new Rook(PieceColor.BLACK));
        startingPosition.put(new Coordinates(8, 7), new Rook(PieceColor.BLACK));
        startingPosition.put(new Coordinates(2, 7), new Horse(PieceColor.BLACK));
        startingPosition.put(new Coordinates(7, 7), new Horse(PieceColor.BLACK));
        startingPosition.put(new Coordinates(3, 7), new Bishop(PieceColor.BLACK));
        startingPosition.put(new Coordinates(6, 7), new Bishop(PieceColor.BLACK));
        startingPosition.put(new Coordinates(4, 7), new Queen(PieceColor.BLACK));
        startingPosition.put(new Coordinates(5, 7), new King(PieceColor.BLACK));

        return startingPosition;
    }
    

}
