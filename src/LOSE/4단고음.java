import java.util.*;

class Solution {

    public int solution(int n) {
        return dfs(n - 2, 2);
    }

    int dfs(int n, int x) {
        if(n==3&&x==2) return 1;
        if(n<3||Math.log(n)/Math.log(3)*2<x) return 0;
        
        int result = dfs(n-1, x+1);
        if(n%3==0) result+=dfs(n/3, x-2);
        return result;
    }
}
