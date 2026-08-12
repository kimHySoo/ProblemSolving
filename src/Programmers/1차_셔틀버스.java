import java.util.*;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        String answer = "";
        
        int len = timetable.length;
        int[] table = new int[len];
        
        for(int i=0;i<len;i++){
            String s = timetable[i];
            int a = Integer.parseInt(s.substring(0, 2));
            int b = Integer.parseInt(s.substring(3, 5));
            table[i] = a*60+b; 
        }
        Arrays.sort(table);
        int bus = 540;
        int idx = 0;
        int con = bus+(n-1)*t;
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i=0;i<n;i++){
            while(idx<len&&table[idx]<=bus) {
                pq.add(table[idx++]);
            }
            
            if(i==n-1) break;

            int num = m;
            while(!pq.isEmpty()&&num-->0) pq.poll();
            bus+=t;
        }
        
        List<Integer> wait = new ArrayList<>(pq);
        
        if(!wait.isEmpty()&&wait.size()>=m){
            Collections.sort(wait);
            con = wait.get(m-1)-1;            
        }
        
        
        
        answer += String.format("%02d:%02d", con / 60, con % 60);
        return answer;
    }
}
