public class Main {
	public static void main(String[] args) {
		int[][] triangle  = {{7},{3,8},{8,1,0},{2,7,4,4},{4,5,2,6,5}};
		solution(triangle);
	}

    static int solution(int[][] triangle) {
        int answer = 0;
        
        int n = triangle.length;
        for(int i=1;i<n;i++) {
        	int m = triangle[i].length;
        	for(int j=0;j<m;j++) {
        		int cur = triangle[i][j];
        		if(j!=m-1) triangle[i][j] += triangle[i-1][j];
        		if(j!=0) triangle[i][j] = Math.max(cur+triangle[i-1][j-1], triangle[i][j]);
        	}
        }
        
        for(int i=0;i<triangle[n-1].length;i++) answer = Math.max(answer, triangle[n-1][i]);
        return answer;
    }
   
}
