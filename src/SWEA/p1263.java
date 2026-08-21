import java.util.*;
import java.io.*;

public class p1263 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int test = Integer.parseInt(br.readLine());
		StringTokenizer st;
		int inf = 10000000;
		
		for(int t = 1;t<=test;t++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int[][] map = new int[n][n];
			for(int i=0;i<n;i++) for(int j=0;j<n;j++) {
				int x = Integer.parseInt(st.nextToken());
				if(i==j) continue;
				map[i][j] = x==1?1:inf;
			}
			
			for(int i=0;i<n;i++) for(int j=0;j<n;j++) for(int k=0;k<n;k++) {
				map[j][k] = Math.min(map[j][k], map[j][i]+map[i][k]);
			}
			int answer = Integer.MAX_VALUE;
			for(int i=0;i<n;i++) {
				int sum = 0;
				for(int j=0;j<n;j++) sum+=map[i][j];
				answer = Math.min(answer, sum);
			}
			sb.append("#"+t+" "+answer).append('\n');
		}
		System.out.println(sb.toString());
	}
}







