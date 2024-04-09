package edu.austral.dissis.chess.engine.game;

import edu.austral.dissis.chess.engine.boards.Board;
import edu.austral.dissis.chess.engine.coordinates.Coordinates;
import edu.austral.dissis.chess.engine.enums.StatusInfo;
import edu.austral.dissis.chess.engine.enums.PieceColor;
import edu.austral.dissis.chess.engine.interactor.Interactor;
import edu.austral.dissis.chess.engine.moves.Move;
import edu.austral.dissis.chess.engine.pieces.*;
import edu.austral.dissis.chess.engine.player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        while(board.getStatusInfo() != StatusInfo.CHECKMATE || board.getStatusInfo() != StatusInfo.DRAW){
            board = peekLastBoard(); //Queda medio feo repetir. Buscar si existe una mejor forma de hacerlo
            Player playerTurn = board.getStatus().getPlayerTurn();

            interactor.colorTurnMessage(playerTurn.getColor());
            playTurn(playerTurn);
        }
    }

    private void playTurn(Player player){
        Move move = new Move(peekLastBoard(), interactor.getInputFromCoordinates(), interactor.getInputToCoordinates());
        Board board = peekLastBoard();

        if (!move.isMoveValid()) {
            interactor.invalidMoveMessage();
            playTurn(player);
        } else {
            updateBoardHistory(board, board.movePiece(move));
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
        Player otherPlayer = switchPlayer(board.getStatus().getPlayerTurn());
        return new Status(board, otherPlayer);
    }

    private void updateBoardHistory(Board oldBoard, Board newBoard){
        boardHistory.add(new Board(oldBoard.getXSize(), oldBoard.getYSize(), newBoard.getBoard(), generateNewStatus(newBoard)));
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

    public Map<Coordinates, Piece> startingPosition() {
        Map<Coordinates, Piece> startingPosition = new HashMap<>();

        //White pieces
        for(int i = 1; i <= 8; i++){
            startingPosition.put(new Coordinates(i, 2), new Pawn(PieceColor.WHITE, new Coordinates(i, 2)));
        }
        startingPosition.put(new Coordinates(1, 1), new Rook(PieceColor.WHITE, new Coordinates(1, 1)));
        startingPosition.put(new Coordinates(8, 1), new Rook(PieceColor.WHITE, new Coordinates(8, 1)));
        startingPosition.put(new Coordinates(2, 1), new Horse(PieceColor.WHITE, new Coordinates(2, 1)));
        startingPosition.put(new Coordinates(7, 1), new Horse(PieceColor.WHITE, new Coordinates(7, 1)));
        startingPosition.put(new Coordinates(3, 1), new Bishop(PieceColor.WHITE, new Coordinates(3, 1)));
        startingPosition.put(new Coordinates(6, 1), new Bishop(PieceColor.WHITE, new Coordinates(6, 1)));
        startingPosition.put(new Coordinates(4, 1), new Queen(PieceColor.WHITE, new Coordinates(4, 1)));
        startingPosition.put(new Coordinates(5, 1), new King(PieceColor.WHITE, new Coordinates(5, 1)));

        //Black pieces
        for(int i = 1; i <= 8; i++){
            startingPosition.put(new Coordinates(i, 7), new Pawn(PieceColor.BLACK, new Coordinates(i, 7)));
        }
        startingPosition.put(new Coordinates(1, 8), new Rook(PieceColor.BLACK, new Coordinates(1, 8)));
        startingPosition.put(new Coordinates(8, 8), new Rook(PieceColor.BLACK, new Coordinates(8, 8)));
        startingPosition.put(new Coordinates(2, 8), new Horse(PieceColor.BLACK, new Coordinates(2, 8)));
        startingPosition.put(new Coordinates(7, 8), new Horse(PieceColor.BLACK, new Coordinates(7, 8)));
        startingPosition.put(new Coordinates(3, 8), new Bishop(PieceColor.BLACK, new Coordinates(3, 8)));
        startingPosition.put(new Coordinates(6, 8), new Bishop(PieceColor.BLACK, new Coordinates(6, 8)));
        startingPosition.put(new Coordinates(4, 8), new Queen(PieceColor.BLACK, new Coordinates(4, 8)));
        startingPosition.put(new Coordinates(5, 8), new King(PieceColor.BLACK, new Coordinates(5, 8)));

        return startingPosition;
    }
}
