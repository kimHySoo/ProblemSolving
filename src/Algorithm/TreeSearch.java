package Algorithm;
import java.util.*;
import java.io.*;

public class TreeSearch {
	static int n;
	static int[] p, left, right;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		p = new int[n];
		left = new int[n];
		right = new int[n];
		for(int i=0;i<n;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = st.nextToken().charAt(0)-'A';
			int b = st.nextToken().charAt(0)-'A';
			int c = st.nextToken().charAt(0)-'A';
			if(b>=0) {
				left[a]=b;
				p[b]=a;
			}
			if(c>=0) {
				right[a]=c;
				p[c]=a;
			}
		}
		preorder(0);
		sb.append('\n');
		inorder(0);
		sb.append('\n');
		postorder(0);
		System.out.print(sb.toString());
	}
	static void preorder(int v) {
		sb.append((char)(v+'A'));
		if(left[v]!=0) preorder(left[v]);
		if(right[v]!=0) preorder(right[v]);
	}
	static void inorder(int v) {
		if(left[v]!=0) inorder(left[v]);
		sb.append((char)(v+'A'));
		if(right[v]!=0) inorder(right[v]);
	}
	static void postorder(int v) {
		if(left[v]!=0) postorder(left[v]);
		if(right[v]!=0) postorder(right[v]);
		sb.append((char)(v+'A'));
	}
}
