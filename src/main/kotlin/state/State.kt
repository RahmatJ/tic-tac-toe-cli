package state

import gamePref.GamePref
import player.Player

abstract class State {
    val gamePref: GamePref = GamePref.getInstance()
    abstract fun run(): State
    open var isDone: Boolean = false
}

class InitState : State() {

    override fun run(): State {
        println("Welcome to Tic Tac Toe")

        return AddPlayerState()
    }
}

class AddPlayerState : State() {

    override fun run(): State {
        val currentCharacter = gamePref.getCurrentCharacter()
        print("Character: $currentCharacter\nPlease insert your name: ")
        val playerName = readln()
        val player = Player(playerName, currentCharacter)
        gamePref.addPlayer(player = player)

        println(gamePref.getPlayers())
        if (gamePref.getPlayerCount() >= 2) {
            return SetupBoardState()
        }
        return AddPlayerState()
    }
}

class SetupBoardState : State() {
    override fun run(): State {
        gamePref.resetTurn()
        val board = gamePref.getBoard()
        board.printBoard(isClean = true)
        return PlayerTurnState()
    }
}

class PlayerTurnState : State() {

    override fun run(): State {
        val board = gamePref.getBoard()
        println("Turn: ${gamePref.getCurrentTurn()}")
        val currentPlayer = gamePref.getCurrentPlayer()
        println("Current Player: ${currentPlayer.playerName}")
        val currentCharacter = currentPlayer.getCharacter()
        board.printBoard()
        print("Please select target tile: ")
        val input: String = readln()
        if (input.isEmpty()) {
            println("PLEASE INSERT VALID INPUT")
            return PlayerTurnState()
        }
        val tileNumber: Int = input.toInt()
        if (tileNumber > 9 || tileNumber < 1) {
            println("Tile out of index. PLEASE INSERT VALID INPUT")
            return PlayerTurnState()
        }
        if (!board.canInsertTile(tileNumber)) {
            println("Unable to choose that tile. Please select another tile.")
            return PlayerTurnState()
        }

        board.insertTile(tileNumber, currentCharacter)
        gamePref.changeTurn()

        board.printBoard()

        if (board.isWinningMove(tileNumber, currentCharacter)) {
            println("Player ${currentPlayer.playerName} WIN")
            gamePref.resetTurn()
            return DoneState()
        }

        if (gamePref.isFinish()) {
            gamePref.resetTurn()
            return DoneState()
        }

        return PlayerTurnState()
    }
}

class DoneState : State() {
    override var isDone: Boolean = true

    override fun run(): State {
        println("DONE")
        return DoneState()
    }
}