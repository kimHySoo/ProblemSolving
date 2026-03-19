//이진 탐색 구현 미흡, 활용 미흡
//https://www.acmicpc.net/problem/12015
package BOJ;

import java.io.*;
import java.util.*;
public class p12015 {
	static List<Integer> lis; 
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		lis = new ArrayList<>();
		for(int i=0;i<n;i++) {
			int x = Integer.parseInt(st.nextToken());
			if(lis.size()==0||x>lis.get(lis.size()-1)) lis.add(x);
			else {
				int idx = binary(0, lis.size(), x);
				lis.set(idx, x);
			}
		}
		System.out.println(lis.size());
		
	}
	static int binary(int left, int right, int key) {
		if(left>right) return left;
		int mid = (left+right)/2;
		if(lis.get(mid)==key) return mid;
		else if(lis.get(mid)>key) return binary(left, mid-1, key);
		else return binary(mid+1, right, key);
	}
}