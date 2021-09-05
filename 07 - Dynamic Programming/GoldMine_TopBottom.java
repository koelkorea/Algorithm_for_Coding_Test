package code07.Dynamic_Programming;

import java.util.Arrays;
import java.util.Scanner;

public class GoldMine_TopBottom {

	/* (풀이2) 탑-바텀 다이나믹 : 재귀
	 * 
	 * N(1~)*M(1~20) 크기의 금광이 T개(1~1000)가 있다. 금광은 1*1로 구분 되고 각 칸은 특정 크기의 금(1~100)이 있다.
	 * 채굴자는 첫번째 열부터 시작해서 끝열까지 M-1번 이동가능하고, 각 단계마다 현재위치를 기준으로 위 - 현재방향 -아래 3개중 하나의 입구를 고를수 있다.
	 * 이때 채굴자가 T개의 금광에서 각각 채굴 가능한 최대치의 금의 양을 구하는 알고리즘 작성해라
	 */
	
	static int mine[][] = new int [20][20];
	static int dynamic[][] = new int [20][20];
	
	static int recursion(int N, int M) {
		
		// 현재 위치에서부터 10시방향, 9시방향, 7시 방향의 금광위치의 금량 기록용
		int ten_Direction, nine_Direction, seven_Direction;
		
		if(M==0) {
			
			return dynamic[N][0];
			
		}else {
		
			// 현재위치 기준 10시 방향에 위치한 금광의 금량
			if(N != 0) {	
				
				ten_Direction = recursion(N-1, M-1);
				
			}else {
				
				ten_Direction = 0;	 // 행좌표가 -1로 되는걸 방지하기
				
			}
			
			// 현재위치 기준 9시 방향에 위치한 금광의 금량
			nine_Direction = recursion(N, M-1);
			
			// 현재위치 기준 7시 방향에 위치한 금광의 금량
			if(N < 19) {
				
				seven_Direction = recursion(N+1, M-1);
			
			}else {
				
				seven_Direction = 0;	 // 행좌표가 19넘는걸 방지하기
				
			}
			
			return dynamic[N][M] = dynamic[N][M] + Math.max(ten_Direction, Math.max(nine_Direction, seven_Direction));
		}
		
		
		
		
	}
	
	
	
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		
		int T = scan.nextInt();
		
		int N = scan.nextInt();
		int M = scan.nextInt();
		

		for(int x = 0; x < T; x++) {
		
			for(int i = 0; i < N; i++) {
				
				for(int j = 0; j < M; j++) {
					
					mine[i][j] = scan.nextInt();
					
					dynamic[i][j] = mine[i][j];
					
				}
				
			}
			
			int answer = 0;
			
			for(int i = 0; i < N; i++) {
				
				// System.out.println(dynamic[i][M-1]);
				
				answer = Math.max(answer, recursion(i, M-1));
				
			}
			
			System.out.println(Arrays.deepToString(mine));
			System.out.println(Arrays.deepToString(dynamic));
			System.out.println(answer);
			
			
		}
		

		scan.close();
	}

}
