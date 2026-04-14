package BOJ;
import java.io.*;
import java.util.*;

public class p17299 {
	public static void main(String[] args) throws IOException{
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		int[] ans = new int[n];
		int[] count = new int[1000001];
		Deque<Integer> stack = new ArrayDeque<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			count[arr[i]]++;
		}
		
		for(int i=n-1;i>=0;i--) {
			while(!stack.isEmpty()&&count[arr[i]]>=count[arr[stack.peek()]]) stack.pop();
			
			if(stack.isEmpty()) ans[i]=-1;
			else ans[i]=arr[stack.peek()];
			
			stack.push(i);
		}
		
		for(int x:ans) sb.append(x).append(' ');
		System.out.print(sb.toString());
	}

}
