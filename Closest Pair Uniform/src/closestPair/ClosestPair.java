package closestPair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;



public class ClosestPair {
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
	public static class Point {
		private double x;
		private double y;
		public Point(double x, double y) {
			this.x=x;
			this.y=y;
		}
		public double getX(){
			return x;
		}
		public double getY(){
			return y;
		}
		public double getDistance(Point p){
			return Math.hypot(p.x-x , p.y-y);
		}
		public String toString() {
		    return String.format("%.5f", x) + " " + String.format("%.5f", y);
		}
		
	}

	public static class YCompare implements Comparator<Point> {
		@Override
		public int compare(Point p1, Point p2) {
			return Double.compare(p1.getY(), p2.getY());
		}
		
	}
	public static class XCompare implements Comparator<Point> {
		@Override
		public int compare(Point p1, Point p2) {
			return Double.compare(p1.getX(), p2.getX());
		}
		
	}
	public static class Pair {
		private Point p1;
		private Point p2;
		private double dist;
		public Pair(){
		}
		public void setPoint1(Point p){
			p1=p;
		}
		public void setPoint2(Point p){
			p2=p;
		}
		public void setDist(double d){
			dist=d;
		}
		public double getDist(){
			return dist;
		}
		public String toString() {
		    return p2 + " " + p1;
		}
	}

	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}

	public static Pair closest(List<Point> px, List<Point> py, int n, double[] interval){
		if (n<=40){
			Pair closestPair = new Pair();
			double delta = Double.MAX_VALUE;
			for (int i = 0; i < px.size()-1; i++) {
				for (int j = i+1; j < px.size(); j++) {
					double dist = px.get(i).getDistance(px.get(j));
					if(delta>dist){
						delta=dist;
						closestPair.setDist(dist);
						closestPair.setPoint1(px.get(i));
						closestPair.setPoint2(px.get(j));
						
						
					}
				}
			}
			return closestPair;
			
		}else{
			Pair closestPair = new Pair();
			List<Point> lx = new ArrayList<Point>();
			List<Point> rx = new ArrayList<Point>();
			
			double div=(interval[0]+interval[1])/2;
			for (int i = 0; i < px.size(); i++) {
				if(px.get(i).getX()>div){
					rx.add(px.get(i));
				}else{
					lx.add(px.get(i));
				}
			}	
			List<Point> ly = new ArrayList<Point>();
			List<Point> ry = new ArrayList<Point>();
			for (int i = 0; i < py.size(); i++) {
				if(py.get(i).getX()>div){
					ry.add(py.get(i));
				}else{
					ly.add(py.get(i));
				}
			}

			double[] leftInterval = {interval[0],div};
			double[] rightInterval = {div, interval[1]};
			Pair pair1 = closest(lx, ly, lx.size(), leftInterval);
			Pair pair2 = closest(rx, ry, rx.size(), rightInterval);
			double delta;
			if(pair1.getDist() > pair2.getDist()){
				closestPair=pair2;
				delta = pair2.getDist();
			}else{
				closestPair=pair1;
				delta = pair1.getDist();
			}
			List<Point> sy = new ArrayList<Point>();
			for (Point point : py) {
				if(Math.abs(point.getX()-div) < delta){
					sy.add(point);
				}
			}
			for (int i = 0; i < sy.size()-1; i++) {
				int C = Math.min(sy.size()-i,16);
				for (int j = i+1; j < i+C && sy.get(j).getY()-sy.get(i).getY() < delta; j++) {
					double dist = sy.get(i).getDistance(sy.get(j));
					if(delta>dist){
						delta=dist;
						closestPair.setDist(dist);
						closestPair.setPoint1(sy.get(i));
						closestPair.setPoint2(sy.get(j));
					}

				}
			}
			return closestPair;
			
		}

	}
	public static Pair slowSolution(List<Point> px){
		Pair closestPair = new Pair();
		double delta = Double.MAX_VALUE;
		for (int i = 0; i < px.size()-1; i++) {
			for (int j = i+1; j < px.size(); j++) {
				double dist = px.get(i).getDistance(px.get(j));
				if(delta>dist){
					delta=dist;
					closestPair.setDist(dist);
					closestPair.setPoint1(px.get(i));
					closestPair.setPoint2(px.get(j));
					
					
				}
			}
		}
		return closestPair;
	}
	
	public static void main(String[] args) {
		//MakeTestCases.main(args);
		
		FastReader scan=new FastReader(); 
		int N = scan.nextInt();
		while(N!=0){
			
			List<Point> xPoints = new ArrayList<Point>(N);
			List<Point> yPoints = new ArrayList<Point>(N);
			for (int i = 0; i < N; i++) {
				double x = scan.nextDouble();
				double y = scan.nextDouble();
				Point point = new Point(x,y);
				xPoints.add(point);
				yPoints.add(point);
			}
			if (N>40){
				Collections.sort(yPoints, new YCompare());
			}

			double[] interval={-100000,100000};
			Pair closestPairFast = closest(xPoints, yPoints, N, interval);
			System.out.println(closestPairFast);
			
			Pair closestPairFastSlow = slowSolution(xPoints);
			if(closestPairFastSlow.getDist()!=closestPairFast.getDist()){
				System.out.println(N);
			}
			N = scan.nextInt();
		}

	}

}

