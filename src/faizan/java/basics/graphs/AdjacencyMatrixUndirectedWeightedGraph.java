package faizan.java.basics.graphs;

public class AdjacencyMatrixUndirectedWeightedGraph extends UndirectedWeightedGraph{
	int[][] graph;
	public AdjacencyMatrixUndirectedWeightedGraph(int size) {
		graph=new int[size][size];
	}
	@Override
	public void addEdge(int u, int v, int w) {
		graph[u][v]=w;
		graph[v][u]=w;
	}

	@Override
	public boolean areConnected(int u, int v) {
		return graph[u][v]>0;
	}

	@Override
	public void printGraph() {
		System.out.println("Adjacency Matrix Undirected Weighted Graph");
		for(int[] vertex:graph) {
			for(int adjacent:vertex) {
				System.out.print(adjacent+" ");
			}
			System.out.println();
		}
	}
	@Override
	public int getWeight(int u, int v) {
		return graph[u][v];
	}

}