package edu.austral.dissis.chess.engine.ui;

import edu.austral.dissis.chess.engine.Game;
import edu.austral.dissis.chess.engine.components.Board;
import edu.austral.dissis.chess.engine.components.Coordinates;
import edu.austral.dissis.chess.engine.components.Piece;
import edu.austral.dissis.chess.engine.enums.PieceColor;
import edu.austral.dissis.chess.engine.enums.StatusOptions;
import edu.austral.dissis.chess.engine.games.classicChess.ClassicChess;
import edu.austral.dissis.chess.gui.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MySimpleGameEngine implements GameEngine {
    private final Game game;

    public MySimpleGameEngine(){
        this.game = new ClassicChess().generateGame();
    }

    @NotNull
    @Override
    public MoveResult applyMove(@NotNull Move move) {
        Coordinates from = new Coordinates(move.getFrom().getColumn(), move.getFrom().getRow());
        Coordinates to = new Coordinates(move.getTo().getColumn(), move.getTo().getRow());
        Board board = game.peekBoard();
        Piece piece = board.getPieceAt(from);

        StatusOptions status = game.playTurn(from, to);

        return  switch (status) {
            case FAILURE -> { yield new InvalidMove("Invalid move"); }
            case WHITE_CHECKMATE -> { yield new GameOver(PlayerColor.WHITE); }
            case BLACK_CHECKMATE -> { yield new GameOver(PlayerColor.BLACK); }
            default -> { yield new NewGameState(getListOfChessPieces(game.peekBoard()), getNextPlayerColor(), new UndoState(true, false)); }
        };
    }


    private List<ChessPiece> getListOfChessPieces(Board board) {
        List<ChessPiece> pieces = new ArrayList<>();
        for (Coordinates coordinates : board.getPieceDistribution().keySet()) {

            Piece piece = board.getPieceAt(coordinates);
            pieces.add(translatePiece(piece, coordinates));
        }
        return pieces;
    }

    private ChessPiece translatePiece(Piece piece, Coordinates coordinates) {
        String id = String.valueOf(piece.getId());
        PlayerColor color = translatePieceColor(piece.getColor());
        Position position = translateCoordinates(coordinates);
        String pieceName = piece.getPieceName().toString().toLowerCase();

        return new ChessPiece(id, color, position, pieceName);
    }

    private PlayerColor translatePieceColor(PieceColor color){
        return color.equals(PieceColor.WHITE) ? PlayerColor.WHITE : PlayerColor.BLACK;
    }

    private Position translateCoordinates(Coordinates coordinates){
        return new Position(coordinates.getY(), coordinates.getX());
    }

    private PlayerColor getNextPlayerColor() {
        if (game.getPlayerTurnColor().equals(PlayerColor.WHITE)) {
            return PlayerColor.BLACK;
        } else {
            return PlayerColor.WHITE;
        }
    }

    @NotNull
    @Override
    public InitialState init() {
        List<ChessPiece> pieces = getListOfChessPieces(game.peekBoard());
        return new InitialState(new BoardSize(8, 8), pieces, PlayerColor.WHITE);
    }

    @NotNull
    public NewGameState redo() {
        game.redo();

        return new NewGameState(getListOfChessPieces(game.peekBoard()), getNextPlayerColor(), new UndoState(true, game.canRedo()));
    }

    @NotNull
    public NewGameState undo() {
        game.redo();

        return new NewGameState(getListOfChessPieces(game.peekBoard()), getNextPlayerColor(), new UndoState(game.canUndo(), true));
    }


}
