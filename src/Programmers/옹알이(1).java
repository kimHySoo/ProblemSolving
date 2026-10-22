class Solution {
    public int 옹알이(String[] babbling) {
        int answer = 0;
        for(String s:babbling){
            char[] c = s.toCharArray();
            int max = c.length;
            boolean[] chk = new boolean[4];
            boolean scs = true;
            for(int i=0;i<max;i++){
                if(!chk[0]&&c[i]=='a'&&i+3<=max&&s.substring(i, i+3).equals("aya")){
                    i+=2;
                } else if(!chk[1]&&c[i]=='y'&&i+2<=max&&s.substring(i, i+2).equals("ye")){
                    i+=1;
                } else if(!chk[2]&&c[i]=='w'&&i+3<=max&&s.substring(i, i+3).equals("woo")){
                    i+=2;
                } else if(!chk[3]&&c[i]=='m'&&i+2<=max&&s.substring(i, i+2).equals("ma")){
                    i+=1;
                } else {
                    scs = false;
                    break;
                }
            }
            if(scs) answer++;
            
        }
        return answer;
    }
}
