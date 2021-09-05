package code07.Dynamic_Programming;

import java.util.Scanner;

public class Ant_Warrior_Raid_BottomTop {

	/* (풀이2) 다운-탑 다이나믹 방식(반복문)
	 * 
	 * 개미전사들은 이웃 개미나라에 N개의 식량창고가 있는 곳을 알아냈다.
	 * 해당 식량창고들은 일직선으로 연결된 위치에 존재하며, 
	 * 기습을 위해서는 최소한 털어먹은 창고의 이웃창고는 공격할 수 없다는 원칙을 세웠다.
	 * N(0 <= N <= 100)과 각 식량 창고의 식량 양 K(개당 최대 1000) 가 입력될 경우 이들이 최대치로 얻을수 있는 식량의 양을 구하는 알고리즘을 짜라
	 */
	
	// 다이나믹 프로그래밍을 위한 배열 (해당 배열 값은 n번쨰 까지의 창고를 기준으로 약탈할 경우 얻는 식량의 최대값)
	static int data[] = new int [100];
	
	 // input값을 받기 위한 배열
	static int input[] = new int [100];
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		// 식량정보를 입력배열 N-1까지 채우기
		int N = scan.nextInt();
		
		for(int i = 0; i < N; i++) {
			
			input[i] = scan.nextInt();
			
		}
		
		// 다이나믹 배열 1번째 값을 입력(입력배열 1번쨰 값으
		data[0] = input[0];
		
		
		// 조건문을 통해 다이나믹 배열 2번째 값을 채움
		if(input[1] >= input[0]) {
			
			// 입력배열 2번쨰의 식량값이 1번쨰 값보다 크거나 같으면..
			//	-> 2번쨰 창고까지의 입수 최대량 = 입력 2번째 값
			data[1] = input[1];
			
		}else if(input[1] < input[0]) {
			
			// 입력배열 2번쨰의 식량값이 1번쨰 값보다 작으면..
			//	-> 2번쨰 창고까지의 입수 최대량 = 입력 1번째 값
			data[1] = input[0];
		}
		
		// 다이나믹 프로그래밍 진행(Bottom-up : 반복문 사용)
		for(int i = 2; i < N; i++) {
			
			// 나는 조건문으로 문제 해결
			if(input[i] + data[i-2] >= data[i-1] ){
				
				data[i] = input[i] + data[i-2];
				
			}else {
				
				data[i] = data[i-1];
				
			}
			
		}
		
		
		System.out.println(data[N-1]);
		
		scan.close();

	}

}
