package faizan.java.basics.graphs;

public abstract class DirectedWeightedGraph implements Directed,Weighted {
	public abstract void addEdge(int u,int v,int w) ;
	public abstract boolean pathExists(int u,int v);
	public abstract void printGraph();
}
