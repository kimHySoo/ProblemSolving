//LinkedList 구현 연습
package SWEA;
import java.io.*;
import java.util.*;
public class p13051 {
	
	static class Node{
		int value;
		Node prev, next;
		
		Node() {
		
		}
		
		Node(int value){
			this.value = value;
			this.prev = null;
			this.next = null;
		}
	}
	
	static class LinkedList{
		int size;
		Node head, tail;
		
		LinkedList() {
			head = new Node();
			tail = head;
			size = 0;
		}
		
		void add(int value) {
			Node node = new Node(value);
			tail.next = node;
			node.prev = tail;
			tail = node;
			size++;
		}
		
		void add(int index, int value) {
			Node node = new Node(value);
			Node cur = node;
			if(index<=size/2) {
				cur = head;
				
				for(int i=0;i<index;i++) {
					cur = cur.next;
				}
			} else {
				
				cur = tail;
				
				for(int i=size-1;i>=index;i--) {
					cur = cur.prev;
				}
				
			}
			
			node.prev = cur;
			node.next = cur.next;
			if(cur.next != null) cur.next.prev = node;
			cur.next = node;
			if(node.next == null) tail = node; 
			
			size++;
		}
		
		void remove(int index) {
			Node cur = new Node(0);
			if(index<=size/2) {
				cur = head.next;
				
				for(int i=0;i<index;i++) {
					cur = cur.next;
				}
				
			} else {
				cur = tail;
				
				for(int i=size-1;i>index;i--) {
					cur = cur.prev;
				}
				
			}
			
			if(cur.next!=null) cur.next.prev = cur.prev;
			if(cur.prev!=null) cur.prev.next = cur.next;
			if(cur==tail) tail=cur.prev;
			
			size--;
		}
		
		void change(int index, int value) {
			Node cur = new Node(0);
			
			if(index<=size/2) {
				cur = head.next;
				
				for(int i=0;i<index;i++) {
					cur = cur.next;
				}
				
			} else {
				cur = tail;
				
				for(int i=size-1;i>index;i--) {
					cur = cur.prev;
				}
				
			}
			
			cur.value = value;
			
		}
		
		int get(int index) {
			Node cur = new Node(0);
			if(index>=size) {
				
				return -1;
				
			} else if(index<=size/2) {
				cur = head;
				
				for(int i=0;i<=index;i++) {
					cur = cur.next;
				}
				
			} else {
				cur = tail;
				
				for(int i=size-1;i>=index+1;i--) {
					cur = cur.prev;
				}
				
			}
			
			return cur.value;
		}
		
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int test = Integer.parseInt(br.readLine());
		
		LinkedList ll;
		
		for(int t=1;t<=test;t++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			
			ll = new LinkedList();
			
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<n;i++) ll.add(Integer.parseInt(st.nextToken()));
			
			for(int i=0;i<m;i++) {
				st = new StringTokenizer(br.readLine());
				
				char c = st.nextToken().charAt(0);
				int idx = Integer.parseInt(st.nextToken());
				
				if(c=='I') {
					int val = Integer.parseInt(st.nextToken());
					ll.add(idx, val);
				} else if(c=='D') {
					ll.remove(idx);
				} else {
					int val = Integer.parseInt(st.nextToken());
					ll.change(idx, val);
				}
				
			}
			
			sb.append('#').append(t).append(' ').append(ll.get(l)).append('\n');
				
			
		}
		System.out.print(sb.toString());
	}
}