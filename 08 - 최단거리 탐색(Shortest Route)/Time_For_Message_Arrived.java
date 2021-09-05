package code08.Shortest_Route;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

/* (문제 1) 향상된 다익스트라 사용(Node관련 클래스는 작성된걸 사용)
 * 
 * N( < 30000)개의 도시가 있는데, 각 도시는 도시간 존재하는 연결망(총 M개 ( < 200000))을 통해 메시지를 발신, 수신한다. 
 * 메시지가 전달되는 시간은 시설에 따라 각각 일정 시간이 소요된다.
 * 이때 한 도시에서 위급상황이 터져 전보를 보낼시에,
 * 모든 도시가 이를 알기까지의 시간을 얼마나 걸리는지에 대한 알고리즘을 짜라
 */
public class Time_For_Message_Arrived {

	// 전파망 정보 입력 배열 생성
	static ArrayList<ArrayList<Node>> map = new ArrayList<ArrayList<Node>>();
	
	// 최초시작위치~각도시별 최소거리 배열 (다이나믹 프로그래밍적 원리 사용)
	static int shortest[] = new int[30001];
	
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		// 총 도시의 수
		int N = scan.nextInt();
		// 총 연결망의 수
		int M = scan.nextInt();
		// 전보를 보낼 도시의 위치
		int start = scan.nextInt();
		
		// 전파망 정보 입력 배열 초기화 작업!!!(잊지 말자)
		for(int i = 0; i <= N; i++) {
			
			map.add(new ArrayList<Node>());
			
		}
		
		// 전파망 정보 입력(각각 시작, 목표, 거리 M번 입력)
		for(int i = 0; i < M; i++) {
			
			// 길의 시작도시
			int from = scan.nextInt();
			// 길의 목표도시
			int to = scan.nextInt();
			// 해당 길의 거리
			int distance = scan.nextInt();
			
			// 루트정보 2중 Arraylist에 입력(M개 입력)
			map.get(from).add(new Node(to, distance));
			
		}
		
		// 최초시작위치~각도시별 최소거리 배열 무한으로 채우기
		Arrays.fill(shortest, (int) 1e9);
		
		// 다익스르라 알고리즘 발동
		dijkstra(start);
		
		// 소식을 들은 도시의 수
		int count = 0;
		
		// 전파망 전체 전달시간 기록용
		int answer = 0;
		
		
		// 시작위치~각도시별 최소거리 배열 N개의 정보를 검토
		for(int i = 1; i <= N; i++) {
			
			// 미방문 + 시작지점 제외한 배열의 거리값중 최대값의 answer에 기입
			if(shortest[i] != 0 && shortest[i] != 1e9) {
				
				count++;
				
				if(answer < shortest[i]) {
				
					answer = shortest[i];
				
				}
			}
			
			if(shortest[i] == 1e9) {
				
				System.out.println(i + "는 시작지점 " + start + "에서 전파를 보낼수 없습니다");
				
			}else {
				
				System.out.println(i + "는 시작지점 " + start + "에서 전파를 보내려면 최소한 " + shortest[i] + "걸립니다.");
				
			}
			
		}
		
		// 정답
		System.out.println("전파를 받는게 가능한 도시의 수 : " + count);
		System.out.println("모든 도시에 전파보내기를 완료하는 시간 : " + answer);
		
		scan.close();
		
	}

	// 향상된 다익스트라 알고리즘(BFS 응용)
	private static void dijkstra(int start) {
		
		// 힙정렬(기본 오름차순)적용된 queue 
		PriorityQueue<Node> queue = new PriorityQueue<Node>();
		
		// 시작 위치의 노드위치 + 거리를 0으로 입력
		queue.offer(new Node(start, 0));
		
		// 해당 노드(최초시작위치 ~ 최초시작위치)의 시작지점부터의 최소 거리 정보를 입력
		shortest[start] = 0;
		
		// queue에 저장된 노드 정보가 없을때까지 반복(BFS적용)
		while(!queue.isEmpty()) {
			
			// queue에서 배출한 정보 잡아줄 Node 객체 생성
			Node current = queue.poll();
			
			// 해당 queue정보 중, 도착 노드
			int currentIndex= current.getIndex();
			// 해당 queue정보 중, 시작~도착 지점까지의 거리
			int currentDis = current.getDistance();
			
			// 만약 최초시작~도착 지점까지의 거리가 기존의 시작위치~해당 도착도시별 최소거리보다 작다면, 무시하고 다른 녀석으로..
			if(shortest[currentIndex] < currentDis) {
				
				continue; // 무시하라는 것
				
			}
			
			// 도착지점을 기준으로 시작지점으로 지정한 루트 배열 정보의 수만큼 반복 (어차피 도달하지 못하는 곳은 무한대로 처리되서 갱신 X)
			for(int i = 0; i < map.get(currentIndex).size(); i++) {
				
				// 기존 최초시작~도착 지점 최소거리 + 해당 도착지점~도달가능한 i번 노드를 향한 거리 -> 새로운 최소거리 후보
				int cost = shortest[currentIndex] + map.get(currentIndex).get(i).getDistance();
				
				// 새로운 최초시작~i번노드 향한 최소거리가 기존 최초시작~i번노드까지의 최소거리보다 작다면...
				if(cost < shortest[map.get(currentIndex).get(i).getIndex()]) {
					
					// 최초시작~해당 도착지점의 최소거리를 새로운 최소거리로 갱신
					shortest[map.get(currentIndex).get(i).getIndex()] = cost;
					
					// 해당 도착노드와 최소거리 정보를 queue에 넣는다.
					queue.offer(new Node(map.get(currentIndex).get(i).getIndex(), cost));
					
				}
				
			}
			
			
		}
		
		
	}
	
	

}
