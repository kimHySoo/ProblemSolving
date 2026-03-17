//https://www.acmicpc.net/problem/4386
package Algorithm;

import java.util.*;
import java.io.*;
public class Prim {
	static class Edge implements Comparable<Edge>{
		int v;
		double w;
		Edge(int v, double w) {
			this.v = v;
			this.w = w;
		}
		//거리의 최소를 우선순위큐에서 먼저 뽑기위해 오버라이드
		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.w, o.w);
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		Queue<Edge> pq = new PriorityQueue<>();
		double[][] star = new double[n+1][2];
		for(int i=1;i<n+1;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			star[i][0] = Double.parseDouble(st.nextToken());
			star[i][1] = Double.parseDouble(st.nextToken());
		}
		double sum = 0;
		int cnt = 0;
		boolean[] visited = new boolean[n+1];
		//아무 지점에서 시작
		pq.add(new Edge(1, 0));
		while(!pq.isEmpty()) {
			Edge e = pq.poll();
			int cur = e.v;
			double cost = e.w;
			if(visited[cur]) continue;
			visited[cur]=true;
			sum+=cost;
			if(++cnt==n) break;
			for(int i=1;i<n+1;i++) {
				if(i==cur||visited[i]) continue;
				pq.add(new Edge(i, dist(star[i][0], star[i][1], star[cur][0], star[cur][1])));
			}
		}
		System.out.printf("%.2f", sum);
	}
	static double dist(double x1, double y1, double x2, double y2) {
		return Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
	}
}