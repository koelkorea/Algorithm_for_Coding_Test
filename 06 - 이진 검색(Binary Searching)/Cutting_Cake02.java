package code06.Binary_Searching;

import java.util.Arrays;
import java.util.Scanner;

public class Cutting_Cake02 {

	/* (풀이2) 이진탐색
	 * 
	 * 떡볶이 떡을 만드는 기계는 일정하지 않은 길이로 떡을 N개만큼 뽑는다. 
	 * 
	 * 그리고 당신은 그걸 절단기로 일정하게 자른다.
	 * 절단기의 높이(H)가 당신이 파는 떡의 길이라면, 그보다 높은 길이의 떡은 "길이 - H" 만큼 잘리고, 안 그러면 안 잘린다
	 * 
	 * 그리고 그 남은 자투리를 가져가는 사람이 있다고 쳤을때, 
	 * 그 사람이 최소 총길이 M만큼의 떡을 원한다고 치면 당신은 얼마만큼의 H를 지정할 수 있는가?
	 */
	
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		
		// 떡의 개수(입력)
		int N = scan.nextInt();
		
		// 요구 떡의 길이(입력)
		int M = scan.nextInt();
		
		// N개의 떡 길이에 대한 정보를 담은 배열
		int length_cake[] = new int[N];
		
		// 총 떡들의 정보(입력)
		for(int i = 0; i < N; i++) {
			
			length_cake[i] = scan.nextInt();
			
		}
		
		// 이진 탐색의 시작점
		int start = 0;
		
		// 이진 탐색의 끝점 == 최대가능 설정 절단기 높이 (최대 떡길이 보다 길어질 이유 없음) 
		int end = Arrays.stream(length_cake).max().getAsInt();
		
		//System.out.println(end);
		
		// 이진탐색 수행값(= 결과값 용도)
		int answer = 0;
			
		// 이진탐색 go : 절단기 설정값의 시작값으로 설정된 값이 마지노선값보다 커지는 순간까지(1차반복문) || M만큼의 나머지 떡 길이를 딱 맞추는 절판기 높이 H가 나오는 경우
		while(start <= end || answer == M) {
		
			// 해당 실행순번에서 나오는 나머지 떡량(M의 후보)
			long total_M = 0;
			
			// 이진탐색의 연쇄적인 중간값을 위한 키(절단기 높이 H로 설정 -> 매번 변할수 있다)
			int mid = (start + end) / 2;
			
			// 주어진 N개의 떡을 mid = H로 설정했을 경우 나오는 나머지떡 총량 total_M을 구하기 위한 반복문(2차반복문 : N)
			for (int i = 0; i < N; i++) {
			
				// 떡 길이가 mid로 설정한 절단기 높이보다 크면 -> 그 나머지를 나머지떡 총량값으로...
				if(length_cake[i] > mid) {
				
					total_M += (length_cake[i] - mid);
				
				}
				
			}
			
			// 해당 경우의 나머지떡 총길이인 total_M이 손님이 요구한 최소 나머지떡길이 M보다 작은경우 = 절단기의 높이 낮추기(= 마지노선 값 낮추기 = 왼쪽 부분 탐색)
			if(total_M < M) {
				
				// 절단기 높이의 마지노선 값을 mid보다 1 작은 수치로 놓는다.
				end = mid - 1;
				
			// 해당 경우의 나머지떡 총길이인 total_M이 손님이 요구한 최소 나머지떡길이 M의 값과 같거나 더 큰 경우  = 절단기의 높이 높이기(= 최소값 줄이기)
			}else if(total_M >= M) {
				
				// 해당 이진탐색 중인 절단기 설정값을 mid값을 정답 후보로
				answer = mid;
				
				// 절단기 길이 최소값 추정을 mid보다 1 높게 잡는다
				// [일단 >=의 경우 그것이 이진탐색의 끝(start와 end의 격차가 없는)인 최적값인 경우] 
				//   -> 이렇게 계속 start값이 올라감 -> end로 설정된 녀석보다 start가 커짐 -> while문 탈출 
				//		(애초에 이 이진탐색 자체가 끝까지 가려고 만든거.. 목표치로 결국 수렴하게 됨)
				start = mid + 1;
				
			}
			
		}
		
		System.out.println(answer);
		
		scan.close();
		

	}
	

}
