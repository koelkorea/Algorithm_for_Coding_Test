package code10.Others;

import java.util.Arrays;
import java.util.Scanner;

/* 2진트리를 사용한 구간합 알고리즘(BIT 펜윅트리) : 만약 구간이 도중이 변경될 경우에도 시간복잡도가 이전처럼 유지될수 있도록 함
 *						 (2진법 인덱스 구조를 활용, 구간합 문제 효과적 해결)
 *  -> 시간복잡도 : (m+k)*[log(2)n]) : arr갱신이나 구간합을 구하나 전부 [log(2)n]의 속도가 해결이 가능하기 때문
 *
 *
 * (구간합 문제에서의 2진법 index 구조)
 * -> 간단히 말해서 tree구조를 이루는 배열이 arr의 배치와 상호연계됨 (= arr가 변하면 즉각적으로 변화하게 구조가 설계) 
 *    index가 2^n (n은 0부터)에 위치한 tree배열은 arr 배열의 1~2^n까지의 합에 해당하는 녀석을 저장,
 *    index가 홀수인 경우에 위치한 tree배열은 arr 배열과 같은 값을 취하며
 *    그외의 index는 현재 자신의 index의 값 + 그전 index의 값에 해당하는 값을 가진다.
 *    
 *    ex) arr = {1,2,3,4,5,6,7,8} 에 해당하는 경우
 *    	  tree = {1,3,3,10,5,11,7,36}
 *    
 *   	  2진트리로 보여주면...
 *    
 *		 							1
 *		 				1+2					3
 *		 			1+2+3+4		5		5+6			7
 *			 1+2...+7+8
 *     
 * 	  	   -> 여기서 해당 트리의 index를 2진법으로 변환한 K를 통해 (K & -K)만큼 INDEX를 거슬러 올라가며 해당하는 tree값을 더할경우.. 
 * 			   귀신같이 arr[1]~[해당 tree index]까지의 합을 구할수 있다. 
 * 			  (본 코드에서는 prefixSum 메서드로 명명하여, 구간합 구할때 사용할 부분 메서드로 이용함)
 * 
 * 
 * 
 * (K & -K) = K(2)의 가장 작은 위치의 1
 *  -> 여기서 K와 -K는 2진법으로 표기된 것이고, 그에 따라 -i는 i의 2의 보수 = (i & -i)는 i의 2진법의 가장 끝에 위치한 1의 값이다
 *	   ex) 6의 2진법 = 110(2) -> (6 & -6) == (110(2) & 010(2)) = 010(2) = 2
 * 
 */ 
public class Example_Binary_Index_Tree_with_interval_Sum {

	// 전체 데이터의 개수는 최대 1,000,000개 (1000001인 이유는 0번쨰 자리를 안 쓰기 때문)
	
	// 각 데이터를 입력받을 배열
	static long arr[] = new long [1000001]; 
	// arr의 구조에 따라 상단에 설명된 2진 트리의 법칙으로 연동할 tree
	static long tree[] = new long[1000001];
	
	// 데이터의 개수(n), 
	static int n;
	// 변경 횟수(m)
	static int m;
	// 구간 합 계산 횟수(k) = 케이스의 수
	static int k;

	// i번째 수까지의 누적 합을 계산하는 함수(intervalSum을 위한 부분 함수)
	static long prefixSum(int i) {
		
	    long result = 0;
	    
	    // tree[i] -> (k&-k)에 따른 tree배열의 값 더함 -> tree의 index가 [2의 배수]가 될떄까지 반복(= 그 순간 i = (i & -i)이므로  i + (i & -i) = 0이 되기에 연산 종료 + 누적합 구하기 완료)	
	    while(i > 0) {
	    	
	        result += tree[i];
	        
	        // 0이 아닌 마지막 비트만큼 빼가면서 이동 (2진법 인덱스 단위) -> 이를 통해 누적합을 구하기 위한 방향으로 tree 배열을 거친다(정확한 설명은 상단에 게시)
	        // 사실상 (가우스부호 : 정수화)[log(2)n] 수준으로 값을 구할수 있다
	        i -= (i & -i);	
	        
	    }
	    return result;
	} 
	
