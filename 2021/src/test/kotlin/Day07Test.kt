import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 07")
class Day07Test {

    // Arrange
    private val example = listOf(16,1,2,0,4,2,7,1,2,14)
    private val input = Resources.resourceAsListOfInt("day07.in", ",")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day07(example).solve1()
            // Assert
            assertThat(answer).isEqualTo(37)
        }

        @Test
        fun `Matches actual answer`() {
            // Act
            val answer = Day07(input).solve1()
            // Assert
            assertThat(answer).isEqualTo(344_735)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day07(example).solve2()
            // Assert
            assertThat(answer).isEqualTo(168)
        }

        @Test
        fun `Matches actual answer`() {
            // Act
            val answer = Day07(input).solve2()
            // Assert
            assertThat(answer).isEqualTo(96_798_233)
        }
    }
}
