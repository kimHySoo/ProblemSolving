package BOJ;

import java.util.*;
import java.io.*;
public class p30804 {
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int n = Integer.parseInt(br.readLine());
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	int[] arr = new int[n];
    	for(int i=0;i<n;i++) arr[i]=Integer.parseInt(st.nextToken());
    	int[] count = new int[10];
    	int left = 0;
    	int cnt = 0;
    	int max = 0;
    	for(int r=0;r<n;r++) {
    		if(count[arr[r]]++==0) cnt++;
    		while(cnt>2) if(--count[arr[left++]]==0) cnt--;
    		max = Math.max(max, r-left+1);
    		}
    	System.out.println(max);
    }
}
