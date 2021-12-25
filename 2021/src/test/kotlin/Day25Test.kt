import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 23")
class Day25Test {

    // Arrange
    private val example = Resources.resourceAsListOfString("day25.example")
    private val input = Resources.resourceAsListOfString("day25.in")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day25(example).solve1()
            // Assert
            assertThat(answer).isEqualTo(58)
        }

        @Test
        fun `Matches actual answer`() {
            // Act
            val answer = Day25(input).solve1()
            // Assert
            assertThat(answer).isEqualTo(329)
        }
    }
}
