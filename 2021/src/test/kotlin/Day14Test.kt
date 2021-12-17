import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 14")
class Day14Test {

    // Arrange
    private val example = Resources.resourceAsListOfString("day14.example", "\n\n")
    private val input = Resources.resourceAsListOfString("day14.in", "\n\n")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day14(example).solve1()
            // Assert
            assertThat(answer).isEqualTo(1_588)
        }

        @Test
        fun `Matches actual answer`() {
            // Act
            val answer = Day14(input).solve1()
            // Assert
            assertThat(answer).isEqualTo(2_345)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day14(example).solve2()
            // Assert
            assertThat(answer).isEqualTo(2_188_189_693_529)
        }

        @Test
        fun `Matches actual answer`() {
            // Act
            val answer = Day14(input).solve2()
            // Assert
            assertThat(answer).isEqualTo(2_432_786_807_053)
        }
    }
}
