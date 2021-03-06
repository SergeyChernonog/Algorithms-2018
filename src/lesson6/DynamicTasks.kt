@file:Suppress("UNUSED_PARAMETER")

package lesson6

import java.io.File
import java.lang.StringBuilder

/**
 * Наибольшая общая подпоследовательность.
 * Средняя
 *
 * Дано две строки, например "nematode knowledge" и "empty bottle".
 * Найти их самую длинную общую подпоследовательность -- в примере это "emt ole".
 * Подпоследовательность отличается от подстроки тем, что её символы не обязаны идти подряд
 * (но по-прежнему должны быть расположены в исходной строке в том же порядке).
 * Если общей подпоследовательности нет, вернуть пустую строку.
 * При сравнении подстрок, регистр символов *имеет* значение.
 */

/**
 * Алгоритм проходит по всем буквам каждого слова при составлении матрицы и восстановлении подстроки - сложность O(M*N),
 * где M и N - длины слов.
 * Для хранения информации используется матрица размера O(M*N)
 */

fun longestCommonSubSequence(first: String, second: String): String {

    val matrix = createCountMatrix(first, second);
    val res = StringBuilder()
    var i = first.length
    var j = second.length

    while (i > 0 && j > 0) {
        when {
            first[i - 1] == second[j - 1] -> {
                res.append(first[i - 1])
                i--
                j--
            }
            matrix[i - 1][j] > matrix[i][j - 1] -> i--
            else -> j--
        }
    }
    return res.reverse().toString()
}

fun createCountMatrix(first: String, second: String): Array<IntArray> {
    val matrix = Array(first.length + 1) { IntArray(second.length + 1) }
    for (i in 1 until matrix.size) {
        for (j in 1 until matrix.first().size) {
            matrix[i][j] = if (first[i - 1] == second[j - 1]) matrix[i - 1][j - 1] + 1
            else maxOf(matrix[i - 1][j], matrix[i][j - 1])
        }
    }
    return matrix
}


/**
 * Наибольшая возрастающая подпоследовательность
 * Средняя
 *
 * Дан список целых чисел, например, [2 8 5 9 12 6].
 * Найти в нём самую длинную возрастающую подпоследовательность.
 * Элементы подпоследовательности не обязаны идти подряд,
 * но должны быть расположены в исходном списке в том же порядке.
 * Если самых длинных возрастающих подпоследовательностей несколько (как в примере),
 * то вернуть ту, в которой числа расположены раньше (приоритет имеют первые числа).
 * В примере ответами являются 2, 8, 9, 12 или 2, 5, 9, 12 -- выбираем первую из них.
 */
fun longestIncreasingSubSequence(list: List<Int>): List<Int> {
    TODO()
}

/**
 * Самый короткий маршрут на прямоугольном поле.
 * Сложная
 *
 * В файле с именем inputName задано прямоугольное поле:
 *
 * 0 2 3 2 4 1
 * 1 5 3 4 6 2
 * 2 6 2 5 1 3
 * 1 4 3 2 6 2
 * 4 2 3 1 5 0
 *
 * Можно совершать шаги длиной в одну клетку вправо, вниз или по диагонали вправо-вниз.
 * В каждой клетке записано некоторое натуральное число или нуль.
 * Необходимо попасть из верхней левой клетки в правую нижнюю.
 * Вес маршрута вычисляется как сумма чисел со всех посещенных клеток.
 * Необходимо найти маршрут с минимальным весом и вернуть этот минимальный вес.
 *
 * Здесь ответ 2 + 3 + 4 + 1 + 2 = 12
 */


/**
 * Алгоритм проходит по каждой ячейке - сложность O(M*N), где M*N - колличество клеток
 * Для хранения поля и подсчета пути используется матрица размера O(M*N)
 */

fun shortestPathOnField(inputName: String): Int {
    val fieldMatrix = mutableListOf<MutableList<Int>>()
    File(inputName).forEachLine { line -> fieldMatrix.add(line.split(" ").map { it.toInt() }.toMutableList()) }


    for (i in 0 until fieldMatrix.size) {
        for (j in 0 until fieldMatrix.first().size) {
            fieldMatrix[i][j] = fieldMatrix[i][j] + fieldMatrix.getMinFromNeighbours(i, j)
        }
    }
    return fieldMatrix.last().last()
}

fun MutableList<MutableList<Int>>.getMinFromNeighbours(i: Int, j: Int) =
        when {
            i == 0 && j == 0 -> 0
            i == 0 -> this[i][j - 1]
            j == 0 -> this[i - 1][j]
            else -> minOf(this[i - 1][j], this[i][j - 1], this[i - 1][j - 1])
        }


// Задачу "Максимальное независимое множество вершин в графе без циклов"
// смотрите в уроке 5