package edu.austral.dissis.chess.engine;

import edu.austral.dissis.chess.engine.board.Board;
import edu.austral.dissis.chess.engine.board.Coordinates;
import edu.austral.dissis.chess.engine.enums.PieceColor;
import edu.austral.dissis.chess.engine.enums.PieceName;
import edu.austral.dissis.chess.engine.enums.StatusOptions;
import edu.austral.dissis.chess.engine.games.classicChess.ClassicChess;
import edu.austral.dissis.chess.engine.games.classicChess.ClassicChessBoardGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClassicChessTests {

    @Test
    public void testStartingBoard() {
        Board board = new ClassicChessBoardGenerator().generateBoard();

        for (int i = 1; i <= 8; i++) {
            assertEquals(PieceColor.BLACK, board.getColorAt(new Coordinates(i, 8)));
            assertEquals(PieceColor.BLACK, board.getColorAt(new Coordinates(i, 7)));
        }

        for(int i = 1; i <= 8; i++){
            assertEquals(PieceColor.WHITE, board.getColorAt(new Coordinates(i,2)));
            assertEquals(PieceColor.WHITE, board.getColorAt(new Coordinates(i,1)));
        }

        for(int y = 2; y <= 8; y = y + 5){
            for(int x = 1; x <= 8; x++){
                assertEquals(PieceName.PAWN, board.getPieceAt(new Coordinates(x,y)).getPieceName());
            }
        }

        for(int y = 1; y <= 8; y = y + 7){
            assertEquals(PieceName.ROOK, board.getPieceAt(new Coordinates(1,y)).getPieceName());
            assertEquals(PieceName.ROOK, board.getPieceAt(new Coordinates(8,y)).getPieceName());
            assertEquals(PieceName.KNIGHT, board.getPieceAt(new Coordinates(2,y)).getPieceName());
            assertEquals(PieceName.KNIGHT, board.getPieceAt(new Coordinates(7,y)).getPieceName());
            assertEquals(PieceName.BISHOP, board.getPieceAt(new Coordinates(3,y)).getPieceName());
            assertEquals(PieceName.BISHOP, board.getPieceAt(new Coordinates(6,y)).getPieceName());
            assertEquals(PieceName.QUEEN, board.getPieceAt(new Coordinates(4,y)).getPieceName());
            assertEquals(PieceName.KING, board.getPieceAt(new Coordinates(5,y)).getPieceName());
        }
    }

    @Test
    public void testTurns(){
        Game game = new ClassicChess().generateGame();

        //White moves a pawn
        game.playTurn(new Coordinates('A',2), new Coordinates('A',3));

        //White tries to move the same pawn again
        assertThrows(IllegalArgumentException.class, () -> {
            game.playTurn(new Coordinates('A',3), new Coordinates('A',4));
        });

        //Black moves a pawn
        game.playTurn(new Coordinates('A',7), new Coordinates('A',6));

        //Black tries to move the same pawn again
        assertThrows(IllegalArgumentException.class, () -> {
            game.playTurn(new Coordinates('A',6), new Coordinates('A',5));
        });
    }

    @Test
    public void testCheck(){
        Game game = new ClassicChess().generateGame();

        game.playTurn(new Coordinates('E',2), new Coordinates('E',3));
        game.playTurn(new Coordinates('F',7), new Coordinates('F',6));
        game.playTurn(new Coordinates('D',1), new Coordinates('H',5));

        assertTrue(game.peekBoard().isKingThreatened(PieceColor.BLACK));
    }

    @Test
    public void testWhiteCheckmate(){
        Game game = new ClassicChess().generateGame();

        game.playTurn(new Coordinates('E',2), new Coordinates('E',3));
        game.playTurn(new Coordinates('F',7), new Coordinates('F',6));
        game.playTurn(new Coordinates('A',2), new Coordinates('A',3));
        game.playTurn(new Coordinates('G',7), new Coordinates('G',6));
        game.playTurn(new Coordinates('A',3), new Coordinates('A',4));
        game.playTurn(new Coordinates('G',6), new Coordinates('G',5));
        StatusOptions finalStatus = game.playTurn(new Coordinates('D',1), new Coordinates('H',5));

        assertTrue(game.peekBoard().isKingThreatened(PieceColor.BLACK));
        //assertEquals(StatusOptions.WHITE_CHECKMATE, finalStatus);


//        game.playTurn(new Coordinates('E',2), new Coordinates('E',3));
//        game.playTurn(new Coordinates('A',7), new Coordinates('A',6));
//        game.playTurn(new Coordinates('D',1), new Coordinates('F',3));
//        assertEquals(PieceName.QUEEN, game.peekBoard().getPieceAt(new Coordinates('F',3)).getPieceName());
//        game.playTurn(new Coordinates('A',6), new Coordinates('A',5));
//        game.playTurn(new Coordinates('F',3), new Coordinates('D',5));
//        assertEquals(PieceName.QUEEN, game.peekBoard().getPieceAt(new Coordinates('D',5)).getPieceName());


//


    }
}
