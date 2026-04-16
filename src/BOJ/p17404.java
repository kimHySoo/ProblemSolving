//이 문제는 시작점 기준으로 for문 돌려서 2차원 dp로 해결가능
package BOJ;
import java.io.*;
import java.util.*;

public class p17404 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] cost = new int[n][3];
		for(int i=0;i<n;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0;j<3;j++) cost[i][j]=Integer.parseInt(st.nextToken());
		}
		
		int[][][] dp = new int[n+1][3][3];
		
		for(int i=0;i<3;i++)for(int j=0;j<3;j++) {
			if(i==j) dp[0][i][j]=cost[0][i];
			else dp[0][i][j]=Integer.MAX_VALUE/2;
		}
		
		for(int i=1;i<n;i++) {
			for(int j=0;j<3;j++) {
				for(int k=0;k<3;k++) {
					dp[i][j][k]=Math.min(dp[i-1][(j+1)%3][k], dp[i-1][(j+2)%3][k])+cost[i][j];
				}
			}
		}
		
		int x = Integer.MAX_VALUE;
		for(int i=0;i<3;i++) for(int j=0;j<3;j++) if(i!=j) x=Math.min(x, dp[n-1][i][j]);
		
		System.out.println(x);
	}
}