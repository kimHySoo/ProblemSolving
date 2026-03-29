//그냥 단순히 문자열 기준으로 뭐가 더 큰지만 비교하면 끝남
package BOJ;
import java.io.*;
import java.util.*;
public class p16496 {
	static int n;
	static int[] arr, tmp;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		tmp = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) arr[i] = Integer.parseInt(st.nextToken());
		sort(0, n-1);
		for(int i:arr) sb.append(i);
		String ans = sb.toString();
		if(ans.charAt(0)=='0') ans = "0";
		System.out.print(ans);
	}
    static boolean isBig(int x, int y) {
        String s1 = String.valueOf(x);
        String s2 = String.valueOf(y);
        return (s1 + s2).compareTo(s2 + s1) >= 0;
    }
	static void sort(int left, int right) {
		if(left>=right) return;
		int mid = (left+right)/2;
		sort(left, mid);
		sort(mid+1, right);
		merge(left, mid, right);
	}
	static void merge(int left, int mid, int right) {
		int i = left;
		int j = mid+1;
		int idx = left;
		while(i<=mid&&j<=right) {
			if(isBig(arr[i], arr[j])) tmp[idx++]=arr[i++];
			else tmp[idx++]=arr[j++];
		}
		while(i<=mid) tmp[idx++]=arr[i++];
		while(j<=right) tmp[idx++]=arr[j++];
		for(int k=left;k<=right;k++) arr[k]=tmp[k];
	}
}
