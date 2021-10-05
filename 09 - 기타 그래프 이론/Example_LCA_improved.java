package code09.ETC_Graphs;

import java.util.ArrayList;
import java.util.Scanner;

/* [LCA(Lowest Common Ancestor : 최소공통조상 찾기 알고리즘)] : 말그대로 두 원소가 동시에 속한 선조 중 가장 가까운 대의 선조를 찾는 알고리즘

   <향상된 매커니즘 (2진 트리 구조로 그래프가 짜여있다는 가정 필요)> 
   : 반복 + 다이나믹 프로그래밍을 2차원으로 확대해서, 각 노드의 직부모 뿐 아니라, 2^i번째 부모(2진트리 그래프 구조하의 최대높이)에 대한 정보까지 기록함
      -> 2^0 = 1, 2^1 = 2므로, 최소 해당 원소의 직선조와 조선조까지는 확인가능 (= 그래프를 이루는 간선의 수가 100000개 이상인 경우 시간복잡성에 문제가 생기는부분 보완)
	 
	1. 최선조는 무조건 1번이라고 가정
	2. 최선조인 1번을 기준 모든 노드의 깊이를 각 노드당 2의 제곱 단위로 높이의 최대값인 LOG(2)N개까지 계산(총 N * LOG(2)N 크기의 배열)(dfs를 사용해 [1~N][0]을 구함 + 2차원 배열 다이나믹 프로그래밍)
	3. 대상이 될 두개의 노드를 확인
	4. 해당 노드의 깊이가 동일할때까지 거슬로 올라감(LOG(2)N번 반복 + 조건문)
	5. 4번 작업이 끝나서 두 원소의 대가 같은 위치에 있으면, 부모가 같아질때까지 또 계속 올라감(LOG(2)N번 반복 + 조건문)
	6. 3~4를 만족할떄까지 반복


    <향상된 녀석의 최악의 시간복잡도>
    : M * logN (글자 거슬러 올라가는 속도가 2의 제곱으로 빨라졌기에 가능)
*/
public class Example_LCA_improved {


	// 그래프를 구성한 간선의 갯수    n
	static int n;
	// 구할 케이스의 수 m
	static int m;
	
	// 각 노드의 부모들에 대한 정보(2의 배수로 올라갈거므로 최대 LOG(2)N만큼의 부모값을 저장할 공간을 2차원에 준비)
	static int parent[][] = new int[100001][21]; // 21은 LOG(2)1000000 = 20의 값보다 바로 큰 수
	
	// 최선조1번에서 각 노드까지의 깊이(depth)
	static int d[] = new int[100001]; 
	
	// 각 노드의 깊이가 계산되었는지 여부
	static boolean c[] = new boolean[100001]; 
	
	// 방향성 그래프(graph) 정보(2중 arraylisy 쓰는 이유)
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
	
	// 루트 노드부터 시작하여 깊이(depth)를 구하는 함수
	static void dfs(int x, int depth) {
		
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
	        // 연결된 위치 y의 직부모 x를 2차원 배열에 해당 행의 1열에 입력
	        parent[y][0] = x;
	        
	        // 해당 y의 위치와 이전에 입력된 깊이+1을 해당 y의 깊이로 입력하고 재귀문..
	        dfs(y, depth + 1);
	        
	    }
	    
	}
	
	 // 2. 전체 부모 관계를 설정하는 함수(해당 메서드를 통해 DFS 수행 + 2차원 배열에 각 노드의 선조정보 기록)
	static void setParent() {
		
	    dfs(1, 0); // DFS를 통해서, N번 노드의 직선조까지 구한다(루트 노드는 1번 노드)
	    
	    // 그 뒤 N*LOG(2)N 2차원 다이나믹 프로그래밍 정보를 채운다
	    for (int i = 1; i < 21; i++) {
	    	
	        for (int j = 1; j <= n; j++) {
	        	
	        	// j의 i+1번째 선조의 값 = j의 i번째 선조(= j의 i+1번째 선조의 아들)의 i번쨰 선조로 갱신
	            parent[j][i] = parent[parent[j][i - 1]][i - 1];
	            
	        }
	    }
	}
	
	// 4,5. A와 B의 최소 공통 조상을 찾는 함수
	static int lca(int a, int b) {
		
	    // 2번쨰 제시된 쪽의 수의 깊이가 더 깊도록 설정(4.의 코딩의 편의를 위한 배치)
	    if(d[a] > d[b]) {
	    	
	    	// 1번쨰 수인 a쪽의 깊이가 더 크면, 두 수의 순서를 swap한다
	    	int tmp = a;
	    	a = b;
	    	b = tmp;
	    	
	        
	    }
	    // 4. 먼저 깊이(depth)가 동일한 노드 값이 세팅되도록 설정(첫번쨰와 두번째 수의 깊이가 실존한다면, 그 차이만큼에 해당하는 b의 선조값을 불러와서 깊이값을 맞춘다.. 이래서 높이값인 log(2)N이 필요
	    for(int i = 21 - 1; i >= 0; i--) {
	    	
	    	// 만약 두번째수의 깊이 - 첫번쨰 수의 깊이가 20~0까지의 수보다 크거나 같다면(두번쨰와 첫번쨰의 깊이차가 존재한다면)
	        if(d[b] - d[a] >= i) {
	        	
	        	// 각 노드의 부모노드를 기록하는 b행의 그 깊이의 차이만큼의 크기인 i의 열에 해당하는 값이 b로 정의(두번쨰 수인 b는 b의 i+1번쨰 선조노드로 갱신)
	            b = parent[b][i];
	            
	        }
	    }
	    
	    // 5. 두 수 A,B의 공통선조가 발결될 때까지 계속
	    // - 일단 현재 소환된 A,B의 같은 대의 선조들이 같은 값이면 여기서 A를 출력해서 끝냄
	    if(a == b) {
	    	
	    	return a;
	    	
	    }
	    
	    // - 그게 아니라면, 
	    for(int i = 21 - 1; i >= 0; i--) {
	    	
	        // 조상을 향해 거슬러 올라가기(공통조상이 발견되지 않는다면, 각 수의 다음 높이값(2^i번쨰 이후의 값)에 해당하는 선조값을 본다)
	        if(parent[a][i] != parent[b][i]) {
	        	
	            a = parent[a][i];
	            b = parent[b][i];
	            
	        }
	    }
	    // 그 결과로 발견된 공통조상을 출력해서 끝냄
	    return parent[a][0];
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
	    
	    setParent();
	   
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
