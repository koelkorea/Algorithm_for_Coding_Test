package code04.DFS_BFS;

import java.util.Scanner;

public class Num_Of_Drink_Ice_Answer {
	
	public static int N;
	public static int M;
	
	static int[][] visited = new int[1000][1000]; 
	static int[][] input = new int[1000][1000];
	
	public static void main(String[] args) {
		
		/* M*N 크기의 얼음틀이 있고, 이를 좌표로 표현할때 구멍이 있으면 0, 없는 칸막이 부분은 1이다.
		 * 구멍이 뚫린 애들이 붙어있으면 그건 연결된 하나의 얼음으로 가정한다면, 생성되는 얼음을 총 갯수를 구하는 알고리즘을 짜라.
		 */

		Scanner scan = new Scanner(System.in);
		
		N = scan.nextInt();
		M = scan.nextInt();
		
		// 정답 출력용
		int answer = 0;
		
		// 얼음판 정보 입력
		for(int i = 0; i < N; i++) {
			
			// 1줄 연속 입력을 담보하는 string 입력(숫자로 넣으면 분해가 많이 귀찮다)
			String str = scan.next();
			
			for(int j = 0; j < M; j++) {
			
				input[i][j] = str.charAt(j) - '0';
			
			}
		}
		
		// 입력된 얼음판 정보를 토대로 알고리즘 수행
		for(int i = 0; i < N; i++) {
			
			for(int j = 0; j < M; j++) {
			
				// 한 구역의 아이스크림 판별이 끝나면 연결된 얼음의 수를 하나 늘린다.
				if(DFS(i, j) == true) {
					
					answer++;
				}
			
			}
		}
		
		System.out.println(answer);
		scan.close();
	}

	static boolean DFS(int x, int y){
		
		if(x < 0 || x >= N || y < 0 || y >= M ){
			
			return false;
		}
		
		// 방문하지 않았을 경우에만 반응 한정
		if(visited[x][y] == 0 ) {
			
			// 방문으로 변경
			visited[x][y] = 1;
			
			// 그중 메트릭스의 값이 0인 경우에는 순차적으로 상,하,좌,우 좌표를 재귀로 불러들임
			if(input[x][y] == 0) {
			
				DFS( x-1,  y);
				DFS( x+1,  y);
				DFS( x,  y-1);
				DFS( x,  y+1);
				
				// DFS 코스를 전부 통과하면, true
				return true;
			
			}
		}
		
		return false;
		
	}
	
}
