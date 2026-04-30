import java.util.*;
class 스포방지 {
    public int solution(String message, int[][] spoiler_ranges) {
        int answer = 0;
        
        HashMap<String, Integer> map = new HashMap<>();
    		
		String[] dict = message.split(" ");
		
		int word = 0;
		int idx = 0;
		boolean chk = false;
		
		for(int i=0;i<message.length();i++){
			char c = message.charAt(i);
			while(spoiler_ranges.length>idx+1&&spoiler_ranges[idx][1]<i) idx++;
			if(!chk) {
				chk = true;
				int end = i+dict[word].length()-1;
				if(spoiler_ranges[idx][0]<=end&&spoiler_ranges[idx][1]>=i) {
					if(!map.containsKey(dict[word])) map.put(dict[word], 1);
				}
				else map.put(dict[word], 0);
			}
			if(c==' ') {
				word++;
				chk = false;
			}
		}
		
		for(String s:map.keySet()) answer+=map.get(s);
        return answer;

    }
}
