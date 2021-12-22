import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 22")
class Day22Test {

    // Arrange
    private val smallExample = Resources.resourceAsListOfString("day22.small.example")
    private val mediumExample = Resources.resourceAsListOfString("day22.medium.example")
    private val largeExample = Resources.resourceAsListOfString("day22.large.example")
    private val input = Resources.resourceAsListOfString("day22.in")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches small example`() {
            // Act
            val answer = Day22(smallExample).solve1()
            // Assert
            assertThat(answer).isEqualTo(39)
        }

        @Test
        fun `Matches medium example`() {
            // Act
            val answer = Day22(mediumExample).solve1()
            // Assert
            assertThat(answer).isEqualTo(590_784)
        }

        @Test
        fun `Matches actual answer`() {
            // Act
            val answer = Day22(input).solve1()
            // Assert
            assertThat(answer).isEqualTo(583_636)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches large example`() {
            // Act
            val answer = Day22(largeExample).solve2()
            // Assert
            assertThat(answer).isEqualTo(2_758_514_936_282_235)
        }

        @Test
        fun `Matches actual answer`() {
            // Act
            val answer = Day22(input).solve2()
            // Assert
            assertThat(answer).isEqualTo(1_294_137_045_134_837)
        }
    }
}
