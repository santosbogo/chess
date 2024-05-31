package edu.austral.dissis.chess.games.classicChess;

import edu.austral.dissis.chess.enums.ChessPieceNames;
import edu.austral.dissis.engine.Game;
import edu.austral.dissis.engine.components.Board;
import edu.austral.dissis.engine.enums.PieceColor;
import edu.austral.dissis.engine.games.GameGenerator;
import edu.austral.dissis.engine.move.sharedSpecialMovers.SpecialMover;
import edu.austral.dissis.engine.move.sharedSpecialMovers.CrownSpecialMover;
import edu.austral.dissis.chess.moves.specialMovers.classicChessSpecialMovers.LongCastleSpecialMover;
import edu.austral.dissis.chess.moves.specialMovers.classicChessSpecialMovers.ShortCastleMover;
import edu.austral.dissis.engine.validators.endOfGameValidators.EndOfGameValidator;
import edu.austral.dissis.chess.validators.endOfGameValidators.classicChess.CheckmateEndGameValidator;
import edu.austral.dissis.chess.validators.endOfGameValidators.classicChess.StalemateEndGameValidator;

import java.util.List;

public class ClassicChess implements GameGenerator {
  private final List<EndOfGameValidator> endOfGameValidators = List.of(
          new CheckmateEndGameValidator(),
          new StalemateEndGameValidator());
  private final List<SpecialMover> specialMovers = List.of(new ShortCastleMover(),
          new LongCastleSpecialMover(),
          new CrownSpecialMover(new ClassicChessPieceFactory().generatePiece(ChessPieceNames.QUEEN, PieceColor.WHITE)));

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
