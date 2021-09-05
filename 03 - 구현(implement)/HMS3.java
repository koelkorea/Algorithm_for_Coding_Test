package code03.implement;

import java.util.Scanner;

public class HMS3 {
	
	public static void main(String[] args) {
		
		/* 정수 N이 입력되면 00시 00분 00초부터 N시 59분 59초까지의 모든 시각 중에서, 
		 * 3이 하나라도 포함되는 모든 경우의 수를 구하는 프로그램을 작성하세요.
		 */
		
		Scanner scan = new Scanner(System.in);
		
		int N = scan.nextInt();
		int answer = 0;
	
		for(int i = 0; i < N+1; i++) {
			
			for(int j = 0; j < 60; j++) {
			
				for(int z = 0; z < 60; z++) {
					
					if(i/10 == 3 || i%10 == 3|| j/10 == 3 || j%10 == 3 || z/10 == 3 || z%10 == 3) {
						
						answer++;
						
					}
					
				}
				
			}
			
		}
		
		System.out.println(answer);
		
		scan.close();
		
		
	}

}
