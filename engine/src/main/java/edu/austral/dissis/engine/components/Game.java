package edu.austral.dissis.engine.components;

import edu.austral.dissis.engine.move.Mover;
import edu.austral.dissis.engine.enums.PieceColor;
import edu.austral.dissis.engine.enums.StatusOptions;
import edu.austral.dissis.engine.move.SpecialMover;
import edu.austral.dissis.engine.referee.MoveReferee;
import edu.austral.dissis.engine.referee.StatusReferee;
import edu.austral.dissis.engine.validators.endOfGameValidators.EndOfGameValidator;

import java.util.List;
public class Game {
  private final List<SpecialMover> specialMovers;
  private final List<EndOfGameValidator> endOfGameValidators;
  private final Board board;
  private final PieceColor playerTurn;
  private final StatusOptions status;
  private final GameHistory gameHistory;

  public Game(Board startingPosition, List<EndOfGameValidator> endOfGameValidators, List<SpecialMover> specialMovers, PieceColor startingColor) {
    this.board = startingPosition;
    this.endOfGameValidators = endOfGameValidators;
    this.specialMovers = specialMovers;
    this.playerTurn = startingColor;
    this.status = StatusOptions.NORMAL;
    this.gameHistory = new GameHistory();
  }

  private Game(Board board, List<EndOfGameValidator> endOfGameValidators, List<SpecialMover> specialMovers, PieceColor playerTurn, StatusOptions status, GameHistory gameHistory) {
    this.board = board;
    this.endOfGameValidators = endOfGameValidators;
    this.specialMovers = specialMovers;
    this.playerTurn = playerTurn;
    this.status = status;
    this.gameHistory = gameHistory;
  }

  public Game playTurn(Coordinates from, Coordinates to) {
    MoveReferee moveReferee = new MoveReferee(playerTurn, getBoard());

    if (getBoard().isEmptySquare(from) || !isYourTurn(from)) {
      return new Game(getBoard(), endOfGameValidators, specialMovers, playerTurn, StatusOptions.FAILURE, gameHistory);
    }

    if (moveReferee.isValidMove(from, to)) {
      Mover mover = new Mover(getBoard(), this.specialMovers, from, to);

      Board nextBoard = mover.getNextBoard();
      PieceColor nextTurnColor = mover.getNextColorTurn(nextBoard);

      StatusOptions nextStatus = StatusReferee.getStatus(nextTurnColor, nextBoard, endOfGameValidators);

      Game nextGame = new Game(nextBoard, endOfGameValidators, specialMovers, nextTurnColor, nextStatus, gameHistory);
      gameHistory.save(this);
      return nextGame;
    } else {
      return new Game(getBoard(), endOfGameValidators, specialMovers, playerTurn, StatusOptions.FAILURE, gameHistory);
    }
  }

  private boolean isYourTurn(Coordinates from) {
    return getBoard().getColorAt(from).equals(playerTurn);
  }

  public PieceColor getPlayerTurnColor() {
    return playerTurn;
  }

  public StatusOptions getStatus() {
    return status;
  }

  public Board getBoard() {
    return board;
  }

  public Game undo() {
    if (!canUndo()) {
      throw new IllegalStateException("Cannot undo");
    }
    return gameHistory.undo(this);
  }

  public boolean canUndo() {
    return gameHistory.canUndo();
  }

  public Game redo() {
    if (!canRedo()) {
      throw new IllegalStateException("Cannot redo");
    }
    return gameHistory.redo(this);
  }

  public boolean canRedo() {
    return gameHistory.canRedo();
  }
}
