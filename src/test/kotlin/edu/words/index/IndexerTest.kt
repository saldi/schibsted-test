package edu.words.index

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class IndexerTest {

    @Test
    fun `find files`() {
        val files = findTextFiles(Paths.get("./src/test/resources/files"))
        assertThat(files).hasSize(2)
    }


}