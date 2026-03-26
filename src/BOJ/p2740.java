package BOJ;
import java.util.*;
import java.io.*;
public class p2740 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[][] x = new int[n][m];
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<m;j++) x[i][j] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int[][] y = new int[a][b];
		for(int i=0;i<a;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<b;j++) y[i][j]=Integer.parseInt(st.nextToken());
		}
		int[][] ans = new int[n][b];
		for(int i=0;i<n;i++) {
			for(int j=0;j<b;j++) {
				for(int k=0;k<m;k++) ans[i][j]+=x[i][k]*y[k][j];
				sb.append(ans[i][j]).append(' ');
			}
		sb.append('\n');
		}
		System.out.println(sb.toString());
	}
}
