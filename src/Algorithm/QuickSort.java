package Algorithm;
import java.util.*;
import java.io.*;

public class QuickSort {
	static int[] arr = { 32, 2, 4, 334, 52, 345, 432, 324, 34, 2534, 64, 756, 245, 34 };
	static int n = arr.length;
	public static void main(String[] args) throws IOException {
		quicksort2(0, n-1);
		System.out.println(Arrays.toString(arr));
	}
	static void quicksort1(int left, int right) {
		if(left>=right) return;
		int pivot = partition1(left, right);
		quicksort1(left, pivot-1);
		quicksort1(pivot+1, right);
	}
	static int partition1(int left, int right) {
		int pivot = arr[left];
		int i = left+1;
		int j = right;
		while(i<=j) {
			while(i<=j&&arr[i]<=pivot) i++;
			while(i<=j&&arr[j]>pivot) j--;
			if(i<j) swap(i, j);
		}
		swap(left, j);
		return j;
	}
	static void swap(int i, int j) {
		int tmp = arr[i];
		arr[i]=arr[j];
		arr[j]=tmp;
	}
	static void quicksort2(int left, int right) {
		if(left>=right) return;
		int pivot = partition2(left, right);
		quicksort2(left, pivot-1);
		quicksort2(pivot+1, right);
	}
	static int partition2(int left, int right) {
		int pivot = arr[right];
		int i = left-1;
		for(int j=left;j<right;j++) {
			if(arr[j]<=pivot) {
				i++;
				swap(i,j);
			}
		}
		swap(i+1, right);
		return i+1;
	}
}