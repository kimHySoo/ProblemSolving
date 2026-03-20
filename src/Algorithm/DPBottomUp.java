package Algorithm;

public class DPBottomUP{
    static int[] weight;
    static boolean[] dp;
    static int sum;
    //2629
    //https://www.acmicpc.net/problem/2629
    static void btu(){
            for (int w : weight) {
            boolean[] next = new boolean[sum + 1];

            for (int d = 0; d <= sum; d++) {
                if (!dp[d]) continue;

                next[d] = true;
                if (d + w <= sum) next[d + w] = true;
                next[Math.abs(d - w)] = true;
            }

            dp = next;
            }
    }
    //11049
    //https://www.acmicpc.net/problem/11049
    public static void main(String[] args){
        int[][] dp;
        int n;
		for(int len=1;len<n;len++) {
			for(int i=0; i<n-len;i++) {
				for(int j=0;j<len;j++) {
					dp[i][i+len]=Math.min(dp[i][i+len], dp[i][i+j]+dp[i+j+1][i+len]+cal(i, i+j, dim[i+len][i+len][1]));
				}
			}
		}
    }
	static int cal(int i, int j, int x) {
		return dim[i][i][0]*dim[j][j][1]*x;
	}
}