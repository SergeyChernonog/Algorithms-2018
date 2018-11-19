package lesson5

import lesson5.impl.GraphBuilder
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertTrue

abstract class AbstractGraphTests {

    private fun Graph.Edge.isNeighbour(other: Graph.Edge): Boolean {
        return begin == other.begin || end == other.end || begin == other.end || end == other.begin
    }

    private fun List<Graph.Edge>.assert(shouldExist: Boolean, graph: Graph) {
        val edges = graph.edges
        if (shouldExist) {
            assertEquals(edges.size, size, "Euler loop should traverse all edges")
        } else {
            assertTrue(isEmpty(), "Euler loop should not exist")
        }
        for (edge in this) {
            assertTrue(edge in edges, "Edge $edge is not inside graph")
        }
        for (i in 0 until size - 1) {
            assertTrue(this[i].isNeighbour(this[i + 1]), "Edges ${this[i]} & ${this[i + 1]} are not incident")
        }
        assertTrue(this[0].isNeighbour(this[size - 1]), "Edges ${this[0]} & ${this[size - 1]} are not incident")
    }

    fun findEulerLoop(findEulerLoop: Graph.() -> List<Graph.Edge>) {
        val graph = GraphBuilder().apply {
            val a = addVertex("A")
            val b = addVertex("B")
            val c = addVertex("C")
            addConnection(a, b)
            addConnection(b, c)
            addConnection(a, c)
        }.build()
        val loop = graph.findEulerLoop()
        loop.assert(true, graph)
        val graph2 = GraphBuilder().apply {
            val a = addVertex("A")
            val b = addVertex("B")
            val c = addVertex("C")
            val d = addVertex("D")
            val e = addVertex("E")
            val f = addVertex("F")
            val g = addVertex("G")
            val h = addVertex("H")
            val i = addVertex("I")
            val j = addVertex("J")
            val k = addVertex("K")
            addConnection(a, b)
            addConnection(b, c)
            addConnection(c, d)
            addConnection(a, e)
            addConnection(d, k)
            addConnection(e, j)
            addConnection(j, k)
            addConnection(b, f)
            addConnection(c, i)
            addConnection(f, i)
            addConnection(b, g)
            addConnection(g, h)
            addConnection(h, c)
        }.build()
        val loop2 = graph2.findEulerLoop()
        loop2.assert(true, graph2)
    }

    fun minimumSpanningTree(minimumSpanningTree: Graph.() -> Graph) {

        //новые тесты
        val disconnectedGraph = GraphBuilder().apply {
            val a = addVertex("A")
            val b = addVertex("B")
            val c = addVertex("C")
            val d = addVertex("D")
            addConnection(a, b)
            addConnection(c, d)
        }.build()
        val treeOfDisconnected = disconnectedGraph.minimumSpanningTree()
        assertEquals(0, treeOfDisconnected.edges.size)

        val smallGraph = GraphBuilder().apply { addVertex("A") }.build()
        val smallTree = smallGraph.minimumSpanningTree()
        assertEquals(0, smallTree.edges.size)

        val emptyGraph = GraphBuilder().build()
        val emptyTree = emptyGraph.minimumSpanningTree()
        assertEquals(0, emptyTree.edges.size)

        val vertexAmount = 200
        val bigCompleteGraph = GraphBuilder().apply {
            val vertices = Array<Graph.Vertex?>(vertexAmount) { null }
            for (i in 0 until vertexAmount) {
                vertices[i] = addVertex(i.toString())
            }
            for (i in 0 until vertices.size) {
                for (j in i + 1 until vertices.size)
                    addConnection(vertices[i]!!, vertices[j]!!)
            }
        }.build()
        val bigTree = bigCompleteGraph.minimumSpanningTree()
        assertEquals(vertexAmount - 1, bigTree.edges.size)
        assertEquals(vertexAmount - 1, bigTree.findBridges().size)


        val graph = GraphBuilder().apply {
            val a = addVertex("A")
            val b = addVertex("B")
            val c = addVertex("C")
            addConnection(a, b)
            addConnection(b, c)
            addConnection(a, c)
        }.build()
        val tree = graph.minimumSpanningTree()
        assertEquals(2, tree.edges.size)
        assertEquals(2, tree.findBridges().size)
        val graph2 = GraphBuilder().apply {
            val a = addVertex("A")
            val b = addVertex("B")
            val c = addVertex("C")
            val d = addVertex("D")
            val e = addVertex("E")
            val f = addVertex("F")
            val g = addVertex("G")
            val h = addVertex("H")
            val i = addVertex("I")
            val j = addVertex("J")
            val k = addVertex("K")
            addConnection(a, b)
            addConnection(b, c)
            addConnection(c, d)
            addConnection(a, e)
            addConnection(d, k)
            addConnection(e, j)
            addConnection(j, k)
            addConnection(b, f)
            addConnection(c, i)
            addConnection(f, i)
            addConnection(b, g)
            addConnection(g, h)
            addConnection(h, c)
        }.build()
        val tree2 = graph2.minimumSpanningTree()
        assertEquals(10, tree2.edges.size)
        assertEquals(10, tree2.findBridges().size)
    }

