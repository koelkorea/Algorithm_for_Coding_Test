package code08.Shortest_Route;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

/* (문제 2) 미래 도시 : 거리가 같은 경우 다익스트라
 * 	
 * (풀이 2) ArrayList를 통한 다익스트라 공략
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
public class Future_City_with_Arraylist {

	
	// 최초시작위치~각도시별 최소거리 배열 (다이나믹 프로그래밍적 원리 사용)
	static int shortest[] = new int[100];
	
	static ArrayList<ArrayList<Integer>> map = new ArrayList<ArrayList<Integer>>();
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		// 총 도시의 수
		int N = scan.nextInt();
		// 총 연결망의 수
		int M = scan.nextInt();
		
		// map 배열을 1e9로 초기화
		for(int i = 0; i < N; i++) {
			map.add(new ArrayList<Integer>((int) 1e9));
			
			for(int j = 0; j < N; j++) {
				map.get(i).add((int) 1e9);
			}
		}
		
		// 초기화 확인(나중에 0으로 인해 계산이 꼬이니 주의)
		System.out.println(map);
		
		// 전파망 정보 입력 배열 초기화 작업!!!(잊지 말자)
		for(int i = 0; i < M; i++) {
			
			// 연결점1
			int start = scan.nextInt();
			// 연결점2
			int end = scan.nextInt();
			
			map.get(start-1).add(end-1, 1);
			map.get(end-1).add(start-1, 1);
			
		}
		
		// 루트 입력정보 확인
		System.out.println(map);
		
		// 거래처  X번
		int X = scan.nextInt();
		// 소개팅녀 회사 K번 
		int K = scan.nextInt();

		// 최초시작위치~각도시별 최소거리 배열 초기화
		Arrays.fill(shortest, (int) 1e9);
		// 현재 위치 1번도시의 다익스트라 계산 시작
		dijkstra(0);
		// 1차로 K도시에 애인을 만나러 가는 정보 저장
		int first = shortest[K-1];
		
		// System.out.println(Arrays.toString(shortest));
		
		
		// 최초시작위치~각도시별 최소거리 배열 초기화
		Arrays.fill(shortest, (int) 1e9);
		// 현재 위치 K번도시에서의 다익스트라 계산 시작
		dijkstra(K-1);
		// 2차로 X도시에 애인을 만나러 가는 정보 저장
		int second = shortest[X-1];
		
		//system.out.println(Arrays.toString(shortest));
		
		
		// 걸리는 시간 답변
		int answer = first + second;
		
		if(first == 1e9 || second == 1e9) {
			
			System.out.println("해당 목표를 수행할 수 없습니다." + -1);
			
		}else {
			
			System.out.println("1차) 애인을 만나러 K도시에 가는 시간 : " + first);
			System.out.println("2차) 거래처 X도시에 가는 시간  : " + second);
			System.out.println("총 수행시간 : " + answer);
			
		}

		scan.close();
		
		
	}

	// 다익스트라(축약버전 : 거리는 늘 연결만 되면 1로 동일하기 때문)
	static void dijkstra(int start) {
		
		PriorityQueue<Integer> route = new PriorityQueue<Integer>();
		
		// 입력한 start를 큐에 포함
		route.offer(start);
		
		// 시작거리는 0(시작도시~시작도시에 거리가 있을수 없음)
		shortest[start] = 0;
		
		// 큐가 없을때까지 반복
		while(!route.isEmpty()) {
		
			int now = route.poll();
			
			// map 행의 길이만큼(그러니까 N만큼) 반복
			for (int i = 0; i < map.get(now).size(); i++) {
				
				// 현재 위치의 최소거리값 shortest[now]에 해당위치~목표를 향한 거리를 더한 값을 cost 로 명명
				int cost = shortest[now] + map.get(now).get(i);
				
				// 만약 그 cost가 현재 알려진 최소거리값보다 작다면
				if(cost < shortest[i] ) {

					// 해당 도시를 향한 최소거리를 cost로 갱신하고
					shortest[i] = cost;
					// 큐를 추가(BFS)
					route.offer(i);
					
					//System.out.println(Arrays.toString(shortest));
				}
			}
			
		}
		
		
		
	}
	

}
