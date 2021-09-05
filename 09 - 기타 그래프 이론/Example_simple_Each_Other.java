package code09.ETC_Graphs;

import java.util.Scanner;

/* 간단한 서로소(공통원소 없는 두 집합) 찾기 이론( = 합치기 찾기 자료구조)
 * (시간복잡도 : N) 
 *
 * 
 * (기본 메커니즘)
 * - Union(합집합) : 두개의 원소가 포함된 집합을 하나의 집합으로 합치는 연산
 * - Find(찾기) : 특정한 원소가 속한 집합이 어떤 집합인지 알려주는 연산
 * 
 * (작동 구조)
 * 1. Union 함수를 통해 주어진 조건의 합집합(간선의 수)을 검토
 * 	  - Find를 통해 주어진 합집합 내의 노드들의 부모를 찾고, 갱신한다.
 * 2. 모든  제시된 합집합들이 다 끝날때까지 반복
 * 3. 출력하면 각 자리에 해당하는 수의 부모되는 녀석이 출력되고, 
 * 	   이를 통해 해당 원소가 포함된 집단의 가장 작은 수를 찾고, 어떻게 집단이 구성되는지 확인 가능
 */
public class Example_simple_Each_Other {

    // 노드의 개수(V)와 간선(Union 연산)의 개수(E)  >> 노드의 개수는 최대 100,000개라고 가정
    public static int v, e;
    public static int[] parent = new int[100001]; // 부모 테이블 초기화하기

    // 특정 원소가 속한 집합을 찾기(원래 부모를 찾기)
    public static int findParent(int x) {
    	
        // 만약 들어온 x가 x의 부모라면 x를 호출
        if (x == parent[x]) {
        	
        	return x;
        	
        }
        // 루트 노드가 아니라면(초기 입력된 배열값이 아니라면), 루트 노드를 찾을 때까지 그 부모의 부모들을 재귀적으로 호출
        return findParent(parent[x]);
        
    }

    // 두 원소가 속한 집합을 합치기(두 수의 원래 부모 중 작은 녀석을 부모로 통일함)
    public static void unionParent(int a, int b) {
    	
        a = findParent(a);
        b = findParent(b);
        
        if (a < b) {
        	
        	parent[b] = a;
        	
        }else {
        	
        	parent[a] = b;
        	
        }
        
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 노드의 총 갯수
        v = sc.nextInt();
        // 주어진 합집합의 수
        e = sc.nextInt();

        // 부모 테이블상에서, 부모를 자기 자신으로 초기화
        for (int i = 1; i <= v; i++) {
        	
            parent[i] = i;
            
        }

        // Union 연산을 각각 수행
        for (int i = 0; i < e; i++) {
        	
            int a = sc.nextInt();
            int b = sc.nextInt();
            
            unionParent(a, b);
            
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
