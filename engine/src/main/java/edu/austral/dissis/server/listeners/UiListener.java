package edu.austral.dissis.server.listeners;

import static edu.austral.dissis.server.ClientExample.clientId;

import edu.austral.dissis.chess.gui.GameEventListener;
import edu.austral.dissis.chess.gui.Move;
import edu.austral.dissis.server.payloads.moves.MovePayload;
import edu.austral.dissis.server.payloads.moves.RedoPayload;
import edu.austral.dissis.server.payloads.moves.UndoPayload;
import edu.austral.ingsis.clientserver.Client;
import edu.austral.ingsis.clientserver.Message;
import org.jetbrains.annotations.NotNull;

public class UiListener implements GameEventListener {

  Client client;

  public UiListener(Client client) {
    this.client = client;
  }

  @Override
  public void handleMove(@NotNull Move move) {
    client.send(new Message<>("move", new MovePayload(move, clientId)));
  }

  @Override
  public void handleRedo() {
    client.send(new Message<>("redo", new RedoPayload(clientId)));
  }

  @Override
  public void handleUndo() {
    client.send(new Message<>("undo", new UndoPayload(clientId)));
  }
}
