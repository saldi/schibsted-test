package edu.words.index

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class IndexerTest {

    @Test
    fun `find files`() {
        val files = Paths.get("./src/test/resources/files").findTextFiles()
        assertThat(files).hasSize(3)
    }

    @Test
    fun `word indexer simple test`() {
        val wordIndexer = WordIndexer()
        wordIndexer.index("cat", "testfilewithcat.txt")
        wordIndexer.index("dog", "testfilewithdog.txt")
        wordIndexer.index("duck", "testfilewithduck.txt")


        assertThat(wordIndexer.findFiles("cat")).hasSize(1).contains("testfilewithcat.txt")
        assertThat(wordIndexer.findFiles("dog")).hasSize(1).contains("testfilewithdog.txt")
        assertThat(wordIndexer.findFiles("duck")).hasSize(1).contains("testfilewithduck.txt")


    }

    @Test
    fun `indexer simple test`() {
        val indexer = Indexer()
        Paths.get("./src/test/resources/files").findTextFiles()
            ?.forEach { file -> indexer.index(file.bufferedReader(), file.name) }
        assertThat(indexer.findFiles("cat")).hasSize(1).contains("testfilewithcat.txt")
        assertThat(indexer.findFiles("dog")).hasSize(1).contains("testfilewithdog.txt")
        assertThat(indexer.findFiles("duck")).hasSize(1).contains("testfilewithduck.txt")
    }
}