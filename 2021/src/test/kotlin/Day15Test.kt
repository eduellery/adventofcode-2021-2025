import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 15")
class Day15Test {

    // Arrange
    private val example = Resources.resourceAsGrid("day15.ex")
    private val input = Resources.resourceAsGrid("day15.in")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day15(example).solve1()
            // Assert
            assertThat(answer).isEqualTo(40)
        }

        @Test
        fun `Matches actual answer`() {
            // Act
            val answer = Day15(input).solve1()
            // Assert
            assertThat(answer).isEqualTo(687)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day15(example).solve2()
            // Assert
            assertThat(answer).isEqualTo(315)
        }

        @Test
        fun `Matches actual answer`() {
            // Act
            val answer = Day15(input).solve2()
            // Assert
            assertThat(answer).isEqualTo(2_957)
        }
    }
}
