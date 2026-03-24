//gpt평: “다익스트라 구현은 잘했지만, 이 문제는 가중치가 모두 1이라 BFS로 푸는 게 더 좋은 문제다.”

package BOJ;
import java.io.*;
import java.util.*;

public class p13913 {
	static class Edge implements Comparable<Edge>{
		int to, w;
		Edge(int to, int w){
			this.to = to;
			this.w = w;
		}
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.w, o.w);
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int max = 200001;
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[] time = new int[max];
		int[] prev = new int[max];
		Arrays.fill(time, max);
		Queue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(n, 0));
		time[n]=0;
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			int v = cur.to;
			int t = cur.w;
			if(v==m) break;
			if(time[v]<t) continue;
			t++;
			if(v*2<max&&time[2*v]>t) {
				prev[2*v]=v;
				time[2*v]=t;
				pq.add(new Edge(2*v, t));
			}
			if(v-1>=0&&time[v-1]>t) {
				prev[v-1]=v;
				time[v-1]=t;
				pq.add(new Edge(v-1, t));
			}
			if(v+1<max&&time[1+v]>t) {
				prev[v+1]=v;
				time[v+1]=t;
				pq.add(new Edge(1+v, t));
			}

		}
		sb.append(time[m]).append('\n');
		List<Integer> num = new ArrayList<>();
		while(m!=n) {
			num.add(0, m);
			m=prev[m];
		}
		num.add(0, m);
		for(int x:num) sb.append(x).append(' ');
		System.out.print(sb.toString());
	}
}