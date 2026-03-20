package Algorithm;

public class DPTopDown.java{
    //1520
    //https://www.acmicpc.net/problem/1520
    static int dfs(int r, int c) {
        if (r == n-1 && c == m-1) return 1;
        if (memo[r][c] != -1) return memo[r][c];  // 캐시 히트
        memo[r][c] = 0;
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (nr < 0 || nc < 0 || nr >= n || nc >= m) continue;
            if (map[r][c] <= map[nr][nc]) continue;
            memo[r][c] += dfs(nr, nc);
        }
        return memo[r][c];
    }
    //32175
    //https://www.acmicpc.net/problem/32175
    static long dfs(int v) {
        if (v < 0) return 0;
        if (dp[v] != -1) return dp[v]; // 메모이제이션

        dp[v] = 0;
        for (int i = 0; i < n; i++) {
            dp[v] = (dp[v] + dfs(v - cup[i])) % MOD;
        }
        return dp[v];
    }
    //2629
    //https://www.acmicpc.net/problem/2629
    static void dfs(int v, int sum) {
		if(v==n) {
			dp[sum]=true;
			return;
		}
		if(visited[v][sum]) return;
		visited[v][sum]=true;
		dfs(v+1, sum);
		dfs(v+1, sum+weight[v]);
		dfs(v+1, Math.abs(sum-weight[v]));
	}
}
