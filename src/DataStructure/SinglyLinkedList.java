package DataStructure;

class Node{
	//데이터 필드 (문자열로 고정) -> 제네릭
	String data;
	//링크 필드
	Node link; //나 자신을 필드로 가지고 있지만 헷갈리지 말자
	public Node() {
		// TODO Auto-generated constructor stub
	}
	
	public Node(String data) {
		this.data = data;
		this.link = null; //이건 필요 없어 null이 기본이야!
	}
	
}
public class SinglyLinkedList {
	Node head; //연결리스트의 시작점(더미x)
	int size; //은근하게 쓰이는 곳이 좀 있음
	
//	public SinglyLinkedList() {
//		//알아서 내부적으로 Null, 0으로 초기화 시켜둔다.
//	}
	//노드 삽입(첫번쨰 위치, 마지막 위치, 중간 위치)
	//첫번째 위치 삽입
	//데이터를 받는다 -> Node가 넘어와도 괜찮아
	public void addFirst(String data) {
		//1. Node 생성
		Node node = new Node(data);
		//2. Node 링크는 head를 저장한다.
		node.link = head;
		//3. Head는 새로 만든 node를 가리킨다.
		head = node;
		size++;
		
	}
	
	public void addLast(String data) {
		if(size == 0) {
			addFirst(data);
			return;
		}
		// 노드 생성
		Node node = new Node(data);
		// 마지막 노드를 탐새갛고 해당 위치에 넣는다.
		Node curr = head;
		
		//size를 알고 있으면 해당 값만큼 이동하면 됨
		while(curr.link!=null) {
			curr = curr.link;
		}
		curr.link = node;
		size++;
		
	}
	
	
	//중간 위치 삽입
	public void add(int idx, String data) {
		if(idx ==0) {
			addFirst(data);
		}
		else if(idx==size) {
			addLast(data);
		}
		else if(idx < 0 || idx > size) {
			//커스텀이 가능하니
			//음수라면 가장 앞에 넣어주자
			//아주 큰수라면 가장 뒤에 넣어주자
			//예외 발생
		}
		else {
			//이전 노드를 찾겠다
			Node pre = get(idx-1);
			Node node = new Node(data);
			
			node.link = pre.link;
			pre.link = node;
			
			size++;
			
		}
	}
	
	//idx 번째 값 조회
	public Node get(int idx) {
		if(idx<0||idx>=size) {
			//예외처리를 하던지
			//음수면 0번을
			//큰수면 마지막 인덱스를 가져와
			return null;
		}
		Node curr = head;
		for(int i=0;i<idx;i++) {
			curr = curr.link;
		}
		return curr;
	}
	
	//삭제 (첫번째 위치, 마지막 위치, 중간 위치)
	public String removeFirst() {
		if(head == null) return null; //없어, 예외 발생시켜라
		
		//데이터 확보
		
		String data = head.data;
		head = head.link;
		size--;
		
		return data;
	}
	public String remove(int idx) {
		if(idx==0) return removeFirst();
		
		if(idx<0||idx>=size) return null;
		
		Node pre = get(idx-1); //이전 노드 가져오기
//		Node rm = get(idx);		//삭제 노드 가져오기
		Node rm = pre.link; 	//이전 노드 이용하여 가져오기
		
		//지우고 싶은 데이터
		String data = rm.data;
//		String data = pre.link.data; //rm을 변수에 담아두지 않았으면
		
		pre.link = rm.link;
//		pre.link = pre.link.link; //rm을 몯라
		size--;
		return data;
	}
	//마지막 위치 삭제는 생략 -> 중간위치 삭제 활용하면된다.
	
	//출력메서드
	public void printList() {
		Node curr = head;
		if(head == null) {
			System.out.println("공백 리스트");
			return;
		}
		while(curr!=null) {
			System.out.print(curr.data+"->");
			curr = curr.link;
		}
		System.out.println();
	}
}
