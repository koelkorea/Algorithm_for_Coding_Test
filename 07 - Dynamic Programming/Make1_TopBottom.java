package code07.Dynamic_Programming;

import java.util.Arrays;
import java.util.Scanner;

public class Make1_TopBottom {

	/* (풀이2) 바텀-탑 다이나믹 방식(반복)
	 * 
	 * 정수 x가 주어졌을때, 4가지 연산을 할 수 있다.
	 * 1) 5나 3이나 2로 나눠지면 5부터 우선순위를 둬서 나눈다.
	 * 2) 1을 뺀다
	 * 
	 * 목적은 x(1~30000)를 1로 만드는 것인데, 그 과정에서 연산을 사용화는 횟수의 최소값을 구하라
	 */
	
	static int[] arr = new int[30001];
	
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		arr[1] = 0;
		
		int x = scan.nextInt();
		
		for(int i = 2; i <= x; i++) {
	
			arr[i] = arr[i-1] + 1;
			
			if(i%5 == 0) {
			
				arr[i] = Math.min(arr[i], arr[i/5] + 1);
				
			}
			
			if(i%3 == 0) {
				
				arr[i] = Math.min(arr[i], arr[i/3] + 1);
				
			}
			
			if(i%2 == 0) {
				
				arr[i] = Math.min(arr[i], arr[i/2] + 1);
				
			}
		
		}
		
		System.out.println(arr[x]);
		System.out.println(Arrays.toString(arr));
		
		scan.close();

	}

}
