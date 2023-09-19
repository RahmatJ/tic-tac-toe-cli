package player

class Player(var playerName: String) {

    constructor(newName: String, newCharacter: String) : this(newName) {
        this.character = newCharacter
    }

    init {
        playerCount += 1
    }

    private lateinit var character: String

    fun getCharacter(): String {
        return this.character
    }

    companion object {
        @JvmStatic
        private var playerCount = 0

        fun getPlayerCount(): Int {
            return playerCount
        }
    }

}