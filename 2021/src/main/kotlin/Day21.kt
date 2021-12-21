class Day21(val input: List<String>) {

    private val positionPlayerOne: Int = input[0].removePrefix("Player 1 starting position: ").toInt()
    private val positionPlayerTwo: Int = input[1].removePrefix("Player 2 starting position: ").toInt()

    private fun HashMap<Game, Long>.addCount(game: Game, count: Long) {
        this[game] = if (this.containsKey(game)) this[game]!! + count else count
    }

    private fun simulateRoll(game: Game): List<Game> {
        val games = listOf(game.deepCopy(), game.deepCopy(), game.deepCopy())
        return games.forEachIndexed { index, thisGame -> thisGame.roll(index + 1) }.let { games }
    }

    fun solve1(): Long {
        val game = Game(Player.of(1, positionPlayerOne), Player.of(2, positionPlayerTwo))
        var (lastRoll, rollCount) = listOf(0, 0)

        while (game.getWinner(1000) == null) {
            game.roll((if (lastRoll < 100) ++lastRoll else 1.also { lastRoll = 1 }).also { rollCount++ })
                .roll((if (lastRoll < 100) ++lastRoll else 1.also { lastRoll = 1 }).also { rollCount++ })
                .roll((if (lastRoll < 100) ++lastRoll else 1.also { lastRoll = 1 }).also { rollCount++ })
        }

        return game.getLoser().score.toLong() * game.totalRollCount
    }

    fun solve2(): Long {
        var states = hashMapOf<Game, Long>()
        val startGame = Game(Player.of(1, positionPlayerOne, 0), Player.of(2, positionPlayerTwo, 0))
        states[startGame] = 1

        while (states.any { it.key.getWinner(21) == null }) {
            val newStates: HashMap<Game, Long> = hashMapOf()
            states.forEach { (game, count) ->
                if (game.getWinner(21) != null) {
                    newStates.addCount(game, count)
                } else {
                    val newGames = simulateRoll(game)
                    newGames.forEach { newGame ->
                        newStates.addCount(newGame, count)
                    }
                }
            }
            states = newStates
        }

        return states.map { entry -> Pair(entry.key.getWinner(21)!!.name, entry.value) }
            .groupBy { it.first }
            .mapValues { it.value.sumOf { list -> list.second } }
            .maxOf { it.value }
    }

    private data class Player(var name: Int, var position: Int, var score: Int) {
        fun move(roll: Int) {
            position += roll
            while (position > 10) {
                position -= 10
            }
        }

        fun increaseScore() {
            score += position
        }

        companion object {
            fun of(name: Int, position: Int, score: Int = 0): Player = Player(name, position, score)
        }
    }

    private data class Game(var playerOne: Player, var playerTwo: Player) {
        var rollCount = 0
        var totalRollCount = 0
        private var rollingPlayer: Player = playerOne

        fun getWinner(winningScore: Int): Player? {
            if (playerOne.score >= winningScore) return this.playerOne
            if (playerTwo.score >= winningScore) return this.playerTwo
            return null
        }

        fun getLoser(): Player = if (playerOne.score < playerTwo.score) playerOne else playerTwo

        fun deepCopy(): Game {
            val result = Game(playerOne.copy(), playerTwo.copy())
            result.rollingPlayer = if (rollingPlayer == playerOne) result.playerOne else result.playerTwo
            result.rollCount = rollCount
            result.totalRollCount = totalRollCount
            return result
        }

        fun roll(roll: Int): Game = advance(rollingPlayer, roll).let { this }

        private fun advance(player: Player, roll: Int) {
            player.move(roll)
            rollCount++
            totalRollCount++
            if (rollCount == 3) {
                rollingPlayer.increaseScore()
                rollingPlayer = if (rollingPlayer == playerOne) playerTwo else playerOne
                rollCount = 0
            }
        }
    }
}
