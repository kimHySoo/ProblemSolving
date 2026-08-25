import java.util.*;
import java.io.*;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int test = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int t = 1;t<=test;t++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			long ans = 1;
			int[] cnt = new int[m];
			for(int i=0;i<m;i++) cnt[i]=n/m;
			for(int i=0;i<n%m;i++) cnt[i]++;
			for(int i=0;i<m;i++) ans*=(long)cnt[i];
			sb.append("#"+t+" ").append(ans).append('\n');
		}
		System.out.println(sb.toString());
	}
}


