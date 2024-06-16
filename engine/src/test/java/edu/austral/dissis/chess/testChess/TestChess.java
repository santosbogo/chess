package edu.austral.dissis.chess.testChess;

import edu.austral.dissis.chess.enums.ChessPieceNames;
import edu.austral.dissis.chess.games.classicChess.ClassicChessPieceFactory;
import edu.austral.dissis.engine.components.Game;
import edu.austral.dissis.engine.components.Board;
import edu.austral.dissis.engine.enums.PieceColor;
import edu.austral.dissis.engine.games.GameGenerator;
import edu.austral.dissis.engine.move.SpecialMover;
import edu.austral.dissis.chess.moves.classicChessSpecialMovers.CrownSpecialMover;
import edu.austral.dissis.chess.moves.classicChessSpecialMovers.LongCastleSpecialMover;
import edu.austral.dissis.chess.moves.classicChessSpecialMovers.ShortCastleMover;
import edu.austral.dissis.engine.validators.endOfGameValidators.EndOfGameValidator;
import edu.austral.dissis.chess.validators.endOfGameValidators.classicChess.CheckmateEndGameValidator;
import edu.austral.dissis.chess.validators.endOfGameValidators.classicChess.StalemateEndGameValidator;

import java.util.List;

public class TestChess implements GameGenerator {
  private final List<EndOfGameValidator> endOfGameValidators =
      List.of(new CheckmateEndGameValidator(), new StalemateEndGameValidator());
  private final List<SpecialMover> specialMovers = List.of(new ShortCastleMover(),
          new LongCastleSpecialMover(),
          new CrownSpecialMover(new ClassicChessPieceFactory().generatePiece(ChessPieceNames.QUEEN, PieceColor.WHITE)));

  Board board;

  public TestChess(Board board) {
    this.board = board;
  }

  @Override
  public Game generateGame() {
    return new Game(board, endOfGameValidators, specialMovers, PieceColor.WHITE);
  }
}
