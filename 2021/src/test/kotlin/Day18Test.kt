import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 18")
class Day18Test {

    // Arrange
    private val example = Resources.resourceAsListOfString("day18.example")
    private val input = Resources.resourceAsListOfString("day18.in")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day18(example).solve1()
            // Assert
            assertThat(answer).isEqualTo(4_140)
        }

        @Test
        fun `Matches actual answer`() {
            // Act
            val answer = Day18(input).solve1()
            // Assert
            assertThat(answer).isEqualTo(4_435)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day18(example).solve2()
            // Assert
            assertThat(answer).isEqualTo(3_993)
        }

        @Test
        fun `Matches actual answer`() {
            // Act
            val answer = Day18(input).solve2()
            // Assert
            assertThat(answer).isEqualTo(4_802)
        }
    }
}
