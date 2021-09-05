package code04.DFS_BFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BFS_Example {

	// 방문 좌표 기록
    public static boolean[] visited = new boolean[9];
    // 2중 arraylist를 통해 각 턴마다 갈 수 있는 루트 경우에 대한 표시를 한다. (1에서 출발의 경우{2,3} , 2에서 출발{4,5} , 3에서 출발시 가는{4,6,7} ... 그 뒤에 없는 경우에 마지막 루트라고 생각하면 됨)
    public static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();

    // BFS 함수 정의(Queue를 통해 구현)
    public static void bfs(int start) {
        
    	Queue<Integer> q = new LinkedList<>();
    	
    	// 출발 지점을 큐에 넣는다(최초시작은 시작루트)
        q.offer(start);
        
        // 현재(출발) 노드를 방문 처리
        visited[start] = true;
        
        // 큐가 빌 때까지 반복(DFS때처럼 재귀호출(DFS는 이를 통해 더 깊이 내려가는게 가능함)은 하지 않고, while문을 통해 순차적 입력과 출력을 반복하게 한다.)
        // DFS와 다르게 직렬로 파고드는 연산이 목적이 아니라, 병렬적으로 루트를 찾는게 우선이기 때문
        while(!q.isEmpty()) {
        	
            // 큐에서 하나의 원소를 뽑아 출력
            int x = q.poll();
            System.out.print(x + " ");
            
            // 앞서 출력한 해당 원소(X)와 연결된, 아직 방문하지 않은 원소들을 큐에 삽입
            // (2중 arraylist를 통해, X행에 저장된 노드(정보)들이 순차적으로 입력됨(이를 통해 넓이 우선 탐색인 BFS의 알고리즘 수행 가능) 
            for(int i = 0; i < graph.get(x).size(); i++) {
            	
            	// X행의 i(반복됨)의 값을 저장
                int y = graph.get(x).get(i);
                
                // BFS의 queue를 쓰는 핵심 부분 
                // (x행의 i값의 위치가 현재 방문 중이 아니라면? -> 방문처리하고, queue에 해당 값을 집어넣는다. -> queue가 아직 안 비었음 = 계속 while 수행 + for문은 x행의 다음 i를 바탕으로 입력 -> 출력 계속 감)
                if(!visited[y]) {
                	
                    q.offer(y);
                    visited[y] = true;
                    
                }
            }
        }
    }

    public static void main(String[] args) {
        // 그래프 초기화
        for (int i = 0; i < 9; i++) {
            graph.add(new ArrayList<Integer>());
        }

        // 노드 1에 연결된 노드 정보 저장 
        graph.get(1).add(2);
        graph.get(1).add(3);
        graph.get(1).add(8);
        
        // 노드 2에 연결된 노드 정보 저장 
        graph.get(2).add(1);
        graph.get(2).add(7);
        
        // 노드 3에 연결된 노드 정보 저장 
        graph.get(3).add(1);
        graph.get(3).add(4);
        graph.get(3).add(5);
        
        // 노드 4에 연결된 노드 정보 저장 
        graph.get(4).add(3);
        graph.get(4).add(5);
        
        // 노드 5에 연결된 노드 정보 저장 
        graph.get(5).add(3);
        graph.get(5).add(4);
        
        // 노드 6에 연결된 노드 정보 저장 
        graph.get(6).add(7);
        
        // 노드 7에 연결된 노드 정보 저장 
        graph.get(7).add(2);
        graph.get(7).add(6);
        graph.get(7).add(8);
        
        // 노드 8에 연결된 노드 정보 저장 
        graph.get(8).add(1);
        graph.get(8).add(7);

        // 최초 출발지점을 시작으로 한 점화식 시작
        bfs(1);
    }
}
