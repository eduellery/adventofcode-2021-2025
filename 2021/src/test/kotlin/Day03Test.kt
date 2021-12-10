import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 03")
class Day03Test {

    // Arrange
    private val example = listOf("00100", "11110", "10110", "10111", "10101", "01111", "00111", "11100", "10000", "11001", "00010", "01010")
    private val input = Resources.resourceAsListOfString("day03.in")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day03(example).solve1()
            // Assert
            assertThat(answer).isEqualTo(198)
        }

        @Test
        fun `Matches actual answer`() {
            // Act
            val answer = Day03(input).solve1()
            // Assert
            assertThat(answer).isEqualTo(1_092_896)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day03(example).solve2()
            // Assert
            assertThat(answer).isEqualTo(230)
        }

        @Test
        fun `Matches actual answer`() {
            // Act
            val answer = Day03(input).solve2()
            // Assert
            assertThat(answer).isEqualTo(4_672_151)
        }
    }
}