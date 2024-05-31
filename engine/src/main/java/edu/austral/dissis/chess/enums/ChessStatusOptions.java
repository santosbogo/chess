package edu.austral.dissis.chess.enums;

import edu.austral.dissis.engine.enums.StatusOptions;

public enum ChessStatusOptions implements StatusOptions {
  NORMAL,
  FAILURE,
  WHITE_CHECKMATE,
  BLACK_CHECKMATE,
  STALEMATE
}
