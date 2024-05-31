package edu.austral.dissis.engine;

import edu.austral.dissis.engine.components.Board;
import edu.austral.dissis.engine.move.Mover;
import edu.austral.dissis.engine.components.Coordinates;
import edu.austral.dissis.engine.enums.PieceColor;
import edu.austral.dissis.chess.enums.ChessStatusOptions;
import edu.austral.dissis.engine.move.sharedSpecialMovers.SpecialMover;
import edu.austral.dissis.engine.referee.MoveReferee;
import edu.austral.dissis.engine.referee.StatusReferee;
import edu.austral.dissis.engine.validators.endOfGameValidators.EndOfGameValidator;

import java.util.List;
import java.util.Stack;

public class Game {

  private final Stack<Board> boardHistory = new Stack<>();
  private final Stack<Board> redoStack = new Stack<>();
  private final List<SpecialMover> specialMovers;
  List<EndOfGameValidator> endOfGameValidators;
  private PieceColor playerTurn;

  public Game(
      Board startingPosition,
      List<EndOfGameValidator> endOfGameValidators,
      List<SpecialMover> specialMovers,
      PieceColor startingPlayer) {
    this.boardHistory.push(startingPosition);
    this.endOfGameValidators = endOfGameValidators;
    this.specialMovers = specialMovers;
    this.playerTurn = startingPlayer;
  }

  public ChessStatusOptions playTurn(Coordinates from, Coordinates to) {
    MoveReferee moveReferee = new MoveReferee(playerTurn, getBoard());

    if (getBoard().isEmptySquare(from) || !isYourTurn(from)) {
      return ChessStatusOptions.FAILURE;
    }

    if (moveReferee.isValidMove(from, to)) {
      Mover mover = new Mover(getBoard(), this.specialMovers);
      boardHistory.push(mover.move(from, to));
      changePlayerTurn();
      redoStack.clear();
      Board board = getBoard();
      return StatusReferee.getStatus(playerTurn, board, endOfGameValidators);
    } else {
      return ChessStatusOptions.FAILURE;
    }
  }

  private boolean isYourTurn(Coordinates from) {
    return getBoard().getColorAt(from).equals(playerTurn);
  }

  public PieceColor getPlayerTurnColor() {
    return playerTurn;
  }

  private void changePlayerTurn() {
    playerTurn = playerTurn == PieceColor.WHITE ? PieceColor.BLACK : PieceColor.WHITE;
  }

  public Board getBoard() {
    return boardHistory.peek();
  }

  public Board undo() {
    if (canUndo()) {
      redoStack.push(boardHistory.pop());
      changePlayerTurn();
    }
    return getBoard();
  }

  public boolean canUndo() {
    return boardHistory.size() > 1;
  }

  public Board redo() {
    if (canRedo()) {
      boardHistory.push(redoStack.pop());
      changePlayerTurn();
    }
    return getBoard();
  }

  public boolean canRedo() {
    return !redoStack.isEmpty();
  }
}
