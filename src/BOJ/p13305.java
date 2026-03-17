package BOJ;

import java.io.*;
import java.util.*;

public class p13305 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] dist = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<n-1;i++) dist[i]=Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int[] cost = new int[n];
		for(int i=0;i<n;i++) cost[i]=Integer.parseInt(st.nextToken());
		cost[n-1]=0;
		long sum = 0L;
		int y = cost[0];
		int d = dist[0];
		for(int i=1;i<n;i++) {
			int x = cost[i];
			if(x>=y) {
				d+=dist[i];
				continue;
			}
			sum+=(long) d*y;
			d=dist[i];
			y=x;
		}
		System.out.println(sum);
	}
}
