package code08.Shortest_Route;

import java.util.Arrays;
import java.util.Scanner;

/* (문제 2) 미래 도시 : 거리가 같은 경우 다익스트라
 * 	
 * (해답 1) 플로이드 워셜 사용
 *  
 *  미래도시에는 1~N번까지 이름붙여진 회사가 있다. 
 *  각 회사는 도로가 연결되어 있으면 양방향 이동가능 + 1의 이동시간 소요된다.
 *  (총 도로의 수 <= 100)
 *  
 *  방문판매원 A는 1번회사에 있는데, X번 회사에 방문에 판매를 진행하고자 한다.
 *  하지만 한편으로는 K번 회사에 존재하는 소개팅녀를 먼저 만나야한다.
 *  즉 1 -> K -> X 루트를 짜고 움직이는데, 최소거리를 구하는 알고리즘을 작성해라
 *  (도달 불가시 -1 출력)
 */
public class Future_City_Answer {

    public static final int INF = (int) 1e9; // 무한을 의미하는 값으로 10억을 설정
    
    // 노드의 개수(N), 간선의 개수(M), 거쳐 갈 노드(X), 최종 목적지 노드(K)
    public static int n, m, x, k;
    
    // 2차원 배열(그래프 표현)를 만들기
    public static int[][] graph = new int[101][101];

    public static void main(String[] args) {
    	
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        // 최단 거리 테이블을 모두 무한으로 초기화
        for (int i = 0; i < 101; i++) {
            Arrays.fill(graph[i], INF);
        }

        // 자기 자신에서 자기 자신으로 가는 비용은 0으로 초기화
        for (int a = 1; a <= n; a++) {
            for (int b = 1; b <= n; b++) {
                if (a == b) graph[a][b] = 0;
            }
        }

        // 각 간선에 대한 정보를 입력 받아, 그 값으로 초기화
        for (int i = 0; i < m; i++) {
        	
            // A와 B가 서로에게 가는 비용은 1이라고 설정
            int a = sc.nextInt();
            int b = sc.nextInt();
            
            graph[a][b] = 1;
            graph[b][a] = 1;
        }

        x = sc.nextInt();
        k = sc.nextInt();

        // 점화식에 따라 플로이드 워셜 알고리즘을 수행
        for (int k = 1; k <= n; k++) {
            for (int a = 1; a <= n; a++) {
                for (int b = 1; b <= n; b++) {
                    graph[a][b] = Math.min(graph[a][b], graph[a][k] + graph[k][b]);
                }
            }
        }

        // 수행된 결과를 출력
        int distance = graph[1][k] + graph[k][x];

        // 도달할 수 없는 경우, -1을 출력
        if (distance >= INF) {
            System.out.println(-1);
        }
        // 도달할 수 있다면, 최단 거리를 출력
        else {
            System.out.println(distance);
        }
        
        sc.close();
    }
	

}
