package code07.Dynamic_Programming;

import java.util.Arrays;
import java.util.Scanner;

public class Efficient_Currency_Mix_answer {

	/* (풀이) 바텀-탑 다이나믹
	 * 
	 * N(1~100)가지 종류의 화폐가 있다. 이 화폐들의 개수를 최소한으로 사용해서 M(1~10000)원을 만들어야한다.
	 * M원을 만들기 위한 최소한의 화폐 개수를 출력하는 프로그램을 작성하라(불가능할 경우 -1 출력)
	 */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 정수 N, M을 입력받기
        int n = sc.nextInt();
        int m = sc.nextInt();

        // N개의 화폐 단위 정보를 입력 받기
        int[] arr = new int[n];
        
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // 앞서 계산된 결과를 저장하기 위한 DP 테이블 초기화 (다이나믹 배열)
        int[] d = new int[m + 1];
        
        Arrays.fill(d, 10001);

        // 다이나믹 프로그래밍(Dynamic Programming) 진행(보텀업)
        d[0] = 0;
        
        for (int i = 0; i < n; i++) {
        	
            for (int j = arr[i]; j <= m; j++) {
            	
                // (i - k)원을 만드는 방법이 존재하는 경우
                if (d[j - arr[i]] != 10001) {
                	
                    d[j] = Math.min(d[j], d[j - arr[i]] + 1);
                    
                }
            }
        }

        // 계산된 결과 출력
        if (d[m] == 10001) { // 최종적으로 M원을 만드는 방법이 없는 경우
            System.out.println(-1);
        }
        else {
            System.out.println(d[m]);
        }
        
        sc.close();
        
    }
    

}
