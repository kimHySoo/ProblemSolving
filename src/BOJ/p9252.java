package BOJ;
import java.io.*;
import java.util.*;

public class p9252 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String x = br.readLine();
		String y = br.readLine();
		int[][] dp = new int[x.length()+1][y.length()+1];
		int[] how = new int[x.length()+1];
		int last = -1;
		for(int i=1;i<1+x.length();i++) for(int j=1;j<1+y.length();j++) {
			dp[i][j]=Math.max(dp[i-1][j], dp[i][j-1]);
			if(x.charAt(i-1)==y.charAt(j-1)) {
				if(dp[i][j]<dp[i-1][j-1]+1) {
					dp[i][j]=dp[i-1][j-1]+1;
				}
			}
		}
		int n = x.length();
		int m = y.length();
		System.out.println(dp[n][m]);
		while(n>0&&m>0) {
			if(x.charAt(n-1)==y.charAt(m-1)) {
				sb.append(x.charAt(n-1));
				n--;
				m--;
			}
			else if(dp[n-1][m]>=dp[n][m-1]) n--;
			else m--;
		}
		System.out.print(sb.reverse().toString());
	}
}