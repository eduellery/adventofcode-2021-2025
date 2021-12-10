import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 10")
class Day10Test {

    // Arrange
    private val example = Resources.resourceAsListOfString("day10.ex")
    private val input = Resources.resourceAsListOfString("day10.in")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day10(example).solve1()
            // Assert
            assertThat(answer).isEqualTo(26_397)
        }

        @Test
        fun `Matches actual answer`() {
            // Act
            val answer = Day10(input).solve1()
            // Assert
            assertThat(answer).isEqualTo(358_737)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day10(example).solve2()
            // Assert
            assertThat(answer).isEqualTo(288_957)
        }

        @Test
        fun `Matches actual answer`() {
            // Act
            val answer = Day10(input).solve2()
            // Assert
            assertThat(answer).isEqualTo(4_329_504_793)
        }
    }
}
