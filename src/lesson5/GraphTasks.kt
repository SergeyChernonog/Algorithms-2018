@file:Suppress("UNUSED_PARAMETER", "unused")

package lesson5

import lesson5.impl.GraphBuilder
import java.util.*
import kotlin.collections.HashMap

/**
 * Эйлеров цикл.
 * Средняя
 *
 * Дан граф (получатель). Найти по нему любой Эйлеров цикл.
 * Если в графе нет Эйлеровых циклов, вернуть пустой список.
 * Соседние дуги в списке-результате должны быть инцидентны друг другу,
 * а первая дуга в списке инцидентна последней.
 * Длина списка, если он не пуст, должна быть равна количеству дуг в графе.
 * Веса дуг никак не учитываются.
 *
 * Пример:
 *
 *      G -- H
 *      |    |
 * A -- B -- C -- D
 * |    |    |    |
 * E    F -- I    |
 * |              |
 * J ------------ K
 *
 * Вариант ответа: A, E, J, K, D, C, H, G, B, C, I, F, B, A
 *
 * Справка: Эйлеров цикл -- это цикл, проходящий через все рёбра
 * связного графа ровно по одному разу
 */
fun Graph.findEulerLoop(): List<Graph.Edge> {
    TODO()
}

/**
 * Минимальное остовное дерево.
 * Средняя
 *
 * Дан граф (получатель). Найти по нему минимальное остовное дерево.
 * Если есть несколько минимальных остовных деревьев с одинаковым числом дуг,
 * вернуть любое из них. Веса дуг не учитывать.
 *
 * Пример:
 *
 *      G -- H
 *      |    |
 * A -- B -- C -- D
 * |    |    |    |
 * E    F -- I    |
 * |              |
 * J ------------ K
 *
 * Ответ:
 *
 *      G    H
 *      |    |
 * A -- B -- C -- D
 * |    |    |
 * E    F    I
 * |
 * J ------------ K
 */

/**
 * Используется алгоритм Краскала - выполняется Е итераций алгоритма, где E - количество ребер.
 * Затраты памяти для хранения нового графа - O(E + V), где V - количество вершин.
 * Для проверки нового графа на цикличность используется поиск в ширину сложность - O(E + V),
 * затраты памяти для хранения очереди вершин и списка посещенных - O(E + V).
 */
fun Graph.minimumSpanningTree(): Graph {

    if (shortestPath(vertices.first()).any { it.value.distance == Int.MAX_VALUE }) return GraphBuilder().build()

    return GraphBuilder().apply {
        addVertices(vertices)
        edges.forEach {
            addConnection(it)
            if (build().haveCycle()) removeConnection(it)
        }
    }.build()
}

fun Graph.haveCycle(): Boolean {
    val queue = ArrayDeque<Pair<Graph.Vertex, Graph.Vertex?>>() // пара из текущей и предыдущей вершин
    val visited = mutableListOf<Graph.Vertex>()
    queue.add(Pair(this.vertices.first(), null))
    while (queue.isNotEmpty()) {
        val (current, prev) = queue.poll()
        if (current in visited) return true
        this.getNeighbors(current).filter { it != prev }.forEach { queue.add(Pair(it, current)) }
        visited.add(current)
    }
    return false
}


/**
 * Максимальное независимое множество вершин в графе без циклов.
 * Сложная
 *
 * Дан граф без циклов (получатель), например
 *
 *      G -- H -- J
 *      |
 * A -- B -- D
 * |         |
 * C -- F    I
 * |
 * E
 *
 * Найти в нём самое большое независимое множество вершин и вернуть его.
 * Никакая пара вершин в независимом множестве не должна быть связана ребром.
 *
 * Если самых больших множеств несколько, приоритет имеет то из них,
 * в котором вершины расположены раньше во множестве this.vertices (начиная с первых).
 *
 * В данном случае ответ (A, E, F, D, G, J)
 *
 * Эта задача может быть зачтена за пятый и шестой урок одновременно
 */
fun Graph.largestIndependentVertexSet(): Set<Graph.Vertex> {
    val root = this.vertices.first()
    val storage = hashMapOf<Graph.Vertex, Set<Graph.Vertex>>()
    val parents = hashMapOf<Graph.Vertex, Graph.Vertex?>(root to null)
    return independentSetForVertex(storage, root, parents)
}

fun Graph.independentSetForVertex(storage: MutableMap<Graph.Vertex, Set<Graph.Vertex>>,
                                  vertex: Graph.Vertex, parents: Map<Graph.Vertex,
                Graph.Vertex?>): Set<Graph.Vertex> {

    val children = this.getNeighbors(vertex)


    return emptySet()
}


/**
 * Наидлиннейший простой путь.
 * Сложная
 *
 * Дан граф (получатель). Найти в нём простой путь, включающий максимальное количество рёбер.
 * Простым считается путь, вершины в котором не повторяются.
 * Если таких путей несколько, вернуть любой из них.
 *
 * Пример:
 *
 *      G -- H
 *      |    |
 * A -- B -- C -- D
 * |    |    |    |
 * E    F -- I    |
 * |              |
 * J ------------ K
 *
 * Ответ: A, E, J, K, D, C, H, G, B, F, I
 */
fun Graph.longestSimplePath(): Path {
    TODO()
}