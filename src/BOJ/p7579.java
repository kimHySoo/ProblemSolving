//gpt: 정석 풀이와는 거리가 있음 - 정석: dp[c] = 비용이 c일 때 확보할 수 있는 최대 메모리
//역순 순회하는 이유: 해당 상품을 단 한번만 사용하기 위해
package BOJ;

import java.io.*;
import java.util.*;
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


/*
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] memory = new int[n];
        int[] cost = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            memory[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int totalCost = 0;
        for (int i = 0; i < n; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
            totalCost += cost[i];
        }

        // dp[c] = 비용 c로 확보 가능한 최대 메모리
        int[] dp = new int[totalCost + 1];

        for (int i = 0; i < n; i++) {
            for (int c = totalCost; c >= cost[i]; c--) {
                dp[c] = Math.max(dp[c], dp[c - cost[i]] + memory[i]);
            }
        }

        for (int c = 0; c <= totalCost; c++) {
            if (dp[c] >= m) {
                System.out.println(c);
                break;
            }
        }
    }
}

*/