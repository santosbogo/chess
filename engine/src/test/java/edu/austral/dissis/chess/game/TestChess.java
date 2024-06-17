package edu.austral.dissis.chess.game;

import edu.austral.dissis.chess.enums.ChessPieceNames;
import edu.austral.dissis.chess.games.classic.ClassicChessPieceFactory;
import edu.austral.dissis.chess.moves.specialmovers.classic.CrownSpecialMover;
import edu.austral.dissis.chess.moves.specialmovers.classic.LongCastleSpecialMover;
import edu.austral.dissis.chess.moves.specialmovers.classic.ShortCastleMover;
import edu.austral.dissis.chess.validators.endofgamevalidators.classic.CheckmateEndGameValidator;
import edu.austral.dissis.chess.validators.endofgamevalidators.classic.StalemateEndGameValidator;
import edu.austral.dissis.engine.components.Board;
import edu.austral.dissis.engine.components.Game;
import edu.austral.dissis.engine.enums.PieceColor;
import edu.austral.dissis.engine.games.GameGenerator;
import edu.austral.dissis.engine.move.SpecialMover;
import edu.austral.dissis.engine.validators.endofgamevalidators.EndOfGameValidator;
import java.util.List;

public class TestChess implements GameGenerator {
  private final List<EndOfGameValidator> endOfGameValidators =
      List.of(new CheckmateEndGameValidator(), new StalemateEndGameValidator());

  private final List<SpecialMover> specialMovers =
      List.of(
          new ShortCastleMover(),
          new LongCastleSpecialMover(),
          new CrownSpecialMover(
              new ClassicChessPieceFactory()
                  .generatePiece(ChessPieceNames.QUEEN, PieceColor.WHITE)));

  private final Board board;

  public TestChess(Board board) {
    this.board = board;
  }

  @Override
  public Game generateGame() {
    return new Game(board, endOfGameValidators, specialMovers, PieceColor.WHITE);
  }
}
