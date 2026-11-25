import java.util.*;
import java.io.*;

public class Main {
	static int mod = 1_000_000_007;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		
		long[] fact = new long[5001];
		fact[0] = 1;
		for(int i=1;i<5001;i++) fact[i] = fact[i-1]*i%mod;
		
		for(int t=0;t<n;t++) {
			int x = Integer.parseInt(br.readLine());
			
			if((x&1)==1) {
				sb.append(0).append('\n');
				continue;
			}
			
			long p = fact[x/2]*fact[x/2]%mod*(x/2+1)%mod;
			long inv = pow(p, mod-2)%mod;
			long q = fact[x]*inv%mod;
			
			sb.append(q).append('\n');
		}
		System.out.println(sb.toString());
	}
	static long pow(long x, long e) {
		long r = 1;
		x%=mod;
		
		while(e>0) {
			
			if((e&1)==1) r = r*x%mod;
			x = x*x%mod;
			
			e>>=1;
		}
		
		return r;
	}
}
