import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 23")
class Day23Test {

    // Arrange
    private val example = Resources.resourceAsListOfString("day23.example")
    private val input = Resources.resourceAsListOfString("day23.in")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day23(example).solve1()
            // Assert
            assertThat(answer).isEqualTo(12_521)
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
            assertThat(answer).isEqualTo(44_169)
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
