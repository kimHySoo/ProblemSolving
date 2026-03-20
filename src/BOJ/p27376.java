//long연산+계산식 실수+시뮬레이션 방식 주의
//한번더 풀어볼만한 문제
package BOJ;

import java.io.*;
import java.util.*;

public class p27376 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		Queue<int[]> pq = new PriorityQueue<>((a,b)->Integer.compare(a[0], b[0]));
		for(int i=0;i<k;i++) {
			st = new StringTokenizer(br.readLine());
			int x= Integer.parseInt(st.nextToken());
			int y= Integer.parseInt(st.nextToken());
			int z= Integer.parseInt(st.nextToken());
			pq.add(new int[] {x, y, z});
		}
		long time = 0;
		int dist = 0;
		while(!pq.isEmpty()) {
			int[] p = pq.poll();
			int x = p[0];
			int t = p[1];
			int s = p[2];
			time += x-dist; 
			dist = x;
			if((time<s)) time = s;
			else if((((time-s)/t)&1)==1){
				long y = (time-s)/t;
				time = (y+1)*t+s;
			}
		}
		time += n-dist;
		System.out.println(time);
	}
}
