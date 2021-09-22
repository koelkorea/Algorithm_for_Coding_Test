package code03.implement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class CapitalNumber_Answer {

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

		// char 형식으로 문자열을 담을 arrList 만들기
		ArrayList<Character> list = new ArrayList<Character>();

		// input을 ArrayList에 넣어주기
		for(int i = 0; i < input.length(); i++) {

		    // 알파벳인 경우만! 결과 리스트에 삽입(isLetter는 해당 데이터가 문자인 경우)
		    if (Character.isLetter(input.charAt(i))) {

			list.add(input.charAt(i));

		    }

		    // 숫자는 따로 더하기
		    else {
			sum += input.charAt(i) - '0';
		    }			


		}

		 // 알파벳을 오름차순으로 정렬
		Collections.sort(list);

		// 알파벳을 차례대로 출력
		for (int i = 0; i < list.size(); i++) {

		    System.out.print(list.get(i));

		}

		// 숫자가 하나라도 존재하는 경우 가장 뒤에 출력
		if (sum != 0) {

			System.out.print(sum);

		}

		System.out.println();

		scan.close();
		
	}

}
