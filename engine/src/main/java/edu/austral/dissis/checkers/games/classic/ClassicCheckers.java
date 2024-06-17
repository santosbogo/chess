package edu.austral.dissis.checkers.games.classic;

import edu.austral.dissis.checkers.enums.CheckersPieceNames;
import edu.austral.dissis.checkers.moves.specialmovers.CrownSpecialMover;
import edu.austral.dissis.checkers.moves.specialmovers.EatSpecialMover;
import edu.austral.dissis.checkers.validators.endofgamevalidators.classic.BoothPlayersCantMoveStalemateEndOfGameValidator;
import edu.austral.dissis.checkers.validators.endofgamevalidators.classic.EatAllPiecesCheckmateEndOfGameValidator;
import edu.austral.dissis.checkers.validators.endofgamevalidators.classic.OnePlayerCantMoveCheckmateEndOfGameValidator;
import edu.austral.dissis.engine.components.Board;
import edu.austral.dissis.engine.components.Game;
import edu.austral.dissis.engine.enums.PieceColor;
import edu.austral.dissis.engine.games.GameGenerator;
import edu.austral.dissis.engine.move.SpecialMover;
import edu.austral.dissis.engine.validators.endofgamevalidators.EndOfGameValidator;
import java.util.List;

public class ClassicCheckers implements GameGenerator {
  private final List<EndOfGameValidator> endOfGameValidators =
      List.of(
          new OnePlayerCantMoveCheckmateEndOfGameValidator(),
          new EatAllPiecesCheckmateEndOfGameValidator(),
          new BoothPlayersCantMoveStalemateEndOfGameValidator());

  private final List<SpecialMover> specialMovers =
      List.of(
          new CrownSpecialMover(
              new ClassicCheckersPieceFactory()
                  .generatePiece(CheckersPieceNames.KING, PieceColor.WHITE)),
          new EatSpecialMover());

  @Override
  public Game generateGame() {
    return new Game(generateBoard(), endOfGameValidators, specialMovers, PieceColor.WHITE);
  }

  private Board generateBoard() {
    return new ClassicCheckersBoardGenerator().generateBoard();
  }
}
