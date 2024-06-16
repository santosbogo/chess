package edu.austral.dissis.engine.components;

import java.util.Stack;

public class GameHistory {
    private final Stack<Game> history;
    private final Stack<Game> redoStack;

    public GameHistory() {
        this.history = new Stack<>();
        this.redoStack = new Stack<>();
    }

    public void save(Game game) {
        history.push(game);
        redoStack.clear();
    }

    public Game undo(Game currentGame) {
        if (history.isEmpty()) {
            throw new IllegalStateException("Cannot undo");
        }
        redoStack.push(currentGame);
        return history.pop();
    }

    public Game redo(Game currentGame) {
        if (redoStack.isEmpty()) {
            throw new IllegalStateException("Cannot redo");
        }
        history.push(currentGame);
        return redoStack.pop();
    }

    public boolean canUndo() {
        return !history.isEmpty();
    }

    public boolean canRedo() {
        return !redoStack.isEmpty();
    }
}