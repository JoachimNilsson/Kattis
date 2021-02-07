package phoneList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class PhoneList {
	
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
    } 
    
    static class Node {
        private boolean isNumber;
        private Map<String, Node> children;
        
        
        public Node(String number, boolean isNumber) {
        	
        	this.isNumber = isNumber;
        	this.children = new HashMap<String, Node>();
        }
        public boolean getIfNumber() {
        	return isNumber;
        }
        public void setIfNumber(boolean setNumber) {
        	isNumber=setNumber;
        }
        
        public Map<String, Node> getChildren() {
        	return children;
        } 
    }
    
    static boolean insert(Node node, String number) {
    	for (int i = 0; i < number.length(); i++) {
    		String prefix = number.substring(0, i+1);
    		if (!node.getChildren().containsKey(prefix)) {
				node.getChildren().put(prefix, new Node(prefix, false));
			} else {
				if(node.getChildren().get(prefix).getIfNumber()) {
					return true;
				}
			}
    		node = node.getChildren().get(prefix);
		}
    	node.setIfNumber(true);
    	if (node.getChildren().isEmpty()) {
    		return false;
    	}else {
    		return true;
    	} 	
    }
    
    public static void main(String[] args){
    	FastReader s = new FastReader();
    	int t = s.nextInt();
 
    	for (int i = 0; i < t; i++) {
    		String res = "YES";
			int n = s.nextInt();
			Node root = new Node(null, false);
			testCase:	
			for (int j = 0; j < n; j++) {
				String number = s.next();
				if(insert(root, number)) {
					res="NO";
					for (int k = j+1; k < n; k++) {
						s.next();
					}
					break testCase;
				}
			}
			System.out.println(res);
		}
    }
}
