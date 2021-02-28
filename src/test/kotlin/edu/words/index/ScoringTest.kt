package edu.words.index

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class ScoringTest {
    @Test
    fun `default scoring test`() {
        val words = listOf<String>("cat", "dog")
        Assertions.assertThat(DefaultCountScore.count(words, 2)).isEqualTo(100)
        Assertions.assertThat(DefaultCountScore.count(words, 0)).isEqualTo(0)
        Assertions.assertThat(DefaultCountScore.count(words, 1)).isEqualTo(50)
    }


}