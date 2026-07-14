import java.util.*;
class 매출하락최소화 {
    public int solution(int[] sales, int[][] links) {
        int answer = 0;
		int n = sales.length;
		int m = links.length;
		
		List<Integer>[] g = new ArrayList[n+1];
		for(int i=1;i<n+1;i++) g[i] = new ArrayList<>();
		
		for(int i=0;i<m;i++) {
			int p = links[i][0];
			int c = links[i][1];
			g[p].add(c);
		}
		
		Deque<Integer> q = new ArrayDeque<>();
		boolean[] visited = new boolean[n+1];
		int[] order = new int[n+1];
		q.add(1);
		visited[1] = true;
		int idx = 0;
		
		while(!q.isEmpty()) {
			int x = q.poll();
			order[idx++] = x;
			for(int y:g[x]) {
				if(visited[y]) continue;
				visited[y] = true;
				q.add(y);
			}
		}
		
		long[][] dp = new long[n+1][2];
		
		for(int i=n-1;i>=0;i--) {
			
			long sum = 0;
			long x = Integer.MAX_VALUE;
			boolean selected = false;
			
			for(int cur:g[order[i]]) {
				long y = Math.min(dp[cur][0], dp[cur][1]);
				sum+=y;
				if(dp[cur][1]<=dp[cur][0]) {
					selected = true;
				} else {
					x = Math.min(x, dp[cur][1]-dp[cur][0]);
				}
			}
			dp[order[i]][1] = sales[order[i]-1]+sum;
			
			if(g[order[i]].isEmpty()) {
				dp[order[i]][0]=0;
			} else if(selected) {
				dp[order[i]][0] = sum;
			} else {
				dp[order[i]][0] = sum+x;
			}
		}
		
		answer = (int)Math.min(dp[1][0], dp[1][1]);
		
        return answer;
    }
}
