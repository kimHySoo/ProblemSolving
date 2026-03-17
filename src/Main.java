import java.io.*;
import java.util.*;

public class Main {
public static void main(String[] args) throws IOException{
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	StringTokenizer st = new StringTokenizer(br.readLine());
	int n = Integer.parseInt(st.nextToken());
	int k = Integer.parseInt(st.nextToken());
	List<int[]> jewel = new ArrayList<>(); 
	Queue<Integer> bag = new PriorityQueue<>();
	for(int i=0;i<n;i++) {
		st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		jewel.add(new int[] {x,y});
	}
	Collections.sort(jewel, (a,b)->{
		if(a[1]!=b[1]) return Integer.compare(b[1], a[1]);
		else return Integer.compare(a[0], b[0]);
	});
	for(int i=0;i<k;i++) bag.add(Integer.parseInt(br.readLine()));
	int ans = 0;
	while(!bag.isEmpty()) {
		int c = bag.poll();
		int x = jewel.size();
		for(int i=0;i<x;i++) {
			if(jewel.get(i)[0]<=c) {
				ans+=jewel.get(i)[1];
				jewel.remove(i);
				break;
			}
		}
	}
	System.out.println(ans);
	}
}
