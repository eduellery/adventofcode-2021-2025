import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 01")
class Day01Test {

    // Arrange
    private val example = listOf(199, 200, 208, 210, 200, 207, 240, 269, 260, 263)
    private val input = Resources.resourceAsListOfInt("day01.in")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day01(example).solve1()
            // Assert
            assertThat(answer).isEqualTo(7)
        }

        @Test
        fun `Matches actual answer`() {
            // Act
            val answer = Day01(input).solve1()
            // Assert
            assertThat(answer).isEqualTo(1_266)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day01(example).solve2()
            // Assert
            assertThat(answer).isEqualTo(5)
        }

        @Test
        fun `Matches actual answer`() {
            // Act
            val answer = Day01(input).solve2()
            // Assert
            assertThat(answer).isEqualTo(1_217)
        }
    }
}