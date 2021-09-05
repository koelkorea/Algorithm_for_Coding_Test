package code02.greedy;

import java.util.Scanner;

public class LRUD01 {


	
	public static void main(String[] args) {
		
		/* A는 N*N 크기의 정사각형 공간 위에 서 있다. 가장 왼쪽 위 좌표는 (1,1), 가장 오른쪽은 (N,N)이고
		 * 시작좌표는 (1,1)이고, A는 한번에  상,하,좌,우 한 방향으로만 이동할 수 있다. 
		 * N만큼 이동계획서가 있다고 가정한다면, 계획후의 좌표를 나타내는 알고리즘을 구해라.
		 * (단 좌표가 0 이하로 벗어나는 이동지점을 나타내는 계획은 무효처리하고 다른 계획으로 빈 계획을 채운다.)
		 */

		// 1. 해당 문제의 계획은 N만큼 나온다고 가정할 경우.. (계획의 총 숫자는 사전에 정해져 있음을 가정
		Scanner scan = new Scanner(System.in);
		
		// 반복될 계획의 수와 가로세로 좌표의 최대치
		int N = scan.nextInt();
		
		// x의 초기좌표
		int x = 1;
		
		// y의 초기좌표
		int y = 1;
		
		// 반복횟수 세는 용도
		int count = 0;
		
		// 예외는 횟수로 안 세고, N번을 채울때까지 계획을 반복실행
		while(count != N) {
			
			// 매 횟수마다 입력 받기
			String input = scan.next();
			
			switch (input) {
			
				// 왼쪽 이동(X좌표 -1..)
				case "L": 
					
					if((x > 1) && (x <= N)){ 
						
						x--;
						count++;
						
					}
					
					break;	// 매 입력마다 조건(바깥으로 나가면)이 안 맞으면, 횟수 올라가지 않고 좌표이동도 없음
				
				// 오른 이동(X좌표 +1..)
				case "R": 
					
					
					if(x < N){ 
					
						x++; 
						count++;
						
					}
					
					break;
				
				// 위로 이동(X좌표 +1..)
				case "U": 
					
					if((y > 1) && (y <= N)) { 
						
						y--;
						count++;
						
					}
				
					break;
					
				// 아래 이동(X좌표 +1..)	
				case "D": 
					
					if(y < N){ 
						
						y++; 
						count++;
						
					}
					
					break;	
	
			}
			
		}
		
		System.out.println(y + " " + x);
		scan.close();
	}

}
