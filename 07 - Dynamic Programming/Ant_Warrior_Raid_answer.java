package code07.Dynamic_Programming;

import java.util.Scanner;

public class Ant_Warrior_Raid_answer {

	/* (해답) 다운-탑 다이나믹 방식(재귀)
	 * 
	 * 개미전사들은 이웃 개미나라에 N개의 식량창고가 있는 곳을 알아냈다.
	 * 해당 식량창고들은 일직선으로 연결된 위치에 존재하며, 
	 * 기습을 위해서는 최소한 털어먹은 창고의 이웃창고는 공격할 수 없다는 원칙을 세웠다.
	 * N(0 <= N <= 100)과 각 식량 창고의 식량 양 K(개당 최대 1000) 가 입력될 경우 이들이 최대치로 얻을수 있는 식량의 양을 구하는 알고리즘을 짜라
	 */
	
    // 앞서 계산된 결과를 저장하기 위한 DP 테이블 초기화 
    public static int[] d = new int[100];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 정수 N을 입력받기
        int n = sc.nextInt();

        // 모든 식량 정보 입력받기
        int[] arr = new int[n];
        
        for (int i = 0; i < n; i++) {
        	
            arr[i] = sc.nextInt();
            
        }
        
        // 다이나믹 프로그래밍(Dynamic Programming) 진행
        //	-> 해답은 Math 클래스의 MAX메서드를 통해, 복잡하게 이를 조건문으로 넣지 않고 쉽게 진행
        d[0] = arr[0];
        
        d[1] = Math.max(arr[0], arr[1]);
        
        for (int i = 2; i < n; i++) {
            d[i] = Math.max(d[i - 1], d[i - 2] + arr[i]);
        }

        // 계산된 결과 출력
        System.out.println(d[n - 1]);
        
        sc.close();
    }

}
