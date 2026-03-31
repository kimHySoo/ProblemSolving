package DataStructure;
public class SegmentTree {
	static int[] arr, tree; //4*N의 크기로 1차원 배열을 만든다.
	static int n;
	static int build(int i, int l, int r) {
		// 1. tree에서 사용할 노드의 번호
		// l, r: 구간의 양쪽 끝
		// i번 노드에다가 [l, r] 구간을 저장
		if(l==r) return tree[i]=arr[l];
		int mid = (l+r)/2;
		return tree[i]=build(i*2, l, mid)+build(i*2+1, mid+1, r);
	}
	static int query(int queryLeft, int queryRight) {
		return query(1, 0, n-1, queryLeft, queryRight);
	}
	static int query(int i, int l, int r, int queryLeft, int queryRight) {
		//1. 만약 구간이 정확히 일치한다면
		if(l==queryLeft&&r==queryRight) return tree[i];
		int mid = l + (r-l)/2;
		
		//2. 구간이 완전히 왼쪽 자식 구간에 있는 경우
		//	[queryLeft, queryRight]가 [l, mid]에 포함
		if(queryRight<=mid) return query(i*2, l, mid, queryLeft, queryRight);
		
		//3. 구해야하는 구간이 완전히 오른쪽 자식에 있는 경우
		// 	[queryLeft, queryRight]가 [mid+1,r]에 포함
		else if(queryLeft>mid) return query(i*2+1, mid+1, r, queryLeft, queryRight);
		
		//4. 구간을 쪼개서 양쪽 자식에게 구해오라고 해야하는 경우
		//	queryLeft <= mid <= queryRight
		else return query(i*2, l, mid, queryLeft, mid) + query(i*2+1, mid+1, r, mid+1, queryRight);
	}
	static void update(int index, int newValue) {
		update(1, 0, n-1, index, newValue);
	}
	static int update(int i, int l, int r, int index, int newValue) {
		if(l==r) return tree[i] = newValue; //l==r==index
		int mid = (l+r)/2;
		if(index<=mid) { //왼쪽 구간 업데이트 해야하는 경우
			tree[i] = update(i*2, l, mid, index, newValue)+tree[i*2+1];
		} else {
			tree[i]= tree[i*2]+update(i*2+1, mid+1, r, index, newValue);
		}
		return tree[i];
	}
	public static void main(String[] args) {
		arr = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		n = arr.length;
		tree = new int[4*n];
		
		//1. build tree : 루트 구간부터 시작해서 ~> 쪼개면서 세그먼트 트리 만들자
		build(1, 0, n-1); //1번 노드, [0, n-1]구간에 대해 트리 만들기 시작
		
		// 원본 데이터 개수가 10개 일때
		
		int sum = query(0, 9); //전체의 합
		System.out.println(sum);
		sum = query(7, 9);
		System.out.println(sum);
		
		update(9, 20); //10을 20으로 변경
		sum = query(0, 9); //전체의 합
		System.out.println(sum);
		sum = query(7, 9);
		System.out.println(sum);
	}
}
