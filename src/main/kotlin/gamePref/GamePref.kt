package gamePref

import board.Board
import player.Player

class GamePref {
    private var board: Board = Board()
    private var players: MutableList<Player> = mutableListOf<Player>()
    private var currentTurn: Int = 1

    private var possibleCharacters: List<String> = listOf("X", "O")

    fun getBoard(): Board {
        return this.board
    }

    fun addPlayer(player: Player) {
        this.players.add(player)
        changeTurn()
    }

    fun getPlayers(): List<Player> {
        return this.players
    }

    fun getPlayerCount(): Int {
        return Player.getPlayerCount()
    }

    fun getCurrentPlayer(): Player {
        return this.players[this.currentTurn % 2]
    }

    fun changeTurn() {
        this.currentTurn += 1
    }

    fun resetTurn() {
        this.currentTurn = 1
    }

    fun getCurrentTurn(): Int {
        return this.currentTurn
    }

    fun getCurrentCharacter(): String {
        return possibleCharacters[this.currentTurn % 2]
    }

    fun isFinish(): Boolean {
        return board.isBoardComplete()
    }

    companion object {
        private var instance: GamePref? = null

        fun getInstance(): GamePref {
            if (instance == null) {
                instance = GamePref()
            }

            return instance as GamePref
        }
    }

}