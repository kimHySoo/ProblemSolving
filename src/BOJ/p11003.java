//우선순위큐는 시간복잡도가 O(nlogn)을 벗어날 수 없으니 Deque를 써야함

package BOJ;
import java.io.*;
import java.util.*;

public class p11003 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int l = Integer.parseInt(st.nextToken());
		int[] arr = new int[n];
		Deque<int[]> q = new ArrayDeque<>();
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			int x = Integer.parseInt(st.nextToken());
			while(!q.isEmpty()&&q.peekLast()[0]>=x) q.pollLast();
			q.addLast(new int[] {x, i});
			while(!q.isEmpty()&&i-q.peekFirst()[1]>=l) q.pollFirst();
			sb.append(q.peekFirst()[0]).append(' ');
		}
		System.out.println(sb.toString());
		
		
	}
}