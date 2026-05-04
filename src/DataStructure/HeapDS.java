import java.io.*;
import java.util.*;

public class HeapDS {
	
	static class Heap{
		int[] arr;
		int size;
		
		Heap() {
			arr = new int[100001];
			size = 0;
		}
		
		void add(int x) {
			size++;
			arr[size] = x;
			int idx = size;
			while(idx>1 && arr[idx/2]<arr[idx]) {
				swap(idx, idx/2);
				idx/=2;
			}
		}
		
		int remove() {
			if(size==0) return -1;
			int ans = arr[1];
			arr[1]=arr[size];
			arr[size--]=0;
			heapify(1);
			return ans;
		}
	}
	
	static void heapify(int x) {
		int max = x;
		int left = x*2;
		int right = x*2+1;
		
		if(left<=heap.size&&heap.arr[left]>heap.arr[max]) max = left;
		if(right<=heap.size&&heap.arr[right]>heap.arr[max]) max = right;
		
		if(max!=x) {
			swap(max, x);
			heapify(max);
		}
	}
	
	static void swap(int x, int y) {
		int tmp = heap.arr[x];
		heap.arr[x] = heap.arr[y];
		heap.arr[y] = tmp;
	}
	
	static Heap heap;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int test = Integer.parseInt(br.readLine());
	
		for(int t = 1;t<=test;t++) {
			sb.append('#').append(t);
			heap = new Heap();
			int n = Integer.parseInt(br.readLine());
			
			for(int i=0;i<n;i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				if(x==2) sb.append(' ').append(heap.remove());
				else {
					int y = Integer.parseInt(st.nextToken());
					heap.add(y);
				}
			}
			
			
			sb.append('\n');
		}
		System.out.print(sb.toString());
		
		
	}
}
