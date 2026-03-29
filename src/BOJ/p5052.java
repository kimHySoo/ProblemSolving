//클래스와 함수 구현을 복습에 좋은 문제
//실제로 trie가 아니라 단순 정렬로도 풀리지만 클래스 구현만으로 가치있는듯?

package BOJ;
import java.io.*;
public class p5052 {
	static class Node{
		Node[] child = new Node[10];
		boolean isEnd;
	}
	static class Trie{
		Node root = new Node();
		boolean insert(String s) {
			Node cur = root;
			for(int i=0;i<s.length();i++) {
				int index = s.charAt(i)-'0';
				if(cur.isEnd) return false;
				if(cur.child[index]==null) cur.child[index] = new Node();
				cur = cur.child[index];
			}
			if(cur.isEnd) return false;
			for(int i=0;i<10;i++) if(cur.child[i]!=null) return false;
			cur.isEnd = true;
			return true;
		}
	}
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringBuilder sb = new StringBuilder();
    	int test = Integer.parseInt(br.readLine());
    	while(test-->0) {
    		int n = Integer.parseInt(br.readLine());
    		Trie trie = new Trie();
    		boolean ok = true;
    		while(n-->0) {
    			String line = br.readLine();
    			if(ok) ok = trie.insert(line);
    		}
    		sb.append(ok?"YES\n":"NO\n");
    	}
    	System.out.println(sb.toString());
    }
}
