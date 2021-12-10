import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 08")
class Day08Test {

    // Arrange
    private val example = Resources.resourceAsListOfString("day08.ex")
    private val input = Resources.resourceAsListOfString("day08.in")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day08(example).solve1()
            // Assert
            assertThat(answer).isEqualTo(26)
        }

        @Test
        fun `Matches actual answer`() {
            // Act
            val answer = Day08(input).solve1()
            // Assert
            assertThat(answer).isEqualTo(449)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day08(example).solve2()
            // Assert
            assertThat(answer).isEqualTo(61_229)
        }

        @Test
        fun `Matches actual answer`() {
            // Act
            val answer = Day08(input).solve2()
            // Assert
            assertThat(answer).isEqualTo(968_175)
        }
    }
}
