package code05.aligning;

//삽입 정렬 : 기본적으로 뒤에서부터 연속적인 비교를 통해 앞으로 자리바꾸기 정렬 알고리즘..
//			(선택정렬과 비슷하지만, 더 효율적이라는 점에 의의가 있다)
public class Example_InsertSort {

    public static void main(String[] args) {

        int n = 10;
        int[] arr = {7, 5, 9, 0, 3, 1, 6, 2, 4, 8};

        // 비교기준점에 해당(이게 움직인다는건 중심축이 변한다는거)
        for (int i = 1; i < n; i++) {
        	
            // 인덱스 i부터 1까지 감소하며 반복하는 문법 (중심축 i를 기준으로, 뒷선의 모든 수인 j를 비교체크)
            for (int j = i; j > 0; j--) {
            	
                // 한 칸씩 왼쪽으로 이동
                if (arr[j] < arr[j - 1]) {
                	
                    // 스와프(Swap)
                    int temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                    
                }
                // 자기보다 작은 데이터를 만나면 그 위치에서 멈춤
                else break;
            }
        }

        for(int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
    }
    
    /* 삽입정렬의 시간복잡도 : O(N^2) -> 기본적으로 N + (N-1) ... + 1까지의 합만큼의 요소를 체크해야하기 때문(빠져나갈 구멍도 없다)
     * 삽입정렬의 공간복잡도 : O(N)	
     */

}
