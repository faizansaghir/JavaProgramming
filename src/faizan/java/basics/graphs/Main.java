package faizan.java.basics.graphs;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UndirectedUnweightedGraph graph1=new AdjacencyMatrixUndirectedUnweightedGraph(5);
		graph1.addEdge(0, 1);
		graph1.addEdge(0, 2);
		graph1.addEdge(0, 3);
		graph1.addEdge(1, 4);
		graph1.addEdge(2, 3);
		graph1.addEdge(2, 4);
		graph1.printGraph();
		UndirectedUnweightedGraph graph2=new AdjacencyListUndirectedUnweightedGraph();
		graph2.addEdge(0, 1);
		graph2.addEdge(0, 2);
		graph2.addEdge(0, 3);
		graph2.addEdge(1, 4);
		graph2.addEdge(2, 3);
		graph2.addEdge(2, 4);
		graph2.printGraph();
		UndirectedWeightedGraph graph3=new AdjacencyMatrixUndirectedWeightedGraph(5);
		graph3.addEdge(0, 1, 5);
		graph3.addEdge(0, 2, 3);
		graph3.addEdge(0, 3, 6);
		graph3.addEdge(1, 4, 2);
		graph3.addEdge(2, 3, 1);
		graph3.addEdge(2, 4, 2);
		graph3.printGraph();
		UndirectedWeightedGraph graph4=new AdjacencyListUndirectedWeightedGraph();
		graph4.addEdge(0, 1, 5);
		graph4.addEdge(0, 2, 3);
		graph4.addEdge(0, 3, 6);
		graph4.addEdge(1, 4, 2);
		graph4.addEdge(2, 3, 1);
		graph4.addEdge(2, 4, 2);
		graph4.printGraph();
		DirectedUnweightedGraph graph5=new AdjacencyMatrixDirectedUnweightedGraph(5);
		graph5.addEdge(0, 2);
		graph5.addEdge(0, 3);
		graph5.addEdge(1, 0);
		graph5.addEdge(1, 4);
		graph5.addEdge(2, 4);
		graph5.addEdge(3, 2);
		graph5.addEdge(4, 1);
		graph5.printGraph();
		DirectedUnweightedGraph graph6=new AdjacencyListDirectedUnweightedGraph();
		graph6.addEdge(0, 2);
		graph6.addEdge(0, 3);
		graph6.addEdge(1, 0);
		graph6.addEdge(1, 4);
		graph6.addEdge(2, 4);
		graph6.addEdge(3, 2);
		graph6.addEdge(4, 1);
		graph6.printGraph();
		DirectedWeightedGraph graph7=new AdjacencyMatrixDirectedWeightedGraph(5);
		graph7.addEdge(0, 2, 3);
		graph7.addEdge(0, 3, 6);
		graph7.addEdge(1, 0, 5);
		graph7.addEdge(1, 4, 2);
		graph7.addEdge(2, 4, 2);
		graph7.addEdge(3, 2, 1);
		graph7.addEdge(4, 1, 2);
		graph7.printGraph();
		DirectedWeightedGraph graph8=new AdjacencyListDirectedWeightedGraph();
		graph8.addEdge(0, 2, 3);
		graph8.addEdge(0, 3, 6);
		graph8.addEdge(1, 0, 5);
		graph8.addEdge(1, 4, 2);
		graph8.addEdge(2, 4, 2);
		graph8.addEdge(3, 2, 1);
		graph8.addEdge(4, 1, 2);
		graph8.printGraph();
	}

}
