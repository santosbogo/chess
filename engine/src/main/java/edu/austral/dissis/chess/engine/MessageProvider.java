package edu.austral.dissis.chess.engine;

import edu.austral.dissis.chess.engine.components.Chess;

class MessageProvider {

  public static void main(String[] args) {
    Chess chess = new Chess();
    chess.play();
  }
  public static String getMessage() {
    return "Hello      World!";
  }
}
