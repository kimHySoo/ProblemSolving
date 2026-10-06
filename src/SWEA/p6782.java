import java.util.*;
import java.io.*;

public class p6782 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int test = Integer.parseInt(br.readLine());
		
		int max = 100001;
		
		for(int t=1;t<test+1;t++) {
			long x = Long.parseLong(br.readLine());

			int cnt = 0;
			
			while(x!=2) {
				long b = x;
				long a = 2;
				
				while(a<=b) {
					long mid = (a+b)/2;
					if(mid<=(x-1)/mid) {
						a = mid+1;
					} else {
						b = mid-1;
					}
				}
				cnt+=(a*a-x);
				x=a;
				cnt++;
			}
			sb.append("#"+t+" "+cnt+"\n");
		}
		System.out.println(sb.toString());
	}
}


