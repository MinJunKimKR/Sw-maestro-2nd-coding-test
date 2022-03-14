

import java.util.Scanner;

public class BOJ_S3_15649_N과M1 {
	static int N, M, a[];
	static boolean[] isSelected;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		a = new int[N];
		isSelected = new boolean[N + 1];
		solve(0);
	}
	
	private static void solve(int cnt) {
		if (cnt == M) {
			for (int i = 0; i < M; i++) {
				System.out.print(a[i] + " ");
			}
			System.out.println();
			return;
		}
		
		for (int i = 1; i <= N; i++) { //1번부터 N번까지 모두 시도
			if (isSelected[i]) continue;//쓰고있으면 넘어가
			
			isSelected[i] = true;//첫번째 자리
			a[cnt] = i;  
			solve(cnt + 1);//다음자리
			isSelected[i] = false;//초기화
		}
	}

}
