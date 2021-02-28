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
        } returns "dog cat" andThen ":quit"
        IndexerApp.from(testConsole, DefaultCountScore).run("./src/test/resources/files")
        verify {
            testConsole.println("Indexed testfilewithcat.txt")
            testConsole.println("Indexed testfilewithdog.txt")
            testConsole.println("Indexed testfilewithduck.txt")
        }
        verify(exactly = 2) {
            testConsole.print("search>")
        }
    }


}