package BOJ;
import java.io.*;
import java.util.*;

public class p3015 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		long cnt = 0;

		int[] arr = new int[n];
		Deque<int[]> stack = new ArrayDeque<>(); 
		
		for(int i=0;i<n;i++) arr[i] = Integer.parseInt(br.readLine());
		
		for(int i=0;i<n;i++) {
			int dup = 1;
			while(!stack.isEmpty()&&arr[stack.peek()[0]]<arr[i]) {
				int[] x = stack.pop();
				int d = x[1];
				cnt+=d;
				cnt+=(long)d*(d-1)/2;
				if(!stack.isEmpty()) cnt+=d;
			}
			if(!stack.isEmpty()&&arr[stack.peek()[0]]==arr[i]) {
				dup+=stack.pop()[1];
			}
			stack.push(new int[] {i, dup});
		}
		while(!stack.isEmpty()) {
			int[] x = stack.pop();
			int d = x[1];
			cnt+=(long) d*(d-1)/2;
			if(!stack.isEmpty()) cnt+=d;
		}
		System.out.println(cnt);
		
	}
}