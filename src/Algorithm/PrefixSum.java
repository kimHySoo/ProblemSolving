package Algorithm;

import java.io.*;
import java.util.*;
// https://www.acmicpc.net/problem/11660
public class PrefixSum {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[][] map = new int[n+1][n+1];
		for(int i=1;i<n+1;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<n+1;j++) {
				map[i][j]=map[i-1][j]+map[i][j-1]-map[i-1][j-1]+Integer.parseInt(st.nextToken());
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			sb.append(map[x2][y2]-map[x1-1][y2]-map[x2][y1-1]+map[x1-1][y1-1]).append('\n');
		}
		System.out.print(sb.toString());
	}
}
