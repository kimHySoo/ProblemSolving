package Algorithm;
import java.util.*;
import java.io.*;
public class FiboMatrix {
	static int mod = 1000000007;
	static long[][] base = {{1, 1}, {1, 0}};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long n = Long.parseLong(br.readLine());
		long[][] ans = solve(n);
		long[][] fibo = {{1}, {0}};
		fibo = cal(ans, fibo);
		System.out.println(fibo[1][0]);
	}
	static long[][] solve(long n){
        if(n==0) return new long[][]{{1,0},{0,1}}; 이거 추가할것
		if(n==1) return base;
		long[][] half = solve(n/2);
		long[][] result = cal(half, half);
		if((n&1)==1) return cal(result, base);
		else return result;
	}
	static long[][] cal(long[][] x, long[][] y){
		int n = y[0].length;
		long[][] z = new long[2][n];
		for(int i=0;i<2;i++) for(int j=0;j<n;j++) {
			for(int k=0;k<2;k++) {
				z[i][j]=(z[i][j]+x[i][k]*y[k][j])%mod;
			}
		}
		return z;
	}
}
