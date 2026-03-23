//우선순위 큐를 언제 사용해야할지 명확히 판단할것
package BOJ;

import java.io.*;
import java.util.*;

public class p1202 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[][] jewel = new int[n][2];
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			jewel[i][0]=Integer.parseInt(st.nextToken());
			jewel[i][1]=Integer.parseInt(st.nextToken());
		}
		Arrays.sort(jewel, (a,b)->{
			if(a[0]!=b[0]) return Integer.compare(a[0], b[0]);
			else return Integer.compare(a[1], b[1]);
		});
		int[] bag = new int[k];
		for(int i=0;i<k;i++) bag[i]=Integer.parseInt(br.readLine());
		Arrays.sort(bag);
		Queue<Integer> pq = new PriorityQueue<>((a,b)->Integer.compare(b, a));
		long sum = 0;
		int i1 = 0;
		int i2 = 0;
		while(i2<k) {
			while(i1<n&&jewel[i1][0]<=bag[i2]) {
				pq.add(jewel[i1++][1]);
			}
			if(!pq.isEmpty()) sum+=pq.poll();
			i2++;
		}
		System.out.println(sum);
	}
}
