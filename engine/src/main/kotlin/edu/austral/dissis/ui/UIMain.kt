package edu.austral.dissis.ui

import edu.austral.dissis.chess.gui.CachedImageResolver
import edu.austral.dissis.chess.gui.DefaultImageResolver
import edu.austral.dissis.chess.gui.createGameViewFrom
import edu.austral.dissis.engine.GameProvider
import javafx.application.Application
import javafx.application.Application.launch
import javafx.scene.Scene
import javafx.stage.Stage

fun main() {
    launch(GameApplication::class.java)
}

class GameApplication : Application() {
    private val gameEngine = MySimpleGameEngine(GameProvider.game)
    private val imageResolver = CachedImageResolver(DefaultImageResolver())

    companion object {
        const val GAME_TITLE = "Chess"
    }

    override fun start(primaryStage: Stage) {
        primaryStage.title = GAME_TITLE

        val root = createGameViewFrom(gameEngine, imageResolver)

        primaryStage.scene = Scene(root)
        primaryStage.show()
    }
}
