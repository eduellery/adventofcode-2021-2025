import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 12")
class Day12Test {

    // Arrange
    private val smallExample = Resources.resourceAsListOfString("day12.small.example")
    private val mediumExample = Resources.resourceAsListOfString("day12.medium.example")
    private val largeExample = Resources.resourceAsListOfString("day12.large.example")
    private val input = Resources.resourceAsListOfString("day12.in")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches small example`() {
            // Act
            val answer = Day12(smallExample).solve1()
            // Assert
            assertThat(answer).isEqualTo(10)
        }

        @Test
        fun `Matches medium example`() {
            // Act
            val answer = Day12(mediumExample).solve1()
            // Assert
            assertThat(answer).isEqualTo(19)
        }

        @Test
        fun `Matches large example`() {
            // Act
            val answer = Day12(largeExample).solve1()
            // Assert
            assertThat(answer).isEqualTo(226)
        }

        @Test
        fun `Matches actual answer`() {
            // Act
            val answer = Day12(input).solve1()
            // Assert
            assertThat(answer).isEqualTo(3_485)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches small example`() {
            // Act
            val answer = Day12(smallExample).solve2()
            // Assert
            assertThat(answer).isEqualTo(36)
        }

        @Test
        fun `Matches medium example`() {
            // Act
            val answer = Day12(mediumExample).solve2()
            // Assert
            assertThat(answer).isEqualTo(103)
        }

        @Test
        fun `Matches large example`() {
            // Act
            val answer = Day12(largeExample).solve2()
            // Assert
            assertThat(answer).isEqualTo(3_509)
        }
        @Test
        fun `Matches actual answer`() {
            // Act
            val answer = Day12(input).solve2()
            // Assert
            assertThat(answer).isEqualTo(85_062)
        }
    }
}
