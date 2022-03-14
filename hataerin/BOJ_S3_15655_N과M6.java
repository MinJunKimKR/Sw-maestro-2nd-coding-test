

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_S3_15655_N과M6 {
	static int N, M, a[], data[];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		data = new int[N];
		for (int i = 0; i < N; i++) {
			data[i] = sc.nextInt();
		}
		Arrays.sort(data);
		a = new int[N];
		solve(0, 0);//자리수, 시작하는 위치 0부터
	}
	
	private static void solve(int cnt, int start) {//바뀌는 값 위주로
		if (cnt == M) {
			for (int i = 0; i < M; i++) {
				System.out.print(a[i] + " ");
			}
			System.out.println();
			return;
		}
		
		for (int i = start; i < N; i++) {
			a[cnt] = data[i];  
			solve(cnt + 1, i + 1);
		}
	}

}
