package code02.greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Adventurers {

	public static void main(String[] args) {
		
		/* N명의 모험가가 있다. 각각에게 공포도를 측정한다. 
		     길드장은 그룹의 안전을 위해 그룹의 공포도가 X수준인 모험가는 반드시 X명 이상으로 구성한 그룹에 들도록 규정한다.
		   N명의 모험가에 대한 정보가 주어진다치면, 여행을 떠날 수 있는 그룹의 최대값을 구하는 프로그램 작성해라 */
		
		Scanner scan = new Scanner(System.in);
		
		// 모험가들의 수
		int N = scan.nextInt();
		
		// 현재 편성 중인 그룹의 현재 인원
		int count = 0;
		
		// 현재 그룹의 수
		int num = 0;
		
		// 공포도 기록용 ArrList
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		// Arrlist에 모험가 놈들의 공포도 입력
		for(int i = 0; i < N; i++) {
			
			list.add(scan.nextInt());
			
		}
		
		// Arrlist의 공포도 오름차순으로 정렬
		Collections.sort(list);
		
		
		for(int i = 0; i < N; i++) {
			
			// 배열의 데이터 하나를 깔때마다 횟수 증가시키기
			count++;
			
			// 만약, 팀의 인원수와 현재 모험가의 공포도가 같다면, count를 원상복구 시키고, 그룹의 수를 1증가시키는 식으로 측정 (오름차순의 이유가 있다.. 이래야 안 꼬임)
			if(count == list.get(i)) {
				
				count = 0;
				num++;
				
			}
			
			
		}
		
		System.out.println(num);
		
		scan.close();
		
	}

}
