import java.io.*;
import java.util.*;

public class 디스크컨트롤러 {
    static class Disk{
        int idx, s, w;
        Disk(int idx, int s, int w){
            this.idx = idx;
            this.s = s;
            this.w = w;
        }

    }
	public static void main(String[] args) throws IOException{
		int[][] jobs = {{0, 10},{0, 1},{100, 1}};
		
		PriorityQueue<Disk> pq = new PriorityQueue<>((a, b)->{
            if(a.w!=b.w) return Integer.compare(a.w, b.w);
            else if(a.s!=b.s) return Integer.compare(a.s, b.s);
            return Integer.compare(a.idx, b.idx);
        });
        
        List<Disk>table = new ArrayList<>();
        
        
        for(int i=0;i<jobs.length;i++){
            table.add(new Disk(i, jobs[i][0], jobs[i][1]));
        }
        
        Collections.sort(table, (a,b)-> Integer.compare(a.s, b.s));
        
        int n = table.size();
        int worktime = 0;
        int answer = 0;
        int idx = 0;
        for(Disk d:table) System.out.println(d.idx);
        while(idx<n){
            if(pq.isEmpty())worktime=Math.max(worktime, table.get(idx).s);

            while(idx<n&&table.get(idx).s<=worktime) {
                pq.add(table.get(idx++));              
            }
            
            Disk cur = pq.poll();
            worktime+=cur.w;
            answer+=worktime - cur.s;
            
        }
        while(!pq.isEmpty()){
            Disk cur = pq.poll();

            worktime=Math.max(worktime, cur.s);
            worktime+=cur.w;
            answer+=worktime - cur.s;
        }
        
        
        System.out.println(answer/n);
	}
}
