package code03.implement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class CapitalNumber {

	public static void main(String[] args) {
		
	
		/* 알파벳 대문자와 숫자로만 구성된 문자열이 입력(String)으로 주어진다.
		 * 이때 모든 알파벳을 오름차순으로 정렬하여 이어서 출력하고 모든 숫자를 더한 값을 이어서 다음과 같이 출력하는 프로그램을 짜라
		 * 
		 * ex) K1KA5CB7 -> ABCKK13
		 */

		Scanner scan = new Scanner(System.in);
		
		// 주어진 input
		String input = scan.next();
		
		// 추후 추가할 합계 녀석
		int sum = 0; 
		
		ArrayList<String> list = new ArrayList<String>();
		
		// input을 ArrayList에 넣어주기
		for(int i = 0; i < input.length(); i++) {
			
			list.add(input.substring(i, i+1));
			
		}
		
		// 숫자와 대문자를 정렬후, 다시 재정렬(숫자가 뒤로오게 유도하기 위함.. 그게 아니면 ARRAYLIST와 반복문이 꼬임) 
		Collections.sort(list);
		// 역정렬을 하면 숫자가 뒤로옴(SORT의 정렬기준은 숫자 - 문자)
		Collections.reverse(list);
		
		// 뒤에서부터 숫자를 체크(try결과 숫자가 아니면, catch로 잡는다. 그후 바로 반복문을 빠져나옴)
		for(int i = list.size() - 1; i >= 0; i--) {
			
			int temp;
			
			try {
				
				temp = Integer.parseInt(list.get(i));
				
			} catch (Exception e) {
				
				break;
				
			}
			
			// 숫자임이 판명되면, sum에 더하고
			sum += temp;
			
			// 해당 배열을 arrList에서 지운다
			list.remove(i);
			
		}
		
		// 다시 대문자가 A부터 오도록 SORT
		Collections.sort(list);
		
		// arrList의 요소를 순차 출력하여 마무리
		list.forEach(answer -> System.out.print(answer));
		
		// 합계가 0이 아닌 경우에 한정해서
		if(sum != 0) {
			
			// 1. SUM을 문자화하여 arrList에 추가
			//list.add(String.valueOf(sum));
			
			// 2. 해당 숫자를 출력
			System.out.println(sum);
		
		}
		scan.close();
		
	}

}
