package faizan.java.basics.graphs;

public class AdjacencyMatrixDirectedWeightedGraph extends DirectedWeightedGraph {
	int[][] graph;
	public AdjacencyMatrixDirectedWeightedGraph(int size) {
		graph=new int[size][size];
	}
	@Override
	public void addEdge(int u, int v, int w) {
		graph[u][v]=w;
	}

	@Override
	public void printGraph() {
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
		return graph[u][v];
	}
	@Override
	public boolean pathExists(int u, int v) {
		return graph[u][v]>0;
	}
	public void getShortestPrathTree(int source) {
		boolean[] sptSet=new boolean[graph.length];
		int[] distances=new int[graph.length];
		for(int i=0;i<graph.length;i++) {
			distances[i]=Integer.MAX_VALUE;
		}
		distances[source]=0;
		for(int i=0;i<graph.length-1;i++) {
			int ind=getMinimumDistanceNodeIndex(distances,sptSet);
			sptSet[ind]=true;
			int[] adjacentNodes=graph[ind];
			for(int j=0;j<adjacentNodes.length;j++) {
				if(adjacentNodes[j]!=0)
					distances[j]=Math.min(distances[j], distances[ind]+adjacentNodes[j]);
			}
		}
		System.out.println("Shortest path tree from node indexed "+source);
		for(int i=0;i<distances.length;i++) {
			System.out.printf("[%d,%d] ",i,distances[i]);
		}
		System.out.println();
	}
	private int getMinimumDistanceNodeIndex(int[] distances,boolean[] sptSet) {
		int min=Integer.MAX_VALUE;
		int index=-1;
		for(int i=0;i<distances.length;i++) {
			if(!sptSet[i] && distances[i]<=min) {
				index=i;
				min=distances[i];
			}
		}
		return index;
	}
}
