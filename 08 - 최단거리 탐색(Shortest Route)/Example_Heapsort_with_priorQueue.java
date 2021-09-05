package code08.Shortest_Route;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

// 주어진 배열을 Queue와 Heapsort를 써서 정렬하는 알고리즘(PriorQueue의 개념 <- 향상된 다익스트라에서 사용)
// 1. 배열을 입력받으면
// 2. HEAPSORT를 사용하는 PRIORQUEUE에 OFFER (+로 하면 오름차순, -로하면 내림차순으로 정렬, 꺼낼때도 -면 뒤에서부터 꺼내는 효과로 똑같이 적용)
// 3. 해당 PQ가 빌때까지 배출하면 끝 
public class Example_Heapsort_with_priorQueue {
	
	static int n;
	
	static ArrayList<Integer> arr = new ArrayList<Integer>();
	
	static void heapSort_with_PriorQueue(ArrayList<Integer> arr) {
		
	    PriorityQueue<Integer> pq = new PriorityQueue<>();
	    
	    // 모든 원소를 차례대로 힙에 삽입
	    for (int i = 0; i < arr.size(); i++) {
	    	
	    	pq.offer(arr.get(i));
	    	//pq.offer(-arr.get(i));
	    	
	    }
	    
	    // 힙에 삽입된 모든 원소를 차례대로 꺼내어 출력
	    while (!pq.isEmpty()) {
	    	
	    	System.out.println(pq.peek());
	    	//System.out.println(-pq.peek());

	    	pq.poll();
	        
	    }
		
	}
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		n = scan.nextInt();
		
	    for (int i = 0; i < n; i++) {
	    	
	        int x = scan.nextInt();
	       
	        arr.add(x);
	        
	    }
	    
	    heapSort_with_PriorQueue(arr);
	    
	    scan.close();

	}

}
