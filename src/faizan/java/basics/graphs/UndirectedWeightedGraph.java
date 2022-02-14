package faizan.java.basics.graphs;

public abstract class UndirectedWeightedGraph implements Undirected,Weighted {
	public abstract void addEdge(int u,int v,int w) ;
	public abstract boolean areConnected(int u,int v);
	public abstract void printGraph();
	public abstract boolean isCyclic();
}
