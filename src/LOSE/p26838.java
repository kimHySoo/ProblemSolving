import java.util.*;
import java.io.*;

public class p26838 {
	static long[][] dp;
	static int[] col;
	static ArrayList<Integer>[] g;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int test = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		for(int t = 1;t<=test;t++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			col = new int[n+1];
			g = new ArrayList[n+1];
			dp = new long[n+1][1<<k];
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<n;i++) {
				col[i+1] = Integer.parseInt(st.nextToken());
				g[i+1] = new ArrayList<>();
			}
			
			for(int i=0;i<m;i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				g[x].add(y);
				g[y].add(x);
			}
			long cnt = 0;
			for(int i=1;i<=n;i++) Arrays.fill(dp[i], -1);
			for(int i=1;i<n+1;i++) {
				cnt+=dfs(i, 1<<col[i]-1);
			}
			sb.append(cnt).append('\n');
		}
		System.out.println(sb.toString());
	}
	static long dfs(int v, int s) {
		if(dp[v][s]!=-1) return dp[v][s];
		long cnt = 0;
		for(int next:g[v]) {
			int bit = 1<<(col[next]-1);
			if((bit&s)!=0) continue;
			else cnt+=dfs(next, bit|s)+1;
		}
		
		return dp[v][s] = cnt;
	}
}
