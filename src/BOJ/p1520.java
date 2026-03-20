package BOJ;
import java.io.*;
import java.util.*;

public class p1520 {
	static int n, m, cnt;
	static int[][] dp;
	static int[][] map;
	static int[] dr = {1, 0, -1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		dp = new int[n][m];
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			Arrays.fill(dp[i], -1);
			for(int j=0;j<m;j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		dfs(0, 0);
		System.out.println(dp[0][0]);
	}
	static int dfs(int r, int c) {
		if(r==n-1&&c==m-1) return 1;
		if(dp[r][c]!=-1) return dp[r][c];
		dp[r][c]=0;
		for(int i=0;i<4;i++) {
			int nr = r+dr[i];
			int nc = c+dc[i];
			if(nr<0||nc<0||nr>=n||nc>=m) continue;
			if(map[r][c]<=map[nr][nc]) continue;
			dp[r][c]+=dfs(nr, nc);
		}
		return dp[r][c];
	}
}