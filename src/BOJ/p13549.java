package BOJ;

import java.io.*;
import java.util.*;

public class p13549 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int max = 100001;
		int[] visited = new int[max];
		Arrays.fill(visited, Integer.MAX_VALUE);
		Queue<int[]> pq = new PriorityQueue<>((a,b)->Integer.compare(a[1], b[1]));
		pq.add(new int[] {n, 0});
		while(!pq.isEmpty()) {
			int[] x = pq.poll();
			int cur = x[0];
			int time = x[1];
			if(cur==k) {
				sb.append(time);
				break;
			}
			else if(cur==0) {
				pq.add(new int[] {1, 1});
			}
			else if(cur>k) {
				pq.add(new int[] {k, time+cur-k});
			}
			else if(visited[cur]<time) continue;
			else {
				if(cur>0&&visited[cur-1]>time+1) {
					visited[cur-1]=time+1;
					pq.add(new int[] {cur-1, time+1});
				}
				if(cur+1<max&&visited[cur+1]>time+1) {
					visited[cur+1]=time+1;
					pq.add(new int[] {cur+1, time+1});
				}
				if(cur*2<max&&visited[cur*2]>time) {
					visited[cur*2]=time;
					pq.add(new int[] {cur*2, time});
				}
				else if(cur*2>max) {
					pq.add(new int[] {k, time+cur*2-k});
				}
			}
		}
		System.out.print(sb.toString());
	}
}