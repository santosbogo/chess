package edu.austral.dissis.chess.engine.games.classicChess;

import edu.austral.dissis.chess.engine.Game;
import edu.austral.dissis.chess.engine.board.Board;
import edu.austral.dissis.chess.engine.enums.PieceColor;
import edu.austral.dissis.chess.engine.games.BoardGenerator;
import edu.austral.dissis.chess.engine.games.GameGenerator;
import edu.austral.dissis.chess.engine.validators.endOfGameValidators.EndOfGameValidator;
import edu.austral.dissis.chess.engine.validators.endOfGameValidators.classicChess.CheckmateEndGameValidator;
import edu.austral.dissis.chess.engine.validators.endOfGameValidators.classicChess.StalemateEndGameValidator;

import java.util.List;

public class ClassicChess implements GameGenerator {
  private final List<EndOfGameValidator> endOfGameValidators = List.of(
          new CheckmateEndGameValidator(),
          new StalemateEndGameValidator()
  );

  @Override
  public Game generateGame() {
    return new Game(generateBoard(), endOfGameValidators, PieceColor.WHITE);
  }

  private Board generateBoard() {
    BoardGenerator boardGenerator = new ClassicChessBoardGenerator();
    return boardGenerator.generateBoard();
  }

}
