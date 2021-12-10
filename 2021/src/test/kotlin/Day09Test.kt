import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 09")
class Day09Test {

    // Arrange
    private val example = listOf("2199943210", "3987894921", "9856789892", "8767896789", "9899965678")
    private val input = Resources.resourceAsListOfString("day09.in")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day09(example).solve1()
            // Assert
            assertThat(answer).isEqualTo(15)
        }

        @Test
        fun `Matches actual answer`() {
            // Act
            val answer = Day09(input).solve1()
            // Assert
            assertThat(answer).isEqualTo(439)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day09(example).solve2()
            // Assert
            assertThat(answer).isEqualTo(1_134)
        }

        @Test
        fun `Matches actual answer`() {
            // Act
            val answer = Day09(input).solve2()
            // Assert
            assertThat(answer).isEqualTo(900_900)
        }
    }
}
