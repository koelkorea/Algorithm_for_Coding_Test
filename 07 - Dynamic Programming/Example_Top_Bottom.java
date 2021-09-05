package code07.Dynamic_Programming;


// 다이나믹 프로그래밍 : 동적 메모리랑 아무 관계 없는 용어지만, 
//						계산이 이전 계산의 결과를 바탕으로 점화되는 방식일 경우 이를 하나하나 저장하는 배열을 통해 효율과 시간을 잡는방식
// 탑-바텀(하향적) 방식 : 재귀함수를 사용한 다이나믹 프로그래밍 방식.. 
//						왜 위에서 아래냐면, 배열 값을 먼저 채우는게 아니라 메서드를 통해 구할 값을 정해놓고 이를 분해하여 수렴값으로 하강하는 식으로 접근하기 때문 
public class Example_Top_Bottom {

	
    // 한 번 계산된 결과를 메모이제이션(Memoization)하기 위한 배열 초기화
    public static long[] d = new long[100];

    // 피보나치 함수(Fibonacci Function)를 재귀함수로 구현 (탑다운 다이나믹 프로그래밍)
    public static long fibo(int x) {
        // 종료 조건(1 혹은 2일 때 1을 반환)
        if (x == 1 || x == 2) {
            return 1;
        }
        // 이미 계산한 적 있는 문제라면 그대로 반환
        if (d[x] != 0) {
            return d[x];
        }
        // 아직 계산하지 않은 문제라면 점화식에 따라서 피보나치 결과 반환
        d[x] = fibo(x - 1) + fibo(x - 2);
        return d[x];
    }

    public static void main(String[] args) {
        System.out.println(fibo(50));
    }
    
}
