package board

class Board {
    private val EMPTY_STRING = ""

    /**
     * -------------
     * | O | O | O |
     * -------------
     * | X | X | X |
     * -------------
     * | O | O | O |
     * -------------
     * */
    private lateinit var board: Array<Array<String>>

    init {
        init()
    }

    private fun init() {
        board = Array(3) {
            Array(3) { "" }
        }
    }

    private fun getTileCoordinate(tileNumber: Int): Coordinate {
        return when (tileNumber) {
            1 -> Coordinate(0, 0)
            2 -> Coordinate(0, 1)
            3 -> Coordinate(0, 2)
            4 -> Coordinate(1, 0)
            5 -> Coordinate(1, 1)
            6 -> Coordinate(1, 2)
            7 -> Coordinate(2, 0)
            8 -> Coordinate(2, 1)
            9 -> Coordinate(2, 2)
            else -> throw Exception("Unexpected Tile")
        }
    }

    fun canInsertTile(tileNumber: Int): Boolean {
        val (x, y) = getTileCoordinate(tileNumber)
        return board[x][y].equals(EMPTY_STRING)
    }

    fun insertTile(tileNumber: Int, character: String) {
        val (x, y) = getTileCoordinate(tileNumber)
        this.board[x][y] = character
    }

    fun printBoard(isClean: Boolean = false) {
        var cellNumber = 1
        board.forEachIndexed { i, x ->
            print("| ")
            x.forEachIndexed { j, _ ->
                if (isClean) {
                    print("${board[i][j]} | ")
                } else {
                    print("$cellNumber. ${board[i][j]} | ")
                }
                cellNumber += 1
            }
            println()
        }
    }

    fun isBoardComplete(): Boolean {
        board.forEach { x ->
            x.forEach { y ->
                if (y == EMPTY_STRING) {
                    return false
                }
            }
        }
        return true
    }

    private fun isSameVertical(yValue: Int, character: String): Boolean {
        val verticalLen = board[1].size
        for (i in 0 until verticalLen) {
            if (board[i][yValue] != character) return false
        }
        return true
    }

    private fun isSameHorizontal(yValue: Int, character: String): Boolean {
        val verticalLen = board.size
        for (i in 0 until verticalLen) {
            if (board[yValue][i] != character) return false
        }
        return true
    }

    private fun isSameDiagonal(character: String, isMainDiagonal: Boolean = true): Boolean {
        val verticalLen = board.size
        if (isMainDiagonal) {
            for (i in 0 until verticalLen) {
                if (board[i][i] != character) return false
            }
        } else {
            for (i in 0 until verticalLen) {
                if (board[i][verticalLen - 1 - i] != character) return false
            }
        }

        return true
    }

    fun isWinningMove(tileNumber: Int, character: String): Boolean {
        val (x, y) = getTileCoordinate(tileNumber)

        if (isSameVertical(y, character)) return true
        if (isSameHorizontal(x, character)) return true
        if (isSameDiagonal(character, isMainDiagonal = true)) return true
        if (isSameDiagonal(character, isMainDiagonal = false)) return true
        return false
    }
}