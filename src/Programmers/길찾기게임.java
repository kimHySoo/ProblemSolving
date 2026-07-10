
public class 길찾기게임 {
	public static void main(String[] args) {
		int[][] node_info = {{5,3},{11,5},{13,3},{3,5},{6,1},{1,3},{8,6},{7,2},{2,2}};
//		System.out.println(solution(node_info));
		System.out.println(Arrays.deepToString(solution(node_info)));
		
	}
	
	static class Node{
		int val, x, y;
		Node parent, left, right;
		
		Node(int val, int x, int y){
			this.val = val;
			this.x = x;
			this.y = y;
		}
	}
	static List<Integer> pre, post;
	static List<Node> g;
	static int[][] solution(int[][] nodeinfo) {
		int n = nodeinfo.length;
		int[][] answer = new int[2][n];
		g = new ArrayList<>();
		
		for(int i=0;i<n;i++) {
			g.add(new Node(i+1, nodeinfo[i][0], nodeinfo[i][1]));
		}
		
		g.sort((a,b)->{
			if(a.y!=b.y) return Integer.compare(b.y, a.y);
			return Integer.compare(a.x, b.x);
		});
		
		for(int i=1;i<n;i++) {
			Node root = g.get(0);
			Node cur = g.get(i);
			
			while(true) {
				if(root.x<cur.x) {
					if(root.right==null) {
						root.right = cur;
						break;
					} else {
						root = root.right;
					}
				} else {
					if(root.left == null) {
						root.left = cur;
						break;
					} else {
						root = root.left;
					}
				}
			}
			
		}
		
		pre = new ArrayList<>();
		post = new ArrayList<>();
		
		Node root = g.get(0);
		preorder(root);
		postorder(root);
		
		for(int i=0;i<n;i++) {
			answer[0][i] = pre.get(i);
			answer[1][i] = post.get(i);
		}
		
	    return answer;
	}
	
	static void preorder(Node cur) {
		pre.add(cur.val);
		if(cur.left!=null) preorder(cur.left);
		if(cur.right!=null) preorder(cur.right);
	}
	static void postorder(Node cur) {
		if(cur.left!=null) postorder(cur.left);
		if(cur.right!=null) postorder(cur.right);
		post.add(cur.val);
	}
	
	
}
