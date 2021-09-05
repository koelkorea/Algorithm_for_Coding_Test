package code02.greedy;

import java.util.Scanner;

public class Operators {

	public static void main(String[] args) {
		
		/* 각 자리가 0~9로 이루어진 문자열 S가 주어졌을때, 왼쪽에서 오른쪽으로 하나씩 모든 숫자를 확인하며 숫자 사이에
		   *, +를 넣어 만들어질 수 있는 수 중 가장 큰 수를 만드는 알고리즘을 작성해라  */
		
		Scanner scan = new Scanner(System.in);
		
		// 주어진 input
		String S = scan.next();
		
		// 이를 int 배열화
		int arr[] = new int[S.length()];
		
		for(int i = 0; i < S.length(); i++) {
			
			// substring은 첫 수는 포함 마지막 수는 제외한다
			arr[i] = Integer.parseInt(S.substring(i , i+1));
			
		}
		
		// answer(정답) 첫수는 무조건 더하기 적용
		int answer = arr[0];
		
		// 조건 반복문을 통한 문제해결
		for(int i = 1; i < arr.length; i++) {

			// 총합이나 해당회차의 숫자가 0, 1이면 + 적용
			if(arr[i] <= 1 || answer <= 1) {
				
				answer += arr[i];
			
			// 그 외는 *
			}else {
				
				answer *= arr[i];
				
			}
			
		}
		
		System.out.println(answer);
		scan.close();

	}

}
