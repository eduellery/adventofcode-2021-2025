import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 05")
class Day05Test {

    // Arrange
    private val example = Resources.resourceAsListOfString("day05.example")
    private val input = Resources.resourceAsListOfString("day05.in")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day05(example).solve1()
            // Assert
            assertThat(answer).isEqualTo(5)
        }

        @Test
        fun `Matches actual answer`() {
            // Act
            val answer = Day05(input).solve1()
            // Assert
            assertThat(answer).isEqualTo(5_169)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day05(example).solve2()
            // Assert
            assertThat(answer).isEqualTo(12)
        }

        @Test
        fun `Matches actual answer`() {
            // Act
            val answer = Day05(input).solve2()
            // Assert
            assertThat(answer).isEqualTo(22_083)
        }
    }
}
