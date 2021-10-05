package code08.Shortest_Route;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/* [다익스트라 알고리즘] (다이나믹 프로그래밍 + 2중 그래프 통해) 
    : 현재 start 지점에서 방향성이 존재하는 길들을 통해 모든 위치로의 최단루트값을 담은 배열을 구하는 알고리즘

    <큐 없는 다익스트라 시간 복잡도>
    : 2중 FOR문 (V^2) 

    <다익스트라 알고리즘의 가정>
    1. 그래프가 방향성이 있음을 가정  <-> 무방향 그래프 버전으로 '프림(prim) 알고리즘'이 존재
    2. 가중치는 양수고 음수가 아니다.
*/
public class Example_Dijkstra {

    public static final int INF = (int) 1e9; // 무한을 의미하는 값으로 10억을 설정
    
    // 노드의 개수(N), 간선의 개수(M), 시작 노드 번호(Start)    // 노드의 개수는 최대 100,000개라고 가정
    public static int n, m, start;
    
    // 각 노드에 연결되어 있는 노드에 대한 정보를 담는 배열
    public static ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();
    
    // 각 노드별 최단 거리 테이블 만들기
    public static int[] d = new int[100001];
    
    // 방문한 적이 있는지 체크하는 목적의 배열 만들기
    public static boolean[] visited = new boolean[100001];

    // 방문하지 않은 노드 중에서, 가장 최단 거리가 짧은 노드의 번호를 반환(시작 노드와 연결된 노드녀석들로 시작 -> 그 중 가장 짧은 노드를 고르는거)
    public static int getSmallestNode() {
    	
        int min_value = INF;
        
        int index = 0; // 가장 최단 거리가 짧은 노드(인덱스)
        
        for (int i = 1; i <= n; i++) {
        	
            if (d[i] < min_value && !visited[i]) {
            	
                min_value = d[i];
                index = i;
                
            }
        }
        return index;
    }

    public static void dijkstra(int start) {
    	
        // 시작 노드에 대해서 초기화
        d[start] = 0;
        
        visited[start] = true;
        
        // 시작노드와 연결된 노드들과 시작노드에서의 거리를 각각 그들의 최소거리로 둔다(이거 중요)
        for (int j = 0; j < graph.get(start).size(); j++) {
        	
            d[graph.get(start).get(j).getIndex()] = graph.get(start).get(j).getDistance();
            
        }
        
        // 시작 노드를 제외한 전체 n - 1개의 노드에 대해 반복
        for (int i = 0; i < n - 1; i++) {
        	
            // 현재 최단 거리가 가장 짧은 노드를 꺼내서, 방문 처리(시작 노드와 연결된 노드녀석들로 점화식이 시작 -> 그 중 가장 짧은 노드를 고르는거)
            int now = getSmallestNode();
            
            // getSmallestNode() 메서드로 당첨된 노드는 방문처리
            visited[now] = true;
            
            // 그 당첨된 현재 노드(가장 짧은 거리를 가진)와 연결된 다른 노드를 확인       
            for (int j = 0; j < graph.get(now).size(); j++) {
            	
                int cost = d[now] + graph.get(now).get(j).getDistance();
                
                // 현재 노드를 거쳐서, 다른 노드로 이동하는 거리가 더 짧은 경우(그 루트가 현재 기록된 해당 노드로의 최단거리보다 짧은 경우)
                if (cost < d[graph.get(now).get(j).getIndex()]) {
                	
                    d[graph.get(now).get(j).getIndex()] = cost;
                    
                }
            }
        }
    }

    public static void main(String[] args) {
    	
        Scanner sc = new Scanner(System.in);

        // 노드의 개수(N), 간선의 개수(M), 시작 노드 번호(Start)
        n = sc.nextInt();
        m = sc.nextInt();
        start = sc.nextInt();

        // 그래프 초기화
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<Node>());
        }

        // 모든 간선 정보를 입력받기
        for (int i = 0; i < m; i++) {
        	
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            
            // a번 노드에서 b번 노드로 가는 비용이 c라는 의미
            graph.get(a).add(new Node(b, c));
            
        }

        // 최단 거리 테이블을 모두 무한으로 초기화
        Arrays.fill(d, INF);
        
        // 다익스트라 알고리즘을 수행
        dijkstra(start);

        // 모든 노드로 가기 위한 최단 거리를 출력
        for (int i = 1; i <= n; i++) {
        	
            // 도달할 수 없는 경우, 무한(INFINITY)이라고 출력
            if (d[i] == INF) {
            	
                System.out.println("INFINITY");
                
            }else {// 도달할 수 있는 경우 거리를 출력
            	
                System.out.println(d[i]);
                
            }
        }
        
        sc.close();
        
    }

}
