//다차원 다익스트라
package BOJ;
import java.io.*;
import java.util.*;

public class p25953 {
	static class Edge implements Comparable<Edge>{
		int to, time, w;
		Edge(int to, int time, int w){
			this.to = to;
			this.time = time;
			this.w = w;
		}
		public int compareTo(Edge o) {
			return Integer.compare(this.w, o.w);
		}
	}
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        
        int inf = Integer.MAX_VALUE;
        List<Edge>[] edge = new ArrayList[n];
        for(int i=0;i<n;i++) edge[i] = new ArrayList<>();
        for(int time = 1;time<t+1;time++) {
            for(int i=0;i<m;i++) {
            	st = new StringTokenizer(br.readLine());
            	int x = Integer.parseInt(st.nextToken());
            	int y = Integer.parseInt(st.nextToken());
            	int z = Integer.parseInt(st.nextToken());
            	edge[x].add(new Edge(y, time, z));
            	edge[y].add(new Edge(x, time, z));
            }
        }

        
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(s, 0, 0));
        
        int[][] dist = new int[t+1][n];
        for(int i=1;i<t+1;i++) {
        	Arrays.fill(dist[i], inf);
        	dist[i][s]=0;
        }
        
        while(!pq.isEmpty()) {
        	Edge cur = pq.poll();
        	if(cur.to==e) break;
        	if(dist[cur.time][cur.to]<cur.w) continue;
        	for(Edge next : edge[cur.to]) {
        		int nw = cur.w + next.w;
        		if(cur.time>=next.time||nw>=dist[next.time][next.to]) continue;
        		dist[next.time][next.to] = nw;
        		pq.add(new Edge(next.to, next.time, nw));
        	}
        }
        int ans = inf;
        for(int i=1;i<t+1;i++) {
        	ans = Math.min(ans, dist[i][e]);
        }
        System.out.println(ans==inf?-1:ans);
        
    }
}