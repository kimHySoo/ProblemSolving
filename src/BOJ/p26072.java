package BOJ;
import java.io.*;
import java.util.*;
public class p26072 {
	static int n;
	static int[] loc, w;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());
        loc = new int[n];
        w = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++) loc[i]=Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++) w[i]=Integer.parseInt(st.nextToken());
        double left = 0;
        double right = l;
        for(int i=0;i<200;i++) {
        	double mid = (left+right)/2;
        	double[] d = dist(mid);
        	if(d[0]>d[1]) right = mid;
        	else left = mid;
        }
        System.out.println(left);
    }
    static double[] dist(double x) {
    	double[] d = new double[2];
    	for(int i=0;i<n;i++) {
    		if(loc[i]>x) d[1]+=(loc[i]-x)*w[i];
    		else d[0]+=(x-loc[i])*w[i];
    	}
    	return d;
    }
}