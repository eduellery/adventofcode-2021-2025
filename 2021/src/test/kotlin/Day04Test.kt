import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 04")
class Day04Test {

    // Arrange
    private val example = Resources.resourceAsListOfString("day04.example", "\n\n")
    private val input = Resources.resourceAsListOfString("day04.in", "\n\n")
    private val exampleAlternative = Resources.resourceAsListOfString("day04.example")
    private val inputAlternative = Resources.resourceAsListOfString("day04.in")

    @Nested
    @DisplayName("Main")
    inner class Main {

        @Nested
        @DisplayName("Part 1")
        inner class Part1 {
            @Test
            fun `Matches example`() {
                // Act
                val answer = Day04(example).solve1()
                // Assert
                assertThat(answer).isEqualTo(4_512)
            }

            @Test
            fun `Matches actual answer`() {
                // Act
                val answer = Day04(input).solve1()
                // Assert
                assertThat(answer).isEqualTo(89_001)
            }
        }

        @Nested
        @DisplayName("Part 2")
        inner class Part2 {
            @Test
            fun `Matches example`() {
                // Act
                val answer = Day04(example).solve2()
                // Assert
                assertThat(answer).isEqualTo(1_924)
            }

            @Test
            fun `Matches actual answer`() {
                // Act
                val answer = Day04(input).solve2()
                // Assert
                assertThat(answer).isEqualTo(7_296)
            }
        }
    }

    @Nested
    @DisplayName("Alternative")
    inner class Alternative {

        @Nested
        @DisplayName("Part 1")
        inner class Part1 {
            @Test
            fun `Matches example`() {
                // Act
                val answer = Day04Alternative(exampleAlternative).solve1()
                // Assert
                assertThat(answer).isEqualTo(4_512)
            }

            @Test
            fun `Matches actual answer`() {
                // Act
                val answer = Day04Alternative(inputAlternative).solve1()
                // Assert
                assertThat(answer).isEqualTo(89_001)
            }
        }

        @Nested
        @DisplayName("Part 2")
        inner class Part2 {
            @Test
            fun `Matches example`() {
                // Act
                val answer = Day04Alternative(exampleAlternative).solve2()
                // Assert
                assertThat(answer).isEqualTo(1_924)
            }

            @Test
            fun `Matches actual answer`() {
                // Act
                val answer = Day04Alternative(inputAlternative).solve2()
                // Assert
                assertThat(answer).isEqualTo(7_296)
            }
        }
    }
}