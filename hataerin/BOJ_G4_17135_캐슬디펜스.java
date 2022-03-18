package day11;


/**
	시뮬 순서
	1. 적군의 위치를 저장(O)
	2. 궁수들의 위치를 선정(조합 : 3~9 반복) 

		3. 적들의 위치를 복사
		4. 적들이 모두 없어질때 까지 (5~7 반복)
		
		    5. 각 궁수별 가장 가까운 적들의 정보를 얻는다.
			조건 > 궁수의 가장 가까운 적 규칙
					- 지정된 거리를 초과하면 안된다.
					- 지정된 거리내에서 가장 가까운 거리의 적을 선정한다.
					- 만약, 가장 가까운 거리의 적이 여러명이 있다면 왼쪽좌표가 가장 적은 적을 선정한다.    
			6. 선정된 적들을 제거하고 카운트를 누적한다.
			7. 적들을 한줄씩 내린다.(내린 적이 성에 도달하면 제거)

		8. 각 조합에서 제거된 적들의 카운트에서 가장 최대인 카운트를 기록한다.
		9. 적들의 위치 복사 내용을 초기화한다.

    10. 가장 많은 적을 제거한 카운트를 출력한다.
*/
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class BOJ_G4_17135_캐슬디펜스 {

	static class Enemy {
		int x, y;

		Enemy(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int N, M, D;
	static int ans;
	static List<Enemy> enemys = new ArrayList<>();
	public static void main(String[] args) {
		Scanner sc = new Scanner("5 5 1\n" + 
				"0 0 0 0 0\n" + 
				"0 0 0 0 0\n" + 
				"0 0 0 0 0\n" + 
				"0 0 0 0 0\n" + 
				"1 1 1 1 1" + 
				"");
	
		N = sc.nextInt();
		M = sc.nextInt();
		D = sc.nextInt();
		
		// 1. 적군의 위치를 저장
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (sc.nextInt() == 1) {
					enemys.add(new Enemy(i, j));
				}
			}
		}
		
		// 2. 궁수들의 위치 선정
		List<Enemy> temp = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			for (int j = i + 1; j < M; j++) {
				for (int k = j + 1; k < M; k++) {
					// i, j, k - 궁수 위치 선정 끝
					// 3. 적군 복사 하기 
					for (Enemy e : enemys) {
						temp.add(new Enemy(e.x, e.y));
					}
					
					// 시뮬레이션 실행하기
					int cnt = game(temp, new int[] {i, j, k});
					ans = Math.max(ans, cnt);
				}
			}
		}
		System.out.println(ans);
	}
	private static int game(List<Enemy> enemys, int[] archers) {
		// 한번의 궁수 위치에서 제거한 적의 합
		int cnt = 0;
		while (enemys.size() != 0) {
			Set<Enemy> removeEnemys = new HashSet<>();
			for (int archer : archers) {
				// 궁수 한명이 제거할 수 있는 가장 가까운 적 찾기
				Enemy e = findNearEnemy(enemys, archer);
				if (e != null) {
					removeEnemys.add(e);
				}
			}
			cnt += removeEnemys.size();
			
			// 찾은 적들을 제거한다.
			enemys.removeAll(removeEnemys);
			
			// 적들을 하강시킨다.
			downEnemys(enemys);
		}
		return cnt;
	}
	
	private static Enemy findNearEnemy(List<Enemy> enemys, int archer) {
		int minD = Integer.MAX_VALUE, minY = 15;
		Enemy find = null;
		for (Enemy e : enemys) {
			int d = N - e.x + Math.abs(e.y - archer);
			if (d > D || d > minD) continue;
			
			if (d < minD) {
				minD = d;
				find = e;
				minY = e.y;
				continue;
			}
			
			if (minY > e.y) {
				find = e;
				minY = e.y;
			}
		}
		return find;
	}
	
	private static void downEnemys(List<Enemy> enemys) {
		Iterator<Enemy> ite = enemys.iterator();
		while(ite.hasNext()) {
			Enemy e = ite.next();
			e.x++;
			if (e.x == N) ite.remove();
		}
	}
}