package code05.aligning;

import java.util.*;

public class Exchanging_Each_Factor_Answer {
	
	// (해설) 배열 이용 (단, 처음부터 배열을 쓰고, Arrays클래스의 기능으로 정렬 수행... 배열은 Arrays관할 list는 Collections 관할..)
	
	/* 두개의 배열 A,B가 있다. 녀석들은 각각 N개의 원소로 구성되어 있고, 자연수이다.
	 * 최대 K회의 A요소 <-> B요소를 바꿔칠 수 있다.
	 * 이때 연산후 A의 요소가 최대값이 되기 위해서는 어떻게 해야 하는가? 
	 */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // N과 K를 입력받기
        int n = sc.nextInt();
        int k = sc.nextInt();

        // 배열 A의 모든 원소를 입력받기
        Integer[] a = new Integer[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        // 배열 B의 모든 원소를 입력받기
        Integer[] b = new Integer[n];
        for (int i = 0; i < n; i++) {
            b[i] = sc.nextInt();
        }

        // 배열 A는 오름차순 정렬 수행
        Arrays.sort(a);
        // 배열 B는 내림차순 정렬 수행
        Arrays.sort(b, Collections.reverseOrder());

        // 첫 번째 인덱스부터 확인하며, 두 배열의 원소를 최대 K번 비교 
        for (int i = 0; i < k; i++) {
            // A의 원소가 B의 원소보다 작은 경우
            if (a[i] < b[i]) {
                // 두 원소를 교체
                int temp = a[i];
                a[i] = b[i];
                b[i] = temp;
            }
            // A의 원소가 B의 원소보다 크거나 같을 때, 반복문을 탈출
            else break;
        }

        // 배열 A의 모든 원소의 합을 출력
        long result = 0;
        for (int i = 0; i < n; i++) {
            result += a[i];
        }
        
        System.out.println(result);
		sc.close();
    }

}