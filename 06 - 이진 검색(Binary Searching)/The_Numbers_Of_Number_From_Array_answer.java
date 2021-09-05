package code06.Binary_Searching;

import java.util.Scanner;

public class The_Numbers_Of_Number_From_Array_answer {

	/* (해답) 단순 2진탐색 X -> lowerbound, upperbound 개념으로 2가지 방침의 2진탐색 알고리즘을 짜야함 
	 * 
	 * N개의 원소를 포함하고 있는 수열이 오름차순으로 정렬되어 있다.
	 * 이떄 이 수열에서 X가 등장하는 횟수를 계산해라
	 * 만약 X인 원소가 없다면, -1을 출력해라
	 * 
	 * EX) {1,1,2,2,2,3}에서 X=2면, 2가 총 4개 있으니 -> 4를 출력해야 함 
	 * 
	 * 단 시간복잡도가 logN(그러니까 2진탐색해라) 수준으로 나오도록 설계를 마쳐야 한다.
	 */
	
    public static int lowerBound(int[] arr, int target, int start, int end) {
        while (start < end) {
        	
            int mid = (start + end) / 2;
            
            if (arr[mid] >= target) {
            	
            	end = mid;
            	
            }else {
            	
            	start = mid + 1;
            	
            }
        }
        return end;
    }

    public static int upperBound(int[] arr, int target, int start, int end) {
        while (start < end) {
        	
            int mid = (start + end) / 2;
            
            if (arr[mid] > target) {
            	
            	end = mid;
            	
            }else {
            	
            	start = mid + 1;
            	
            }
        }
        return end;
    }

    // 값이 [left_value, right_value]인 데이터의 개수를 반환하는 함수
    public static int countByRange(int[] arr, int leftValue, int rightValue) {
        // 유의: lowerBound와 upperBound는 end 변수의 값을 배열의 길이로 설정
        int rightIndex = upperBound(arr, rightValue, 0, arr.length);
        int leftIndex = lowerBound(arr, leftValue, 0, arr.length);
        return rightIndex - leftIndex;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 데이터의 개수 N, 찾고자 하는 값 x 입력받기
        int n = sc.nextInt();
        int x = sc.nextInt();

        // 전체 데이터 입력받기
        int[] arr = new int[n];
        
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        
        // 값이 [x, x] 범위에 있는 데이터의 개수 계산
        int cnt = countByRange(arr, x, x);
        
        // 값이 x인 원소가 존재하지 않는다면
        if (cnt == 0) System.out.println(-1);
        //  값이 x인 원소가 존재한다면
        else System.out.println(cnt);
        
        sc.close();
    }
    
}
