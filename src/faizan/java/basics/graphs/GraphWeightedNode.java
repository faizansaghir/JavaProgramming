package faizan.java.basics.graphs;

public class GraphWeightedNode implements Comparable<GraphWeightedNode> {
	int index;
	int weight;
	public GraphWeightedNode(int index,int weight) {
		this.index=index;
		this.weight=weight;
	}
	public String toString() {
		return "["+index+","+weight+"]";
	}
	@Override
	public int compareTo(GraphWeightedNode o) {
		return weight-o.weight;
	}
}
