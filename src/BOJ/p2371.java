//열심히 아이디어 짰지만 결국 브루트포스;;
package BOJ;

import java.io.*;
import java.util.*;

public class p2371 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test = Integer.parseInt(br.readLine());
        List<Integer>[] num = new ArrayList[test];
        for(int t=0;t<test;t++) {
        	num[t] = new ArrayList<>();
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	int x= Integer.parseInt(st.nextToken());
        	while(x!=-1) {
        		num[t].add(x);
        		x=Integer.parseInt(st.nextToken());
        	}
        }
        int ans = 0;
        for(int i=0;i<test-1;i++) {
        	for(int j=i+1;j<test;j++) {
        		int n = num[i].size();
        		int m = num[j].size();
        		int x = Math.min(n, m);
        		int idx = 0;
        		while(idx<x&&num[i].get(idx)==num[j].get(idx)) idx++;
        		ans=Math.max(idx+1, ans);
        	}
        }
        System.out.println(ans);
    }
}