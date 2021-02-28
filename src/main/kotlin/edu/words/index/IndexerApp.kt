package edu.words.index


fun main() {
    IndexerApp.default().start()
}

interface Console {
    fun print(message: String)
    fun println(message: String)
    fun readLine(): String?
}

class StandardConsole : Console {

    override fun print(message: String) {
        kotlin.io.print(message)
    }

    override fun println(message: String) {
        kotlin.io.println(message)
    }

    override fun readLine(): String? {
        return kotlin.io.readLine()
    }

}


class IndexerApp(private val console: Console) {

    companion object {
        fun default(): IndexerApp {
            return IndexerApp(StandardConsole())
        }

        fun from(console: Console): IndexerApp {
            return IndexerApp(console)
        }
    }

    fun run(directory: String) {

    }


    fun start() {
        var keepRunning = true
        while (keepRunning) {
            console.print("search>")
            val input = console.readLine()
            when (input) {
                ":quit" -> keepRunning = false
            }
        }
    }

}