package edu.austral.dissis.chess.games.classic;

import edu.austral.dissis.chess.enums.ChessPieceNames;
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

public class ClassicChess implements GameGenerator {
  private final List<EndOfGameValidator> endOfGameValidators =
      List.of(new CheckmateEndGameValidator(), new StalemateEndGameValidator());
  private final List<SpecialMover> specialMovers =
      List.of(
          new ShortCastleMover(),
          new LongCastleSpecialMover(),
          new CrownSpecialMover(
              new ClassicChessPieceFactory()
                  .generatePiece(ChessPieceNames.QUEEN, PieceColor.WHITE)));

  @Override
  public Game generateGame() {
    return new Game(generateBoard(), endOfGameValidators, specialMovers, PieceColor.WHITE);
  }

  public Game generateGame(Board board) {
    return new Game(board, endOfGameValidators, specialMovers, PieceColor.WHITE);
  }

  private Board generateBoard() {
    return new ClassicChessBoardGenerator().generateBoard();
  }
}
