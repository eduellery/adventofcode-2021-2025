package alternative

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 20 Alternative")
class Day20Test {

    // Arrange
    private val example = Resources.resourceAsListOfString("day20.example", "\n\n")
    private val input = Resources.resourceAsListOfString("day20.in", "\n\n")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day20(example).solve1()
            // Assert
            assertThat(answer).isEqualTo(35)
        }

        @Test
        fun `Matches actual answer`() {
            // Act
            val answer = Day20(input).solve1()
            // Assert
            assertThat(answer).isEqualTo(5_461)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day20(example).solve2()
            // Assert
            assertThat(answer).isEqualTo(3_351)
        }

        @Test
        fun `Matches actual answer`() {
            // Act
            val answer = Day20(input).solve2()
            // Assert
            assertThat(answer).isEqualTo(18_226)
        }
    }
}
