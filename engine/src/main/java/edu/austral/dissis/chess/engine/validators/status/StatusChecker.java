package edu.austral.dissis.chess.engine.validators.status;

import edu.austral.dissis.chess.engine.enums.StatusOptions;

public class StatusChecker {
  private final StatusOptions statusOptions;

  public StatusChecker(StatusOptions statusOptions) {
    this.statusOptions = statusOptions;
  }

  public StatusOptions getStatus() {
    return statusOptions;
  }
}
