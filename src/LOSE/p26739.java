import java.util.*;
import java.io.*;

public class p26739 {
	static long[][] dp;
	static int[] col;
	static ArrayList<Integer>[] g;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int test = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		for(int t = 1;t<=test;t++) {
			int n = Integer.parseInt(br.readLine());
			
			PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->{
				if(b[1]!=a[1]) return Integer.compare(b[1], a[1]);
				else return Integer.compare(a[0], b[0]);
			});
			
			for(int i=0;i<n;i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				pq.add(new int[] {x, y});
			}
			
			int start = Integer.MAX_VALUE/2;
			
			while(!pq.isEmpty()) {
				int[] x = pq.poll();
				start = Math.min(x[1], start) - x[0];
			}
			sb.append(start).append('\n');
		}
		System.out.println(sb.toString());
	}
}






