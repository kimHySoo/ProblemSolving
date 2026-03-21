package Algorithm;
import java.io.*;
import java.util.*;

public class Knapsack{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] dp = new int[k + 1];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            for (int cap = k; cap >= w; cap--) {
                dp[cap] = Math.max(dp[cap], dp[cap - w] + v);
            }
        }

        System.out.println(dp[k]);
    }
}