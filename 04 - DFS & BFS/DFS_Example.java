package code04.DFS_BFS;

import java.util.*;

public class DFS_Example {

	// 방문 좌표 기록
    public static boolean[] visited = new boolean[9];
    // 2중 arraylist를 통해 각 턴마다 갈 수 있는 루트 경우에 대한 표시를 한다. (1에서 출발의 경우{2,3} , 2에서 출발{4,5} , 3에서 출발시 가는{4,6,7} ... 그 뒤에 없는 경우에 마지막 루트라고 생각하면 됨)
    public static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();

    // DFS 함수 정의(재귀사용)
    public static void dfs(int x) {
    	
        // 현재 노드를 방문 처리(최초 실행한 최초 방문 노드 위치를..)
        visited[x] = true;
        System.out.print(x + " ");
        
        // 전체 행의 갯수만큼을 반복
        for (int i = 0; i < graph.get(x).size(); i++) {
        	
        	// X행의 i(반복됨)의 값을 저장
            int y = graph.get(x).get(i);
            
            // DFS의 핵심 (현재 노드와 연결된 다른 노드를 재귀적으로 방문 -> 재귀를 통해 계속 후속 연산을 깊게 수행)
            // (x행의 i값의 위치가 현재 방문 중이 아니라면? -> 해당 값을 parameter로 DFS수행을 반복한다(가능한 깊숙하게..)
            if (!visited[y]) {
            	
            	dfs(y);
            	
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
        dfs(1);
    }
}
