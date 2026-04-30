import java.io.*;
import java.util.*;

public class 유사칸토어비트열 {
	static int v, sum;
	static long s, e;
	static int[] full;
	static long[] five;
	public static void main(String[] args) {
		v = 2;
		s = 4;
		e = 17; 
		full = new int[v+1];
		five = new long[v+1];
		full[1]=4;
		five[0]=1;
		sum = 0;
		for(int i=1;i<v+1;i++) five[i]=five[i-1]*5;
		divide(s, e, v);
		System.out.println(sum);
	}
	static void divide(long l, long r, int n) {
	    if(n == 1) {
	        sum += (r - l + 1) - (l <= 3 && r >= 3 ? 1 : 0);
	        return;
	    }
	    for(int i = 1; i < 6; i++) {
	        if(i == 3) continue;
	        long lo = five[n-1] * (i-1) + 1;
	        long hi = five[n-1] * i;        
	        if(r < lo || l > hi) continue;  
	        long nl = Math.max(l, lo) - five[n-1]*(i-1); 
	        long nr = Math.min(r, hi) - five[n-1]*(i-1);
	        if(nl == 1 && nr == five[n-1]) sum += all(n-1);
	        else divide(nl, nr, n-1);
	    }
	}
	static int all(int n) {
		System.out.println("all");
		if(full[n]!=0||n==0) return full[n];
		full[n] = all(n-1)*4;
		return full[n];
	}
}
