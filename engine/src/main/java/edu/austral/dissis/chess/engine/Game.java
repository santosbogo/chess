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
import java.util.ArrayList;
import java.util.List;

public class Game {

  private final List<Board> boardHistory;
  List<EndOfGameValidator> endOfGameValidators;
  private PieceColor playerTurn;

  public Game() { // Default constructor (Classic Chess)
    ClassicChess classicChess = new ClassicChess();
    this.boardHistory = new ArrayList<>();
    this.boardHistory.add(classicChess.getStartingBoard());
    this.endOfGameValidators = classicChess.getEndOfGameValidators();
    this.playerTurn = classicChess.getStartingPlayer();
  }

  public Game(
      Board startingPosition,
      List<EndOfGameValidator> endOfGameValidators,
      PieceColor startingPlayer) {
    this.boardHistory = new ArrayList<>();
    this.boardHistory.add(startingPosition);
    this.endOfGameValidators = endOfGameValidators;
    this.playerTurn = startingPlayer;
  }

  public StatusOptions playTurn(Coordinates from, Coordinates to) {
    MoveReferee moveReferee = new MoveReferee(playerTurn, peekBoard());

    if (!isYourTurn(from)) {
      return StatusOptions.FAILURE;
    }

    if (moveReferee.isValidMove(from, to)) {
      BoardModifier boardModifier = new BoardModifier(peekBoard());
      boardHistory.add(boardModifier.move(from, to));
      changePlayerTurn();
      return StatusReferee.getStatus(playerTurn, peekBoard(), endOfGameValidators);
    } else {
      //            throw new IllegalArgumentException("Invalid move");
      return StatusOptions.FAILURE;
    }
  }

  private boolean isYourTurn(Coordinates from) {
    return peekBoard().getColorAt(from).equals(playerTurn);
  }

  private void changePlayerTurn() {
    playerTurn = playerTurn == PieceColor.WHITE ? PieceColor.BLACK : PieceColor.WHITE;
  }

  public Board peekBoard() {
    return boardHistory.getLast();
  }

  public Board undo() {
    boardHistory.removeLast();
    changePlayerTurn();
    return peekBoard();
  }
}
