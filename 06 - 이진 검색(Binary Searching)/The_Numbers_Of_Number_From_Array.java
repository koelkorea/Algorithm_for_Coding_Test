package code06.Binary_Searching;

import java.util.Scanner;

public class The_Numbers_Of_Number_From_Array {

	/* (풀이1) 이진탐색
	 * 
	 * N개의 원소를 포함하고 있는 수열이 오름차순으로 정렬되어 있다.
	 * 이떄 이 수열에서 X가 등장하는 횟수를 계산해라
	 * 만약 X인 원소가 없다면, -1을 출력해라
	 * 
	 * EX) {1,1,2,2,2,3}에서 X=2면, 2가 총 4개 있으니 -> 4를 출력해야 함 
	 * 
	 * 단 시간복잡도가 logN(그러니까 2진탐색해라) 수준으로 나오도록 설계를 마쳐야 한다.
	 */
	
	// 2진탐색 ver1 : 가장 왼쪽에 있는 목표값을 찾기(같은 값이나 그보다 큰 값이 나오면, 범위 마지노선 값을 줄인다)
	static int lowerbound(int target, int arr[], int start, int end) {
		
		// 범위의 시작지점이 끝(마지노선)보다 낮은 경우에만 속행
		while(start < end) {
			
			int mid = (start + end) / 2;
			
			if(arr[mid] < target) {
				
				// 시작값을 늘리기만(땡기기만 함)
				start = mid + 1;
			
			// lowerbound의 경우는 목표값을 찾은 경우, 목표값보다 큰값을 찾았을때처럼 마지막 값을 줄인다(범위줄임)
			}else if(arr[mid] >= target){
				
				end = mid;
				
			}
			
		}
		
		return end;

//		쓰지 않는 이유 : 목표하는 녀석이 처음이나 끝에 가 있을때 인덱스 오류 생김
		
//		while(start <= end) {
//			
//			int mid = (start + end) / 2;
//			
//			if(arr[mid] < target) {
//				
//				start = mid + 1;
//				
//			}else if(arr[mid] >= target){
//				
//				end = mid - 1;
//				
//			}
//			
//		}
		

		
	}
	
	// 2진탐색 ver2 : 가장 오른쪽에 있는 목표값을 찾기(같은 값이나 그보다 작은 값이 나오면, 범위 시작 값을 늘린다)
	static int upperbound(int target, int arr[], int start, int end) {
		
		// 범위의 시작지점이 끝(마지노선)보다 낮은 경우에만 속행
		while(start < end) {
			
			int mid = (start + end) / 2;
			
			// upperbound의 경우는 목표값을 찾은 경우, 목표값보다 작은값을 찾았을때처럼 처음 값을 줄인다(범위줄임)
			if(arr[mid] <= target) {
				
				start = mid + 1;

			}else if(arr[mid] > target){

				end = mid;
				
			}
			
		}
		
		return end;
		
//		쓰지 않는 이유 : 목표하는 녀석이 처음이나 끝에 가 있을때 인덱스 오류 생김
		
//		while(start <= end) {
//		
//			int mid = (start + end) / 2;
//			
//			if(arr[mid] < target) {
//			
//				start = mid + 1;
//			
//			}else if(arr[mid] >= target){
//			
//				end = mid - 1;
//			
//			}
//		
//		}
		
		
		
	}
	
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		// 배열의 원소들의 수
		int N = scan.nextInt();
		// 배열내 검색을 위한 값
		int x = scan.nextInt();
		// N만한 크기의 배열 만들기
		int arr [] = new int[N];
		
		// 배열 채우기
		for(int i = 0; i < N; i++) {
			
			arr[i] = scan.nextInt();
			
		}
		
		// 시작지점
		int start = 0;
		// 끝지점(arr.length()와 같음)
		int end = N;
		
		int answer = upperbound(x, arr, start, end) - lowerbound(x, arr, start, end);
		
		if(answer > 0) {
			System.out.println(answer);
		}else {
			System.out.println(-1);
		}
		
		
		scan.close();

	}


	
	
}
