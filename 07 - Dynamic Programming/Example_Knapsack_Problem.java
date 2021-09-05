package code07.Dynamic_Programming;

import java.util.Arrays;
import java.util.Scanner;

/* Knapsack Problem : 쉽게말해 도둑이 물건 가져가는데 '무게' '가치'라는 값을 고려해서 가능한 최대의 가치만큼의 물건을 훔치는 해를 찾는 알고리즘 
 * 
 * [knapsack 문제의 가정]
 * 1. 물건은 총 n개이고, 그에 따른 무게(w)과 가치(v)또한 n개만큼 존재
 * 2. 배낭의 용량은 w이고, 이 이상의 물건은 못 가져간다.
 * 3. 목표는 MAX(sum(v))값을 만족하되, 용량은 W을 넘지 않는 물건 조합을 고르는 것이다.  
 * 
 * 	-> 단순히, V가 높은 순대로 or W가 낮은 순대로.. 혹은 가성비(V/W) 순서로 오름차순 배열을 해서 풀려고 하면 무게 제한 때문에 최적해를 못찾는 참사가 발생함 
 * 
 * [다이나믹 프로그래밍에 사용되는 로직]
 *  1. MaxV_W(i,w) = 배낭 용량이 w이고, 물건1 ~ 물건i가 조건에 포함될 경우의 달성가능한 MAX(sum(v)) 의미
 *  				 (메서드(고려하는 물건배열들, 배낭용량) = 목표값 의미)
 *  2. MaxV_W 함수를 2차원 배열 MaxV_W[i,w]에 담는다고 가정 후(다이나믹 시전)
 *  3. 다이나믹 프로그래밍의 법칙 중, 함수(X) = 함수(x-1) + 연산시전 원칙 응용
 *  
 *     -> if 현재 1~i번까지의 물건을 고려하는 조건에서, 물건(i)를 선택하지 않는다면? 물건i는 최적해 선택지에서 없는거나 다르지 않음
 *     		-> MaxV_W[i, w] = MaxV_W[i-1, w]
 *     
 *     -> else 현재 1~i번까지의 물건을 고려하는 조건에서, 물건(i)를 선택한다면? i를 넣었을떄의 최대값과 안 넣었을 때의 최대값중 더 큰놈을 선택해야 함 
 *     		-> MaxV_W[i, w] = MAX(MaxV_W[i-1, w], vi + MaxV_W[i-1, w-wi]) 
 *     			 <- 물건 i를 고려하지 않는 최대v값 VS 물건i와 물건 i를 고려하지 않고 물건i의 무게를 배낭 무게에서 뺀 최대v값  -> 둘 중 큰 수를 다이나믹에 넣는다.
 *     
 *     -> 이라는 점을 명심해서 점화식 사용가능(top down)
 *     
 *  4. 2~3에 원칙에 따라 배열 초기화(x == 0 ->  MAX(MaxV_W[i-1, w] = 0)와 메서드 입력    
 *  
 *  (시간복잡도 : O(n	w) <- 두 배열의 길이인 n,w만큼의 2중 for문이기 때문)
 *   -> 다항시간(입력크기가 커질수록 더 커지는 수)과는 관계x  <- 무게값인 W는 단순한 숫자이지 그만큼 비트수가 더 필요한 배열 크기 같은걸 의미하는게 아니기 때문(2차원 배열 크기에는 개입하나 w만큼의 수를 저장하는것이 아님) 
 *  
 */
public class Example_Knapsack_Problem {
	
	// 각 물건들 별로 가치(v)와 무게(w)에 대한 정보를 개별적으로 입력받을 value, weight int배열로 생생
	static int value[];
	static int weight[];
	
	// 체크중인 x, y 배열 위치의 LCS의 길이를 2차원적으로 표현한 배열(물론 끝부분의 것은 무조건적인 최대값이다만, 어쨌든 top down 방식은 거슬러 내려가서 받는 값들을 저장해야함)
	static int dynamic[][];
	
