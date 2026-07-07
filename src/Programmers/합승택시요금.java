import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class 합승택시요금 {
	public static void main(String[] args) {
		int n = 6;
		int s = 4;
		int a = 6;
		int b = 2;
		int[][] fares = {{4,1,10},{3,5,24},{5,6,2},{3,1,41},{5,1,24},{4,6,50},{2,4,66},{2,3,22},{1,6,25}};
//		int[][] fares = {{5,7,9},{4,6,4},{3,6,1},{3,2,3},{2,1,6}};
//		int[][] fares = {{2,6,6},{6,3,7},{4,6,7},{6,5,11},{2,5,12},{5,3,20},{2,4,8},{4,3,9}};
		solution(n, s, a, b, fares);
	}
	static class Node implements Comparable<Node>{
		int v, cost;
		Node(int v, int cost) {
				this.v = v;
				this.cost = cost;
		}
		@Override
		public int compareTo(Node o){
			return Integer.compare(this.cost, o.cost);
		}
	}
	static List<Node>[] g;
    static int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;
        g = new ArrayList[n+1];
        
        for(int i=1;i<n+1;i++) g[i] = new ArrayList<>();
        
        for(int i=0;i<fares.length;i++) {
        	g[fares[i][0]].add(new Node(fares[i][1], fares[i][2]));
        	g[fares[i][1]].add(new Node(fares[i][0], fares[i][2]));
        }
        
        int[] stos = result(n, s);
        int[] stoa = result(n, a);
        int[] stob = result(n, b);
        
        for(int i=1;i<n+1;i++) {
        	answer = Math.min(answer, stos[i]+stoa[i]+stob[i]);
        }
        System.out.println(answer);
        return answer;
    }
    static int[] result(int n, int s) {
    	int[] ans = new int[n+1];
    	Arrays.fill(ans, Integer.MAX_VALUE);
    	PriorityQueue<Node> pq = new PriorityQueue<>();
    	pq.add(new Node(s, 0));
    	
    	while(!pq.isEmpty()) {
    		Node cur = pq.poll();
    		if(cur.cost>=ans[cur.v]) continue;
    		ans[cur.v] = cur.cost;
    		
    		for(Node next:g[cur.v]) {
    			int w = cur.cost+next.cost;
    			if(w>=ans[next.v]) continue;
    			pq.add(new Node(next.v, w));
    		}
    	}
    	return ans;
    	
    }
}
