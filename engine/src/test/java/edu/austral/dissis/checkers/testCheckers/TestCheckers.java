package edu.austral.dissis.checkers.testCheckers;

import edu.austral.dissis.checkers.enums.CheckersPieceNames;
import edu.austral.dissis.checkers.games.ClassicCheckersPieceFactory;
import edu.austral.dissis.checkers.moves.specialMovers.CrownSpecialMover;
import edu.austral.dissis.checkers.moves.specialMovers.EatSpecialMover;
import edu.austral.dissis.checkers.validators.endOfGameValidators.BoothPlayersCantMoveStalemateEndOfGameValidator;
import edu.austral.dissis.checkers.validators.endOfGameValidators.EatAllPiecesCheckmateEndOfGameValidator;
import edu.austral.dissis.checkers.validators.endOfGameValidators.OnePlayerCantMoveCheckmateEndOfGameValidator;
import edu.austral.dissis.engine.components.Game;
import edu.austral.dissis.engine.components.Board;
import edu.austral.dissis.engine.enums.PieceColor;
import edu.austral.dissis.engine.games.GameGenerator;
import edu.austral.dissis.engine.move.SpecialMover;
import edu.austral.dissis.engine.validators.endOfGameValidators.EndOfGameValidator;

import java.util.ArrayList;
import java.util.List;

public class TestCheckers implements GameGenerator {
  private final List<EndOfGameValidator> endOfGameValidators = List.of(
          new OnePlayerCantMoveCheckmateEndOfGameValidator(),
          new EatAllPiecesCheckmateEndOfGameValidator(),
          new BoothPlayersCantMoveStalemateEndOfGameValidator());

  private final List<SpecialMover> specialMovers = List.of(
          new CrownSpecialMover(new ClassicCheckersPieceFactory().generatePiece(CheckersPieceNames.KING, PieceColor.WHITE)),
          new EatSpecialMover());

  Board board;

  public TestCheckers(Board board) {
    this.board = board;
  }

  @Override
  public Game generateGame() {
    return new Game(board, endOfGameValidators, specialMovers, PieceColor.WHITE);
  }
}
