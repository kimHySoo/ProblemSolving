package BOJ;
import java.util.*;
import java.io.*;
public class p5904 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int len = 3;
		int k = 0;
		while(len<=n) {
			k++;
			len = len*2+k+3;
		}
		System.out.println(solve(n, len, k));
	}
	static char solve(int n, int len, int k) {
		int prev = (len-k-3)/2;
		if(n<=prev) return solve(n, prev, k-1);
		else if(prev+1==n) return 'm';
		else if(n<=len-prev) return 'o';
		else return solve(n-prev-k-3, prev, k-1);
	}
}
