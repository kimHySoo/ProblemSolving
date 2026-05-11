package SWEA;
import java.io.*;
import java.util.*;

public class p4038 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int test = Integer.parseInt(br.readLine());

        for (int t = 1; t <= test; t++) {
            sb.append('#').append(t).append(' ');
            
            String x = br.readLine();
            String y = br.readLine();
            
            sb.append(kmp(x, y)).append('\n');
        }
        System.out.println(sb.toString());
    }
    static int[] getPi(String x) {
    	int n = x.length();
    	int[] arr = new int[n];
    	
    	int j = 0;
    	for(int i=1;i<n;i++) {
    		while(j>0&&x.charAt(i)!=x.charAt(j)) j=arr[j-1];
    		if(x.charAt(i)==x.charAt(j)) arr[i]=++j;
    	}
    	
    	return arr;
    }
    static int kmp(String x, String y) {
    	int[] pi = getPi(y);
    	int cnt = 0;
    	int idx = 0;
    	int n = x.length();
    	int m = y.length();
    	
    	for(int i=0;i<n;i++) {
  			while(idx>0&&x.charAt(i)!=y.charAt(idx)) idx = pi[idx-1];

    		if(x.charAt(i)==y.charAt(idx)) idx++;
    		
    		if(idx==m) {
    			cnt++;
    			idx = pi[idx-1];
    		}
    	}
    	
    	return cnt;
    }
}