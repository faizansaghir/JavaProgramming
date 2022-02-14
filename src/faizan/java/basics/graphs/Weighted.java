package faizan.java.basics.graphs;

public interface Weighted {
	public abstract int getWeight(int u,int v);
	public abstract void shortestPathTree(int source);
}
