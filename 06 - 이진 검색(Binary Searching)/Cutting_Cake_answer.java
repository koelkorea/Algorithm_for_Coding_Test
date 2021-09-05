package code06.Binary_Searching;

import java.util.Scanner;

public class Cutting_Cake_answer {

	/* 떡볶이 떡을 만드는 기계는 일정하지 않은 길이로 떡을 N개만큼 뽑는다. 
	 * 
	 * 그리고 당신은 그걸 절단기로 일정하게 자른다.
	 * 절단기의 높이(H)가 당신이 파는 떡의 길이라면, 그보다 높은 길이의 떡은 "길이 - H" 만큼 잘리고, 안 그러면 안 잘린다
	 * 
	 * 그리고 그 남은 자투리를 가져가는 사람이 있다고 쳤을때, 
	 * 그 사람이 최소 총길이 M만큼의 떡을 원한다고 치면 당신은 얼마만큼의 H를 지정할 수 있는가?
	 */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 떡의 개수(N)와 요청한 떡의 길이(M)
        int n = sc.nextInt();
        int m = sc.nextInt();

        // 각 떡의 개별 높이 정보 
        int[] arr = new int[n];
        
        for (int i = 0; i < n; i++) {
        	
            arr[i] = sc.nextInt();
            
        }

        // 이진 탐색을 위한 시작점과 끝점 설정
        int start = 0;
        int end = (int) 1e9;
        
        // 이진 탐색 수행 (반복적)
        int result = 0; 
        
        while (start <= end) {
        	
            long total = 0;
            
            int mid = (start + end) / 2;
            
            for (int i = 0; i < n; i++) {
            	
                // 잘랐을 때의 떡의 양 계산
                if (arr[i] > mid) {
                	
                	total += arr[i] - mid; 
                	
                }
                
            }
            
            if (total < m) { // 떡의 양이 부족한 경우 더 많이 자르기 = 절단기의 높이 낮추기(왼쪽 부분 탐색)
            	
                end = mid - 1;
                
            }
            else { // 떡의 양이 충분한 경우 덜 자르기 = 절단기의 높이 높이기(오른쪽 부분 탐색)
            	
                result = mid; // 최대한 덜 잘랐을 때가 정답이므로, 여기에서 result에 기록 
                start = mid + 1;
            }
            
        }

        System.out.println(result);
        sc.close();
        
    }

}
