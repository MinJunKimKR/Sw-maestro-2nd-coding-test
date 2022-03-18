package day11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class BOJ_15686_치킨배달 {
	
	static int ans = Integer.MAX_VALUE;
	static int N, M;
	static int[] sel;
	static class Loc {
		int x, y;
		public Loc(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	static int calc(Loc l1, Loc l2) {
		return Math.abs(l1.x - l2.x) + Math.abs(l1.y - l2.y);
	}
	static List<Loc> chickens = new ArrayList<>();
	static List<Loc> homes = new ArrayList<>();
	// 집과 치킨집 과의 거리
	static int[][] dist = new int[13][100];
	public static void main(String[] args) {
		Scanner sc = new Scanner("5 1\n" + 
				"1 2 0 2 1\n" + 
				"1 2 0 2 1\n" + 
				"1 2 0 2 1\n" + 
				"1 2 0 2 1\n" + 
				"1 2 0 2 1\n" + 
				"");
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int v = sc.nextInt();
				if (v == 0) continue;
				
				if (v == 1) homes.add(new Loc(i, j));
				else        chickens.add(new Loc(i, j));
			}
		}
		
		// 미리 반복적인 거리 계산을 저장한다.
		for (int i = 0; i < chickens.size(); i++) {
			for (int j = 0; j < homes.size(); j++) {
				dist[i][j] = calc(chickens.get(i), homes.get(j));
			}
		}
		
		// 치킨 거리 구하기
		sel = new int[M];
		comb(0, 0);
		
		System.out.println(ans);
		sc.close();
	}
	static void comb(int cnt, int start) {
		if (cnt == M) {
			// 선택된 치킨집과 집과의 거리 구하기
			solve();
			return;
		}
		for (int i = start; i < chickens.size(); i++) {
			sel[cnt] = i;
			comb(cnt + 1, i + 1);
		}
	}
	
	private static void solve() {
		int sum = 0;
		for (int i = 0; i < homes.size(); i++) {
			int minDist = Integer.MAX_VALUE;
			for (int j = 0; j < M; j++) {
//				minDist = Math.min(calc(homes.get(i), chickens.get(sel[j])), minDist);
				minDist = Math.min(dist[sel[j]][i], minDist);
			}
			sum += minDist;
		}
		ans = Math.min(ans, sum);
	}
}





