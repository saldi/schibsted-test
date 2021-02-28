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
        IndexerApp.from(testConsole).start()
        verify(exactly = 2) {
            testConsole.print("search>")
        }
    }


}