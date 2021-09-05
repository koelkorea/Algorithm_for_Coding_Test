package code04.DFS_BFS;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/* N*M 크기의 직사각형 미로가 있다. 그리고 미로에는 괴물이 있는데, 이들이 있는 좌표는 피해야 한다.
* 시작 위치는 (1,1)이고 출구는 (N,M)이며, 괴물이 있는 위치는 0으로 없는 위치는 1로 표기한다.
* 이때 탈출하기 위해 움직여야 할 최소 좌표를 구하여라. (시작과 마지막도 포함한다)
*/

// 해답) Node + Queue + 좌표배열 사용(근본적으로는 풀이1과 같다.. 일부를 제외하면)


public class Lets_Escape_Maze_Answer {
	
	// maze를 구성할 세로 N, 가로 M
	static int N;
	static int M;
	
	// maze를 미리 정의(클래스와 메서드를 넘다들며 쓸거라..)
	static int maze[][] = new int [201][201];
	
	// BFS에서 순차적 좌표적용을 위한 이동지정 좌표 지정(매 턴마다 동서남북 4군데를 지정해서 돌도록 할거임)
	static int move_x[] = {0,0,-1,1};
	static int move_y[] = {-1,1,0,0};	
	
	// BFS의 핵심 : 1. queue를 선언하고, 첫번째 변수를 지정한다.   2. 이후 반복문을 통해 queue의 poll(가장 앞의 값 추출후 삭제), offer(해당 값을 가장 뒤에 추가)을 반복하게 한다. while -> for로 하면
	// while -> for의 의미 : for에서 반복을 통해 순차적으로 넣은 루트 정보는 queue의 특성상 순차적으로 출력되어 원하는데로 가로탐색을 하도록 유도한다.
	static int BFS(int x, int y){
		
		// 1. queue 선언 ( < >안에 Node 클래스만 들어가게 함, 이를 통해 x,y를 쉽게 저장 + 추출 가능)
		Queue<Node> q = new LinkedList<Node>();
		
		// 처음에 지정된 x,y를 품은 Node값을 queue에 넣고 시작한다.
		q.offer(new Node(x,y));
		
		// 그 이후, queue가 비어있을때까지 아래의 내용을 반복
		while(!q.isEmpty()) {
			
			// queue의 가장 선두의 값을 뺴내서, Node의 객체에 저장 (이 좌표 정보를 토대로, for문에서 넓이 탐색 좌표를 순차적으로 queue에 저장한다)
			Node node = q.poll();
			
			// Node의 좌표(getter값)을 x,y로 다시 지정(여기를 직접적으로 node.get~으로 지정한게 내 풀이)
			x = node.getX();
			y = node.getY();
						
			// for문을 통해 앞서 입력한 동서남북 4개의 좌표로 넓이 탐색을 통한 좌표들을 queue에 순차적으로 반복해서 넣는다 (그 순서대로 while을 통해 뱉고 수행하다보면, 넓이 탐색 알고리즘 흐름이 만들어짐)
			for(int i = 0; i < 4; i++) {
				
				// 입력된 x,y기준 동서남북의 좌표를 순차적 입력(4번 수행되고, 순차적으로 queue에 입력)
				int new_x = x + move_x[i];
				int new_y = y + move_y[i];
				
				// 1차관문 : maze범위 밖이면 그 이후 순서는 무시하고, 다음 순서를 진행시킨다.
				if(new_x < 0 || new_x >= M || new_y < 0 || new_y >= N) {
					continue;
				}
				
				// 2차관문 : 괴물이 있는 방이면  그 이후 순서는 무시하고, 다음 순서를 진행시킨다.
				if(maze[new_x][new_y] == 0) {
					continue;
				}
				
				// 해당 좌표가 처음 온 곳이다? (이를 통해 다른 비효율적 루트의 접근 배제)
				if(maze[new_x][new_y] == 1) {
					
					// 해당 좌표정보를 queue에 입력
					q.offer(new Node(new_x,new_y));
					
					// 해당 좌표의 값을 이전좌표 + 1로 변경
					maze[new_x][new_y] = maze[x][y] + 1;
					
				}
						
			}
			
		}
		// 최종적으로 더 이상 탐색지점이 없을때(QUEUE가 비게 됨) 목표지점의 값을 출력함(목표값이 1일때만 그 값이 갱신 가능함 = 가장 빨리 접근한 경우의 수만 인정.. 고로 늦은 값이 갱신 X)
		return maze[N-1][M-1];
		
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