package code08.Shortest_Route;

import java.util.Arrays;
import java.util.Scanner;

/* [플로이드 워셜 알고리즘] 
   : 각 지점에서(X좌표) 모든 위치(Y좌표)로의 최단루트값을 담은 배열(주어진 지도배열 갱신)을 구하는 알고리즘
     (다이나믹 프로그래밍 + 3중 for문을 통한 대조를 통해..) 
     
    <시간복잡도>
    : 3중 FOR문으로 인한 V^3....  BUT! 코드 자체는 가장 단순 + 직관적이고, 한번 끝내면 삽질할 이유도 없다.
*/
public class Example_Floyd_Warshall {

    public static final int INF = (int) 1e9; // 무한을 의미하는 값으로 10억을 설정
    
    // 노드의 개수(N), 간선의 개수(M)    // 노드의 개수는 최대 500개라고 가정
    public static int n, m;
    
    // 2차원 배열(그래프 표현)를 만들기
    public static int[][] graph = new int[501][501];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        // 최단 거리 테이블을 모두 무한으로 초기화
        for (int i = 0; i < 501; i++) {
        	
            Arrays.fill(graph[i], INF);
            
        }

        // 자기 자신에서 자기 자신으로 가는 비용은 0으로 초기화(플로이드 워셜 알고리즘 핵심... 지도 배열 값을 각 지점(X)에서 목표(Y)를 향한 최적루트 값으로 수정하는것)
        for (int a = 1; a <= n; a++) {
        	
            for (int b = 1; b <= n; b++) {
            	
                if (a == b) {
                	
                	graph[a][b] = 0;
                	
                }
                
            }
            
        }

        // 각 간선에 대한 정보를 입력 받아, 그 값으로 초기화
        for (int i = 0; i < m; i++) {
        	
            // A에서 B로 가는 비용은 C라고 설정
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            
            graph[a][b] = c;
            
        }

        // 점화식에 따라 플로이드 워셜 알고리즘을 수행
        for (int k = 1; k <= n; k++) {
        	
            for (int a = 1; a <= n; a++) {
            	
                for (int b = 1; b <= n; b++) {
                	
                    graph[a][b] = Math.min(graph[a][b], graph[a][k] + graph[k][b]);
                    
                }
            }
        }

        // 수행된 결과를 출력
        for (int a = 1; a <= n; a++) {
        	
            for (int b = 1; b <= n; b++) {
            	
                // 도달할 수 없는 경우, 무한(INFINITY)이라고 출력
                if (graph[a][b] == INF) {
                	
                    System.out.print("INFINITY ");
                    
                }else {// 도달할 수 있는 경우 거리를 출력
                	
                    System.out.print(graph[a][b] + " ");
                    
                }
            }
            
            System.out.println();
        }
        
        sc.close();
        
    }
    
}
