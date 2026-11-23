import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException{	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());

		int x = n/2;
		int y = n-n/2;
		
		int[] a1 = new int[x];
		int[] a2 = new int[y];
		
		long[] s1 = new long[1<<x];
		long[] s2 = new long[1<<y];
		
		for(int i=0;i<x;i++) a1[i] = Integer.parseInt(st.nextToken());
		for(int i=0;i<y;i++) a2[i] = Integer.parseInt(st.nextToken());
		
		int l = 1;
		for(int w:a1) {
			for(int j=0;j<l;j++) s1[j+l] = s1[j]+w;
			l*=2;
		}
		
		l=1;
		for(int w:a2) {
			for(int j=0;j<l;j++) s2[j+l] = s2[j]+w;
			l*=2;
		}
		
		Arrays.sort(s2);

		int cnt = 0;
		
		for(long w:s1) {
			int left = 0;
			int right = 1<<y;
			
			while(left<right) {
				int mid = left+(right-left)/2;
				
				if(s2[mid]+w>m) right = mid;
				else left = mid+1;
			}
			
			cnt+=left;
		}
		
		System.out.println(cnt);
	}
}
