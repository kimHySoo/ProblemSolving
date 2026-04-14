//단조스택 개념 숙지하기

package BOJ;
import java.io.*;
import java.util.*;

public class p17298 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[n];
		
		for (int i = 0; i < n; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		Deque<Integer> mono = new ArrayDeque<>();
		int[] ans = new int[n];
		
		for (int i = n - 1; i >= 0; i--) {
			while (!mono.isEmpty() && arr[mono.peek()] <= arr[i])
				mono.pop();
			if (mono.isEmpty())
				ans[i] = -1;
			else
				ans[i] = arr[mono.peek()];
			mono.push(i);
		}
		
		for (int x : ans)
			sb.append(x).append(' ');
		
		System.out.print(sb.toString());
	}
}