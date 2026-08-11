class Solution {
    class Node{
        char c;
        Node[] child;
        boolean isFin;
        int cnt = 0;
        Node(){
            child = new Node[26];
            isFin = false;
            cnt = 0;
        }
        Node(char c){
            this.c = c ;
            child = new Node[26];
            isFin = false;
            cnt = 1;
        }
    }
    class Trie{
        Node head;
        
        Trie(){
            head = new Node();
        }
        
        void add(String s){
            Node cur = head;
            for(char c:s.toCharArray()){
                int x = c-'a';
                if(cur.child[x]==null) cur.child[x] = new Node(c);
                else cur.child[x].cnt++;
                cur = cur.child[x];
            }
            cur.isFin = true;
        }
        int find(String s){
            Node cur = head;
            int ans = 0;
            for(char c:s.toCharArray()){
                ans++;
                int x = c-'a';
                cur = cur.child[x];
                if(cur.cnt==1) return ans;                
            }
            return ans;
        }
    }
    public int solution(String[] words) {
        int answer = 0;
        Trie trie = new Trie();
        for(String s:words){
            trie.add(s);
        }
        for(String s:words){
            answer+=trie.find(s);
        }
        return answer;
    }
}
