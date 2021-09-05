package code07.Dynamic_Programming;

import java.util.Arrays;
import java.util.Scanner;

public class Ant_Warrior_Raid_TopBottom {

	/* (풀이1) 탑-다운 다이나믹 방식(재귀)
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
	
	// data[x-1]을 구하는 재귀를 위한 메서드 짜기(x-1번째 다이나믹 배열 data[x-1]에 들어갈 값을 구하는 메서드.. 이를 쓰는 이유는 다이나믹 배열을 채우기 위함..)
	//											-> 그냥 다이나믹 배열을 호출하면, 초기값인 0이 호출되니 의도와는 다른 값이 나옴
	static int maxPlan(int x, int input[]){
		
		// [1번째 관문]
		// x값이 1이면, 다이나믹 배열에 입력 배열 1번째 값 투입
		if(x == 1) {
			
			data[0] = input[0];
			
			return data[0];
		
		// x값이 2이면, 
		}else if(x == 2) {
			
			// 조건부(입력배열 2번째 값이 1번째 값보다 크다면) 다이나믹 배열에 입력 배열 1번째 값 투입	
			if(input[1] > input[0]) {
				
				data[1] = input[1];
			
			// 조건부(입력배열 2번째 값이 1번째 값보다 작거나 같다면) 다이나믹 배열에 입력 배열 1번째 값 투입	
			}else {
				
				data[1] = input[0];
				
			}
			
			return data[1];
			
		}
		
		// [2번째 관문] 다이나믹 배열값이 0이 아니라면, 그 값을 출력(이미 계산된 값은 계산된 녀석으로.. 이렇게 기록하여 시간 절약)
		if(data[x-1] != 0) {
			
			return data[x-1];
			
		}
		
		// [3번째 관문] 다이나믹 배열 [x-1]에 들아가는 값을 결정
		// 입력 배열 [x-1] + [x-3]까지의 식량 최대값이 [x-2]위치까지의 식량 최대값보다 크거나 작을경우..
		// (다이나믹 배열에 값이 없음 = 재귀를 통해 구해준다 -> 재귀를 조건문에 포함한다)
		if(input[x-1] + maxPlan(x-2, input) > maxPlan(x-1, input) ){
			
			// [x-1]까지의 식량 최대값 = 입력 배열[x-1] + 다이나믹 배열[x-3] (위의 조건문을 통해 이미 배열이 채워짐)
			data[x-1] = input[x-1] + data[x-3];
			
		}else {
			
			// 그게 아니면 이전의 배열 결과값인 data[x-2] = data[x-1] 처리
			data[x-1] = data[x-2];
			
		}
		
		return data[x-1];
		
		
	}

	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		int N = scan.nextInt();
		
		for(int i = 0; i < N; i++) {
			
			input[i] = scan.nextInt();
			
		}
		
		
		
		System.out.println(maxPlan(N, input));
		
		System.out.println(Arrays.toString(data));
		
		scan.close();

	}

}