    fun makeBigTree() {
        var vertAmount = 100
        var withRoot = true
        var setWithRootSize = 1
        var setWithoutRootSize = 0
        val random = Random()
        val tree = GraphBuilder().apply {
            val queue = ArrayDeque<Graph.Vertex>()
            queue.add(addVertex("root"))
            vertAmount--
            while (queue.isNotEmpty() && vertAmount != 0) {
                val current = queue.poll()
                val numberOfChildren = random.nextInt(vertAmount)
                vertAmount -= numberOfChildren
                for (i in 1..numberOfChildren) {
                    val child = addVertex(current.name + i)
                    addConnection(current, child)
                    queue.add(child)
                }
            }
        }.build()
        val setSize = if (setWithRootSize > setWithoutRootSize) setWithRootSize else setWithoutRootSize
    }


    fun largestIndependentVertexSet(largestIndependentVertexSet: Graph.() -> Set<Graph.Vertex>) {
        // новые тесты
        val smallGraph = GraphBuilder().apply { addVertex("A") }.build()
        val smallIndependent = smallGraph.largestIndependentVertexSet()
        assertEquals(setOf(smallGraph["A"]), smallIndependent)

        val emptyGraph = GraphBuilder().build()
        val emptyIndependent = emptyGraph.largestIndependentVertexSet()
        assertEquals(emptySet(), emptyIndependent)

        val bigGraph = GraphBuilder().apply {
            var count = 780
            val root = addVertex("r")
            val queue = ArrayDeque<Graph.Vertex>()
            queue.add(root)
            while (queue.isNotEmpty() && count > 0) {
                val vertex = queue.poll()
                for (i in 0 until 5) {
                    val child = addVertex(vertex.name + i)
                    addConnection(vertex, child)
                    queue.add(child)
                    count--
                }
            }
        }.build()
        val bigSet = bigGraph.largestIndependentVertexSet()
        assertEquals(651, bigSet.size)


        val graph = GraphBuilder().apply {
            val a = addVertex("A")
            val b = addVertex("B")
            val c = addVertex("C")
            val d = addVertex("D")
            val e = addVertex("E")
            val f = addVertex("F")
            val g = addVertex("G")
            val h = addVertex("H")
            val i = addVertex("I")
            val j = addVertex("J")
            addConnection(a, b)
            addConnection(a, c)
            addConnection(b, d)
            addConnection(c, e)
            addConnection(c, f)
            addConnection(b, g)
            addConnection(d, i)
            addConnection(g, h)
            addConnection(h, j)
        }.build()
        val independent = graph.largestIndependentVertexSet()
        assertEquals(setOf(graph["A"], graph["D"], graph["E"], graph["F"], graph["G"], graph["J"]),
                independent)
    }

    fun longestSimplePath(longestSimplePath: Graph.() -> Path) {

        // новые тесты
        val smallGraph = GraphBuilder().apply { addVertex("A") }.build()
        val smallPath = smallGraph.longestSimplePath()
        assertEquals(0, smallPath.length)

        val vertexAmount = 200
        val bigCompleteGraph = GraphBuilder().apply {
            val vertices = Array<Graph.Vertex?>(vertexAmount) { null }
            for (i in 0 until vertexAmount) {
                vertices[i] = addVertex(i.toString())
            }
            for (i in 0 until vertices.size) {
                for (j in i + 1 until vertices.size)
                    addConnection(vertices[i]!!, vertices[j]!!)
            }
        }.build()
        val bigPath = bigCompleteGraph.longestSimplePath()
        assertEquals(vertexAmount - 1, bigPath.length)


        val graph = GraphBuilder().apply {
            val a = addVertex("A")
            val b = addVertex("B")
            val c = addVertex("C")
            addConnection(a, b)
            addConnection(b, c)
            addConnection(a, c)
        }.build()
        val longestPath = graph.longestSimplePath()
        assertEquals(2, longestPath.length)

        val graph2 = GraphBuilder().apply {
            val a = addVertex("A")
            val b = addVertex("B")
            val c = addVertex("C")
            val d = addVertex("D")
            val e = addVertex("E")
            val f = addVertex("F")
            val g = addVertex("G")
            val h = addVertex("H")
            val i = addVertex("I")
            val j = addVertex("J")
            val k = addVertex("K")
            addConnection(a, b)
            addConnection(b, c)
            addConnection(c, d)
            addConnection(a, e)
            addConnection(d, k)
            addConnection(e, j)
            addConnection(j, k)
            addConnection(b, f)
            addConnection(c, i)
            addConnection(f, i)
            addConnection(b, g)
            addConnection(g, h)
            addConnection(h, c)
        }.build()
        val longestPath2 = graph2.longestSimplePath()
        assertEquals(10, longestPath2.length)
    }

}