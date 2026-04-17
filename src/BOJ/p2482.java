package BOJ;
import java.io.*;
import java.util.*;

public class p2482 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());
        int[][] dp = new int[n+1][n+1];
        int mod = 1_000_000_003;
        for(int i=0;i<n+1;i++) {
        	dp[i][1]=i;
        	dp[i][0]=1;
        }
        for(int i=2;i<n+1;i++) {
        	for(int j=2;j<i/2+1;j++) dp[i][j]=(dp[i-1][j]+dp[i-2][j-1])%mod;
        }
        System.out.println(dp[n][k]);
    }

}