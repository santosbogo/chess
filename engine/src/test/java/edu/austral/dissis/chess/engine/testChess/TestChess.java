package edu.austral.dissis.chess.engine.testChess;

import edu.austral.dissis.chess.engine.Game;
import edu.austral.dissis.chess.engine.components.Board;
import edu.austral.dissis.chess.engine.enums.PieceColor;
import edu.austral.dissis.chess.engine.games.GameGenerator;
import edu.austral.dissis.chess.engine.validators.endOfGameValidators.EndOfGameValidator;
import edu.austral.dissis.chess.engine.validators.endOfGameValidators.classicChess.CheckmateEndGameValidator;
import edu.austral.dissis.chess.engine.validators.endOfGameValidators.classicChess.StalemateEndGameValidator;
import java.util.List;

public class TestChess implements GameGenerator {
  private final List<EndOfGameValidator> endOfGameValidators =
      List.of(new CheckmateEndGameValidator(), new StalemateEndGameValidator());

  Board board;

  public TestChess(Board board) {
    this.board = board;
  }

  @Override
  public Game generateGame() {
    return new Game(board, endOfGameValidators, PieceColor.WHITE);
  }
}
