package DataStructure;

public class LinkedList{

	static class Node{
		int value;
		Node prelink;
		Node postlink;
		Node() {
		
		}
		
		Node(int value){
			this.value = value;
			this.prelink = null;
			this.postlink = null;
		}
		
	}
    
	static class DoublyLinkedList{
		Node head;
		int size;
		DoublyLinkedList() {
			head = new Node();
			size = 0;
		}
		
		void add(int value) {
			Node node = new Node(value);
			Node cur = head;
			
			while(cur.postlink!=null) cur = cur.postlink;
			
			cur.postlink = node;
			node.prelink = cur;
			
			size++;
		}
		
		void add(int index, int value) {
			Node node = new Node(value);
			if(size<index) {
				add(value);
				return;
			}
			Node cur = head;
			for(int i=0;i<index;i++) {
				cur = cur.postlink; 
			}
			
			if(cur.prelink!=null) cur.prelink.postlink = node;
			node.prelink = cur.prelink;
			node.postlink = cur;
			cur.prelink = node;
			
			size++;
		}
		
	}
	
}