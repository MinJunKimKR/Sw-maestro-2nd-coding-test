import java.util.Scanner;

public class BOJ_2567_색종이2 {
	public static void main(String[] args) {
		boolean[][] map = new boolean[102][102];

		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int t = 0; t < T; t++) {
			int w = sc.nextInt();
			int h = sc.nextInt();
			for (int i = w; i < w + 10; i++) {
				for (int j = h; j < h + 10; j++) {
					map[i][j] = true;
				}
			}
		}
		
		int[] dx = {0, 0, 1, -1};
		int[] dy = {1, -1, 0, 0};
		int cnt = 0;
		for (int i = 1; i < 101; i++) {
			for (int j = 1; j < 101; j++) {
				if(map[i][j] == true) {
					for(int k = 0; k < 4; k++) {
						if(map[i+dx[k]][j+dy[k]] == false) {
							cnt++;
						}
					}
				}
			}
		}
		System.out.println(cnt);
	}
}
