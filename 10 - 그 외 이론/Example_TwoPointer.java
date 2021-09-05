package code10.Others;

/* 주어진 배열에서 연속된 값의 합이 특정값인 경우의 수를 구하는 알고리즘
 * 
 * 1. 배열정보와 값을 입력
 * 2. 1번째 수부터 for-> while문을 통해서
 * 		- 1~end의 수가 m을 넘어서지 않으면 end를 증가시키고, 
 * 		- 그 이외의 경우는 start를 이동시키고 현재 구간합 - 배열(start)를 수행 (if 구간합이 m을 만족시킬 경우는 cnt++)
 * 3. 이걸 배열 마지막 위치까지 반복해서 cnt를 출력하면 됨 
 */
public class Example_TwoPointer {

    public static int n = 5; // 데이터의 개수 N
    public static int m = 5; // 찾고자 하는 부분합 M
    public static int[] arr = {1, 2, 3, 2, 5}; // 전체 수열

    public static void main(String[] args) {
    	
    	// 부분합에 부합하는 연속된 수의 경우의수
        int cnt = 0;
        
        // 현재 연속된 수의 부분합
        int intervalSum = 0;
        
        // 현재 마지막 연속된 수가 위치한 위치
        int end = 0;

        // start를 n-1번만큼 차례대로 증가시키며 반복
        for (int start = 0; start < n; start++) {
        	
            // end를 가능한 만큼 이동시키기(현재 연속수의 부분합이 m보다 작음 + 마지막값이 n보다 작은 경우를 만족한다면 계속 순환 -> m을 넘거나 같으면 이탈) 
            while (intervalSum < m && end < n) {
            	
            	// 부분합에 마지막 연속위치 배열의 값 추가
                intervalSum += arr[end];
                // 마지막 위치를 한칸 뒤로 옮김
                end += 1;
                
            }
            
            // while문 이탈시, 부분합이 m일 때 카운트 증가(조건에 맞으므로)
            if (intervalSum == m) {
            	
                cnt += 1;
                
            }
            
            // 해당 시작점의 경우의 수를 다 구한 경우, 해당 배열의 숫자를 현재 부분합에서 뺀다 
            // (빼고 m보다 작은경우) : 다음~끝 배열값까지의 합이 m보다 작다는거니 다른 만족의 경우의 수 없음
            // (빼고 m보다 큰 경우) : 해당 처음값의 또 빼면서, 조건에 맞는 값이 등장하는지 보면 됨
            //  -> 만약 중간구간의 연속값이 정답인 경우 어쩌냐? 
            //		-> 걱정할거 없는게, 해당 start 구간에서 도중 연속값의 합이 m을 넘는 순간 거기서 끝부분은 멈추고 다음start로 이동함
            intervalSum -= arr[start];
        }

        System.out.println(cnt);
    }
    
}
