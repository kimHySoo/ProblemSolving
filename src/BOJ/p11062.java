package BOJ;
import java.util.*;
import java.io.*;

public class p11062 {
	static int n;
	static int[] arr;
	static int[][] dp;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int test = Integer.parseInt(br.readLine());
		while(test-->0) {
			n = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr = new int[n];
			dp = new int[n][n];
			for(int i=0;i<n;i++) for(int j=0;j<n;j++) dp[i][j]=-1;
			for(int i=0;i<n;i++) arr[i]=Integer.parseInt(st.nextToken());
			sb.append(dfs(0, 0, n-1)).append('\n');
		}
		System.out.print(sb.toString());
	}
	static int dfs(int turn, int i, int j) {
		if(turn==n||i>j) return 0;
		if(dp[i][j]!=-1) return dp[i][j];
		if(((turn&1)==0)) return dp[i][j]=Math.max(dfs(turn+1, i+1, j)+arr[i], dfs(turn+1, i, j-1)+arr[j]);
		else return dp[i][j]=Math.min(dfs(turn+1, i+1, j), dfs(turn+1, i, j-1));
	}
}
