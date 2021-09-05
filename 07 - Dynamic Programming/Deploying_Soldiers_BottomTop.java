package code07.Dynamic_Programming;

import java.util.Arrays;
import java.util.Scanner;


public class Deploying_Soldiers_BottomTop {

	/* (풀이2) 바텀-탑 다이나믹 방식 : 반복문 사용
	 * 
	 * N(1~2000)명의 병사가 무작위로 나열되어 있고, 얘들은 각각 전투력을 보유한다.
	 * 배치시 전투력(1~10000000)이 높은 병사가 앞에 오도록 내림차순으로 배치하고자 한다.
	 * 그 과정으로 배치 과정에서 특정한 위치에 있는 병사는 열외시키는 방법으로 단순화하고자 하는데, 
	 * 그러고도 남는 병사가 최대가 되도록 하기 위햇허는 열외시켜야 하는 병사는 몇명인가?
	 * 
	 */
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		// 병사의 수 입력
		int N = scan.nextInt();
		
		// 병사 전투력 입력 배열
		int input[] = new int[N];
		// 특정 위치의 병사의 전투력을 끝(전투력 최저치)으로 봤을시 만들어질 수 있는 배열의 길이(이를 통해 누구를 기준으로 했을때 가장 긴 배열이 만들어지는지 체크)
		int dynamic[] = new int[N];
		
		// 기본 배열의 길이는 1(열외되는 경우)
		Arrays.fill(dynamic, 1);
		
		// 병사 전투력 입력
		for(int i = 0; i < N; i++) {
			
			input[i] = scan.nextInt();
		}
		
		
		// 2번쨰 위치를 시작으로 각각 i번째 병사까지를 기준으로 그 병사를 최저전투력으로 맨끝에 둘경우 만들수 있는 최대 배열의 수를 구함
		for(int i = 1; i < N; i++) {
			
			for(int j = 0; j < i; j++) {
				
				// 만약 앞선 j의 병사의 전투력이 i위치의 병사보다 높다면
				if(input[j] > input[i]) {
					
					// 해당 병사를 기준으로 한 배열의 최대 길이는 이전(반복문으로 갱신된) dynamic[i]와 j위치 병사를 기준으로 뒀을경우보다 1만큼 더 긴 배열중 더 큰 수를 dynamic[i]배열로 갱신함
					dynamic[i] = Math.max(dynamic[i], dynamic[j] + 1);
					
				}
				
			}
			
		}
		
		// 최대 배열길이 측정용
		int answer = 0;
		
		// 각 위치의 병사를 기준으로 한 배열 길이 최대치 중 가장 긴 녀석을 추출(열외인원 파악을 위해)
		for(int i = 1; i < N; i++) {
		
			answer = Math.max(answer, dynamic[i]);
				
		}		
		
		System.out.println(Arrays.toString(dynamic));
		
		// 열외인원 파악완료
		System.out.println(N - answer);
		
		scan.close();

	}

}
