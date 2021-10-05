package code09.ETC_Graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/* 위상정렬 : 어떤 일들을 하는 모든 순서를 방향성을 거스리지 않고 찾는 알고리즘(정점 - .. - 가장 아래 위상 순으로 나열.. 그러니까 조직도 같은거)
 * 
 * 위상정렬 성립조건(DAG(Direct Acyclic Graph) : 싸이클이 없는(1) 방향 그래프(2)) 
 * 1. 싸이클이 존재하지 않는다.
 * 2. 각 간선은 방향성이 존재해야 한다.
 * 3. + 각 간선을 통해 모든 노드가 포함이 되어야 한다(설령 하나의 내부그룹으로 포함되지 않는다고 하더라도 그래야함) 
 * 	-> 모든 원소를 방문하기 전에 큐가 비는 경우 = 싸이클이 존재하는 경우일수 있음(진입가능 루트가 얘 뿐인데, 싸이클로 인해 진입차수가 0이 아닌경우)
 * 
 * [필요개념]
 * 1. 진입차수(Indegree) : 특정한 노드로 들어오는 간선의 갯수
 * 2. 진출차수(Outdegree) : 특정한 노드에서 나가는 간선의 갯수
 * 
 * [매커니즘] : 큐가 아니라 스택으로도 구현 가능(중요한건 진입차수가 0인녀석을 찾아 단계별로 수행하는 조건이지, 순서가 최우선적으로 중요한게 아님)
 * 1. 진입차수 = 0 (= 선행조건 모두 만족한 요소.. 싸이클에 걸리면 만족불가) 인 모든 노드를 큐(스택)에 넣는다.
 * 2. 큐에서 원소를 꺼내서(해당 열의 배열을 검토), 해당 노드와 연결된 간선을 그래프에서 제거 (어떻게 해당 행을 훍느냐 따라 여러 형태의 답이 나올 수 있음)
 * 3. 새롭게 진입차수가 0인 녀석들을 큐(스택)에 넣는다.
 * 4. 큐(스택)가 빌때까지 반복하고, 다 끝나면 해당 큐(스택)의 내용을 출력
 * 
 */
public class Example_Topological_Sort {

    // 노드의 개수(V)와 간선의 개수(E)
    // 노드의 개수는 최대 100,000개라고 가정
    public static int v, e;
    
    // 모든 노드에 대한 진입차수는 0으로 초기화
    public static int[] indegree = new int[100001];
    
    // 각 노드에 연결된 간선 정보를 담기 위한 연결 리스트 초기화
    public static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();

    // 위상 정렬 함수
    public static void topologySort() {
    	
        ArrayList<Integer> result = new ArrayList<>(); // 알고리즘 수행 결과를 담을 리스트
        Queue<Integer> q = new LinkedList<>(); // 큐 라이브러리 사용

        // 처음 시작할 때는 진입차수가 0인 노드를 큐에 삽입
        // (중간에 싸이클에 걸린다면, 그 녀석은 진입차수가 0이 아니다. 싸이클에 걸린다면 진출차수가 해당 노드에 걸려 있으면 전진 불가)
        for (int i = 1; i <= v; i++) {
        	
            if (indegree[i] == 0) {
            	
                q.offer(i);
                
            }
            
        }

        // 큐가 빌 때까지 반복
        while (!q.isEmpty()) {
        	
            // 큐에서 원소 꺼내기
            int now = q.poll();
            result.add(now);
            
            // 해당 원소와 연결된 노드들의 진입차수에서 1 빼기
            for (int i = 0; i < graph.get(now).size(); i++) {
            	
                indegree[graph.get(now).get(i)] -= 1;
                
                // 새롭게 진입차수가 0이 되는 노드를 큐에 삽입
                if (indegree[graph.get(now).get(i)] == 0) {
                	
                    q.offer(graph.get(now).get(i));
                    
                }
                
            }
            
        }

        // 위상 정렬을 수행한 결과 출력
        for (int i = 0; i < result.size(); i++) {
            System.out.print(result.get(i) + " ");
        }
    }

    public static void main(String[] args) {
    	
        Scanner sc = new Scanner(System.in);

        v = sc.nextInt();
        e = sc.nextInt();

        // 그래프 초기화
        for (int i = 0; i <= v; i++) {
            graph.add(new ArrayList<Integer>());
        }

        // 방향 그래프의 모든 간선 정보를 입력 받기
        for (int i = 0; i < e; i++) {
        	
            int a = sc.nextInt();
            int b = sc.nextInt();
            
            graph.get(a).add(b); // 정점 A에서 B로 이동 가능
            
            // 진입 차수를 1 증가
            indegree[b] += 1;
        }

        topologySort();
        
        sc.close();
    }

}
