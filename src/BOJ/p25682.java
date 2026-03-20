//3일전에 오답노트한 문제
package BOJ;

import java.io.*;
import java.util.*;

public class p25682 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[][] prefix = new int[n+1][m+1];
		for(int i=1;i<n+1;i++) {
			String line = br.readLine();
			for(int j=1;j<m+1;j++) {
				prefix[i][j] = prefix[i-1][j]+prefix[i][j-1]-prefix[i-1][j-1];
				if(((i+j)&1)==1&&line.charAt(j-1)=='B') prefix[i][j]++;
				else if(((i+j)&1)==0&&line.charAt(j-1)=='W') prefix[i][j]++;
			}
		}
		int min = Integer.MAX_VALUE;
		for(int i=k;i<n+1;i++) for(int j=k;j<m+1;j++) {
			int x=  prefix[i][j]-prefix[i-k][j]-prefix[i][j-k]+prefix[i-k][j-k];
			x = Math.min(x, k*k-x);
			min = Math.min(min, x);
		}
		System.out.println(min);
	}
}