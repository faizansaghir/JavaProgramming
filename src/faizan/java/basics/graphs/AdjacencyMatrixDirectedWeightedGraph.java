package faizan.java.basics.graphs;

public class AdjacencyMatrixDirectedWeightedGraph extends DirectedWeightedGraph {
	int[][] graph;
	public AdjacencyMatrixDirectedWeightedGraph(int size) {
		// TODO Auto-generated constructor stub
		graph=new int[size][size];
	}
	@Override
	public void addEdge(int u, int v, int w) {
		// TODO Auto-generated method stub
		graph[u][v]=w;
	}

	@Override
	public void printGraph() {
		// TODO Auto-generated method stub
		System.out.println("Adjacency Matrix Directed Weighted Graph");
		for(int[] vertex:graph) {
			for(int adjacent:vertex) {
				System.out.print(adjacent+" ");
			}
			System.out.println();
		}
	}

	@Override
	public int getWeight(int u, int v) {
		// TODO Auto-generated method stub
		return graph[u][v];
	}
	@Override
	public boolean pathExists(int u, int v) {
		// TODO Auto-generated method stub
		return graph[u][v]>0;
	}

}
