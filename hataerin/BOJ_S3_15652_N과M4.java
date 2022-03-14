

import java.util.Scanner;

public class BOJ_S3_15652_N과M4 {
	static int N, M, a[];
	static boolean[] isSelected;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		a = new int[N];
		solve(0, 1);//자리수, 시작하는 위치
	}
	
	private static void solve(int cnt, int start) {
		if (cnt == M) {
			for (int i = 0; i < M; i++) {
				System.out.print(a[i] + " ");
			}
			System.out.println();
			return;
		}
		
		for (int i = start; i <= N; i++) {
			a[cnt] = i;  
			solve(cnt + 1, i);
		}
	}

}
