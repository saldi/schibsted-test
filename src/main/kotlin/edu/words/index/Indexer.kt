package edu.words.index

import java.io.File
import java.io.Reader
import java.io.StreamTokenizer
import java.nio.file.Path

fun File.isTextFile(): Boolean {
    return "txt" == extension
}

fun Path.findTextFiles(): Array<File>? {
    return toFile().listFiles { file ->
        file.isTextFile()
    }
}


class Indexer {
    val wordIndexer = WordIndexer()

    fun index(input: Reader, fileName: String) {
        input.use {
            val streamTokenizer = StreamTokenizer(input)
            streamTokenizer.eolIsSignificant(false)
            var token: Int
            while (streamTokenizer.nextToken().also { token = it } != StreamTokenizer.TT_EOF) {
                if (token == StreamTokenizer.TT_WORD) {
                    wordIndexer.index(streamTokenizer.sval, fileName)
                }
            }
        }
    }

    fun findFiles(word: String): Set<String>? {
        return wordIndexer.findFiles(word)
    }
}

class WordIndexer {

    val wordIndex: MutableMap<Char, MutableMap<String, MutableSet<String>>> = HashMap()

    fun index(word: String, fileName: String) {
        val firstChar = word[0]
        val words = wordIndex.getOrPut(firstChar) { HashMap() }
        val setOfFiles = words.getOrPut(word) { HashSet() }
        setOfFiles.add(fileName)
    }

    fun findFiles(word: String): Set<String>? {
        val firstChar = word[0]
        if (wordIndex.containsKey(firstChar)) {
            val words = wordIndex[firstChar]
            return words?.getOrDefault(word, HashSet())
        } else return HashSet()
    }

}


