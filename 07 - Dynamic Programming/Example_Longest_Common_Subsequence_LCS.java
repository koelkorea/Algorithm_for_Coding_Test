package code07.Dynamic_Programming;

import java.util.Arrays;
import java.util.Scanner;

/* 두 문자열에서 LCS(Longest Common Subsequence)의 길이값을 잡아내는 알고리즘
 *  : '공통 일반 부분수열'을 의미.. 설명이 힘드니 예시를... 
 * 
 * ex) <abcbdab>와 <bdcaba>의 LCS = <bcba>  <- 두 문자열에서 bcba가 텀은 있어도 어쨌든 공통된 순서로 등장
 *       --- -      - - --  
 * 
 * [다이나믹 프로그래밍에 사용되는 로직] : 구하는건 LCS의 내용이 아니라 그 길이값임을 가정한다.
 *  1. 문자열 A의 길이가 i, 문자열 B의 길이가 j
 *  2. 이들의 LCS함수를 2차원 배열 L[i,j]에 담는다고 가정 후(여기서 다이나믹 시전) 
 *  3. 다이나믹 프로그래밍의 법칙 중, 함수(X) = 함수(x-1) + 연산시전 원칙 응용
 *  
 *     -> if (두 문자열의 끝에서부터) x[i] = y[j] 나온다면..
 *     		-> L[i,j] = L[i-1,j-1] + 1 
 *     
 *     -> else (두 문자열의 끝에서부터) x[i] =/ y[j] 나온다면..
 *     		-> L[i,j] = MAX(L[i,j-1], L[i-1,j])  <- 이는 만약 X를 줄이거나 Y를 줄였을때 LCS에 포함되는게 존재한다면, 큰 쪽을 선택함으로서 오판의 여지를 막을수 있다
 *     												(만약 LCS가 갱신되지 않는다면.. 두 값이 같으니 어찌되던 상관없음)
 *     
 *     -> 이라는 점을 명심해서 점화식 사용가능(top down)
 *     
 *  4. 2~3에 원칙에 따라 배열 초기화(x || y == 0 -> L(x,y) = 0)와 메서드 입력    
 *  
 *  (시간복잡도 : O(mn) <- 두 배열의 길이인 m,n만큼의 2중 for문이기 때문)
 *  
 */
public class Example_Longest_Common_Subsequence_LCS {
	
	// 문자열1 : 변수 x를 char를 통해 배열화
	static char x[];
	// 문자열2 : 변수 y를 char를 통해 배열화
	static char y[];
	
	// 체크중인 문자열인 x와 y의 배열 위치 별로 측정된 LCS의 길이를 2차원적으로 표현한 배열(물론 끝부분의 것은 무조건적인 최대값이다만, 어쨌든 top down 방식은 거슬러 내려가서 받는 값들을 저장해야함)
	static int dynamic[][];
	
	// LCS를 구하는 메서드
	static int LCS(int m, int n) { // m = X 배열의 길이,  n = Y 배열의 길이
		
		// 문자열 1(X)과 2(Y)의 0번째 문자는 LCS가 존재할리 없으므로 각각 초기화를 한다.
		for(int i = 0; i <= m; i++) {
			
			dynamic[i][0] = 0;	// X좌표 0에 해당하는 부분 초기화
			
		}
		
		for(int j = 0; j <= n; j++) {
			
			dynamic[0][j] = 0;	// Y좌표 0에 해당하는 부분 초기화
			
		}
		
		// [2중 FOR문]
		for(int i = 1; i <= m; i++) {	// 1번째 for문은 m = X 배열의 길이에 반복문을 대입 (x좌표 1부터)
			
			for(int j = 1; j <= n; j++) {	// 2번째 for문은 n = Y 배열의 길이에 반복문을 대입 (Y좌표 1부터)
				
				// 만약 특정위치의 X와 Y의 문자가 같다면.. ([0]부터 ~)
				if(x[i-1] == y[j-1]) {
					
					dynamic[i][j] = dynamic[i-1][j-1] + 1;	// LCS의 DP배열 <- 이전에 측정된 LCS + 1로 갱신
					
				}else {	// 다르다면
					
					dynamic[i][j] = Math.max(dynamic[i-1][j], dynamic[i][j-1]);	// LCS의 DP배열 <- 이전에 측정된 LCS 중(조건이야 X, Y축이 각각 하나씩 빠짐.. 문제는 거기서 LCS가 늘어날 가능성이 있으니..) 더 큰걸로 갱신해 유지
					
				}
				
			}
			
		}
		
		// for문을 다 돌았으면 DP테이블 리턴
		return dynamic[m][n];
		
	}
	
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		
		// m = X 배열의 길이,  n = Y 배열의 길이 입력
		int m = scanner.nextInt();
		int n = scanner.nextInt();
		
		// 주어진 'm = X 배열의 길이,  n = Y 배열의 길이'에 따른 X와 Y 문자배열 생성
		x = new char[m]; 
		y = new char[n]; 
		
		// 주어진 'm = X 배열의 길이,  n = Y 배열의 길이'에 따른 LCS결과를 담을 DP배열 [m+1][n+1]만큼의 크기로 생성
		dynamic = new int[m+1][n+1];
		
		// 문자열 X값을 직접 입력받음(크기는 m+1이고 [0]쪽은 말그대로 문자열 0번째 위치이므로, 없는걸 상정해야 하니 [1]부터 저장해줌)
		for(int i = 1; i <= m; i++) {
			
			x[i-1] = (char) scanner.next().charAt(0);
			
		}
		
		// 문자열 Y값을 직접 입력받음(크기는 n+1이고 [0]쪽은 말그대로 문자열 0번째 위치이므로, 없는걸 상정해야 하니 [1]부터 저장해줌)
		for(int j = 1; j <= n; j++) {
			
			y[j-1] = (char) scanner.next().charAt(0);
			
		}

		System.out.println(Arrays.deepToString(dynamic));	// LCS 실행전 DP 출력
		
		// LCS 실행
		System.out.println("주어진 2배열의 LCS : " + LCS(m, n));
		
		System.out.println(Arrays.deepToString(dynamic));	// LCS 실행후 DP 출력
		
		scanner.close();

	}

}
