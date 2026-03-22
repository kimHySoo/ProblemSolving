//DP+역추적

package BOJ;
import java.io.*;
import java.util.*;
public class p14002 {
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringBuilder sb = new StringBuilder();
    	int n = Integer.parseInt(br.readLine());
    	int[] arr = new int[n];
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	for(int i=0;i<n;i++) arr[i]=Integer.parseInt(st.nextToken());
    	int[] dp = new int[n];
    	int[] how = new int[n];
    	Arrays.fill(how, -1);
    	int last = 0;
    	int max = 1;
    	for(int i=0;i<n;i++) {
    		dp[i]=1;
    		for(int j=0;j<i;j++) if(arr[i]>arr[j]) {
    			if(dp[i]<dp[j]+1) {
    				dp[i]=dp[j]+1;
    				how[i]=j;
    			}
    			if(dp[i]>max) {
    				max=dp[i];
    				last=i;
    			}
    		}
    	}
    	sb.append(max).append('\n');
    	int[] ans = new int[max];
    	int idx = max;
    	while(last!=-1) {
    		ans[--idx]=arr[last];
    		last = how[last];
    	}
    	for(int x:ans) sb.append(x).append(' ');
    	System.out.print(sb.toString());
    }
}
