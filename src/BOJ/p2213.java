package BOJ;
import java.io.*;
import java.util.*;

public class p2213 {
	static List<Integer>[] g;
	static List<Integer> how;
	static int[] num;
	static int n;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(br.readLine());
		num = new int[n + 1];
		g = new ArrayList[n + 1];
		how = new ArrayList<>();
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
		find(1, 0, true);

		sb.append(Math.max(dp[1][0], dp[1][1])).append('\n');

		Collections.sort(how);
		for (int i : how)
			sb.append(i).append(' ');

		System.out.println(sb.toString());
	}

	static void dfs(int v, int p) {
		dp[v][0] = 0;
		dp[v][1] = num[v];
		for (int x : g[v]) {
			if (x == p)
				continue;

			dfs(x, v);

			dp[v][0] += Math.max(dp[x][0], dp[x][1]);
			dp[v][1] += dp[x][0];

		}
	}

	static void find(int v, int p, boolean bf) {
		if (!bf) {
			for (int x : g[v]) {
				if (x == p)
					continue;
				find(x, v, true);
			}
		} else if (dp[v][0] <= dp[v][1]) {
			how.add(v);
			for (int x : g[v]) {
				if (x == p)
					continue;
				find(x, v, false);
			}
		} else {
			for (int x : g[v]) {
				if (x == p)
					continue;
				find(x, v, true);
			}
		}
	}
}