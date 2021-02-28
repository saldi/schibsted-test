package edu.words.index

import java.io.File
import java.io.Reader
import java.nio.file.Path

fun File.isTextFile(): Boolean {
    return "txt" == extension
}

fun findTextFiles(path: Path): Array<File>? {
    return path.toFile().listFiles { file ->
        file.isTextFile()
    }
}


class Indexer {

    fun index(input: Reader, fileName: String) {


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


