import java.util.*;
import java.io.*;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int test = Integer.parseInt(br.readLine());
		StringTokenizer st;
		int mod = 998244353;
		for(int t = 1;t<=test;t++) {
			st = new StringTokenizer(br.readLine());
			long ans = 1;
			for(int i=0;i<3;i++) {
				long x = Long.parseLong(st.nextToken());
				long mid = 1;
				
				if(x%2==0) mid = (((x/2)%mod)*((x+1)%mod))%mod;
				else mid = ((x%mod)*(((x+1)/2)%mod))%mod;
				
				ans=(ans*mid)%mod;
			}
			sb.append(ans).append('\n');
		}
		System.out.println(sb.toString());
	}
}



