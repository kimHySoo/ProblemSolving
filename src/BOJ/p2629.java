//gpt평: bottom up 추천
package BOJ;
import java.io.*;
import java.util.*;

public class p2629 {
	static int[] weight;
	static boolean[][] visited;
	static boolean[] dp;
	static int n;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		weight = new int[n];
		int sum = 0;
		for(int i=0;i<n;i++) {
			weight[i]=Integer.parseInt(st.nextToken());
			sum+=weight[i];
		}
		dp = new boolean[sum+1];
		visited = new boolean[n][sum+1];
		dfs(0, 0);
		int m = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<m;i++) {
			int x = Integer.parseInt(st.nextToken());
			if(x>sum) sb.append("N ");
			else sb.append(dp[x]?"Y ":"N ");
		}
		System.out.print(sb.toString());
	}
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

/*
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] weight = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());

        int sum = 0;
        for (int i = 0; i < n; i++) {
            weight[i] = Integer.parseInt(st.nextToken());
            sum += weight[i];
        }

        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;

        for (int w : weight) {
            boolean[] next = new boolean[sum + 1];

            for (int d = 0; d <= sum; d++) {
                if (!dp[d]) continue;

                next[d] = true;
                if (d + w <= sum) next[d + w] = true;
                next[Math.abs(d - w)] = true;
            }

            dp = next;
        }

        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < m; i++) {
            int bead = Integer.parseInt(st.nextToken());

            if (bead > sum) sb.append("N ");
            else sb.append(dp[bead] ? "Y " : "N ");
        }

        System.out.println(sb);
    }
}
*/