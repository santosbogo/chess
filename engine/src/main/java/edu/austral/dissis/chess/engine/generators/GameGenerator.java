package edu.austral.dissis.chess.engine.generators;

import edu.austral.dissis.chess.engine.components.Board;
import edu.austral.dissis.chess.engine.coordinates.Rules;

public interface GameGenerator {

    public Board generateBoard();

    public Rules generateRules();

    public void play();
}
