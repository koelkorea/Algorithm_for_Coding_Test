package code08.Shortest_Route;

import java.util.Arrays;
import java.util.Scanner;

/* [벨만-포드 알고리즘2 : 배열로 구현]
   : 간선의 갯수인 M만큼만 훍어보면 되는 1번째 방법인 2중 for문(N*M)에 비해 3중 for문(N*2차원 그래프 X축*2차원 그래프 Y축) 실행으로 시간복잡도가 높다... 

   시간복잡도 계산 (N : 노드개수, M : 간선개수, 501 : 2차원 그래프 축 하나의 길이(배열을 통해 구현했음으로 정해진 길이가 필요했음))
    - 오리지날 벨만-포드 : N*M
    - 배열 벨만-포드	   : N*501*501 

   [벨만-포드 알고리즘?] 
   : 현재 start 지점에서 방향성을 가지는 길을 통한 모든 위치로의 최단루트값을 담은 배열을 구하는 알고리즘
     (다익스트라 알로리즘 + 간선 비용 음수 존재까지 가정 = 다익스트라 확장판) 
     
   오리지날 벨만-포드 시간 복잡도
   : 음수까지 처리 가능하나 다익스트라 E * logV 보단 느리다 
   
   <음의 싸이클 판정 매커니즘> : 마지막 순간까지도 계속해서 각 노드의 최단거리 기록 DP가 갱신되는 경우를 잡아냄(음의 싸이클 말고 그럴일이 없음)
   
*/
public class Example_Bellman_Ford_with_array {
	
    public static final int INF = (int) 1e9; // 무한을 의미하는 값으로 10억을 설정
	
    // 노드의 개수(N), 간선의 개수(M)  // 노드의 개수는 최대500개라고 가정
    public static int n, m;
    
    // 각 노드에 연결되어 있는 노드에 대한 정보를 담는 배열(좌표형식의 2중 ARRAYLIST를 사용하는 대신 따로 시작, 끝, 비용 값이 수록된 POINT값의 내용을 지정한 그래프로 접근)
    public static int[][] edges = new int [500][500];

    // 각 노드의 최단 거리 테이블 만들기
    public static int[] d = new int[501];
    
    static boolean bellmanford(int start) {
    	
    	// 시작 노드에 대해서 초기화
    	d[start] = 0;
    	
        // 전체 n - 1번의 라운드(round)를 반복
        for (int x = 0; x < n; x++) {
        	
	        // 전체 n - 1번의 라운드(round)를 반복
	        for (int i = 0; i < edges.length; i++) {
	        	
	            // 매 반복마다 "모든 간선"을 확인하며
	            for (int j = 0; j < edges.length; j++) {
	            	
	            	// 현재 간선의 시작 지점(1번째 입력값부터 순차로 돈다)
	                int current_point = i;
	                // 현재 간선의 끝 지점(1번째 입력값부터 순차로 돈다)
	                int next_point = j;
	                // 현재 간선의 비용(거리)
	                int edge_cost = edges[i][j];
	                
	                // 조건1) 만약, 현재 간선을 거쳐서 다른 노드로 이동하는 거리가 기존에 알고 있던 시작지점~현재노드까지의 거리보다 더 짧은 경우
	                if (d[current_point] != INF && d[next_point] > d[current_point] + edge_cost) {
	                	
	                	// 다익스트라처럼 시작지점~현재노드까지의 최소거리 갱신
	                    d[next_point] = d[current_point] + edge_cost;
	                    
	                    // 조건2) 만약, 1번쨰 조건을 만족해서 갱신했는데, 
	                    //		  이미 나올거 다 나왔다고 판명된 n번째 라운드에서도 값이 갱신된다면 음수 순환이 존재 (이 부분이 벨만포드의 차별점)
	                    if (x == n-1) {
	                    	
	                    	return true;
	                    	
	                    }
	                    
	                }
	                
	            }
	            
	        }
            
        }
        
        return false;
        
    }
    
	public static void main(String[] args) {
		
        Scanner sc = new Scanner(System.in);

        // 노드의 개수(N)
        n = sc.nextInt();
        //간선의 개수(M)
        m = sc.nextInt();
        
        // 좌표별 거리정보 무한으로 초기화
	for(int i = 0; i < n; i++) {
			
		Arrays.fill(edges[i], INF);
	        
	        
	}
        
	//System.out.println(Arrays.deepToString(edges));
		
        // 모든 간선 정보 및 거리를 입력받기
        for (int i = 0; i < m; i++) {
        	
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            
            // a번 노드에서 b번 노드로 가는 비용이 c라는 의미
            edges[a-1][b-1] = c;
            
        }
        
        // 최단 거리 테이블을 모두 무한으로 초기화
        Arrays.fill(d, INF);

        // 벨만 포드 알고리즘을 수행
        boolean negative_cycle = bellmanford(0); // 1번 노드가 시작 노드(배열은 0부터)

        // 만약 음의 간선 무한루프가 존재한다면...
        if (negative_cycle == true) {
        	
        	System.out.println("해당 조건의 그래프는 음수 순환이 존재하는 그래프입니다.");
            
        }
        
        // 1번 노드를 제외한 다른 모든 노드로 가기 위한 최단 거리를 출력
        for (int i = 2; i <= n; i++) {
        	
            // 도달할 수 없는 경우, -1을 출력
            if (d[i-1] == INF) {
            	
            	System.out.println("해당노드 " + i + "번은 시작위치에서 도달할 수 없습니다.");
                
            }else {// 도달할 수 있는 경우 거리를 출력
            	
            	System.out.println("해당노드 " + i + "번은 " + d[i-1] +  "만큼 시간이 걸려서 도달할 수 있습니다.");
                
            }
        }
        

        sc.close();
        
	}

}
