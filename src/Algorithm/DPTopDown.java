package Algorithm;

public class DPTopDown {
    // 1520
    // https://www.acmicpc.net/problem/1520
    static int[][] memo;
    static int[][] map;
    static int n, m, MOD;
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    static int dfs2(int r, int c) {
        if (r == n - 1 && c == m - 1) return 1;
        if (memo[r][c] != -1) return memo[r][c]; // 캐시 히트

        memo[r][c] = 0;
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (nr < 0 || nc < 0 || nr >= n || nc >= m) continue;
            if (map[r][c] <= map[nr][nc]) continue;
            memo[r][c] += dfs2(nr, nc);
        }
        return memo[r][c];
    }

    // 32175
    // https://www.acmicpc.net/problem/32175
    static long[] dp;
    static int[] cup;

    static long dfs1(int v) {
        if (v < 0) return 0;
        if (v == 0) return 1;
        if (dp[v] != -1) return dp[v]; // 메모이제이션

        dp[v] = 0;
        for (int i = 0; i < n; i++) {
            dp[v] = (dp[v] + dfs1(v - cup[i])) % MOD;
        }
        return dp[v];
    }

    // 2629
    // https://www.acmicpc.net/problem/2629
    static int[] weight;
    static boolean[] possible;
    static boolean[][] visited;

    static void dfs(int v, int sum) {
        if (visited[v][sum]) return;
        visited[v][sum] = true;

        if (v == n) {
            possible[sum] = true;
            return;
        }

        dfs(v + 1, sum);
        dfs(v + 1, sum + weight[v]);
        dfs(v + 1, Math.abs(sum - weight[v]));
    }
}