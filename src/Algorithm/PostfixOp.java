package Algorithm;

import java.io.*;
import java.util.*;

public class PostfixOp {
    static int priority(char c) {
        if (c == '+' || c == '-') return 1;
        if (c == '*' || c == '/') return 2;
        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        StringBuilder sb = new StringBuilder();
        Deque<Character> stack = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            if ('A' <= c && c <= 'Z') {
                sb.append(c);
            } 
            else if (c == '(') {
                stack.push(c);
            } 
            else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    sb.append(stack.pop());
                }
                stack.pop(); // '(' 제거
            } 
            else { // 연산자
                while (!stack.isEmpty() && stack.peek() != '('
                        && priority(stack.peek()) >= priority(c)) {
                    sb.append(stack.pop());
                }
                stack.push(c);
            }
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        System.out.print(sb.toString());
    }
}