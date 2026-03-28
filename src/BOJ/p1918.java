//네 코드는 맞는 방향이고 실제로 통과 가능성이 높지만, 괄호를 연산자 우선순위와 섞어 처리한 점이 조금 아쉽다.

package BOJ;
import java.util.*;
import java.io.*;
public class p1918 {
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringBuilder sb = new StringBuilder();
    	String line = br.readLine();
    	HashMap<Character, Integer> map = new HashMap<>();
    	map.put('+', 1);
    	map.put('-', 1);
    	map.put('*', 2);
    	map.put('/', 2);
    	map.put('(', 3);
    	Deque<Character> stack = new ArrayDeque<Character>();
    	for(char c: line.toCharArray()) {
    		if(map.containsKey(c)) {
    			if(stack.isEmpty()||stack.peek()=='('||map.get(stack.peek())<map.get(c)) stack.push(c);
    			else {
    				while(!stack.isEmpty()&&map.get(stack.peek())>=map.get(c)) {
    					sb.append(stack.pop());
    					if(stack.isEmpty()||stack.peek()=='(') break;
    				}
    				stack.push(c);
    			}
    		}
    		else if(c==')') {
    			while(stack.peek()!='(') sb.append(stack.pop());
    			stack.pop();
    		}
    		else sb.append(c);
    	}
    	while(!stack.isEmpty()) {
    		char c = stack.pop();
    		if(c=='(') continue;
    		sb.append(c);
    	}
    	System.out.print(sb.toString());
    	
    }
}
