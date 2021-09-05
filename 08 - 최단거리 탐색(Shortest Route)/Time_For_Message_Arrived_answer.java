package code08.Shortest_Route;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

/* (해답 1) 향상된 다익스트라 사용(Node관련 클래스는 작성된걸 사용)
 * 
 * N( < 30000)개의 도시가 있는데, 각 도시는 도시간 존재하는 연결망(총 M개 ( < 200000))을 통해 메시지를 발신, 수신한다. 
 * 메시지가 전달되는 시간은 시설에 따라 각각 일정 시간이 소요된다.
 * 이때 한 도시에서 위급상황이 터져 전보를 보낼시에,
 * 몇개의 도시가 전보를 받는가? + 이를 알기까지의 시간을 얼마나 걸리는지에 대한 알고리즘을 짜라
 * 
 * -> 다른 부분 (max값을 통해 전체 시간 계산, 0값을 조건문에 포함x 대신 count - 1 시전
 */
public class Time_For_Message_Arrived_answer {

    public static final int INF = (int) 1e9; // 무한을 의미하는 값으로 10억을 설정
    
    // 노드의 개수(N), 간선의 개수(M), 시작 노드 번호(Start)
    public static int n, m, start;
    
    // 각 노드에 연결되어 있는 노드에 대한 정보를 담는 배열
    public static ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();
    
    // 최단 거리 테이블 만들기
    public static int[] d = new int[30001];

    public static void dijkstra(int start) {
    	
        PriorityQueue<Node> pq = new PriorityQueue<>();
        
        // 시작 노드로 가기 위한 최단 경로는 0으로 설정하여, 큐에 삽입
        pq.offer(new Node(start, 0));
        
        d[start] = 0;
        
        while(!pq.isEmpty()) { // 큐가 비어있지 않다면
        	
            // 가장 최단 거리가 짧은 노드에 대한 정보 꺼내기
            Node node = pq.poll();
            
            int dist = node.getDistance(); // 현재 노드까지의 비용 
            int now = node.getIndex(); // 현재 노드
            
            // 현재 노드가 이미 처리된 적이 있는 노드라면 무시
            if (d[now] < dist) continue;
            
            // 현재 노드와 연결된 다른 인접한 노드들을 확인
            for (int i = 0; i < graph.get(now).size(); i++) {
            	
                int cost = d[now] + graph.get(now).get(i).getDistance();
                
                // 현재 노드를 거쳐서, 다른 노드로 이동하는 거리가 더 짧은 경우
                if (cost < d[graph.get(now).get(i).getIndex()]) {
                	
                    d[graph.get(now).get(i).getIndex()] = cost;
                    pq.offer(new Node(graph.get(now).get(i).getIndex(), cost));
                    
                }
            }
        }
    }

    public static void main(String[] args) {
    	
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        start = sc.nextInt();

        // 그래프 초기화
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<Node>());
        }
        
        // 모든 간선 정보를 입력받기
        for (int i = 0; i < m; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int z = sc.nextInt();
            // X번 노드에서 Y번 노드로 가는 비용이 Z라는 의미
            graph.get(x).add(new Node(y, z));
        }

        // 최단 거리 테이블을 모두 무한으로 초기화
        Arrays.fill(d, INF);
        
        // 다익스트라 알고리즘을 수행
        dijkstra(start);

        // 도달할 수 있는 노드의 개수
        int count = 0;
        
        // 도달할 수 있는 노드 중에서, 가장 멀리 있는 노드와의 최단 거리
        int maxDistance = 0;
        
        for (int i = 1; i <= n; i++) {
        	
            // 도달할 수 있는 노드인 경우
            if (d[i] != INF) {
            	
                count += 1;
                maxDistance = Math.max(maxDistance, d[i]);
                
            }
        }

        // 시작 노드는 제외해야 하므로 count - 1을 출력
        System.out.println((count - 1) + " " + maxDistance);

        sc.close();
    }


}
