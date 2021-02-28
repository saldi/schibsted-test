package edu.words.index

import java.nio.file.Paths
import kotlin.system.exitProcess


fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("Please, provide directory with text files")
        exitProcess(-1)
    }
    IndexerApp.default().run(args[0])
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


class IndexerApp(
    private val console: Console,
    private val scoring: Scoring
) {

    val indexer = Indexer()

    companion object {
        fun default(): IndexerApp {
            return IndexerApp(
                StandardConsole(),
                DefaultScoring(DefaultCountScore)
            )
        }

        fun from(console: Console, countScore: CountScore): IndexerApp {
            return IndexerApp(console, DefaultScoring(countScore))
        }
    }

    fun run(directory: String) {
        Paths.get(directory).findTextFiles()
            ?.forEach {
                indexer.index(it.bufferedReader(), it.name)
                console.println("Indexed ${it.name}")
            }
        start()
    }

    fun start() {
        var keepRunning = true
        while (keepRunning) {
            console.print("search>")
            val input = console.readLine()
            when (input) {
                ":quit" -> keepRunning = false
                else -> scoreWords(input)
            }
        }
    }

    private fun scoreWords(input: String?) {
        val words = input?.split(" ")
        val foundedFiles = indexer.findFiles(words)
        val score = scoring.score(foundedFiles, words ?: listOf(), 10)
        if (score.isEmpty()) {
            console.println("no matches found")
        } else {
            score.forEach { sc ->
                console.println(sc.fileName + " " + sc.score)
            }
        }
    }

}