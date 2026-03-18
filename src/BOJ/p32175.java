import java.io.*;
import java.util.*;

public class p32175 {
	static int[] cup, dp;
	static int n, h, cnt;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		cup = new int[n];
		dp = new int[h+1];
		Arrays.fill(dp, -1);
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) cup[i]=Integer.parseInt(st.nextToken());
		dp[0]=1;
		dp[h]=dfs(h);
		System.out.println(dp[h]);
	}
	static int dfs(int v) {
		if(v<0) return 0;
		if(dp[v]!=-1) return dp[v];
		dp[v]=0;
		for(int i=0;i<n;i++) {
			dp[v]+=dfs(v-cup[i]);
			dp[v]%=1000000007;
		}
		return dp[v];
	}
}