import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a,b)-> {
                return Integer.compare(a[1], b[1]);
            }
        );
        
        for(int[] route:routes){
            pq.add(route);
        }
        
        int p2 = Integer.MIN_VALUE;
        
        while(!pq.isEmpty()){
            int[] x = pq.poll();
            if(x[0]<=p2) continue;
            answer++;
            p2 = x[1];
        }
        
        return answer;
    }
}
