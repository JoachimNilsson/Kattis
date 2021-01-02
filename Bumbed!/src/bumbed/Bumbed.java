package bumbed;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeSet;


public class Bumbed {
    static class FastReader 
    { 
        BufferedReader br; 
        StringTokenizer st; 
  
        public FastReader() 
        { 
            br = new BufferedReader(new
                     InputStreamReader(System.in)); 
        } 
  
        String next() 
        { 
            while (st == null || !st.hasMoreElements()) 
            { 
                try
                { 
                    st = new StringTokenizer(br.readLine()); 
                } 
                catch (IOException  e) 
                { 
                    e.printStackTrace(); 
                } 
            } 
            return st.nextToken(); 
        } 
  
        int nextInt() 
        { 
            return Integer.parseInt(next()); 
        } 
 
  
        double nextDouble() 
        { 
            return Double.parseDouble(next()); 
        } 
  
    } 
	public static class Node implements Comparable<Node>{
		private int idx;
		private int d;
	
		public Node(int idx){
			this.idx = idx;

		}
		public int getIndex(){
			return idx;
		}
		public int getD(){
			return d;
		}
		public void setD(int distance){
			d=distance;
		}
		
		@Override
		public int compareTo(Node node) {
			if (this.d-node.d==0){
				return this.idx-node.idx;
			}else{
				return this.d-node.d;
			}
		}

	}
	public static class Edge {
		private Node node;
		private int weight;
		public Edge(Node node, int weight){
			this.node=node;
			this.weight=weight;
		}
		public Node getNode(){
			return node;
		}
		public int getWeight(){
			return weight;
		}
		public String toString() { 
	        return String.format(node.getIndex() + " " + weight); 
	    } 
	}
	
	public static int dijkstras(Node[] nodes, List<List<Edge>> edges,  int start, int end){
		if(start == end){
			return 0;
			
		}
		TreeSet<Node> q = new TreeSet<Node>();
		boolean[] choosenNodes = new boolean[nodes.length];
		for (int i = 0; i < nodes.length; i++) {
			if(i==start){
				nodes[start].setD(0);
				choosenNodes[i]=true;
			}else{
				nodes[i].setD(Integer.MAX_VALUE);
				q.add(nodes[i]);
				choosenNodes[i]=false;
			}
		}
		Node currentNode = nodes[start];
		while (!q.isEmpty()) {
			int currentIdx=currentNode.getIndex();
			List<Edge> neighbours = edges.get(currentIdx);
		
			for (int i = 0; i < neighbours.size(); i++) {
				int neighbourIdx=neighbours.get(i).getNode().getIndex();
				if (choosenNodes[neighbourIdx]==false){
					if (nodes[currentIdx].getD() + neighbours.get(i).getWeight() < nodes[neighbourIdx].getD()) {	
						q.remove(nodes[neighbourIdx]);
						nodes[neighbourIdx].setD(nodes[currentIdx].getD() + neighbours.get(i).getWeight());
						q.add(nodes[neighbourIdx]);
					}
				}
			}
			currentNode = q.pollFirst();
			if(currentNode.getIndex() == end){
				return currentNode.getD();
			}else if(currentNode.getD()==Integer.MAX_VALUE){
				return Integer.MAX_VALUE;
			}
		}
		return Integer.MAX_VALUE;
	}
    
	public static void main(String[] args) {
		FastReader scan = new FastReader();
		int n = scan.nextInt();
		int m = scan.nextInt();
		int f = scan.nextInt();
		int s = scan.nextInt();
		int t = scan.nextInt();
		Node[] nodes = new Node[n];
		List<List<Edge>> edges = new ArrayList<List<Edge>>();
		for (int i = 0; i < n; i++) {
			nodes[i] = new Node(i);
			edges.add(i,new ArrayList<Edge>());
		}
		
		for (int i = 0; i < m; i++) {
			int u = scan.nextInt();
			int v = scan.nextInt();
			int c = scan.nextInt();
			edges.get(u).add(new Edge(nodes[v],c));
			edges.get(v).add(new Edge(nodes[u],c));
		}
		int minDist = dijkstras(nodes, edges,  s, t);
		for (int i = 0; i < f; i++) {
			int u = scan.nextInt();
			int v = scan.nextInt();
			Edge newEdge = new Edge(nodes[v],0);
			boolean removedEdge = false;
			Edge oldEdge = new Edge(nodes[0],0);
			for (Edge e : edges.get(u)) {
				if(e.getNode().getIndex()==v){
					edges.get(u).remove(e);
					oldEdge = e;
					removedEdge = true;
					break;
				}
			}
			edges.get(u).add(newEdge);
			
			int dist = dijkstras(nodes, edges,  s, t);
			if (dist<minDist){
				minDist = dist;
			}
			edges.get(u).remove(newEdge);
			if(removedEdge){
				edges.get(u).add(oldEdge);
			}
			
		}
		System.out.println(minDist);

	}

}
