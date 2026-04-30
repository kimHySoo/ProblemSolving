
public class 숫자짝궁 {
	static String X="5525";
	static String Y="1255";
	public static void main(String[] args) {
		int[] count = new int[10];
		int[] value = new int[10];
		for(char c:X.toCharArray()) {
			count[c-'0']++;
		}
		for(char c:Y.toCharArray()) {
			if(count[c-'0']-->0) value[c-'0']++;
		}
		
		StringBuilder sb = new StringBuilder();
		
        for(int i=9;i>=1;i--) while(value[i]-->0) sb.append(i);
        
        if(sb.isEmpty()) sb.append(0);
        else while(value[0]-->0) sb.append(0);
		
		int ans = Integer.parseInt(sb.toString());
        
        String answer = ans+"";
		System.out.println(Integer.parseInt(sb.toString()));
	}
}
