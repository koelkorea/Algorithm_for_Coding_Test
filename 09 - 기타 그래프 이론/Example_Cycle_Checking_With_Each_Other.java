package code09.ETC_Graphs;

import java.util.Scanner;

/* 싸이클 판별하기(개선된 서로소(공통원소 없는 두 집합) 찾기 이론 발전형)
 *  
 * [매커니즘]
 *  : 제시된 노드를 이루는 두개의 수가 이미 같은 최고 선조를 두는 내부 그룹의 일원이면, 
 *    그건 싸이클이 발생했음을 의미함(내부그룹 = 싸이클을 이루는 노드의 일원)
 *	-> 단 결론적으로 해당 노드가 싸이클에 속하더라도, 
 *	   노드가 처음으로 간선 관계를 정의하는 경우에 선언되는 순간에는 
 *	   최고 선조가 자기자신(parent[x] = x)이므로 내부그룹의 일부라는 관계를 수립함
 * 
 *  1. 각 간선을 하나씩 확인하고, 그 간선을 구성하는 노드 2개의 최선조를 확인
 *  	- IF 최선조가 다르면, Union
 *  	- else IF 최선조가 같다면, 싸이클이 발생함
 *  2. 1을 반복함
 *  
 *  [주의점] : 무방향 그래프 내에서의 싸이클 판별에 사용(방향 그래프 내에서는 DFS를 활용)
 * 
 * (경로 압축 알고리즘 : Find 찾기 함수를 재귀적 호출 -> 부모 테이블 갱신 = 다이나믹 프로그래밍)
 * 
 */
public class Example_Cycle_Checking_With_Each_Other {

    // 노드의 개수(V)와 간선(Union 연산)의 개수(E) >> 노드의 개수는 최대 100,000개라고 가정
    public static int v, e;
    // 각 요소의 최고 선조 테이블 초기화하기
    public static int[] parent = new int[100001]; 

    // 특정 원소가 속한 집합을 찾기
    public static int findParent(int x) {
    	
    	// 루트노드 = x면 x를 리턴(parent[x]가 초기상태이면 x를 그대로 리턴해라
        if (x == parent[x]) {
        	
        	return x;
        	
        }
        
        /* 루트 노드가 아니라면(초기 입력된 배열값이 아니라면), 루트 노드를 찾을 때까지 재귀적으로 호출
         * (경로압축 적용 : 이를 통해 바로 최고 고대 선조를 찾아서 갱신 가능 = 다이나믹 프로그래밍)
         */
        return parent[x] = findParent(parent[x]);// 초기화 된 parent[x] = x -> findparent(parent[x])로 현재까지 찾은 최선조를 해당 배열에 갱신
        // return findParent(parent[x]);
    }

    // 두 원소가 속한 집합을 합치기(이를 통해 parent[x]의 최선조가 존재할 경우 parent 배열에 갱신함)
    // -> 해당 메서드를 통해, 간선을 이루는 노드 간의 관계 정립
    public static void unionParent(int a, int b) {
    	
        a = findParent(a);
        b = findParent(b);
        
        // 두 a,b값의 선조값 중 작은 값을 가진 쪽으로 다른 놈의 선조값도 갱신한다.
        if (a < b) {
        	
        	parent[b] = a;
        
        }else {
        
        	parent[a] = b;
        
        }
    }

    public static void main(String[] args) {
    	
        Scanner sc = new Scanner(System.in);

        // 노드의 개수(V)와
        v = sc.nextInt();
        // 간선(Union 연산)의 개수(E)
        e = sc.nextInt();

        // 부모 테이블상에서, 부모를 자기 자신으로 초기화(parent[x] = x로)
        for (int i = 1; i <= v; i++) {
        	
            parent[i] = i;
            
        }

        boolean cycle = false; // 사이클 발생 여부 (기본적으로 발생하지 않았다고 가장)

        for (int i = 0; i < e; i++) {
        	
            int a = sc.nextInt();
            int b = sc.nextInt(); // (여기까지는 일반 서로소 판별 알고리즘과 유사)
            
            // 사이클이 발생한 경우 종료(제시된 수 a,b의 공통조상이 같은 경우 = 같은 그룹에 속한경우 = 순환고리에 있음 의미)
            // -> 그러니까 연산 중 제시된 두개의 수가 같은 그룹(= 연결되어있음)이라는건 내부 그룹에서 돌고 돈다는 말이다
            //	  (전체 흐름 상, 늦던 빠르던 싸이클이 되는 경우는 결국 제시된 수들이 내부 그룹임이 밝혀지기 마련이다)
            //	    
            if (findParent(a) == findParent(b)) { // a,b의 내부조상이 이미 같은것으로 판명되는 경우
            					  //(새로 선언된 노드라면 unionparent 거치기 전이므로, 그 내용으로 갱신되는 경우에는 넘어감)
            	
            	// 싸이클이 발생했음을 선언하고 
                cycle = true;
                break;	// 순환문 종료
                
            }else {// 사이클이 발생하지 않았다면 합집합(Union) 연산 수행(노드간 관계 수립 = unionparent 메서드 실행)
            	   // -> 숫자가 처음 제시된 경우는 아직 최선조가 갱신되기 전인 parent[x] = x의 경우로서, 
            	   //	 unionparent를 통해 두개의 숫자의 관계가 내부그룹으로서 추가됨(unionparent가 관계정립함수니, 싸이클이 존재하면 또 정립할 이유x)
            	
                unionParent(a, b);
            }
        }

        if (cycle) {
        	
            System.out.println("사이클이 발생했습니다.");
            
        }else {
        	
            System.out.println("사이클이 발생하지 않았습니다.");
            
        }
        
        sc.close();
    }
    
}
