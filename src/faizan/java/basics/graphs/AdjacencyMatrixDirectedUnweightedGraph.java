package faizan.java.basics.graphs;

public class AdjacencyMatrixDirectedUnweightedGraph extends DirectedUnweightedGraph {
	int[][] graph;
	public AdjacencyMatrixDirectedUnweightedGraph(int size) {
		// TODO Auto-generated constructor stub
		graph=new int[size][size];
	}
	@Override
	public void addEdge(int u, int v) {
		// TODO Auto-generated method stub
		graph[u][v]=1;
	}

	@Override
	public void printGraph() {
		// TODO Auto-generated method stub
		System.out.println("Adjacency Matrix Directed Unweighted Graph");
		for(int[] vertex:graph) {
			for(int weight:vertex) {
				System.out.print(weight+" ");
			}
			System.out.println();
		}
	}
	@Override
	public boolean pathExists(int u, int v) {
		// TODO Auto-generated method stub
		return graph[u][v]>0;
	}

}
