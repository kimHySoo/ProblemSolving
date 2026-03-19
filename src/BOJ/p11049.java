//2차원 dp에서는 중간값 넣는거 기억하기
package BOJ;
import java.io.*;
import java.util.*;

public class p11049 {
	static int[][][] dim;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] dp = new int[n][n];
		dim = new int[n][n][2];
		for(int i=0;i<n;i++) {
			Arrays.fill(dp[i], Integer.MAX_VALUE);
			dp[i][i]=0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			dim[i][i][0] = Integer.parseInt(st.nextToken());
			dim[i][i][1] = Integer.parseInt(st.nextToken());
		}
		for(int len=1;len<n;len++) {
			for(int i=0; i<n-len;i++) {
				for(int j=0;j<len;j++) {
					dp[i][i+len]=Math.min(dp[i][i+len], dp[i][i+j]+dp[i+j+1][i+len]+cal(i, i+j, dim[i+len][i+len][1]));
				}
			}
		}
		System.out.println(dp[0][n-1]);
	}
	static int cal(int i, int j, int x) {
		return dim[i][i][0]*dim[j][j][1]*x;
	}
}