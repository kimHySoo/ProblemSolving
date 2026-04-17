package BOJ;
import java.io.*;
import java.util.*;

public class p1311 {
	static int n, inf;
	static int[][] work, dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());
        inf = Integer.MAX_VALUE;
        work = new int[n+1][n];
        dp = new int[n+1][1<<n];
        for(int i=0;i<n+1;i++) Arrays.fill(dp[i], -1);
        for(int i=1;i<n+1;i++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	for(int j=0;j<n;j++) work[i][j]=Integer.parseInt(st.nextToken());
        }
        dp[0][0]=0;
        System.out.println(dfs(1, 0));
        
    }
    static int dfs(int v, int mask) {
    	if(v>n) return 0;
    	if(dp[v][mask]!=-1) return dp[v][mask];
    	dp[v][mask]=inf;
    	for(int i=0;i<n;i++) {
    		int x = 1<<i;
    		if((x&mask)!=0) continue;
    		dp[v][mask] = Math.min(dp[v][mask], work[v][i]+dfs(v+1, mask|x));
    	}
    	return dp[v][mask];
    }
}