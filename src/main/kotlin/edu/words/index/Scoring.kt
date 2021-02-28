package edu.words.index

data class Score(val fileName: String, val score: Int)

fun interface CountScore {
    fun count(words: List<String>, count: Int): Int
}

object DefaultCountScore : CountScore {
    override fun count(words: List<String>, count: Int): Int {
        return when (count) {
            0 -> 0
            words.size -> 100
            else -> ((count.toDouble() / words.size.toDouble()) * 100).toInt()
        }
    }
}

interface Scoring {
    fun score(files: Set<Set<String>>, words: List<String>, maxResult: Int): List<Score>
}

class DefaultScoring(private val countScore: CountScore) : Scoring {

    override fun score(files: Set<Set<String>>, words: List<String>, maxResult: Int): List<Score> {
        val counter = HashMap<String, Int>()
        files.forEach {
            it.forEach { fileName ->
                val count = counter.getOrPut(fileName) { 0 }
                counter.put(fileName, count.inc())
            }
        }
        val score = countScore(counter, words)
        return score.subList(
            0, if (score.size < maxResult) score.size else maxResult
        )
    }

    private fun countScore(counter: Map<String, Int>, words: List<String>): List<Score> {
        val scoreTable = mutableListOf<Score>()
        counter.entries.forEach { entry ->
            scoreTable.add(Score(entry.key,
                countScore.count(words, entry.value)))
        }
        scoreTable.sortedWith(compareBy { it.score })
        return scoreTable
    }

}