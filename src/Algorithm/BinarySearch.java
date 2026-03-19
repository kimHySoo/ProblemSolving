package Algorithm;

import java.io.*;
import java.util.*;

public class BinarySearch {
	static List<Integer> lis;
	static int[] arr = { 1, 2, 3, 4, 5, 6, 6, 7, 8 };

	public static void main(String[] args) throws IOException {

	}

	static int binary_lis(int left, int right, int key) {
		if (left > right)
			return left;
		int mid = (left + right) / 2;
		if (lis.get(mid) == key)
			return mid;
		else if (lis.get(mid) > key)
			return binary_lis(left, mid - 1, key);
		else
			return binary_lis(mid + 1, right, key);
	}
	static int binary_lower(int key) {
	    int left = 0, right = lis.size();
	    while (left < right) {
	        int mid = (left + right) / 2;
	        if (lis.get(mid) >= key) right = mid;
	        else left = mid + 1;
	    }
	    return left;
	}
	static int binary(int left, int right, int key) {
		if(left>right) return -1;
		int mid = (left+right)/2;
		if(arr[mid]==key) return mid;
		else if(arr[mid]>key) return binary(left, mid-1, key);
		else return binary(mid+1,right, key);
	}
	
}