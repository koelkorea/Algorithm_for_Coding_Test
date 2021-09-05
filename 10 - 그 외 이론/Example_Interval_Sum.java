package code10.Others;

/* 특정 배열의 원하는 구간의 합을 접두사 합(Prefix sum)을 이용해 구하는 알고리즘
 * (시간복잡도 : N+M)
 * 
 * 1. 주어진 배열의 합을 담을 배열 생성
 * 2. (접두사 합 배열)1~N까지의 합을 각각 그 배열에 담음  
 * 		-> EX) 1~1까지의 합 1번째, 1~3까지의 합 3번쨰
 * 3. 원하는 구간의 정보를 각각 입력받고, 해당배열[구간마무리] - 해당배열[구간시작]으로 구함
 */

public class Example_Interval_Sum {

    public static int n = 5; // 데이터의 개수 N과 데이터 입력받기
    public static int arr[] = {10, 20, 30, 40, 50};
    public static int[] prefixSum = new int[6];

    public static void main(String[] args) {
    	
        // 접두사 합(Prefix Sum) 배열 계산 (1~접두사위치의 합 -> 접두사합용 배열[접두사위치]에 저장)
        int sumValue = 0;

        for (int i = 0; i < n; i++) {
        	
            sumValue += arr[i];
            prefixSum[i + 1] = sumValue;
            
        }

        // 구간 합 계산(세 번째 수부터 네 번째 수까지)
        int left = 3;
        int right = 4;
        
        // 접두사합[구간끝] - 접두사합[구간시작]
        System.out.println(prefixSum[right] - prefixSum[left - 1]);
        
    }
    
}
