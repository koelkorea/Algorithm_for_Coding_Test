package code09.ETC_Graphs;

import java.util.ArrayList;
import java.util.Scanner;

/* [LCA(Lowest Common Ancestor : 최소공통조상 찾기 알고리즘)] : 말그대로 두 원소가 동시에 속한 선조 중 가장 가까운 대의 선조를 찾는 알고리즘

   <매커니즘> : 재귀를 사용한 dfs + 다이나믹 프로그래밍을 통해 최선조 1번부터 모든 노드를 알아내고 난 뒤, 이를 베이스로 재쉬로 거슬러 올라감
   
	1. 최선조는 무조건 1번이라고 가정
	2. 최선조인 1번을 기준 모든 노드의 깊이를 계산(dfs를 사용 + 다이나믹 프로그래밍)
	3. 대상이 될 두개의 노드를 확인
	4. 해당 노드의 깊이가 동일할때까지 거슬로 올라감(dfs를 사용)
	5. 4번 작업이 끝나서 두 원소의 대가 같은 위치에 있으면, 부모가 같아질때까지 또 계속 올라감(dfs를 사용)
	6. 3~5를 만족할떄까지 반복

   <기본 녀석의 최악의 시간복잡도> (N : 글자수, M : 케이스 수)
   : N*M (결과적으로 공통조상 찾을떄까지 곧이 곧대로 올라가야하는데, 그냥 세로 1열로 나열되면 N개 다 돌아봐야함)

*/
public class Example_LCA_basic {

	// 그래프를 구성한 간선의 갯수    n
	static int n;
	// 구할 케이스의 수 m
	static int m;
	
	// 각 노드의 직부모에 대한 정보
	static int parent[] = new int[100001]; 
	
	// 최선조1번에서 각 노드까지의 깊이(depth)
	static int d[] = new int[100001]; 
	
	// 각 노드의 깊이가 계산되었는지 여부
	static boolean c[] = new boolean[100001]; 
	
	// 방향성 그래프(graph) 정보(2중 arraylisy 쓰는 이유)
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
	
	// 4. 루트 노드(1번노드의 깊이는 0을 입력)부터 시작하여 깊이(depth)를 구하는 함수(위치에서 조건을 만족할떄까지 계속 반복함 = dfs)
	static void dfs(int x, int depth) {	// 누가 뭐래도 1,0이 들어감
		
		// 루트 노드 x는 깊이가 계산됨
	    c[x] = true;
	    // 입력된 깊이인 depth를 깊이를 기록하는 d배열에 입력
	    d[x] = depth;
	    
	    // 본격적인 재귀를 통한 dfs탐색으로 입력될 예정인 루트노드 1번과 연결된 노드의 부모를 기록하고 dfs로 파고듬
	    for (int i = 0; i < graph.get(x).size(); i++) {
	    	
	    	// 1번과 연결된 다른 노드의 위치를 받는다.
	        int y = graph.get(x).get(i);
	        
	        // 그 깊이가 이미 구해졌음 넘김
	        if (c[y] == true) {
	        	
	        	continue; // 이미 깊이를 구했다면 넘기기
	        	
	        }
	        
	        // 연결된 위치 y의 부모는 x로 기록
	        parent[y] = x;
	        
	        // 해당 y의 위치와 이전에 입력된 깊이+1을 해당 y의 깊이로 입력하고 재귀문..
	        dfs(y, depth + 1);
	        
	    }
	    
	}
	
	// 5. A와 B의 최소 공통 조상을 찾는 함수
	// -> 동일한 깊이 추출
	// -> 노드가 같아질떄까지 선조 소환(최소공통선조)
	static int lca(int a, int b) {
		
	    // 먼저 두 수의 선조포함 깊이(depth)가 동일하도록  
		// (두 수의 깊이 중 깊이가 더 큰쪽의 경우 지속적으로 그 부모의 깊이로 갱신해서, 양자의 선조들의 최종 출력 깊이가 같을때까지 반복)
	    while (d[a] != d[b]) { 
	    	
	    	// a가 더 깊으면, a를 a의 부모로 갱신해서 그 깊이를 계속한다.
	        if (d[a] > d[b]) {
	        	
	            a = parent[a];
	            
	        }else { // b가 더 깊으면, b를 b의 부모로 갱신해서 그 깊이를 계속한다.
	        	
	        	b = parent[b];
	        	
	        }
	        
	    }
	    
	    // 노드가 같아질때까지 양자의 선조 소환(최소공통조상 찾기)
	    while (a != b) {
	    	
	        a = parent[a];
	        b = parent[b];
	        
	    }
	    return a;
	}
	
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		
		// 간선의 수 n 입력
		n = scan.nextInt();
		
        // 그래프 초기화
        for (int i = 0; i <= n; i++) {
        	
            graph.add(new ArrayList<Integer>());
            
        }
		
        // 그래프 정보 입력
	    for (int i = 0; i < n - 1; i++) {
	    	
	        int a = scan.nextInt();
	        int b = scan.nextInt();
	        
	        // 2중 arraylisy로 애초에 방향성으로 기획되긴 했는데, 어차피 최선조 노드는 1번이고, 목적은 그 최선조를 기준으로 깊이를 구하는거라 양방향입력도 딱히 상관없음
	        graph.get(a).add(b);
	        graph.get(b).add(a);
	        
	    }
	    
	    dfs(1, 0); // 루트 노드는 1번 노드(여기서부터 모든 노드의 깊이 찾기 시전)

	    // 케이스의 수 m입력
	    m = scan.nextInt();
	    
	    // 케이스를 구성할 lca를 구하고자 하는 수 2개 입력
	    for (int i = 0; i < m; i++) {
	    	
	        int a = scan.nextInt();
	        int b = scan.nextInt();
	        
	        System.out.println(a + "와" + b +"의 최소공통조상 : " + lca(a, b));
	        
	    }
	    
	    scan.close();
		

	}

}
