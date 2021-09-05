package code05.aligning;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Exchanging_Each_Factor {

	// (풀이1) Arraylist와 sort 그리고 set이용
	
	/* 두개의 배열 A,B가 있다. 녀석들은 각각 N개의 원소로 구성되어 있고, 자연수이다.
	 * 최대 K회의 A요소 <-> B요소를 바꿔칠 수 있다.
	 * 이때 연산후 A의 요소가 최대값이 되기 위해서는 어떻게 해야 하는가? 
	 */
	
	// A배열
	static ArrayList<Integer> list_A = new ArrayList<Integer>(100000); 
	// B배열
	static ArrayList<Integer> list_B = new ArrayList<Integer>(100000); 
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		// 배열의 길이 N
		int N = scan.nextInt();

		// A배열 채우기
		for(int i = 0; i < N; i++) {
			
			int tmp = scan.nextInt();
			list_A.add(tmp);
			
		}
		
		// A배열 정렬
		Collections.sort(list_A);
		
		// B배열 채우기
		for(int i = 0; i < N; i++) {
			
			int tmp = scan.nextInt();
			list_B.add(tmp);
			
		}
		
		// A배열 정렬 + 역순
		Collections.sort(list_B);
		Collections.reverse(list_B);
		
		list_A.forEach(action -> System.out.print(action + " "));
		System.out.println();
		list_B.forEach(action -> System.out.print(action + " "));
		
		// 바꿔칠 횟수의 최대값 K
		int K = scan.nextInt();
		
		scan.close();
		
		// set을 이용한 바꾸기 절차
		for(int i = 0; i < K; i++) {
			
			if(list_A.get(i) <= list_B.get(i)) {
				
				int tmp = list_A.get(i);
				list_A.set(i, list_B.get(i));
				list_B.set(i, tmp);

			}else {
				
				break;
				
			}
			
		}
		
		int answer = 0;
		
		// A배열 요소 최대값 더하기
		for(int i = 0; i < N; i++) {
			
			answer += list_A.get(i);
			
		}
		
		System.out.println(answer);
		
		
	}

}
