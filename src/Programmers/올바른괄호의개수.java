import java.util.*;

class Solution {
    public int solution(int n) {
        int answer = 0;
        dp = new int[n+1][n+1];
        for(int i=0;i<=n;i++) Arrays.fill(dp[i], -1);
        
        return dfs(n, n);
    }
    int[][] dp;
    int dfs(int x, int y){
        if(x==0) return 1;
        if(dp[x][y]!=-1) return dp[x][y];
        
        dp[x][y]=0;
        
        if(x>0) dp[x][y]+=dfs(x-1, y);
        if(x<y) dp[x][y]+=dfs(x, y-1);
        
        return dp[x][y];
    }
}
