package code07.Dynamic_Programming;

import java.util.Arrays;
import java.util.Scanner;

public class Make1_BottomTop {

	/* (풀이1) 탑-다운 다이나믹 방식(재귀)
	 * 
	 * 정수 x가 주어졌을때, 4가지 연산을 할 수 있다.
	 * 1) 5나 3이나 2로 나눠지면 5부터 우선순위를 둬서 나눈다.
	 * 2) 1을 뺀다
	 * 
	 * 목적은 x(1~30000)를 1로 만드는 것인데, 그 과정에서 연산을 사용화는 횟수의 최소값을 구하라
	 */
	
	static int[] arr = new int[30001];
	
	static int make1(int x) {		
		
		if(x == 1) {
			return 0;
		}
		
		arr[x] = make1(x-1) + 1;
		
		if(x%5 == 0) {
			
			//arr[x] = Math.min(arr[x], make1(x/5) + 1);
			arr[x] = Math.min(arr[x], arr[x/5] + 1);
			
		}
		
		if(x%3 == 0) {
			
			//arr[x] = Math.min(arr[x], make1(x/3) + 1);
			arr[x] = Math.min(arr[x], arr[x/3] + 1);
			
		}
		
		if(x%2 == 0) {
			
			//arr[x] = Math.min(arr[x], make1(x/2) + 1);
			arr[x] = Math.min(arr[x], arr[x/2] + 1);
			
		}
		
		return arr[x];
		
	}
	
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		int x = scan.nextInt();
		
		
		System.out.println(make1(x));
		System.out.println(Arrays.toString(arr));
		
		scan.close();

	}

}
