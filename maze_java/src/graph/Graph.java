package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Graph<V> {

	private Set<V> vertices = new HashSet<V>(); // all the vercities.
	private Map<V, Set<V>> edges = new HashMap<V, Set<V>>(); // for each vertix, hold all its connected verticies.
	private int num; // number of vertices.

	public void addVertex(V v) throws GraphException {
		if (vertices.contains(v) == true) {
			System.out.println("graph already contains this vertix");
			throw new GraphException();
		} else {
			vertices.add(v); // add V to the set.
			num++;
		}
	}

	public void addEdge(V v1, V v2) throws GraphException {
		if (hasEdge(v1, v2) == true) { // there is an edge already
			System.out.println("there is an edge already.");
			throw new GraphException();
		} else {
			if ((!vertices.contains(v1)) || (!vertices.contains(v2))) // vertices not in graph.
			{
				System.out.println("ERROR verices are not in Grraph");
				throw new GraphException();
			}

			else {
				if (edges.get(v1) == null) {
					Set<V> set = new HashSet<>();
					set.add(v2);
					edges.put(v1, set);
				} else
					edges.get(v1).add(v2);
				if (edges.get(v2) == null) {
					Set<V> set2 = new HashSet<>();
					set2.add(v1);
					edges.put(v2, set2);
				} else {
					edges.get(v2).add(v1);
				}
			}
		}
	}

	public boolean hasEdge(V v1, V v2) {
		if (edges.get(v1) == null)
			return false;
		if (edges.get(v1).contains(v2))
			return true;
		return false;
	}

	public boolean connected(V v1, V v2) throws GraphException {
		Set<V> set = new HashSet<V>();
		if (!(vertices.contains(v1)) || !(vertices.contains(v2))) {
			System.out.println("ERROR vertices not in graph.");
			throw new GraphException();
		} else
			return DFS(v1, v2, set);
	}

	private boolean DFS(V v1, V v2, Set<V> set) {
		boolean flag = false;
		if (v1.equals(v2))
			return true;
		else {
			for (V ver : edges.get(v1)) {
				if (!set.contains(ver))
					if (ver.equals(v2)) // for all the adjacency of v1. if they have v2, return true.
						return true;
			}
			set.add(v1);
			for (V ver : edges.get(v1)) { // if v2 not a neighbor of v1 search in the neighbors of the neighbors of v1.
				if (flag == true)
					break;
				if (!set.contains(ver)) {
					flag = flag || DFS(ver, v2, set);
				}
			}
			return flag;
		}
	}
/*
	public static void main(String[] args) throws GraphException {
		Graph<Integer> g = new Graph<>();
		for (int i = 0; i < 100; i++)
			g.addVertex(i);
		for (int i = 0; i < 50; i++)
			g.addEdge(i, i + 1);
		// g.addEdge(1, 2);
		System.out.println(g.connected(1, 10));
		System.out.println(g.connected(3, 70));
	}
*/
}
