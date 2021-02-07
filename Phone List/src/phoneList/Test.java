package phoneList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import phoneList.PhoneList.FastReader;

public class Test {
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
    public static class CustomPair {
        private Integer key;
        private Long value;
        
        public CustomPair(Integer key, Long value) {
        	this.key = key;
        	this.value = value;
        }

        public Integer getKey() {
        	return key;
        }
        
        public Long getValue() {
        	return value;
        } 
    }

	public static void main(String[] args) {
		String s = "0123456";
		long a = Long.parseLong(s, 10);
		System.out.println(a);
		for (int k = 2; k < 2; k++) {
			System.out.println(k);
		}
		double t = Math.log10(0)+1;
		System.out.println(t);
		System.out.println((int)t);
		
		if((int)Math.log10(0)==Double.NEGATIVE_INFINITY){
			System.out.println("hej");
		}

		

		System.out.println(a);
		System.out.println(s.length());
		FastReader sr = new FastReader();
		String s1 = sr.next();
		long a1 = Long.parseLong(s1, 10);
		System.out.println(a1);
		System.out.println(s1.length());
		

	}

}
