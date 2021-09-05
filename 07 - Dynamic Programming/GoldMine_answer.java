package code07.Dynamic_Programming;

import java.util.Scanner;

public class GoldMine_answer {

	/* 
	 * N(1~)*M(1~20) 크기의 금광이 T개(1~1000)가 있다. 금광은 1*1로 구분 되고 각 칸은 특정 크기의 금(1~100)이 있다.
	 * 채굴자는 첫번째 열부터 시작해서 끝열까지 M-1번 이동가능하고, 각 단계마다 현재위치를 기준으로 위 - 현재방향 -아래 3개중 하나의 입구를 고를수 있다.
	 * 이때 채굴자가 T개의 금광에서 각각 채굴 가능한 최대치의 금의 양을 구하는 알고리즘 작성해라
	 */
    static int testCase, n, m;
    static int[][] arr = new int[20][20];
    static int[][] dp = new int[20][20];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 테스트 케이스(Test Case) 입력
        testCase = sc.nextInt();
        
        for (int tc = 0; tc < testCase; tc++) {
        	
            // 금광 정보 입력
            n = sc.nextInt();
            m = sc.nextInt();
            
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    arr[i][j] = sc.nextInt();
                }
            }
            
            // 다이나믹 프로그래밍을 위한 2차원 DP 테이블 초기화
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    dp[i][j] = arr[i][j];
                }
            }
            
            // 다이나믹 프로그래밍 진행
            for (int j = 1; j < m; j++) {
            	
                for (int i = 0; i < n; i++) {
                	
                    int leftUp, leftDown, left;
                    
                    // 왼쪽 위에서 오는 경우
                    if (i == 0) {
                    	
                    	leftUp = 0;
                    	
                    }else {
                    	
                    	leftUp = dp[i - 1][j - 1];
                    }
                    
                    // 왼쪽 아래에서 오는 경우
                    if (i == n - 1) {
                    	
                    	leftDown = 0;
                    	
                    }else {
                    	
                    	leftDown = dp[i + 1][j - 1];
                    }
                    
                    // 왼쪽에서 오는 경우
                    left = dp[i][j - 1];
                    
                    dp[i][j] = dp[i][j] + Math.max(leftUp, Math.max(leftDown, left));
                }
            }
            
            int result = 0;
            
            for (int i = 0; i < n; i++) {
            	
                result = Math.max(result, dp[i][m - 1]);
                
            }
            System.out.println(result);
        }
        
        sc.close();
    }
}
