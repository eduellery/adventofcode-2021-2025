import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 13")
class Day13Test {

    // Arrange
    private val example = Resources.resourceAsListOfString("day13.example", "\n\n")
    private val input = Resources.resourceAsListOfString("day13.in", "\n\n")
    private val exampleSolution = Resources.resourceAsString("day13.example.solution")
    private val inputSolution = Resources.resourceAsString("day13.in.solution")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day13(example).solve1()
            // Assert
            assertThat(answer).isEqualTo(17)
        }

        @Test
        fun `Matches actual answer`() {
            // Act
            val answer = Day13(input).solve1()
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
            val answer = Day13(example).solve2()
            // Assert
            assertThat(answer).isEqualTo(exampleSolution)
        }

        @Test
        fun `Matches actual answer`() {
            // Act
            val answer = Day13(input).solve2()
            // Assert
            assertThat(answer).isEqualTo(inputSolution)
        }
    }
}
