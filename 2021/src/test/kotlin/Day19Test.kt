import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 19")
class Day19Test {

    // Arrange
    private val example = Resources.resourceAsString("day19.example")
    private val input = Resources.resourceAsString("day19.in")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day19(example).solve1()
            // Assert
            assertThat(answer).isEqualTo(79)
        }

        @Test
        fun `Matches actual answer`() {
            // Act
            val answer = Day19(input).solve1()
            // Assert
            assertThat(answer).isEqualTo(425)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day19(example).solve2()
            // Assert
            assertThat(answer).isEqualTo(3_621)
        }

        @Test
        fun `Matches actual answer`() {
            // Act
            val answer = Day19(input).solve2()
            // Assert
            assertThat(answer).isEqualTo(13_354)
        }
    }
}
