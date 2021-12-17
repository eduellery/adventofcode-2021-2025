import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 17")
class Day17Test {

    // Arrange
    private val example = "target area: x=20..30, y=-10..-5"
    private val input = "target area: x=143..177, y=-106..-71"

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day17(example).solve1()
            // Assert
            assertThat(answer).isEqualTo(45)
        }

        @Test
        fun `Matches actual answer`() {
            // Act
            val answer = Day17(input).solve1()
            // Assert
            assertThat(answer).isEqualTo(5565)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day17(example).solve2()
            // Assert
            assertThat(answer).isEqualTo(112)
        }

        @Test
        fun `Matches actual answer`() {
            // Act
            val answer = Day17(input).solve2()
            // Assert
            assertThat(answer).isEqualTo(2118)
        }
    }
}
