import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 21")
class Day21Test {

    // Arrange
    private val example = listOf("Player 1 starting position: 4", "Player 2 starting position: 8")
    private val input = listOf("Player 1 starting position: 7", "Player 2 starting position: 1")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day21(example).solve1()
            // Assert
            assertThat(answer).isEqualTo(739_785)
        }

        @Test
        fun `Matches actual answer`() {
            // Act
            val answer = Day21(input).solve1()
            // Assert
            assertThat(answer).isEqualTo(684_495)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day21(example).solve2()
            // Assert
            assertThat(answer).isEqualTo(444_356_092_776_315)
        }

        @Test
        fun `Matches actual answer`() {
            // Act
            val answer = Day21(input).solve2()
            // Assert
            assertThat(answer).isEqualTo(152_587_196_649_184)
        }
    }
}
