import java.util.*;
import java.io.*;

public class p1247 {
	static int[][] map;
	static boolean[] visited;
	static int n, answer;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int test = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		for(int t = 1;t<=test;t++) {
			n = Integer.parseInt(br.readLine());
			map = new int[n+2][2];
			visited = new boolean[n];
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<n+2;i++) {
				map[i][0] = Integer.parseInt(st.nextToken());
				map[i][1] = Integer.parseInt(st.nextToken());
			}
			
			answer = Integer.MAX_VALUE;
			dfs(0, -1, 0, 0);
			sb.append("#"+t+" "+answer).append('\n');
		}
		System.out.println(sb.toString());
	}
	static void dfs(int v, int prev, int ans, int cnt) {
		if(cnt==n) {
			answer = Math.min(ans+cal(1, v), answer);
			return;
		}
		for(int i=2;i<n+2;i++) {
			if(visited[i-2]) continue;
			visited[i-2] = true;
			dfs(i, v, ans+cal(v, i), cnt+1);
			visited[i-2] = false;
		}
	}
	static int cal(int prev, int after) {
		return Math.abs(map[prev][1] - map[after][1])+Math.abs(map[prev][0] - map[after][0]);
	}
}







