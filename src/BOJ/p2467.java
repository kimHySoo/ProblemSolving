package BOJ;
import java.io.*;
import java.util.*;

public class p2467 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
        	arr[i] = Integer.parseInt(st.nextToken());
        }
        int left = 0;
        int right = n-1;
        int[] ans = new int[2];
        Arrays.fill(ans, Integer.MAX_VALUE/2-1);
        while(left<right) {
        	int res = arr[left]+arr[right];
        	if(Math.abs(ans[0]+ans[1])>Math.abs(res)) {
        		ans[0] = arr[left];
        		ans[1] = arr[right];
        	}
        	if(res<0) left++;
        	else right--;
        }
        System.out.println(ans[0]+" "+ans[1]);
    }
}