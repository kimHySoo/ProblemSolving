package BOJ;
import java.util.*;
import java.io.*;

public class p1949 {
	static List<Integer>[] g;
	static int n;
	static int[][] dp;
	static int[] num;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(br.readLine());

		g = new ArrayList[n + 1];
		num = new int[n + 1];
		dp = new int[n + 1][2];

		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 1; i < n + 1; i++) {
			g[i] = new ArrayList<>();
			num[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < n - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			g[x].add(y);
			g[y].add(x);
		}

		dfs(1, 0);

		System.out.println(Math.max(dp[1][0], dp[1][1]));
	}

	static void dfs(int v, int p) {
		dp[v][1] = num[v];
		dp[v][0] = 0;
		for (int x : g[v]) {
			if (x == p)
				continue;
			dfs(x, v);

			dp[v][1] += dp[x][0];
			dp[v][0] += Math.max(dp[x][0], dp[x][1]);
		}
	}
}