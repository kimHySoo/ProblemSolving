package SWEA;
import java.util.*;
import java.io.*;

public class p1861 {
	static int[] dr = {1, 0, -1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int[][] map, dp;
	static int n, max, idx;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int test = Integer.parseInt(br.readLine());
		for(int t=1;t<test+1;t++) {
			n = Integer.parseInt(br.readLine());
			map = new int[n][n];
			dp = new int[n][n];
			max = 0;
			idx = Integer.MAX_VALUE;
			for(int i=0;i<n;i++) Arrays.fill(dp[i], -1);
			for(int i=0;i<n;i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0;j<n;j++) map[i][j]=Integer.parseInt(st.nextToken());
			}
			for(int i=0;i<n;i++) for(int j=0;j<n;j++) if(dp[i][j]==-1) dfs(i, j);
			sb.append('#').append(t).append(' ').append(idx).append(' ').append(max).append('\n');
		}
		System.out.print(sb.toString());
	}
	static int dfs(int r, int c) {
		if(dp[r][c]!=-1) return dp[r][c];
		dp[r][c]=1;
		for(int i=0;i<4;i++) {
			if(chk(r, c, i)) {
				int nr = r+dr[i];
				int nc = c+dc[i];
				dp[r][c]+=dfs(nr, nc);
			}
		}
		if(max<dp[r][c]) {
			max=dp[r][c];
			idx = map[r][c];
		}
		else if(max==dp[r][c]) idx = Math.min(idx, map[r][c]);
		return dp[r][c];
	}
	static boolean chk(int r, int c, int d) {
		int nr = r+dr[d];
		int nc = c+dc[d];
		if(nr<0||nc<0||nr>=n||nc>=n||map[nr][nc]!=map[r][c]+1) return false;
		return true;
	}
}
