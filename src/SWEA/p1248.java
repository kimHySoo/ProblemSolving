import java.util.*;
import java.io.*;

public class p1248 {
	static int[] p, left, right, dp;
	static int n;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int test = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		for(int t = 1;t<=test;t++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
		
			p = new int[n+1];
			left = new int[n+1];
			right = new int[n+1];
			dp = new int[n+1];
			Arrays.fill(dp, -1);
			dp[0]=0;
			st = new StringTokenizer(br.readLine());
			
			for(int i=0;i<e;i++) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
			
				p[b] = a;
				if(left[a]==0) left[a]=b;
				else right[a] = b;
			}
			
			boolean[] answer = new boolean[n+1];
			while(x!=0) {
				answer[x] = true;
				x=p[x];
			}
			while(!answer[y]) {
				y=p[y];
			}
			dfs(1);
			sb.append("#"+t+" "+y+" "+dp[y]+"\n");
		}
		System.out.println(sb.toString());
	}
	static int dfs(int v) {
		if(dp[v]!=-1) return dp[v];
		return dp[v]=dfs(left[v])+dfs(right[v])+1;
	}
}







