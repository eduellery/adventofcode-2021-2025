import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 02")
class Day02Test {

    // Arrange
    private val example = listOf("forward 5", "down 5", "forward 8", "up 3", "down 8", "forward 2")
    private val input = Resources.resourceAsListOfString("day02.in")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day02(example).solve1()
            // Assert
            assertThat(answer).isEqualTo(150)
        }

        @Test
        fun `Matches actual answer`() {
            // Act
            val answer = Day02(input).solve1()
            // Assert
            assertThat(answer).isEqualTo(1_804_520)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day02(example).solve2()
            // Assert
            assertThat(answer).isEqualTo(900)
        }

        @Test
        fun `Matches actual answer`() {
            // Act
            val answer = Day02(input).solve2()
            // Assert
            assertThat(answer).isEqualTo(1_971_095_320)
        }
    }
}