package code09.ETC_Graphs;

import java.util.*;


/* [크루스칼 알고리즘] ('무방향' 그래프 내에서 가장 적은 비용으로 연결된 '신장트리'를 찾기 위해 사용)
 *  : 싸이클이 존재할 경우? 를 상정한 다익스트라 알고리즘의 업그레이드 형(싸이클이 존재해도 최소노드 판별가능, 단 무방향임을 감안해야 함)
 *    -> 다익스트라(DP + 큐 + BUT 무방향성 상정 = 1단 ARRAYLIST 사용) + 싸이클 판별(서로소) 
 *    	  -> (시간복잡도 : 간선데이터를 거리에 따라 정렬시키는 ElogE) 
 * 
 * '신장트리' : 모든 노드가 포함되어 연결됨 + 싸이클이 존재X 트리 
 *    		-> 크루스칼 알고리즘에서 찾는 '신장트리' : 최소한의 연결로 이뤄졌지만, 모든 노드를 포함하고 비용도 가장 적은 그래프를 이루는 트리
 *    
 * 
 * <매커니즘> (일종의 그리드 알고리즘)
 * 1. 간선들을 거리를 기준으로 오름차순으로 나열(거리가 적은 쪽부터 나열) 
 * 2. 나열된 간선의 순서대로 구성된 노드들을 그래프(배열 및 연결리스트)에 추가함
 * 3. 도중에 싸이클 유발하는 간선
 *    (= 이미 내부 그룹의 일원으로 관계가 정립되었음에도 또 해당 내부그룹의 일원들로 구성된 간선들이 제시되는 경우)은 포함하지 않는다.
 * 4. 끝까지 수행하는 과정에서 각 노드가 하나의 내부 그룹의 일원으로 관계가 정립되는 순간(새로운 노드가 내부그룹의 일원이 되는 순간) 종료
 * 5. 해당 그래프에 들어간 간선들의 거리값을 합한 값을 출력한다.
 * 
 */

// [엣지 클래스 선언]
class Edge implements Comparable<Edge> {
	
    // 1. 거리
    private int distance;
    // 2. 노드a
    private int nodeA;
    // 3. 노드b
    private int nodeB;

    public Edge(int distance, int nodeA, int nodeB) {
        this.distance = distance;
        this.nodeA = nodeA;
        this.nodeB = nodeB;
    }

    public int getDistance() {
        return this.distance;
    }

    public int getNodeA() {
        return this.nodeA;
    }

    public int getNodeB() {
        return this.nodeB;
    }

    // 거리(비용)가 짧은 것이 높은 우선순위를 가지도록 설정
    @Override
    public int compareTo(Edge other) {
        if (this.distance < other.distance) {
            return -1;
        }
        return 1;
    }
}

public class Example_Kruskal {

    // 노드의 개수(V)와 간선(Union 연산)의 개수(E)
    // 노드의 개수는 최대 100,000개라고 가정
    public static int v, e;
    
    /* 각 노드들의 최고 선조(연결되어 있다면 가장 작은 수 일수록 선조 취급) 기록용 테이블
      	-> 간선 연결시 싸이클의 존재여부를 파악할 용도일뿐, 어차피 '신장트리'의 조건인 '모든 노드 연결'이 만족되면 다 동일한 최고 선조를 가진다 */    
    public static int[] parent = new int[100001]; 

    // 모든 간선을 담을 리스트와, 최종 비용을 담을 변수(2중 ARRAYLIST가 아님 = 방향성 그래프X = 무방향 그래프 상정)
    public static ArrayList<Edge> edges = new ArrayList<>();
    
    // '신장트리' 조건 만족하는 솔루션 중, 최소 간선 길이의 합을 담는 변수
    public static int result = 0;

    // 특정 원소가 속한 집합을 찾기
    public static int findParent(int x) {
    	
    	// 루트노드 = x면 x를 리턴(parent[x]가 초기상태이면 x를 그대로 리턴해라
        if (x == parent[x]) {
        	
        	return x;
        	
        }
        
        /* 루트 노드가 아니라면(초기 입력된 배열값이 아니라면), 루트 노드를 찾을 때까지 재귀적으로 호출
         * (경로압축 적용 : 이를 통해 바로 고대 선조를 찾아서 갱신 가능  = 다이나믹 프로그래밍)
         */
        return parent[x] = findParent(parent[x]);
    }

    // 두 원소가 속한 집합을 합치기(이를 통해 parent[x]의 최선조가 존재할 경우 parent 배열에 갱신함)
    // -> 해당 메서드를 통해, 간선을 이루는 노드 간의 관계 정림
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

        // 노드의 개수(V)와
        v = sc.nextInt();
        // 간선(Union 연산)의 개수(E)
        e = sc.nextInt();

        // 부모 테이블상에서, 부모를 자기 자신으로 초기화(parent[x] = x로)
        for (int i = 1; i <= v; i++) {
        	
            parent[i] = i;
            
        }

        // 모든 간선에 대한 정보를 입력 받기
        for (int i = 0; i < e; i++) {
        	
            int a = sc.nextInt();
            int b = sc.nextInt();
            int cost = sc.nextInt();
            
            // 해당 제시된 변수를 각각 (거리, 노드1, 노드2)로 edges 배열에 추가
            edges.add(new Edge(cost, a, b));
        }

        // 간선을 비용순으로 정렬(크루스칼 알고리즘의 핵심)
        Collections.sort(edges);

        // 간선을 하나씩 확인하며
        for (int i = 0; i < edges.size(); i++) {
        	
            int cost = edges.get(i).getDistance();
            int a = edges.get(i).getNodeA();
            int b = edges.get(i).getNodeB();
            
            // 사이클이 발생하지 않는 경우에만 집합에 포함(새로 선언된 노드라면 unionparent 거치기 전이므로, 그 내용으로 갱신되는 경우에는 넘어감)
            if (findParent(a) != findParent(b)) {
            	
            	// 사이클이 발생하지 않았다면 합집합(Union) 연산 수행(노드간 관계 수립 = unionparent 메서드 실행)
         	    // -> 숫자가 처음 제시된 경우는 아직 최선조가 갱신되기 전인 parent[x] = x의 경우로서, 
         	    //	  unionparent를 통해 두개의 숫자의 관계가 내부그룹으로서 추가됨(unionparent가 관계정립함수니, 싸이클이 존재하면 또 정립할 이유x)
            	
                unionParent(a, b);
                result += cost;	// 총 간선비용에 해당 노드 길이 추가
                
            }
        }

        System.out.println(result);	// 크루스칼 알고리즘의 결과값(총 간선의 길이) 도출
        
        sc.close();
    }

}
