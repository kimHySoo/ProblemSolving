import java.util.*;
import java.io.*;

public class ModularInverse {
	static int mod = 1_000_000_007;
	public static void main(String[] args) throws IOException{	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		long[] fact = new long[n+1];
		fact[0] = 1;
		for(int i=1;i<=n;i++) fact[i] = fact[i-1]*i%mod;
		
		long x = fact[n-m]*fact[m]%mod;
		long inv = pow(x, mod-2);
		long ans = fact[n]*inv%mod;
		
		System.out.println(ans);
	}
	
	static long pow(long x, long e) {
		long r = 1;
		
		while(e>0) {
			if((e&1)==1) r = r*x%mod;
			x = x*x%mod;
			e>>=1;
		}
		
		return r;
	}
}
