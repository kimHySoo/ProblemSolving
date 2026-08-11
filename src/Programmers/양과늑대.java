import java.util.*;

class Solution {
    public int solution(int[] info, int[][] edges) {
        int answer = 0;
        int n = info.length;        

        Tree(n, edges);
        
        Deque<Node> q = new ArrayDeque<>();
        q.add(new Node(0, 1, 0, new HashSet<>()));
        
        while(!q.isEmpty()){
            Node now = q.poll();
            answer = Math.max(answer, now.s);
            now.adj.addAll(tree[now.cur]);
            
            for(int next:now.adj){
                HashSet<Integer> set = new HashSet<>(now.adj);
                set.remove(next);
                
                if(info[next]==0){
                    q.add(new Node(now.w, now.s+1, next, set));
                } else if(now.s>now.w+1){
                    q.add(new Node(now.w+1, now.s, next, set));
                }
                
            }
            
        }
        
        return answer;
    }
    
    List<Integer>[] tree;
    void Tree(int n, int[][] edges){
        tree = new ArrayList[n];
        for(int i=0;i<n;i++){
            tree[i] = new ArrayList<>();
        }
        for(int[] edge:edges){
            tree[edge[0]].add(edge[1]);
        }
    }
    class Node{
        int w, s, cur;
        HashSet<Integer> adj = new HashSet<>();
        Node(int w,int s, int cur, HashSet<Integer> adj){
            this.w = w;
            this.s = s;
            this.cur = cur;
            this.adj = adj;
        }
    }
}
