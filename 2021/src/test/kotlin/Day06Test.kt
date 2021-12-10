import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 06")
class Day06Test {

    // Arrange
    private val example = listOf(3,4,3,1,2)
    private val input = Resources.resourceAsListOfInt("day06.in", ",")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day06(example).solve1()
            // Assert
            assertThat(answer).isEqualTo(5_934)
        }

        @Test
        fun `Matches actual answer`() {
            // Act
            val answer = Day06(input).solve1()
            // Assert
            assertThat(answer).isEqualTo(372_984)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day06(example).solve2()
            // Assert
            assertThat(answer).isEqualTo(26_984_457_539)
        }

        @Test
        fun `Matches actual answer`() {
            // Act
            val answer = Day06(input).solve2()
            // Assert
            assertThat(answer).isEqualTo(1_681_503_251_694)
        }
    }
}
