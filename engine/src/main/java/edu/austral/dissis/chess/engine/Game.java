package edu.austral.dissis.chess.engine;

import edu.austral.dissis.chess.engine.board.Board;
import edu.austral.dissis.chess.engine.board.BoardModifier;
import edu.austral.dissis.chess.engine.board.Coordinates;
import edu.austral.dissis.chess.engine.enums.PieceColor;
import edu.austral.dissis.chess.engine.enums.StatusOptions;
import edu.austral.dissis.chess.engine.games.classicChess.ClassicChess;
import edu.austral.dissis.chess.engine.referee.MoveReferee;
import edu.austral.dissis.chess.engine.referee.StatusReferee;
import edu.austral.dissis.chess.engine.validators.endOfGameValidators.EndOfGameValidator;

import java.util.List;
import java.util.Stack;

public class Game {

  private final Stack<Board> boardHistory = new Stack<>();

  List<EndOfGameValidator> endOfGameValidators;
  private PieceColor playerTurn;

  public Game() { // Default constructor (Classic Chess)
    ClassicChess classicChess = new ClassicChess();
    this.boardHistory.push(classicChess.getStartingBoard());
    this.endOfGameValidators = classicChess.getEndOfGameValidators();
    this.playerTurn = classicChess.getStartingPlayer();
  }

  public Game(
      Board startingPosition,
      List<EndOfGameValidator> endOfGameValidators,
      PieceColor startingPlayer) {
    this.boardHistory.push(startingPosition);
    this.endOfGameValidators = endOfGameValidators;
    this.playerTurn = startingPlayer;
  }

  public StatusOptions playTurn(Coordinates from, Coordinates to) {
    MoveReferee moveReferee = new MoveReferee(playerTurn, peekBoard());

    if (!isYourTurn(from)) {
      return StatusOptions.FAILURE;
    }

    if (moveReferee.isValidMove(from, to)) {
      peekBoard().getPieceAt(from).setFirstMove();
      BoardModifier boardModifier = new BoardModifier(peekBoard());
      boardHistory.push(boardModifier.move(from, to));
      changePlayerTurn();
      return StatusReferee.getStatus(playerTurn, peekBoard(), endOfGameValidators);
    } else {
      return StatusOptions.FAILURE;
    }
  }

  private boolean isYourTurn(Coordinates from) {
    return peekBoard().getColorAt(from).equals(playerTurn);
  }

  public PieceColor getPlayerTurnColor() {
    return playerTurn;
  }

  private void changePlayerTurn() {
    playerTurn = playerTurn == PieceColor.WHITE ? PieceColor.BLACK : PieceColor.WHITE;
  }

  public Board peekBoard() {
    return boardHistory.peek();
  }

  public Board undo() {
    boardHistory.removeLast();
    changePlayerTurn();
    return peekBoard();
  }

  public Board redo() {
    changePlayerTurn();
    return peekBoard();
  }
}
