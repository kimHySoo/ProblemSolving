import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException{
		long sum = 0;
		int x = Integer.MAX_VALUE;
		for(int i=0;i<3;i++) sum+=x;
		System.out.println(sum);
	}
}
