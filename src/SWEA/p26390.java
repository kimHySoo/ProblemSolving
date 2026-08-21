import java.util.*;
import java.io.*;

public class p26390 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int test = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		for(int t = 1;t<=test;t++) {
			int n = Integer.parseInt(br.readLine());

			int[] cnt = new int[n+1];
			int ans = 0;

			for(int i=0;i<n-1;i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				if(++cnt[x]>2) ans++;
				if(++cnt[y]>2) ans++;
			}
			sb.append(ans).append('\n');
		}
		System.out.println(sb.toString());
	}
}







