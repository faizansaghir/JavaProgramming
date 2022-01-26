package faizan.java.basics.graphs;

public class AdjacencyMatrixUndirectedUnweightedGraph extends UndirectedUnweightedGraph {
	int[][] graph;
	AdjacencyMatrixUndirectedUnweightedGraph(int size){
		graph=new int[size][size];
	}
	public void addEdge(int u,int v) {
		graph[u][v]=1;
		graph[v][u]=1;
	}
	public boolean areConnected(int u,int v) {
		return graph[u][v]==1;
	}
	public void printGraph() {
		System.out.println("Adjacency Matrix Undirected Unweighted Graph");
		for(int[] node:graph) {
			for(int adjacency:node) {
				System.out.print(adjacency+" ");
			}
			System.out.println();
		}
	}
}
