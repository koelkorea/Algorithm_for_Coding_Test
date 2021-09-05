package code10.Others;

import java.util.Arrays;

/* 에라토스테네스의 체 : 1~주어진x 구간에 소수가 몇개인지 판별하는 알고리즘(다수의 소수찾기에 유리)
 * (시간복잡도 : NloglogN, BUT 공간복잡도는 N이 커지는 만큼 커진다.)
 * 
 * 1. 2~N까지의 모든 자연수 나열
 * 2. 남은 수 중에서 아직 처리하지 않은 가장 작은 수 i(그 다음으로 작은 소수)를 찾는다.
 * 3. 남은 수 중에서 i의 배수를 모두 제거
 * 4. 마지막까지 2~3을 반복한다.
 */
public class Example_Sieve_of_Eratosthenes {
	
    public static int n = 1000; // 2부터 1,000까지의 모든 수에 대하여 소수 판별
    public static boolean[] arr = new boolean[n + 1];

    public static void main(String[] args) {
    	
        Arrays.fill(arr, true); // 처음엔 모든 수가 소수(True)인 것으로 초기화(0과 1은 제외)
        
        // 에라토스테네스의 체 알고리즘 수행
        // 2부터 n의 제곱근까지의 모든 수를 확인하며(N의 약수는 제곱근 이상의 약수는 존재X)
        for (int i = 2; i <= Math.sqrt(n); i++) {
        	
            // i가 소수인 경우(남은 수인 경우)
            if (arr[i] == true) {
            	
                // i를 제외한 i의 모든 배수를 지우기
                int j = 2;
                
                // 소수 * j가 n보다 커질때까지 계속
                while (i * j <= n) {
                	
                    arr[i * j] = false;
                    j += 1;
                    
                }
            }
        }
        
        // 모든 소수 출력
        for (int i = 2; i <= n; i++) {
            if (arr[i]) System.out.print(i + " ");
        }
    }
}
