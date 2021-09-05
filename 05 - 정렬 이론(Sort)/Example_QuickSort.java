package code05.aligning;

//퀵 정렬 : 기준 데이터를 set(일반적으론 1번째 데이터), 그 데이터를 기준으로 큰 데이터와 작은데이터의 위치를 바꿔주는 것을 시작으로 한 정렬 알고리즘
//			1. 피벗을 설정한다. 
//			2. 왼쪽에서부터 피벗보다 큰 데이터를 선착순 선택   
//			3. 오른쪽에서부터 피벗보다 작은 데이터 선착순 선택
//			4. 그 둘 위치를 교환
//			5. 이를 계속해서 수행(위치가 교차되는 경우가 나오면, 피벗 <-> 작은데이터)
//			6. 기존 피벗값을 중심으로 (작은데이터모임) (피벗값) (큰데이터모임) 이렇게 되어있을텐데, 두 그룹은 또 각각 피벗 설정후 똑같이 SORT
public class Example_QuickSort {

    public static void quickSort(int[] arr, int start, int end) {
    	
        if (start >= end) return; // 원소가 1개인 경우 종료
        
        int pivot = start; // 피벗은 첫 번째 원소
        int left = start + 1;
        int right = end;
        
        while (left <= right) {
            // 피벗보다 큰 데이터를 찾을 때까지 반복(왼쪽에서부터 시작한 위치는 끝위치보다 작아야한다는거도 잊지말자)
        	// 목적 : 왼쪽에 모여있는 데이터는 pivot보다 작은 녀석들이고, 그보다 큰 녀석은 오른쪽으로 직행
            while (left <= end && arr[left] <= arr[pivot]) {
            	
            	left++;
            	
            }
            
            // 피벗보다 작은 데이터를 찾을 때까지 반복(오른쪽에서부터 시작한 위치는 시작위치보다 커야한다는거도 잊지말자)
            // 목적 : 오른쪽에 모여있는 데이터는 pivot보다 큰 녀석들이고, 그보다 작은 녀석은 왼쪽으로 직행
            while (right > start && arr[right] >= arr[pivot]) {
            	
            	right--;
            	
            }
            
            // 엇갈렸다면 작은 데이터와 피벗을 교체
            if (left > right) {
                int temp = arr[pivot];
                arr[pivot] = arr[right];
                arr[right] = temp;
            }
            
            // 엇갈리지 않았다면 작은 데이터와 큰 데이터를 교체
            else {
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
            }
        }
        
        // 분할 이후 왼쪽 부분과 오른쪽 부분에서 각각 정렬 수행 (재귀식ㄴ 입력)
        quickSort(arr, start, right - 1);
        quickSort(arr, right + 1, end);
    }

    public static void main(String[] args) {
        int n = 10;
        int[] arr = {7, 5, 9, 0, 3, 1, 6, 2, 4, 8};

        quickSort(arr, 0, n - 1);

        for(int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
    }
    
    /* 퀵정렬의 특징
     * 1. 가장 많이 사용 (효율이 좋다)
     * 2. 그렇기에 대부분 언어의 기본 정렬 메서드로 쓰인다(그래서 걔네의 시간복잡도도 O(N*logN))
     * 3. 단! 분할이 제대로 안 이루어진 최악의 경우, 기존처럼 O(N^2)의 시간 복잡도를 가진다
     * 	  (예를 들어 정렬이 제대로 되어있는 0,1,2,3,4... 이런경우는 결국 N+N-1+N-2... 체크하게 됨)
     */
    
    /* 퀵정렬의 시간복잡도 : O(N*logN) -> N개의 요소를 1/2씩 쪼개서 파고드는 방식이고, 그런 횟수마다 어쨌던 N개의 원소를 체크하는건 똑같다(그러니까 반복횟수를 줄이는데 의의)
     * 퀵정렬의 공간복잡도 : O(N)	
     */

}
