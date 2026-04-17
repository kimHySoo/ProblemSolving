package BOJ;
import java.io.*;
import java.util.*;

public class p2098{
	static int n, inf;
	static int[][] dp, cost;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		inf = Integer.MAX_VALUE/2;
		dp = new int[n][1<<n];
		cost = new int[n][n];
		for(int i=0;i<n;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				cost[i][j] = Integer.parseInt(st.nextToken());
			}
			Arrays.fill(dp[i], -1);
		}
		System.out.println(dfs(0, 1, 0));
	}
	static int dfs(int v, int mask, int cur) {
		if(v==n-1) return cost[cur][0]==0?inf/2:cost[cur][0];
		if(dp[cur][mask]!=-1) return dp[cur][mask];
		dp[cur][mask] = inf;
		for(int i=1;i<n;i++) {
			int x = 1<<i;
			if((x&mask)!=0||cost[cur][i]==0) continue;
			dp[cur][mask] = Math.min(dp[cur][mask], cost[cur][i]+dfs(v+1, mask|x, i));
		}
		return dp[cur][mask];
	}
}