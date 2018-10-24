@file:Suppress("UNUSED_PARAMETER")

package lesson2

import lesson3.Trie
import java.io.File
import java.lang.IllegalArgumentException
import java.util.*


/**
 * Получение наибольшей прибыли (она же -- поиск максимального подмассива)
 * Простая
 *
 * Во входном файле с именем inputName перечислены цены на акции компании в различные (возрастающие) моменты времени
 * (каждая цена идёт с новой строки). Цена -- это целое положительное число. Пример:
 *
 * 201
 * 196
 * 190
 * 198
 * 187
 * 194
 * 193
 * 185
 *
 * Выбрать два момента времени, первый из них для покупки акций, а второй для продажи, с тем, чтобы разница
 * между ценой продажи и ценой покупки была максимально большой. Второй момент должен быть раньше первого.
 * Вернуть пару из двух моментов.
 * Каждый момент обозначается целым числом -- номер строки во входном файле, нумерация с единицы.
 * Например, для приведённого выше файла результат должен быть Pair(3, 4)
 *
 * В случае обнаружения неверного формата файла бросить любое исключение.
 */
fun optimizeBuyAndSell(inputName: String): Pair<Int, Int> {
    TODO()
}

/**
 * Задача Иосифа Флафия.
 * Простая
 *
 * Образовав круг, стоят menNumber человек, пронумерованных от 1 до menNumber.
 *
 * 1 2 3
 * 8   4
 * 7 6 5
 *
 * Мы считаем от 1 до choiceInterval (например, до 5), начиная с 1-го человека по кругу.
 * Человек, на котором остановился счёт, выбывает.
 *
 * 1 2 3
 * 8   4
 * 7 6 х
 *
 * Далее счёт продолжается со следующего человека, также от 1 до choiceInterval.
 * Выбывшие при счёте пропускаются, и человек, на котором остановился счёт, выбывает.
 *
 * 1 х 3
 * 8   4
 * 7 6 Х
 *
 * Процедура повторяется, пока не останется один человек. Требуется вернуть его номер (в данном случае 3).
 *
 * 1 Х 3
 * х   4
 * 7 6 Х
 *
 * 1 Х 3
 * Х   4
 * х 6 Х
 *
 * х Х 3
 * Х   4
 * Х 6 Х
 *
 * Х Х 3
 * Х   х
 * Х 6 Х
 *
 * Х Х 3
 * Х   Х
 * Х х Х
 */

/**
 * сложность O(N), затраты памяти O(1)
 */

fun josephTask(menNumber: Int, choiceInterval: Int): Int = recursiveJoseph(menNumber, choiceInterval, 1, 0)

tailrec fun recursiveJoseph(menNumber: Int, choiceInterval: Int, i: Int, res: Int): Int {
    return if (menNumber == 1) res + 1
    else recursiveJoseph(menNumber - 1, choiceInterval, i + 1, (res + choiceInterval) % (i + 1))
}


/**
 * Наибольшая общая подстрока.
 * Средняя
 *
 * Дано две строки, например ОБСЕРВАТОРИЯ и КОНСЕРВАТОРЫ.
 * Найти их самую длинную общую подстроку -- в примере это СЕРВАТОР.
 * Если общих подстрок нет, вернуть пустую строку.
 * При сравнении подстрок, регистр символов *имеет* значение.
 * Если имеется несколько самых длинных общих подстрок одной длины,
 * вернуть ту из них, которая встречается раньше в строке first.
 */

/**
 * Алгоритм проходит по всем буквам каждого слова - сложность O(M*N), для хранения информации используется матрица
 * совпадений букв размером O(M*N)
 */

fun longestCommonSubstring(first: String, second: String): String {
    val firstLen = first.length
    val secondLen = second.length
    var max = 0
    var index = -1
    val container = MutableList(firstLen) { MutableList(secondLen) { 0 } }
    for (i in 0 until firstLen) {
        for (j in 0 until secondLen) {
            if (first[i] == second[j]) {
                container[i][j] = 1
                if (i != 0 && j != 0) container[i][j] += container[i - 1][j - 1]
            }
            if (container[i][j] > max) {
                max = container[i][j]
                index = i
            }
        }
    }
    return if (max == 0) ""
    else first.substring(index - max + 1, index + 1)
}


/**
 * Число простых чисел в интервале
 * Простая
 *
 * Рассчитать количество простых чисел в интервале от 1 до limit (включительно).
 * Если limit <= 1, вернуть результат 0.
 *
 * Справка: простым считается число, которое делится нацело только на 1 и на себя.
 * Единица простым числом не считается.
 */
fun calcPrimesNumber(limit: Int): Int {
    TODO()
}

/**
 * Балда
 * Сложная
 *
 * В файле с именем inputName задана матрица из букв в следующем формате
 * (отдельные буквы в ряду разделены пробелами):
 *
 * И Т Ы Н
 * К Р А Н
 * А К В А
 *
 * В аргументе words содержится множество слов для поиска, например,
 * ТРАВА, КРАН, АКВА, НАРТЫ, РАК.
 *
 * Попытаться найти каждое из слов в матрице букв, используя правила игры БАЛДА,
 * и вернуть множество найденных слов. В данном случае:
 * ТРАВА, КРАН, АКВА, НАРТЫ
 *
 * И т Ы Н     И т ы Н
 * К р а Н     К р а н
 * А К в а     А К В А
 *
 * Все слова и буквы -- русские или английские, прописные.
 * В файле буквы разделены пробелами, строки -- переносами строк.
 * Остальные символы ни в файле, ни в словах не допускаются.
 */

/**
 *
 */

fun baldaSearcher(inputName: String, words: Set<String>): Set<String> {
    val dictionary = Trie()
    dictionary.addAll(words)

    val regex = Regex("""[А-ЯЁA-Z ]+""")

    val field = mutableListOf<List<Char>>()
    File(inputName).forEachLine { line ->
        if (!line.matches(regex)) throw IllegalArgumentException("Wrong format")
        field.add(line.split(" ").map { it.first() })
    }

    return field.findWords(dictionary)
}


fun List<List<Char>>.findWords(dictionary: Trie): Set<String> {
    val result = mutableSetOf<String>()
    for (i in 0 until this.size) {
        for (j in 0 until this.first().size) {
            result.addAll(this.search(dictionary, Cell(i, j), "", listOf()))
        }
    }
    return result
}

fun List<List<Char>>.search(dictionary: Trie, searchPoint: Cell, word: String, checked: List<Cell>): Set<String> {
    val result = mutableSetOf<String>()

    if (searchPoint !in checked) {
        val prefix = word + this[searchPoint.row][searchPoint.column]
        val checkedList = checked + searchPoint

        if (dictionary.containsPref(prefix)) {
            if (dictionary.contains(prefix)) result.add(prefix)
            this.getNeighbours(searchPoint).forEach { result.addAll(this.search(dictionary, it, prefix, checkedList)) }
        }
    }
    return result
}

fun List<List<Char>>.getNeighbours(cell: Cell): List<Cell> {
    val (row, column) = cell
    val result = mutableListOf<Cell>()
    if (row + 1 < this.size) result.add(Cell(row + 1, column))
    if (row - 1 >= 0) result.add(Cell(row - 1, column))
    if (column + 1 < this.first().size) result.add(Cell(row, column + 1))
    if (column - 1 >= 0) result.add(Cell(row, column - 1))
    return result
}

data class Cell(val row: Int, val column: Int)
