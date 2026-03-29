package DataStructure;

public class TrieAlgo {
    
	static class Node {
		Node[] child = new Node[26];
		boolean isEnd;
	}

	static class Trie {
		Node root = new Node();

		void insert(String s) {
			Node cur = root;
			for (int i = 0; i < s.length(); i++) {
				int index = s.charAt(i) - 'a';

				if (cur.child[index] == null) {
					cur.child[index] = new Node();
				}
				cur = cur.child[index];
			}

			cur.isEnd = true;
		}

		boolean search(String s) {
			Node cur = root;
			for (int i = 0; i < s.length(); i++) {
				int index = s.charAt(i) - 'a';

				if (cur.child[index] == null) {
					return false;
				}
				cur = cur.child[index];
			}

			return cur.isEnd;
		}
		boolean startsWith(String s) {
			Node cur = root;
			for (int i = 0; i < s.length(); i++) {
				int index = s.charAt(i) - 'a';
				if (cur.child[index] == null) return false;
				cur = cur.child[index];
			}
			return true;
}
	}

}
