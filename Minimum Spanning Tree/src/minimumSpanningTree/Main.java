package minimumSpanningTree;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.TreeSet;

public class Main {
	public static class Node implements Comparable<Node> {
		private int index;
		private int d=Integer.MAX_VALUE;
		
		
		public Node(int index){
			this.index=index;
			
		}
		
		public int getIndex(){
			return index;
		}
		
		public int getD(){
			return d;
		}

		
		public void setD(int d){
			this.d=d;
		}

		@Override
		public int compareTo(Node node) {
			if (this.d-node.d==0){
				return this.index-node.index;
			}else{
				return this.d-node.d;
			}
		}
	
	}
	
	public static class WeightNode {
		private int index;
		private int weight;
		
		public WeightNode(int index, int weight){
			this.index=index;
			this.weight=weight;
		}
		
		public int getIndex(){
			return index;
		}
		
		public int getWeight(){
			return weight;
		}
	}
	public static class Edge implements Comparable<Edge>{
		private int startEdge;
		private int endEdge;
		public Edge(int startEdge, int endEdge){
			this.startEdge=startEdge;
			this.endEdge=endEdge;
		}
		public int getStartEdge(){
			return startEdge;
		}
		public int getEndEdge(){
			return endEdge;
		}
		@Override
		public int compareTo(Edge edge) {
			if(edge.startEdge==startEdge){
				return endEdge-edge.endEdge;
			}else{
				return startEdge-edge.startEdge;
			}
		}
	}

	public static void prim(int n, List<List<WeightNode>> weights){
		int[] parent = new int[n];
		Queue<Edge> edges = new PriorityQueue<Edge>();
		TreeSet<Node> q = new TreeSet<Node>();
		Boolean[] choosenNodes=new Boolean[n];
		Node[] nodes = new Node[n];
		for (int i = 0; i < n; i++) {

			nodes[i]=new Node(i);;
			q.add(nodes[i]);
			choosenNodes[i] = false;
		}
		int totalTime=0;

		Node currentNode = q.pollFirst();
		
		parent[currentNode.getIndex()]=-1;
		choosenNodes[currentNode.getIndex()]=true;
		while (!q.isEmpty()) {
			int currentIdx=currentNode.getIndex();
			List<WeightNode> neighbours = weights.get(currentIdx);
		
			for (int i = 0; i < neighbours.size(); i++) {
				int neighbourIdx=neighbours.get(i).getIndex();
				if (choosenNodes[neighbourIdx]==false){
					if (neighbours.get(i).getWeight() < nodes[neighbourIdx].getD()) {	
						q.remove(nodes[neighbourIdx]);
						nodes[neighbourIdx].setD(neighbours.get(i).getWeight());
						q.add(nodes[neighbourIdx]);
						parent[neighbourIdx]=currentIdx;
					}
				}
			}
	
			currentNode = q.pollFirst();
			int nodeIndex=currentNode.getIndex();
			
			choosenNodes[nodeIndex]=true;
			int d = currentNode.getD();
			if(d==Integer.MAX_VALUE){
				System.out.println("Impossible");
				return;
			}
			totalTime+=d;
			if (nodeIndex > parent[nodeIndex]){
				edges.add(new Edge(parent[nodeIndex], nodeIndex));
				
			}else{
				edges.add(new Edge(nodeIndex, parent[nodeIndex]));
			}
			
			
		}
		
		System.out.println(totalTime);
		while(!edges.isEmpty()){
			Edge e = edges.poll();
			System.out.println(e.getStartEdge() + " " + e.getEndEdge());
		}
		
	}
	public static void main(String[] args) {
		

		Scanner scan = new Scanner(System.in);
		
		int n = scan.nextInt();
		int m = scan.nextInt();
		while(true){
			if(n==0 && m==0){
				scan.close();
				return;
			}else if(n==0){
				System.out.println("Impossible");
			}else {
				List<List<WeightNode>> weights = new ArrayList<List<WeightNode>>(n);
				for (int i = 0; i < n; i++) {
					weights.add(i,new ArrayList<WeightNode>()); 
				}
				for (int i = 0; i < m; i++) {
					int neighbour1= scan.nextInt();
					int neighbour2= scan.nextInt();
					int w = scan.nextInt();
					weights.get(neighbour1).add(new WeightNode(neighbour2,w));
					weights.get(neighbour2).add(new WeightNode(neighbour1,w));
				}
				prim(n, weights);
			}
			n = scan.nextInt();
			m = scan.nextInt();
		}
	}
}
