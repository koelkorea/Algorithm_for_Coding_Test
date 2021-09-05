package code02.greedy;

import java.util.Scanner;

public class Number1 {

    public static void main(String[] args) {
   	 
    	/* 어떤 수 N은 반복적으로 2가지 과정 중 하나를 선택해서 반복수행이 가능하다.
    	 * 
    	 * 1) N - 1		
    	 * 2) N / K (단 나머지가 0이 되는 N에 한해서 사용 가능)
    	 * 
    	 * 끝나는 조건은 N = 1이 될 때이다. 
    	 * N, K 가 주어졌을때, 조건을 만족시키는 과정의 최소 횟수를 구하는 프로그램을 작성해라  
    	 */
    	
	   	 Scanner scan = new Scanner(System.in);
	   	 
	   	 // N, K 받기
	   	 int N = scan.nextInt();
	   	 int K = scan.nextInt();
	   	 
	   	 // 반복횟수 구하는 용도
	   	 int answer = 0;
	   	 
	   	 // N%K = 0 이 아닌 경우는 N-1을 수행할 수 밖에 없음 = 그 나머지의 수는 무조건 -1을 하는 횟수의 숫자이니, 미리 더해줌
	   	 answer += (N%K); 
	   	 
	   	 // -1을 반복하는 횟수를 미리 제거하고 난뒤, N/K의 반복횟수를 깔끔하게 측정가능한 새로운 N(-1 반복작업 끝난 결과)
	   	 N = (N/K) * K;
	
	   	 // 조건 맞을때까지 무한루프
	   	 while(true) {
	   		 
	   		 // N이 1이 되거나, 애초부터 N < K라서 연산이 성립 안되는 경우 반복을 중단시킴 (일단 계산하기 전에 현재 상태를 체크해야, 원하는 조건에서 계산을 멈출수 있다)
	   		 if(N == 1 || N == 0) break;
	   		 
	   		 // 위의 예외 이외에는 계속 K로 나눈 횟수만큼 answer에 더한다.
	   		 N = N/K;
	   		 answer++; 
	   	 }
	
	   	 System.out.println(answer);
	   	 scan.close();
       
    }    
   
}