	static int MaxV_W(int n, int w) {  // n = 총 물건의 갯수, w = 혀용되는 총 무게 배열의 길이
		
		// [2중 FOR문]
		for(int i = 1; i <= n; i++) { // 1번째 for문은 물건의 갯수 n에 해당하는 부분에 반복문을 대입 (n좌표 1부터)
			
			for(int j = 1; j <= w; j++) { // 2번째 for문은 총 무게에 w에 해당하는 부분에 반복문을 대입 (w좌표 1부터) <- 무게값인 W는 단순한 숫자이지 그만큼 비트수가 더 필요한 배열 크기 같은걸 의미하는게 아님 (= 다항시간과 관계 X)
				
				// 만약 해당 물건의 무게가 감당가능한 총무게(1~W까지 서서히 늘어남)보다 크다면..
				if(weight[i-1] > j) {	
					
					dynamic[i][j] = dynamic[i-1][j];	// 배낭에 넣길 고려하는 물건수 i와 감당예정 총 무게 j에 해당하는 DP는 그 물건을 고려하지 않는 DP(고려 물건 i-1, 총무게 j)와 같다. 
														// (다시 말해 그 물건의 무게는 감당할 총 무게보다 무거우니 있으나 마나다)
					
				}else {	// 그게 아니라는건 그 물건의 무게를 감당 가능한 선이라는 의미(= 넣을지 말지 고민할 가치가 있음 의미)
					
					// 그 물건 고려하지 않은 상태의 총무게 j의 DP(MAX 가치)  VS  해당 물건 가치 + 그 물건 고려하지 않은 상태의 총무게 (j-그 물건 무게)의 DP(MAX 가치) 중 -> 더 큰 거 선택
					dynamic[i][j] = Math.max(dynamic[i-1][j] , value[i-1] + dynamic[i-1][j - weight[i-1]]);
					
				}
				
			}
			
		}

		return dynamic[n][w];
		
	}
	
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		
		// (DP로 만들 차원으로 넣을) n = 총 물건의 갯수, w = 혀용되는 총 무게 배열의 길이를 입력한다
		int n = scanner.nextInt();	// 물건의 갯수
		int w = scanner.nextInt();	// 총 무게값
		
		// 각 물품 n개의 가치(value)와 무게(weight)의 정보를 각각 담을 배열 생성
		value = new int[n]; 	//  n개 물건의 가치를 담을 배열
		weight = new int[n]; 	//  n개 물건의 무게를 담을 배열
		
		// 각 물건들의 가치(value)와 무게값(weight)를 입력받는다.
		for(int j = 0; j < n; j++) {
			
			value[j] = scanner.nextInt();
			weight[j] = scanner.nextInt();
			
		}
		
		// DP테이블 생성 (물건의 갯수+1) * (총 무게의 크기+1) 만큼의 크기로..
		dynamic = new int[n+1][w+1];
		
		// 일단 DP의 단서가 될 dynamic[1][]을 채움(점화식의 시작)
		for(int j = 0; j <= w; j++) {
			
			// 만약 감당가능한 총 무게 j(1~w까지 올라감)가 1번쨰 물건의 무게[0]보다 높다면..
			if(j > weight[0]) {
			
				dynamic[1][j] = value[0];	// 물건 1만 고려하고 총무게 j까지 감당할 DP(MAX 가치)를 1번쨰 물건 가치값으로 갱신 
											// (그렇지 못한 경우는 0을 넣어야 하는데, 어차피 초기값이 0이니 조치는 필요없다)
			
			}
		}
		
		System.out.println(Arrays.deepToString(dynamic)); // knapsack 메서드 실행후 DP 출력
		
		// knapsack 실행
		System.out.println("현재 주어진 물품들중 가져갈 수 있는 물건들의 최대가치 : " + MaxV_W(n, w));
		
		System.out.println(Arrays.deepToString(dynamic)); // knapsack 메서드 실행후 DP 출력
		
		scanner.close();

	}

}