	// start부터 end까지의 구간 합을 계산하는 함수 ([log(2)n]수준의 연산을 2번 반복 = 2log(2)n)
	static long intervalSum(int start, int end) {
		
	    return prefixSum(end) - prefixSum(start - 1);
	    
	}

	// tree배열을 갱신하는 메서드(i번째 수를 dif만큼 더하고 -> tree의 모든 인덱스 중 포함될 녀석이 전부 포함될 때까지 반복됨 -> 이를 통해 arr배열의 변화가 tree배열의 변화와 연동)
	static void update(int i, long dif) {
		
	    while(i <= n) {

	        tree[i] += dif;
	        
	        // for문의 i에 0이 아닌 마지막 비트만큼 더하면서 이동 (2진법 인덱스 단위)
	        // 이 역시도 사실상 (가우스부호 : 정수화)[log(2)n] 수준으로 빠르게 tree 배열의 값을 갱신가능하다 
	        i += (i & -i);
	        
	    }
	    
	}

	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		// 데이터의 개수(n), 
		n = scan.nextInt();
		// 변경 횟수(m), 
		m = scan.nextInt();
		// 구간 합 계산 횟수(k)
		k = scan.nextInt();
	    
		// arr값 입력(n번만큼) + arr의 구간합을 기록하는 tree 배열의 값 또한 각 arr[i]값으로 초기화 시전
	    for(int i = 1; i <= n; i++) {
	    	
	    	// x값 입력 -> arr[i]에 입력
	        long x = scan.nextLong();
	        arr[i] = x;
	        
	        // 입력된 arr배열의 내용을 tree값에 연동함
	        update(i, x);
	        
	        System.out.println("tree배열 : " + Arrays.toString(tree));
	        
	    }
	    
	    int count = 0;
	    
	    // m+k번 연산을 반복(갱신하나 구간합 구하나 전부 log(2)n의 속도다)
	    while(count++ < m + k) {
	    	
	    	System.out.println("갱신할까요?");
	    	
	    	// op값에 1을 입력하면 arr의 값을 갱신한다는걸 의미(그게 아니면 구간 합)
	        int op = scan.nextInt();
	        
	        // 업데이트(update) 연산인 경우
	        if(op == 1) {
	        	
	        	System.out.println("갱신할 arr값의 주소와 값을 넣어주세요");
	        	
	        	// 갱신할 arr의 주소
	            int index = scan.nextInt();
	            // 갱신할 arr[index]에 넣을 값
	            long value = scan.nextLong();
	            
	            // 갱신한 arr의 값 만큼, tree 배열에도 이것이 연계되게 함 
	            // -> tree[index]의 값을 새로운 arr[index]인 value - 기존 arr[index] 만큼 + or - 하여 기록 (변화량을 캐치해서 tree값에 반영하는 매카니즘)
	            update(index, value - arr[index]); 
	            
	            // i번째 수를 value로 업데이트
	            arr[index] = value; 
	            
	            System.out.println("갱신된 arr배열 : " + Arrays.toString(arr));
	            System.out.println("갱신된 tree배열 : " + Arrays.toString(tree));
	            
	        }
	        // 구간 합(interval sum) 연산인 경우
	        else {
	        	
	        	System.out.println("구하고 싶은 구간합의 시작점~끝점을 쓰세요");
	        	
	        	// 구간합의 시작위치 입력
	            int start = scan.nextInt(); 
	            // 구간합의 끝위치 입력
	            int end = scan.nextInt();

	            System.out.println(intervalSum(start, end));
	            
	        }
	    }
	    
	    scan.close();

	}

}
