package edu.austral.dissis.server

import com.fasterxml.jackson.core.type.TypeReference
import edu.austral.dissis.chess.gui.CachedImageResolver
import edu.austral.dissis.chess.gui.DefaultImageResolver
import edu.austral.dissis.chess.gui.GameView
import edu.austral.dissis.chess.gui.ImageResolver
import edu.austral.dissis.server.listeners.ClientConnectionListener
import edu.austral.dissis.server.listeners.IdListener
import edu.austral.dissis.server.listeners.UiListener
import edu.austral.dissis.server.listeners.game.GameListener
import edu.austral.dissis.server.listeners.game.GameOverListener
import edu.austral.dissis.server.listeners.game.StartingGameListener
import edu.austral.dissis.server.listeners.moves.InvalidMoveListener
import edu.austral.dissis.server.payloads.game.GameOverPayload
import edu.austral.dissis.server.payloads.game.GamePayload
import edu.austral.dissis.server.payloads.game.StartingGamePayload
import edu.austral.dissis.server.payloads.moves.InvalidMovePayload
import edu.austral.ingsis.clientserver.Client
import edu.austral.ingsis.clientserver.Message
import edu.austral.ingsis.clientserver.netty.client.NettyClientBuilder
import javafx.application.Application
import javafx.scene.Scene
import javafx.stage.Stage
import java.net.InetSocketAddress

fun main() {
    Application.launch(ClientExample::class.java)
}

class ClientExample : Application() {
    companion object {
        lateinit var clientId: String
        lateinit var client: Client
        const val PORT = 8095
    }

    override fun start(primaryStage: Stage) {
        val imageResolver: ImageResolver = CachedImageResolver(DefaultImageResolver())
        val root = GameView(imageResolver)

        client =
            NettyClientBuilder.Companion.createDefault()
                .withAddress(InetSocketAddress("localhost", PORT))
                .withConnectionListener(ClientConnectionListener())
                .addMessageListener(
                    "initialState",
                    object : TypeReference<Message<StartingGamePayload>>() {},
                    StartingGameListener(root),
                )
                .addMessageListener(
                    "invalidMove",
                    object : TypeReference<Message<InvalidMovePayload>>() {},
                    InvalidMoveListener(root),
                )
                .addMessageListener(
                    "gameState",
                    object : TypeReference<Message<GamePayload>>() {},
                    GameListener(root),
                )
                .addMessageListener(
                    "gameOver",
                    object : TypeReference<Message<GameOverPayload>>() {},
                    GameOverListener(root),
                )
                .addMessageListener("id", object : TypeReference<Message<String>>() {}, IdListener())
                .build()

        client.connect()

        root.addListener(UiListener(client))

        primaryStage.title = "Chess"
        primaryStage.scene = Scene(root)
        primaryStage.show()
    }
}
