package Algorithm;

public class Dice {
	static int[] df = {6, 5, 1, 2};
	static int[] ds = {6, 3, 1, 4};
    static int[] dice = {1, 6, 2, 5, 3, 4};
    static void roll1(int dir) {
    	//gpt풀이
        int top = dice[0];
        int bottom = dice[1];
        int north = dice[2];
        int south = dice[3];
        int east = dice[4];
        int west = dice[5];

        if (dir == 0) { // 동
            dice[0] = west;
            dice[1] = east;
            dice[4] = top;
            dice[5] = bottom;
        } else if (dir == 1) { // 남
            dice[0] = north;
            dice[1] = south;
            dice[2] = bottom;
            dice[3] = top;
        } else if (dir == 2) { // 서
            dice[0] = east;
            dice[1] = west;
            dice[4] = bottom;
            dice[5] = top;
        } else { // 북
            dice[0] = south;
            dice[1] = north;
            dice[2] = top;
            dice[3] = bottom;
        }
    }
	static void roll(int x) {
		//내 아이디어
		if(x==0) {
			int tmp = ds[0];
			for(int i=0;i<3;i++) ds[i]=ds[i+1];
			ds[3]=tmp;
			df[0]=ds[0];
			df[2]=ds[2];
		}
		if(x==2) {
			int tmp = ds[3];
			for(int i=3;i>0;i--) ds[i]=ds[i-1];
			ds[0]=tmp;
			df[0]=ds[0];
			df[2]=ds[2];
		}
		if(x==3) {
			int tmp = df[3];
			for(int i=3;i>0;i--) df[i]=df[i-1];
			df[0]=tmp;
			ds[0]=df[0];
			ds[2]=df[2];
		}
		if(x==1) {
			int tmp = df[0];
			for(int i=0;i<3;i++) df[i]=df[i+1];
			df[3]=tmp;
			ds[0]=df[0];
			ds[2]=df[2];
		}
	}
}
