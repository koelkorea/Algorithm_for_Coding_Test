package code05.aligning;

// 계수 정렬 : 정렬의 요소들을 종류별로 세서, 별도의 정렬(계수정렬이라고 지칭)에 해당 index에 목표정렬 factor값 녀석들의 수를 기록해서
//			  , 이 계수정렬을 토대로 정렬된 배열 재생성
public class Example_CountingSort {

    public static final int MAX_VALUE = 9;

    public static void main(String[] args) {
    	
        int n = 15;
        
        // 모든 원소의 값이 0보다 크거나 같다고 가정
        int[] arr = {7, 5, 9, 0, 3, 1, 6, 2, 9, 1, 4, 8, 0, 5, 2};
        
        // 모든 범위를 포함하는 배열 선언(모든 값은 0으로 초기화)
        int[] cnt = new int[MAX_VALUE + 1];

        for (int i = 0; i < n; i++) {
            cnt[arr[i]] += 1; // 각 데이터에 해당하는 인덱스의 값 증가
        }
        for (int i = 0; i <= MAX_VALUE; i++) { // 배열에 기록된 정렬 정보 확인
            for (int j = 0; j < cnt[i]; j++) {
                System.out.print(i + " "); // 띄어쓰기를 기준으로 등장한 횟수만큼 인덱스 출력
            }
        }
    }
    
    /* 계수정렬의 특징
     * 1. 데이터의 최대값 K가 작다면 매우 효율적
     * 2. 그렇기에 특정한 경우를 한정한다면 매우 빠르다
     */
    
    /* 계수정렬의 시간복잡도 : O(N+K) -> 목표 정렬의 요소 N개(요소 최대값은 K-1라 가정) 체크, 추후 0~K-1까지의 요소로 구성된 계수정렬의 요소 체크시간 K를 더함
     * 계수정렬의 공간복잡도 : O(N+K)	
     */
}
