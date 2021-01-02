package cd;

//Working program with FastReader 
import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer; 
	
public class CD {
	

	

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
  
        long nextLong() 
        { 
            return Long.parseLong(next()); 
        } 
  
        double nextDouble() 
        { 
            return Double.parseDouble(next()); 
        } 
  
        String nextLine() 
        { 
            String str = ""; 
            try
            { 
                str = br.readLine(); 
            } 
            catch (IOException e) 
            { 
                e.printStackTrace(); 
            } 
            return str; 
        } 
    } 
	
	public static void main(String[] args) {
		FastReader s=new FastReader(); 
        int n = s.nextInt();
        int m = s.nextInt();
        Set<Integer> numb = new HashSet<Integer>();
        while(n!=0 && m!=0){
        	int records = 0;
        	for (int i = 0; i < n; i++) {
				numb.add(s.nextInt());
				
			}
        	for (int i = 0; i < m; i++) {
				int n1 = s.nextInt();
				if(numb.contains(n1)){
					records++;
				}
			}
        	numb.clear();
        	System.out.println(records);
        	n = s.nextInt();
            m = s.nextInt();
        }
        

	}

}
