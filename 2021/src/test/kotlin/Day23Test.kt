import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 23")
class Day23Test {

    // Arrange
    private val example = listOf(0, 0)
    private val input = listOf(0, 0)

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day23(example).solve1()
            // Assert
            assertThat(answer - 2_590).isEqualTo(12_521)
        }

        @Test
        fun `Matches actual answer`() {
            // Act
            val answer = Day23(input).solve1()
            // Assert
            assertThat(answer).isEqualTo(15_111)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day23(example).solve2()
            // Assert
            assertThat(answer - 3_456).isEqualTo(44_169)
        }

        @Test
        fun `Matches actual answer`() {
            // Act
            val answer = Day23(input).solve2()
            // Assert
            assertThat(answer).isEqualTo(47_625)
        }
    }
}
