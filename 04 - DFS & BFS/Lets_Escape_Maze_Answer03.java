package code04.DFS_BFS;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/* N*M 크기의 직사각형 미로가 있다. 그리고 미로에는 괴물이 있는데, 이들이 있는 좌표는 피해야 한다.
* 시작 위치는 (1,1)이고 출구는 (N,M)이며, 괴물이 있는 위치는 0으로 없는 위치는 1로 표기한다.
* 이때 탈출하기 위해 움직여야 할 최소 좌표를 구하여라. (시작과 마지막도 포함한다)
*/

// 풀이3) Queue사용(손이 좀 많이 간다)


public class Lets_Escape_Maze_Answer03 {
	
	// maze를 구성할 세로 N, 가로 M
	static int N;
	static int M;
	
	// maze를 미리 정의(클래스와 메서드를 넘다들며 쓸거라..)
	static int maze[][] = new int [201][201];
	
	// queue 선언 ( < >안에 정수만 들어가게 함)
	static Queue<Integer> q = new LinkedList<Integer>();
	
	// BFS의 핵심 : 1. queue를 선언하고, 첫번째 변수를 지정한다.   2. 이후 반복문을 통해 queue의 poll(가장 앞의 값 추출후 삭제), offer(해당 값을 가장 뒤에 추가)을 반복하게 한다. while -> for로 하면
	// while -> for의 의미 : for에서 반복을 통해 순차적으로 넣은 루트 정보는 queue의 특성상 순차적으로 출력되어 원하는데로 가로탐색을 하도록 유도한다.
	static int BFS(int x, int y){
		
		// 처음에 지정된 x,y을 순차적으로 queue에 넣고 시작한다. (순차적으로 들어가면, 뺼 때도 그 순서대로 나옴)
		q.offer(x);
		q.offer(y);
		
		// 그 이후, queue가 비어있을때까지 아래의 내용을 반복
		while(!q.isEmpty()) {
			
			// x,y 순서대로 넣었으니 안심하고 순차적으로 뺄걸 기대하자
			x = q.poll();
			y = q.poll();
			
			// 좌표를 쓰지 않아 for는 쓸수 없다. 대신 moving 클래스를 정의해서 해결
			moving(x,y,0,1);
			moving(x,y,0,-1);
			moving(x,y,1,0);
			moving(x,y,-1,0);
			
			
		}
		// 최종적으로 더 이상 탐색지점이 없을때(QUEUE가 비게 됨) 목표지점의 값을 출력함(목표값이 1일때만 그 값이 갱신 가능함 = 가장 빨리 접근한 경우의 수만 인정.. 고로 늦은 값이 갱신 X)
		return maze[N-1][M-1];
		
	}
	
	// 동서남북 이동 매커니즘 가능하게 수정(break, continue를 쓸수 없으니, 조건이 있으면 가급적 다 안으로 밀어넣자.. 예를 들어 음수의 경우 pass하던걸, 정수만 다음 단계로 통과시킨다던지..)
	static void moving(int x, int y, int a, int b){
	
		// 입력된 x,y기준 동서남북의 좌표를 순차적 입력(4번 수행되고, 순차적으로 queue에 입력)
		int new_x = x + a;
		int new_y = y + b;
		
		// 1차관문 : maze범위 밖이면 그 이후 순서는 무시하고, 다음 순서를 진행시킨다.
		if(new_x >= 0 && new_x < M && new_y >= 0 && new_y < N) {
			
			// 2차관문 : 괴물이 있는 방이면  그 이후 순서는 무시하고, 다음 순서를 진행시킨다.
			if(maze[new_x][new_y] == 0) {
				
			}
			
			// 해당 좌표가 처음 온 곳이다? (이를 통해 다른 비효율적 루트의 접근 배제)
			if(maze[new_x][new_y] == 1) {
				
				// 해당 좌표정보 new_x,new_y를 순차적으로 queue에 입력
				q.offer(new_x);
				q.offer(new_y);
				
				// 해당 좌표의 값을 이전좌표 + 1로 변경
				maze[new_x][new_y] = maze[x][y] + 1;
				
			}
			
		}
		

	}
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		// 좌표값 입력
		N = scan.nextInt();
		M = scan.nextInt();
		
		// 미로 생성
		for(int i = 0; i < N; i++) {
			
			String input = scan.next();
			
			for(int j = 0; j < M; j++) {
				
				maze[i][j] = input.charAt(j) - '0';
				
			}
			
		}
		System.out.println(BFS(0,0));
		scan.close();

	}
	
}