package edu.words.index

import java.io.File
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
}