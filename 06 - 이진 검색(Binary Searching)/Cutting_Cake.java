package code06.Binary_Searching;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Cutting_Cake {

	/* (풀이1) Arraylist 사용
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
		
		// N개의 떡 길이에 대한 정보를 담은 배열
		int length_cake[] = new int[N];
		
		// 총 떡들의 정보(입력)
		for(int i = 0; i < N; i++) {
			
			length_cake[i] = scan.nextInt();
			
		}
		
		// 최대가능 설정 절단기 높이 (최대 떡길이 보다 길어질 이유 없음) 
		int H = Arrays.stream(length_cake).max().getAsInt();
		
		System.out.println(H);
		
		// 절단기의 높이(i번째는 i이 절단기 높이) 떡의 총 길이에 대한 길을 담은 배열
		ArrayList<Integer> total_M = new ArrayList<Integer>(H+1);
		
		
		
		// 요구 떡의 길이(입력)
		int M = scan.nextInt();
		
		int tmp = 0;
		
		// 해당 떡을 0~H까지 잘랐을때의 (H+1)개 만큼의 합계 정보를 담은 배열 채우기
		for(int i = 0; i <= H; i++) {
			
			tmp = 0;
			
			// 개별 높이(H)로 맞춘 떡을 잘라 남은 떡의 총 길이
			for(int j = 0; j < N; j++) {
			
				if(length_cake[j] > i) {
				
					tmp += (length_cake[j] - i);
				
				}else {
					
								
				}
			}
			
			total_M.add(tmp);
		
		}
		
		System.out.println(total_M);
		
		
		// 해당 배열에서 M을 찾는 방법(시간복잡도 logN수준)만 놓으면 해결... 이건 내일하자..
		System.out.println(total_M.indexOf(M));
		
		scan.close();
		

	}
	
	

}
