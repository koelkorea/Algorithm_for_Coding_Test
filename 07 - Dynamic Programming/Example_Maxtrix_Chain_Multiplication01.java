package code07.Dynamic_Programming;

import java.util.Arrays;
import java.util.Scanner;

/* 최적화된 행렬곱셈에 대한 알고리즘 : 행렬 A1~An의 곱셈이 이뤄지는데, 이 과정에서 최소한의 곱셈을 하는 방식으로 결합구조를 찾고 그 곱셈의 수를 출력해라
 * 
 * [풀이1] 대각선법 : 자신*자신 = 0위치를 기준 매시도(r)마다 각 행에서 한칸씩의 열을 행해 전진하는 방식... 생각보다 까다로운 코딩인데 대부분 답지는 이거란다..
 * 					(시간복잡도 : O(n^3)  <- 3중 for문을 쓰기 때문)
 * 
 * [가정] = A1 * A2 = P[0] * P[1] * P[2]		<< P[i] = Ai의 세로 크기
 * : 행렬 A1부터 A2까지의 곱셈과 연관(단 A1이 (M * M+1)크기의 행렬을 갖는다면, A2은 (M+1) * (A2의 세로크기) 크기의 행렬을 갖는다고 가정한다.
 * 	 
 * 
 * 
 * [행렬 곱셈의 특징] : 
 * 1. 행렬의 곱셈은 결합법칙 A(BC) = (AB)C 이 성립하지만, 
 * 2. A가 i*k행렬, B가 k*j행렬이면 해당 연산의 곱셈의 수는 k*i*j이다. (한 요소를 정하는데 k번의 곱셈이.. 그리고 이를 i(A의 행의수)*j(B의 열의수) 행렬을 구성하는 만큼 반복함)
 * 3. 따라서 같은 연산이라도 행과 열이 더 낮은 녀석을 위주로 하는게 시간상 유리하다
 *	  (A가 10*100행렬이고 B가 100*5행렬 C는 5*50일시, (AB)C는 100*10*5 + 5*10*50 = 7500번이고, A(BC)는 5*100*50 + 100*10*50 = 75000번이다..)
 *
 * [다이나믹 프로그래밍에 사용되는 로직] : (A1 * ... * Ak) * (Ak * An) 구도가 가장 적은 곱셈의 수를 가진 결합임을 가정하며, 최종 결과는 저 두 수의 곱셈으로 마무리 됨
 *										-> 'Min x = Min (x-1) + 특정연산' 의 법칙 응용가능
 *											-> 1~K까지의 그것을 자르고 또 자르면? 재귀문 사용 가능
 * 												-> 자신~자신까지의 곱셈의 수는 0임에 착안하고 + 그리드 적용하자
 */
public class Example_Maxtrix_Chain_Multiplication01 {
	
	
	// m[][] : 최소 곱셈의 수와 연관된 값을 기록하기 위한 다이나믹 프로그래밍 2차원 배열(
	//		   (ex : m[2][7] = A2 ~ A7까지의 행렬 곱셈을 실행하기 위해 필요한 최소 곱셈의 수)
	static int m[][];
	
	// p[i] : 주어진 행렬 Ai의 세로 크기 (p[0] : 최초행렬 A1의 가로 크기라고 가정함, Ai의 총 크기 = p[i-1] * p[i]) 
	static int p[];
	
