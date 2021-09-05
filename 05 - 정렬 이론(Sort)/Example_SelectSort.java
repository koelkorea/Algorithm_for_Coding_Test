package code05.aligning;

//선택 정렬 : 처리되지 않은 데이터 중 가장 작은 데이터를 선택해 맨 앞에 있는 데이터와 바꾸는걸 반복
//			(결국 이렇게 가장 작은게 앞으로 오게 하고, 그다음 작은게 다음 앞으로)
public class Example_SelectSort {

    public static void main(String[] args) {

        int n = 10;
        int[] arr = {7, 5, 9, 0, 3, 1, 6, 2, 4, 8};

        // 가장 작은 원소가 위치한 인덱스를 찾는 과정
        for (int i = 0; i < n; i++) {
        	
            int min_index = i; // 현재기준 가장 작은 원소의 인덱스를 저장할 용도(일단은 0에서 시작 = 가장 작은거부터 먼저 찾는다.)
            
            // i이후의 요소들과의 비교를 통해 가장 작은 원소가 있는 위치를 get
            for (int j = i + 1; j < n; j++) {
            	
                if (arr[min_index] > arr[j]) {
                	
                    min_index = j;
                    
                }
            }
            // 스와프를 통해 그 가장 작은 요소를 현재 목표 인덱스인 min_index로 보낸다.
            int temp = arr[i];
            arr[i] = arr[min_index];
            arr[min_index] = temp;
        }

        for(int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    /* 선택정렬의 시간복잡도 : O(N^2) -> 기본적으로 N + (N-1) ... + 1까지의 합만큼의 요소를 체크해야하기 때문(빠져나갈 구멍도 없다)
     * 선택정렬의 공간복잡도 : O(N)	
     */
}
