package edu.words.index

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


class IndexerAppIT {

    lateinit var testConsole: Console

    @BeforeEach
    fun setup() {
        testConsole = mockk<Console>(relaxed = true)
    }

    @Test
    fun simpleLoopTest() {
        every {
            testConsole.readLine()
        } returns "goose" andThen "dog cat" andThen ":quit"
        IndexerApp.from(testConsole, DefaultCountScore).run("./src/test/resources/files")
        verify {
            testConsole.println("Indexed testfilewithcat.txt")
            testConsole.println("Indexed testfilewithdog.txt")
            testConsole.println("Indexed testfilewithduck.txt")
        }
        verify {
            testConsole.println("no matches found")
        }
        verify(exactly = 3) {
            testConsole.print("search>")
        }
        verify{
            testConsole.println("testfilewithdog.txt 50")
            testConsole.println("testfilewithcat.txt 50")
        }
    }


}