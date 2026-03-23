package BOJ;

import java.io.*;
import java.util.*;

public class p1956 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		int[][] map = new int[n+1][n+1];
		int max = 4000001;
		for(int i=1;i<n+1;i++) {
			Arrays.fill(map[i], max);
		}
		for(int i=0;i<e;i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			map[x][y]=z;
		}
		for(int k=1;k<n+1;k++) {
			for(int i=1;i<n+1;i++) {
				for(int j=1;j<n+1;j++) {
					if(i==j) continue;
					map[i][j]=Math.min(map[i][j], map[i][k]+map[k][j]);
				}
			}
		}
		int ans = max;
		for(int i=1;i<n+1;i++) {
			for(int j=1;j<n+1;j++) ans=Math.min(ans, map[i][j]+map[j][i]);
		}
		System.out.println(ans>=max?-1:ans);
	}
}