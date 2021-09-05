package code03.implement;

import java.util.Scanner;

public class ChessKnight {

	public static void main(String[] args) {
		
		/*
		 * 8*8 좌표가 있고, 체스의 나이트의 위치는 그 안에서 랜덤 위치가 문자열(소문자+숫자)으로 주어진다고 쳤을때, 이동 가능한 경우의 수를 출력하시오
		 * x축은 a~h고 y축은 1~8이다. 일반 좌표상의 1,1은 a1으로 표기한다.
		 */
		
		Scanner scan = new Scanner(System.in);
		
		// 좌표 입력값 받기
		String input = scan.next();
		
		// 좌표 형변환 후 기록용
		int random [] = new int [2];
		
		// 정답용
		int answer = 0;
		
		// 좌표값 환산
		// x
		random[0] = (int) input.charAt(1) - 48;
		// y
		random[1] = (int) input.charAt(0) - 96;

		// 나이트가 움직일 경우의 수에 해당하는 좌표 (x1,y1)의 경우 ~ (x8,y8)
		int x[] = {-2, -2, 1, -1,  2, 2,  1, -1};
		int y[] = {-1,  1, 2,  2, -1, 1, -2, -2};
		
		// 반복문으로 경우의 수를 소화하는지 본다
		for(int i = 0; i < 8; i++) {
			
			// x,y가 1~8까지 안에서 움직이는지 조건문을 결과 설정
			if (random[0] + x[i] > 0 && random[0] + x[i] <= 8 && random[1] + y[i] > 0 && random[1] + y[i] <= 8){
				
				answer++;
				
			}
			
		}
		
		System.out.println(answer);
		
		scan.close();
	}
	
}
