package code09.ETC_Graphs;

import java.util.Scanner;

/* 개선된 서로소(공통원소 없는 두 집합) 찾기 이론(= 기존 합치기 찾기 + 경로압축)
 * -> 개선점(시간복잡도 : N보다 작음 by 경로 압축) 
 * 	 : 일단 한번 해당 경로의 부모를 순차적으로 타고 올라가는 과정을 수행하면,
 * 	     그 최종결과값인 해당 노드의 최고 선조를 기록하여, 추후 해당 노드의 부모를 다시 찾을때, 
 * 	     순차적으로 부모를 찾아 거슬러가는걸 방지하고, 바로 최선조를 대령함 (= 시간복잡도 줄어둠)
 * 
 * 
 * (경로 압축 알고리즘 : Find 찾기 함수를 재귀적 호출 -> 부모 테이블 갱신 = 다이나믹 프로그래밍)
 * 
 */
public class Example_improved_Each_Other {

    // 노드의 개수(V)와 간선(Union 연산)의 개수(E) >> 노드의 개수는 최대 100,000개라고 가정
    public static int v, e;
    public static int[] parent = new int[100001]; // 부모 테이블 초기화하기

    // 특정 원소가 속한 집합을 찾기
    public static int findParent(int x) {
    	
        // 루트노드 = x면 x를 리턴(parent[x]가 초기상태이면 x를 그대로 리턴해라
        if (x == parent[x]) {
        	
        	return x;
        	
        }
        
        /* 루트 노드가 아니라면(초기 입력된 배열값이 아니라면), 루트 노드를 찾을 때까지 재귀적으로 호출
         * (경로압축 적용 : 이를 통해 바로 고대 선조를 찾아서 갱신 가능  = 다이나믹 프로그래밍)
         */
        return parent[x] = findParent(parent[x]);	// 초기화 된 parent[x] = x -> findparent(parent[x])로 현재까지 찾은 최선조를 해당 배열에 갱신
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

        // Union 연산을 각각 수행
        for (int i = 0; i < e; i++) {
        	
            int a = sc.nextInt();
            int b = sc.nextInt();
            
            unionParent(a, b); //	 unionparent를 통해 두개의 숫자의 관계가 내부그룹으로서 추가됨
        }

        // 각 원소가 속한 집합 출력하기
        System.out.print("각 원소가 속한 집합: ");
        
        for (int i = 1; i <= v; i++) {
        	
            System.out.print(findParent(i) + " ");
            
        }
        
        System.out.println();

        // 부모 테이블 내용 출력하기
        System.out.print("부모 테이블: ");
        
        for (int i = 1; i <= v; i++) {
        	
            System.out.print(parent[i] + " ");
            
        }
        System.out.println();
        sc.close();
        
    }
    
}
