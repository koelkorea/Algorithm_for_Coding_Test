package code07.Dynamic_Programming;

import java.util.Scanner;

public class Make1_answer {

	/* (해답) 바텀-탑 다이나믹 방식(반복)
	 * 
	 * 정수 x가 주어졌을때, 4가지 연산을 할 수 있다.
	 * 1) 5나 3이나 2로 나눠지면 5부터 우선순위를 둬서 나눈다.
	 * 2) 1을 뺀다
	 * 
	 * 목적은 x(1~30000)를 1로 만드는 것인데, 그 과정에서 연산을 사용화는 횟수의 최소값을 구하라
	 */


    // 앞서 계산된 결과를 저장하기 위한 DP 테이블 초기화 (개별 x값마다의 최소 연산횟수 저장)
    public static int[] d = new int[30001];

    public static void main(String[] args) {
    	
        Scanner sc = new Scanner(System.in);

        int x = sc.nextInt();

        // 다이나믹 프로그래밍(Dynamic Programming) 진행(2부터 시작하는 이유 : x==1인 경우 목표가 1을 구하는거라 0이 맞기 때문)
        for (int i = 2; i <= x; i++) {
        	
            // [1단계] 현재의 수에서 1을 빼는 경우
        	// (i를 1로 만드는 연산 최소값 d[i]는 i-1을 1로 만드는 연산 최소값에 1을 더한 값으로 최초 설정)
            d[i] = d[i - 1] + 1;
            
            // [2단계] 현재의 수가 2로 나누어 떨어지는 경우
            // -> 이전의 d[i]값과 비교했을때 d[i / 2] + 1이 작으면 그걸로 d[i]값 갱신
            if (i % 2 == 0)
            	
                d[i] = Math.min(d[i], d[i / 2] + 1);
            
            // [3단계] 현재의 수가 3으로 나누어 떨어지는 경우
            if (i % 3 == 0)
            	
                d[i] = Math.min(d[i], d[i / 3] + 1);
            
            // [4단계] 현재의 수가 5로 나누어 떨어지는 경우
            // -> 이전의 d[i]값과 비교했을때 d[i / 5] + 1이 작으면 그걸로 d[i]값 갱신(5는 있을지도 없을지도 몰라서 이렇게 둔 듯)
            if (i % 5 == 0)
            	
                d[i] = Math.min(d[i], d[i / 5] + 1);
        }

        System.out.println(d[x]);
        
        sc.close();
    }

}