	static int matrixChain(int n) {
		
		// 2차원 DP 행렬 m[i][i]을 모두 초기화(Ai ~ Ai 까지의 곱셈은 스스로 그 자체니 곱셈의 횟수 자체가 없으므로 0이다)
		for(int i = 1; i <= n; i++) {
			
			m[i][i] = 0;
			
		}
		
		
		/* [3중 for문의 흐름]
		 *
		 * 	1. i~j의 차이값인 r을 통해서, r -> 1 ~ n-1까지로 먼저 입력 
		 *  2. 이를 통해, 2차 for에 들어갈 i -> 1 ~ n-r까지로 적용
		 *  3. i~j를 결합법칙으로 끊어줄 조건 반복문에 해당되는 k -> i+1 ~ j-1 까지 적용
		 *  
		 * 		-> 이를 통해 j-1 = 1 이라고 가정된 m[][]들을 순차적으로 채워나감(r=1일때 [0][0] ~ [i][i]까지의 대각선을 순차적으로 채우고, r=2면 [i][i+1]을 제외한 [i][i+2]부터 채움
		 *  
		 */
		
		//  j를 구하기 위한 r = j-i = i ~ j까지의 차이 (Ai * ~ * Aj 연산의 최소 곱셈의 횟수 m[i][j]의 j)   <-   j = i + r;
		for(int r = 1; r <= n-1; r++) { // m[i][2] ~ m[i][n]를 순차적으로 구하기 위해 for문에 r을 저렇게 씀
			
			// 반복문에 쓸 i를 구하기 위한 for문(n-r을 마지막에 둔 이유는 m[i][j]에서 i <= j(= i+r) 여야 하기 때문임)
			//								 -> r = n-r이면, i = n-r, j = n
			for(int i = 1; i <= n-r; i++) {
				
				// r은 j를 구하기 위해 필요함 (m[i][2] ~ m[i][n]를 순차적으로 구하기 위해 for문에 r을 저렇게 씀)
				int j = i + r;
				
				// Ai * ~ Aj의 최소 곱셈의 횟수 = A(i-1) * ~ Aj의 최소 곱셈의 횟수 + Ai의 가로크기 * Ai의 세로크기 * Aj의 세로크기(Ai * ~ Aj 까지의 곱셈을 수행했다면, 그 세로 크기는 p[j]가 됨)
				m[i][j] = m[i+1][j] + p[i-1]*p[i]*p[j];
				
				// K : m[i][j] = Ai ~ Aj까지의 곱셈을 위해 필요한 곱셈의 횟수를 최소로 만들기 위해 결합법칙상 끊어줘야할 행렬 Ak의 k를 기록한 값
				for(int k = i+1; k <= j-1; k++) {
					
					// 만약 Ai ~ Aj까지의 곱셈을 위해 필요한 곱셈의 횟수가, 결합법칙을 통해 쪼갠 곱셈의 횟수보다 작다면
					if(m[i][j] > m[i][k] + m[k+1][j] + p[i-1]*p[i]*p[j]) {	// Ai ~ Ak까지의 최소 곱셈의 수 + Ak ~ Aj까지의 최소곱셈의 수 + p[i]의 가로길이 * p[i]의 세로길이 * p[j]의 세로길이 (Ak ~ Aj곱셈의 결과)
						
						// 만약 Ai ~ Aj까지의 곱셈을 위해 필요한 곱셈의 횟수를 갱신
						m[i][j] = m[i][k] + m[k+1][j] + p[i-1]*p[i]*p[j];
								
					}
					
				}
				
				
			}
			
		}
		
		return m[1][n];
	}
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		// 곱셈할 행렬의 갯수 n 입력받기
		int n = scan.nextInt();
		
		// 행렬의 갯수인 n만큼의 값들과 A1의 가로크기 값인 P[0]까지 기록할 수 있도록, n보다 1 큰 만큼의 행렬을 만든다.  
		m = new int[n+1][n+1];
		p = new int[n+1];
		
		// A1의 가로크기 값인 p[0] ~ An의 세로행렬 값인 p[n]까지 입력함
		for(int i = 0; i <= n; i++) {
			
			p[i] = scan.nextInt();
			
		}

		
		System.out.println(Arrays.deepToString(m));
		
		// 계산 결과 출력
		System.out.println("현재 제시 된 행렬들의 곱셈에 필요한 최소 곱셈의 수 : " + matrixChain(n));
		
		System.out.println(Arrays.deepToString(m));
		
		scan.close();
	}
	

}
