import java.io.*;
import java.util.*;

public class p2948 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int test = Integer.parseInt(br.readLine());
		Set<String> dic;
		for(int t=1;t<test+1;t++) {
			 dic = new HashSet<>();
			 StringTokenizer st = new StringTokenizer(br.readLine());
			 
			 int n = Integer.parseInt(st.nextToken());
			 int m = Integer.parseInt(st.nextToken());
			 int ans = 0;
			 st = new StringTokenizer(br.readLine());
			 for(int i=0;i<n;i++) {
				 dic.add(st.nextToken());
			 }
			 
			 st = new StringTokenizer(br.readLine());
			 for(int i=0;i<m;i++) {
				 String x = st.nextToken();
				 if(dic.contains(x)) {
					 ans++;
					 System.out.println(x);
				 }
			 }
			 
			 sb.append('#').append(t).append(' ').append(ans).append('\n');
		}
		System.out.println(sb.toString());
	}
}
