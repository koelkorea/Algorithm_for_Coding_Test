package code07.Dynamic_Programming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Deploying_Soldiers_answer {

	/* (해답) 바텀-탑 방식 : 반복문 사용 
	 * 
	 * N(1~2000)명의 병사가 무작위로 나열되어 있고, 얘들은 각각 전투력을 보유한다.
	 * 배치시 전투력(1~10000000)이 높은 병사가 앞에 오도록 내림차순으로 배치하고자 한다.
	 * 그 과정으로 배치 과정에서 특정한 위치에 있는 병사는 열외시키는 방법으로 단순화하고자 하는데, 
	 * 그러고도 남는 병사가 최대가 되도록 하기 위햇허는 열외시켜야 하는 병사는 몇명인가?
	 * 
	 */
    static int n;
    static ArrayList<Integer> v = new ArrayList<Integer>();
    static int[] dp = new int[2000];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            v.add(sc.nextInt());
        }

        // 순서를 뒤집어 '최장 증가 부분 수열' 문제로 변환 
        // (해답은 오름차순을 통해 문제를 직관화 시켰음.. 다만 그렇게 하지 않고 내림차순 알고리즘도 정답은 나온다)
        //   -> 왜냐하면 다이나믹을 통해 구할건, 각 i에 해당하는 위치를 오름차순의 경우 최대값, 내림차순의 경우 최저값으로 두었을때 열외를 제외하고 성립되는 배열의 길이이기 때문
        Collections.reverse(v);

        // 다이나믹 프로그래밍을 위한 1차원 DP 테이블 초기화
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
        }

        // 가장 긴 증가하는 부분 수열(LIS) 알고리즘 수행
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (v.get(j) < v.get(i)) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        // 열외해야 하는 병사의 최소 수를 출력
        int maxValue = 0;
        for (int i = 0; i < n; i++) {
            maxValue = Math.max(maxValue, dp[i]);
        }
        
        System.out.println(Arrays.toString(dp));
        
        System.out.println(n - maxValue);
        
        sc.close();
    }

}
