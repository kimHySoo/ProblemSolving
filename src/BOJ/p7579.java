package BOJ;

import java.util.*;
import java.io.*;
public class p7579 {
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	int n = Integer.parseInt(st.nextToken());
    	int m = Integer.parseInt(st.nextToken());
    	int[] app = new int[n];
    	int[] value = new int[n];
    	st = new StringTokenizer(br.readLine());
    	for(int i=0;i<n;i++) app[i]=Integer.parseInt(st.nextToken());
    	st = new StringTokenizer(br.readLine());
    	for(int i=0;i<n;i++) value[i]=Integer.parseInt(st.nextToken());
    	int[] dp = new int[m+1];
    	Arrays.fill(dp, 1000000);
    	dp[0]=0;
    	for(int i=0;i<n;i++) {
    		for(int w=m;w>=0;w--) {
    			if(w-app[i]>=0) dp[w]=Math.min(dp[w], dp[w-app[i]]+value[i]);
    			else if(dp[w]>value[i]) dp[w]=value[i];
    		}
    	}
    	System.out.println(dp[m]);
    }
}
