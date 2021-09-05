package code02.greedy;

import java.util.Scanner;

public class LRUD03 {


	
	public static void main(String[] args) {
		
		/* A는 N*N 크기의 정사각형 공간 위에 서 있다. 가장 왼쪽 위 좌표는 (1,1), 가장 오른쪽은 (N,N)이고
		 * 시작좌표는 (1,1)이고, A는 한번에  상,하,좌,우 한 방향으로만 이동할 수 있다. 
		 * N만큼 이동계획서가 있다고 가정한다면, 계획후의 좌표를 나타내는 알고리즘을 구해라.
		 * (단 좌표가 0 이하로 벗어나는 이동지점을 나타내는 계획은 무효처리하고 다른 계획으로 빈 계획을 채운다.)
		 */

		// 1. 해당 문제의 계획은 사전에 미리 String으로 나온다고 가정.. (계획의 총 숫자는 사전에 정해져 있음을 가정 = 길이가 정해져있다)
		Scanner scan = new Scanner(System.in);
		
		// 반복될 계획의 수와 가로세로 좌표의 최대치
		int N = scan.nextInt();
		
		// x,y 의 초기좌표
		int start[] = {1 , 1};
		
		// 계획
		String input = scan.next();
		
		// 예외는 횟수로 안 세고, N번을 채울때까지 계획을 반복실행
		for(int i = 0; i < input.length(); i++  ) {
			
			// switch (input.substring(i, i+1))
			switch (input.charAt(i)) {
			
				// 왼쪽 이동(X좌표 -1..)
				case 'L': 
					
					if((start[0] > 1) && (start[0] <= N)){ 
						
						start[0]--;
						
					}
					
					break;	// 매 입력마다 조건(바깥으로 나가면)이 안 맞으면, 횟수 올라가지 않고 좌표이동도 없음
				
				// 오른 이동(X좌표 +1..)
				case 'R': 
					
					if(start[0] < N){ 
					
						start[0]++; 
						
					}
					
					break;
				
				// 위로 이동(X좌표 +1..)
				case 'U': 
					
					if((start[1] > 1) && (start[1] <= N)) { 
						
						start[1]--;
						
					}
				
					break;
					
				// 아래 이동(X좌표 +1..)	
				case 'D': 
					
					if(start[1] < N){ 
						
						start[1]++; 
						
					}
					
					break;	
				
			}
			
		}	
		
		System.out.println(start[1] + " " + start[0]);
		scan.close();
	}
}
