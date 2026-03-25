//gpt평: 굳이 cnt를 들고 다녀야 하냐?
package BOJ;
import java.io.*;
import java.util.*;

public class p16978 {
	static int n, max;
	static int[] s, w;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		s = new int[n];
		w = new int[n];
		for(int i=0;i<n;i++) {
			 StringTokenizer st = new StringTokenizer(br.readLine());
			 s[i] = Integer.parseInt(st.nextToken());
			 w[i] = Integer.parseInt(st.nextToken());
		}
		max = 0;
		dfs(0, 0);
		System.out.println(max);
	}
	static void dfs(int v, int cnt) {
		if(v==n) {
			max = Math.max(cnt, max);
			return;
		}
		if(s[v]<=0) {
			dfs(v+1, cnt);
			return;
		}
		boolean ok = true;
		for(int i=0;i<n;i++) {
			if(i==v) continue;
			if(s[i]<=0) continue;
			ok=false;
			s[i]-=w[v];
			s[v]-=w[i];
			int p = 0;
			if(s[i]<=0) p++;
			if(s[v]<=0) p++;
			dfs(v+1, cnt+p);
			s[i]+=w[v];
			s[v]+=w[i];
		}
		if(ok) dfs(v+1, cnt);
	}
}