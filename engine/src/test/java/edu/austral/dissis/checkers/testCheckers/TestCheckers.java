package edu.austral.dissis.checkers.testCheckers;

import edu.austral.dissis.engine.Game;
import edu.austral.dissis.engine.components.Board;
import edu.austral.dissis.engine.enums.PieceColor;
import edu.austral.dissis.engine.games.GameGenerator;
import edu.austral.dissis.engine.moves.SpecialMover;
import edu.austral.dissis.engine.validators.endOfGameValidators.EndOfGameValidator;

import java.util.ArrayList;
import java.util.List;

public class TestCheckers implements GameGenerator {
  private final List<EndOfGameValidator> endOfGameValidators = new ArrayList<>();
  private final List<SpecialMover> specialMovers = List.of();

  Board board;

  public TestCheckers(Board board) {
    this.board = board;
  }

  @Override
  public Game generateGame() {
    return new Game(board, endOfGameValidators, specialMovers, PieceColor.WHITE);
  }

  public void startingPosition(){

  }
}
