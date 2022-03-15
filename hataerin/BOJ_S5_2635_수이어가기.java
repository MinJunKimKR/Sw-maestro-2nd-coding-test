import java.util.Arrays;
import java.util.Scanner;

public class BOJ_2635_수이어가기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		
		int N = sc.nextInt(); //첫번째 수
		int second[] = new int[N]; //두번째 수
		int max = 0; // 규칙으로 만들어지는 최대개수의 수
		int arr[] = new int[N];
		
		if(N == 1) { // 1이 0일때 예외처리 해주기!
			System.out.println(4);
			System.out.println(1 + " " + 1 + " " + 0 + " " + 1);
			System.exit(0);
		}

		for(int k = N/2; k <= N; k++) { //두번째 수
			int i = 1;
			
			arr[0] = N;
			arr[1] = k;
			for(; i < N; i++) {
				if(i < arr.length -1) {
					arr[i+1] = arr[i-1] - arr[i];	
					if(arr[i+1] < 0) break;
				}
			}
			
			if(max < i+1) {
				max = i+1;
				second = Arrays.copyOf(arr, max);
			}		
		}
		
		System.out.println(max); // 최대 개수 출력하기
		for(int j = 0; j < max; j++) {
			System.out.print(second[j] + " ");
		}
	}
}
