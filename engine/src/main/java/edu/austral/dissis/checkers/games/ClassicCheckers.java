package edu.austral.dissis.checkers.games;

import edu.austral.dissis.engine.Game;
import edu.austral.dissis.engine.components.Board;
import edu.austral.dissis.engine.enums.PieceColor;
import edu.austral.dissis.engine.games.GameGenerator;
import edu.austral.dissis.engine.move.sharedSpecialMovers.SpecialMover;
import edu.austral.dissis.engine.validators.endOfGameValidators.EndOfGameValidator;

import java.util.ArrayList;
import java.util.List;

public class ClassicCheckers implements GameGenerator {
    private final List<EndOfGameValidator> endOfGameValidators = new ArrayList<>();
    private final List<SpecialMover> specialMovers = List.of();

    @Override
    public Game generateGame() {
        return new Game(generateBoard(), endOfGameValidators,specialMovers,  PieceColor.WHITE);
    }

    private Board generateBoard() {
        return new ClassicCheckersBoardGenerator().generateBoard();
    }
}
