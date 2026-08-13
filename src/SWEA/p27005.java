import java.util.*;
import java.io.*;

public class p27005 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int test = Integer.parseInt(br.readLine());
		int[] arr = new int[1001];
		arr[0] = arr[1] = 1;
		for(int t = 1;t<=test;t++) {
			int n = Integer.parseInt(br.readLine());
			
			for(int i=2;i<=n;i++) {
				int val = 1;
				
				while(true) {
					arr[i] = val;
					boolean pass = true;
					
					for(int j=1;j<=i/2;j++) {
						if(val-arr[i-j]==arr[i-j]-arr[i-j*2]) {
							pass = false;
							break;
						}
					}
					
					if(pass) break;
					val++;
				}
			}
			sb.append(arr[n]).append('\n');
		}
		System.out.println(sb.toString());
	}
}
