import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 16")
class Day16Test {

    // Arrange
    private val part1Example1 = "8A004A801A8002F478"
    private val part1Example2 = "620080001611562C8802118E34"
    private val part1Example3 = "C0015000016115A2E0802F182340"
    private val part1Example4 = "A0016C880162017C3686B18A3D4780"
    private val part2Example1 = "04005AC33890"
    private val part2Example2 = "CE00C43D881120"
    private val part2Example3 = "F600BC2D8F"
    private val part2Example4 = "9C0141080250320F1802104A08"
    private val input = Resources.resourceAsString("day16.in")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example 1`() {
            // Act
            val answer = Day16(part1Example1).solve1()
            // Assert
            assertThat(answer).isEqualTo(16)
        }

        @Test
        fun `Matches example 2`() {
            // Act
            val answer = Day16(part1Example2).solve1()
            // Assert
            assertThat(answer).isEqualTo(12)
        }

        @Test
        fun `Matches example 3`() {
            // Act
            val answer = Day16(part1Example3).solve1()
            // Assert
            assertThat(answer).isEqualTo(23)
        }

        @Test
        fun `Matches example 4`() {
            // Act
            val answer = Day16(part1Example4).solve1()
            // Assert
            assertThat(answer).isEqualTo(31)
        }

        @Test
        fun `Matches actual answer`() {
            // Act
            val answer = Day16(input).solve1()
            // Assert
            assertThat(answer).isEqualTo(920)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example 1`() {
            // Act
            val answer = Day16(part2Example1).solve2()
            // Assert
            assertThat(answer).isEqualTo(54)
        }

        @Test
        fun `Matches example 2`() {
            // Act
            val answer = Day16(part2Example2).solve2()
            // Assert
            assertThat(answer).isEqualTo(9)
        }

        @Test
        fun `Matches example 3`() {
            // Act
            val answer = Day16(part2Example3).solve2()
            // Assert
            assertThat(answer).isEqualTo(0)
        }

        @Test
        fun `Matches example 4`() {
            // Act
            val answer = Day16(part2Example4).solve2()
            // Assert
            assertThat(answer).isEqualTo(1)
        }

        @Test
        fun `Matches actual answer`() {
            // Act
            val answer = Day16(input).solve2()
            // Assert
            assertThat(answer).isEqualTo(10_185_143_721_112)
        }
    }
}
