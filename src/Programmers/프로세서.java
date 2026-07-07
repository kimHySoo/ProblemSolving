public class Main {
	public static void main(String[] args) {
		int[] priorites = {1, 1, 9, 1, 1, 1};
		int location = 0;
		System.out.println(solution(priorites, location));
		
	}
	class Node{
        int val;
        Node next;
        Node(int val){
            this.val = val;
            this.next = null;
        }
        Node(){

        }
    }
class LinkedList{
        Node head, tail;
        int cnt;
        LinkedList() {
            head = new Node();
            cnt = 0;
        }

        void add(int val) {
            Node cur = new Node(val);

            if(cnt==0) {
                head.next = cur;
                tail = cur;
            } else {
                tail.next = cur;
                tail = cur;
            }
            cnt++;
        }

                int findlast(int idx) {
            Node cur = head.next;
            int result = tail.val;

            while(cur!=null) {
                if(cur.val<idx) {
                    result = cur.val;
                }
                cur = cur.next;
            }

            return result;
        }

        int find(int idx, int loc) {
            Node cur = head.next;

            int loc_idx = 0;
            int last_idx = 0;
            boolean find_last = cur.val>idx?true:false;
            for(int i=0;i<cnt;i++) {
                if(cur.val==loc) loc_idx = i;
                if(!find_last&&cur.val>idx) {
                    find_last = true;
                    last_idx = i;
                }
                cur = cur.next;
            }

            if(loc_idx==last_idx) return 1;
            else if(loc_idx>last_idx) {
                return loc_idx-last_idx+1;
            } else {
                return cnt-last_idx+1+loc_idx;
            }

        }
    }

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        LinkedList[] ll = new LinkedList[10];
        for(int i=1;i<10;i++) ll[i] = new LinkedList();

        for(int i=0;i<priorities.length;i++) {
            int x = priorities[i];
            ll[x].add(i);
        }

        int idx = -1;

        for(int i=9;i>0;i--) {
            if(ll[i].cnt==0) continue;
            if(i!=priorities[location]) {
                answer += ll[i].cnt;
                idx = ll[i].findlast(idx);
            } else {
                answer += ll[i].find(idx, location);
                break;
            }
        }

        return answer;
    }
}
	
    static int solution1(int[] priorities, int location) {
        int answer = 0;
        
        int[] cnt = new int[10];
        int cur = 0;
        for(int i:priorities) {
        	cnt[i]++;
        	cur = Math.max(cur, i);
        }
        
        Deque<Integer> q = new ArrayDeque<>();
        for(int i=0;i<priorities.length;i++) q.add(i);
        
        while(!q.isEmpty()) {
        	int idx = q.poll();
        	if(priorities[idx]==cur) {
        		answer++;
        		if(idx==location) return answer;
        		if(--cnt[cur]==0) {
        			while(cnt[cur]<=0) cur--;
        		}
        	} else {
        		q.add(idx);
        	}
        }
        
        return answer;
    }
   
}
