package Algorithm;

import java.util.Arrays;

public class MergeSort {
	static int[] arr = { 1, 3, 34, 23, 4, 34, 6, 35, 57, 56, 7, 45, 6, 4 };
	static int n = arr.length;
	static int[] tmp = new int[n];
	public static void main(String[] args) {
		sort(0, n-1);
		System.out.println(Arrays.toString(arr));
	}
	static void sort(int left, int right) {
		if(left>=right) return;
		int mid = (left+right)/2;
		sort(left, mid);
		sort(mid+1,right);
		merge(left, mid, right);
	}
	static void merge(int left, int mid, int right) {
		int i = left;
		int j = mid+1;
		int idx = left;
		while(i<=mid&&j<=right) {
			if(arr[i]<=arr[j]) tmp[idx++]=arr[i++];
			else tmp[idx++]=arr[j++];
		}
		while(i<=mid) tmp[idx++]=arr[i++];
		while(j<=right) tmp[idx++]=arr[j++];
		for(int k=left;k<=right;k++) arr[k]=tmp[k];
	}
}
