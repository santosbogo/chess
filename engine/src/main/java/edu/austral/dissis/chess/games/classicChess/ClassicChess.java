package edu.austral.dissis.chess.games.classicChess;

import edu.austral.dissis.engine.Game;
import edu.austral.dissis.engine.components.Board;
import edu.austral.dissis.engine.enums.PieceColor;
import edu.austral.dissis.engine.games.BoardGenerator;
import edu.austral.dissis.engine.games.GameGenerator;
import edu.austral.dissis.engine.validators.endOfGameValidators.EndOfGameValidator;
import edu.austral.dissis.chess.validators.endOfGameValidators.classicChess.CheckmateEndGameValidator;
import edu.austral.dissis.chess.validators.endOfGameValidators.classicChess.StalemateEndGameValidator;

import java.util.List;

public class ClassicChess implements GameGenerator {
  private final List<EndOfGameValidator> endOfGameValidators =
      List.of(new CheckmateEndGameValidator(), new StalemateEndGameValidator());

  @Override
  public Game generateGame() {
    return new Game(generateBoard(), endOfGameValidators, PieceColor.WHITE);
  }

  public Game generateGame(Board board) {
    return new Game(board, endOfGameValidators, PieceColor.WHITE);
  }

  private Board generateBoard() {
    BoardGenerator boardGenerator = new ClassicChessBoardGenerator();
    return boardGenerator.generateBoard();
  }

  public Board getStartingBoard() {
    return generateBoard();
  }

  public List<EndOfGameValidator> getEndOfGameValidators() {
    return endOfGameValidators;
  }

  public PieceColor getStartingPlayer() {
    return PieceColor.WHITE;
  }
}
