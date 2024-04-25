package edu.austral.dissis.chess.engine.components;

public class Status {
  //    private final Board board;;
  //    private final Player playerTurn;
  //
  //    public Status(Board board, Player playerTurn){
  //        this.board = board;
  //        this.playerTurn = playerTurn;
  //    }
  //
  //    public StatusOptions getStatusInfo(){
  //        if(isCheckMate()){
  //            return StatusOptions.CHECKMATE;
  //        }
  //        if(isCheck()){
  //            return StatusOptions.CHECK;
  //        }
  //        if(isDraw()){
  //            return StatusOptions.DRAW;
  //        }
  //        return StatusOptions.NORMAL;
  //    }
  //
  //    public Player getPlayerTurn() {
  //        return playerTurn;
  //    }
  //
  //    private boolean isCheckMate(){
  //
  //        Coordinates kingCoordinates = board.getKingLocation(playerTurn.getColor());
  //
  //        if (!isCheck())
  //            return false;
  //
  //        for (int x = -1; x <= 1; x++) {
  //            for (int y = -1; y <= 1; y++) {
  //                Coordinates next = new Coordinates(kingCoordinates.getX() + x,
  // kingCoordinates.getY() + y);
  //                if (new MoveReferee(board, kingCoordinates, next).isValid() &&
  // !board.isSquareThreatened(next)) {
  //                    return false;
  //                }
  //            }
  //        }
  //        return true;
  //    }
  //
  //    private boolean isCheck(){
  //        Coordinates kingCoordinates = board.getKingLocation(playerTurn.getColor());
  //        return board.isSquareThreatened(kingCoordinates);
  //    }
  //
  //    private boolean isDraw(){
  //        for (int x = 0; x < board.getXSize(); x++) {
  //            for (int y = 0; y < board.getYSize(); y++) {
  //
  //                Coordinates tempCoordinates = new Coordinates(x, y);
  //                Piece piece = board.getPieceAt(tempCoordinates);
  //
  //                if (piece != null
  //                        && piece.getColor().equals(playerTurn.getColor())
  //                        && !piece.isBlocked(board, tempCoordinates))
  //                    return false;
  //            }
  //        }
  //        return true;
  //    }
}
