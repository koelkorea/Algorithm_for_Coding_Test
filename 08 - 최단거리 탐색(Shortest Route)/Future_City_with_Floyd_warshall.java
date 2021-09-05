package code08.Shortest_Route;

import java.util.Arrays;
import java.util.Scanner;

/* (문제 2) 미래 도시 : 거리가 같은 경우 다익스트라
 * 	
 * (풀이 3) Array를 통한 플-마 공략
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
public class Future_City_with_Floyd_warshall {


	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		// 총 도시의 수
		int N = scan.nextInt();
		// 총 연결망의 수
		int M = scan.nextInt();
		
		// 각 구역별 거리기록용 배열 map
		int map[][] = new int [N][N];
		
		// map 배열을 1e9로 초기화
		for(int i = 0; i < N; i++) {
			Arrays.fill(map[i], (int) 1e9);
		}
		
        // 자기 자신에서 자기 자신으로 가는 비용은 0으로 초기화 (플로이드 워셜 알고리즘 핵심... map 배열 값을 수정하는것)
        for (int a = 0; a < N; a++) {
        	
            for (int b = 0; b < N; b++) {
            	
                if (a == b) {
                	
                	map[a][b] = 0;
                	
                }
                
            }
            
        }

		
		// 전파망 정보 입력 배열 초기화 작업!!!(잊지 말자)
		for(int i = 0; i < M; i++) {
			
			// 연결점1
			int start = scan.nextInt();
			// 연결점2
			int end = scan.nextInt();
			
			map[start-1][end-1] = 1;
			map[end-1][start-1] = 1;
			
		}
		
		// 루트 입력정보 확인
		System.out.println(Arrays.deepToString(map));
		
		// 거래처  X번
		int X = scan.nextInt();
		// 소개팅녀 회사 K번 
		int K = scan.nextInt();
		
        // 점화식에 따라 플로이드 워셜 알고리즘을 수행
        for (int k = 0; k < N; k++) {
        	
            for (int a = 0; a < N; a++) {
            	
                for (int b = 0; b < N; b++) {
                	
                    map[a][b] = Math.min(map[a][b], map[a][k] + map[k][b]);
                    
                }
            }
        }

		// 1차로 K도시에 애인을 만나러 가는 정보 저장
		int first = map[0][K-1];
		

		// 2차로 X도시에 애인을 만나러 가는 정보 저장
		int second = map[K-1][X-1];
		
		
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


}
