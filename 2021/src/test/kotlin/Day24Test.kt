import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 23")
class Day24Test {

    // Arrange
    private val input = Resources.resourceAsListOfString("day24.in")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches actual answer`() {
            // Act
            val answer = Day24(input).solve1()
            // Assert
            assertThat(answer).isEqualTo(99_298_993_199_873)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches actual answer`() {
            // Act
            val answer = Day24(input).solve2()
            // Assert
            assertThat(answer).isEqualTo(73_181_221_197_111)
        }
    }
}
