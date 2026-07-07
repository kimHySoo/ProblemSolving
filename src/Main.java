import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
/*
StringBuilder sb = new StringBuilder();
        sb.append('{');

        for(int i=0;i<triangle.length;i++) {
            sb.append('{');
            for(int j=0;j<triangle[i].length;j++) {
                sb.append(triangle[i][j]);
                if(j+1!=triangle[i].length) sb.append(',');
            }
            sb.append('}');
            if(i+1!=triangle.length) sb.append(',');
        }

        sb.append('}');
        System.out.println(sb.toString());


*/

public class Main {
	public static void main(String[] args) {
		int[] priorites = {2, 1, 3, 2};
		int location = 2;
		solution(priorites, location);
	}
	
	static class Node{
		int val;
		Node next;
		Node(int val){
			this.val = val;
			this.next = null;
		}
		Node(){
			
		}
	}
	static class LinkedList{
		Node head, tail;
		int cnt;
		LinkedList() {
			head = new Node();
			cnt = 0;
		}
		
		void add(int val) {
			Node cur = new Node(val);
			cnt++;
			
			if(cnt==0) {
				head.next = cur;
				tail = cur;
			} else {
				tail.next = cur;
				tail = cur;
			}
		}
		
		int findlast(int idx) {
			Node cur = head;
			
			for(int i=0;i<cnt;i++) {
				if(i!=0&&cur.next.val>idx) return cur.val;
				cur = cur.next;
			}
			return tail.val;
		}
		
		int find(int idx, int loc, int turn) {
			
			return turn;
		}
	}
	
    static int solution(int[] priorities, int location) {
        int answer = 0;
        LinkedList[] ll = new LinkedList[10];
        for(int i=1;i<10;i++) ll[i] = new LinkedList();
        
        for(int i=0;i<priorities.length;i++) {
        	int x = priorities[i];
        	ll[x].add(i);
        }
        
        int idx = 0;
        
        for(int i=9;i>0;i--) {
        	if(ll[i].cnt==0) continue;
        	if(i!=priorities[location]) {
        		answer += ll[i].cnt;
        		idx = ll[i].findlast(idx);
        	} else {
        		answer = ll[i].find(idx, location, answer);
        	}
        }
        
        return answer;
    }
   
}
