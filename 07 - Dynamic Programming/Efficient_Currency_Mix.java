package code07.Dynamic_Programming;

import java.util.Arrays;
import java.util.Scanner;

public class Efficient_Currency_Mix {

	/* (풀이1) 바텀-탑 다이나믹 : 반복문
	 * 
	 * N(1~100)가지 종류의 화폐가 있다. 이 화폐들의 개수를 최소한으로 사용해서 M(1~10000)원을 만들어야한다.
	 * M원을 만들기 위한 최소한의 화폐 개수를 출력하는 프로그램을 작성하라(불가능할 경우 -1 출력)
	 */
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		// 화폐 종류의 개수
		int N = scan.nextInt();
		
		// 만들기를 원하는 금액 M
		int M = scan.nextInt();
		
		// 화폐 종류 입력 배열
		int input[] = new int[N];
		
		// 0~M까지 각 금액에 필요한 동전의 최소 구성
		int dynamic[] = new int[M+1];
		
		// 일단 10001(나올수 있는 최대 M보다 큰 수)로 전부 dynamic 배열 초기화
		Arrays.fill(dynamic, 10001);
		// 자연수 0을 구성하는 화폐수는 0으로 고정(구현에 있어 핵심코드)
		dynamic[0] = 0;
		
		// 화폐종류 n가지의 반복문
		for(int i = 0; i < N; i++) {
			
			// 화폐종류 입력
			input[i] = scan.nextInt(); 
			
			// 해당 화폐종류 배열의 값을 기준으로 M까지 1씩 증가시키는 j
			for(int j = input[i]; j <= M; j++) {
				
				// 만약 다이나믹 배열이 10001이 아니라면(초기치는 0뺴면 다 10001 = input[i]에서 시작하는 j의 시작은 0을 참고해서 input[i]
				// (해당 화폐종류의 값)에 1을 채우는걸 시작으로 dynamic[j - input[i] = dynamic[j]로 설정)
				if(dynamic[j - input[i]] != 10001) {
					
					// 거기서 기존의 입력된 dynamic[j]와 현재 참고하는 dynamic[j - input[i]] 중 작은 놈을 택한다.
					dynamic[j] = Math.min(dynamic[j - input[i]] + 1, dynamic[j]);
				
					// 이렇게 1~n까지 다 순차로 돌면, 결국 1~M까지의 최소화폐조합의 결과값만 dynamic배열에 기록
				}
			}
			
		}
		
		// 10001이면 -1 출력
		if(dynamic[M] == 10001) {
			
			System.out.println(-1);
			
		}else {
			
			System.out.println(dynamic[M]);
			
		}
		
		System.out.println(Arrays.toString(dynamic));
		
		scan.close();
	}

}
