package BOJj
import java.util.*;
import java.io.*;

public class p2533 {
	static List<Integer>[] g;
	static int n;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(br.readLine());

		g = new ArrayList[n + 1];
		dp = new int[n + 1][2];

		for (int i = 1; i < n + 1; i++) {
			g[i] = new ArrayList<>();
		}

		for (int i = 0; i < n - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			g[x].add(y);
			g[y].add(x);
		}

		dfs(1, 0);

		System.out.println(Math.min(dp[1][0], dp[1][1]));
	}

	static void dfs(int v, int p) {
		dp[v][1] = 1;
		for (int x : g[v]) {
			if (x == p)
				continue;

			dfs(x, v);

			dp[v][0] += dp[x][1];
			dp[v][1] += Math.min(dp[x][0], dp[x][1]);

		}
	}
}