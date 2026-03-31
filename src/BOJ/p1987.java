package BOJ;
import java.util.*;
import java.io.*;
public class p1987 {
	static boolean[] visited = new boolean[26];
	static int[][] map;
	static int n, m, ans;
	static int[] dr = {1, 0, -1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		for(int i=0;i<n;i++) {
			String line = br.readLine();
			for(int j=0;j<m;j++) map[i][j]=line.charAt(j)-'A';
		}
		ans = 0;
		visited[map[0][0]]=true;
		dfs(0, 0, 1);
		System.out.println(ans);
	}
	static void dfs (int r, int c, int cnt) {
		for(int i=0;i<4;i++) {
			int nr = r+dr[i];
			int nc = c+dc[i];
			if(nr<0||nc<0||nr>=n||nc>=m||visited[map[nr][nc]]) {
				ans = Math.max(cnt, ans);
				continue;
			}
			visited[map[nr][nc]]=true;
			dfs(nr, nc, cnt+1);
			visited[map[nr][nc]]=false;
		}
	}
}

