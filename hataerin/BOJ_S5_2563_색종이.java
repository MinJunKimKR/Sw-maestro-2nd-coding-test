
import java.util.Arrays;
import java.util.Scanner;

public class BOJ_2563_색종이 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int map[][] = new int[100][100]; // 도화지
		
		int N = sc.nextInt(); // 색종이의 개수
		for(int n = 0; n < N; n++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			
			for(int i = x; i < x+10; i++) { // 색종이 넓이만큼 색칠하기
				for(int j = y; j < y+10; j++) {
					map[i][j] = 1;
				}
			}
		}
		
		int cnt = 0; // 넓이
		for(int i = 0; i < 100; i++) {
			for(int j = 0; j < 100; j++) {
				if(map[i][j] == 1) cnt += 1; // 색칠된만큼 넓이 구하기
			}
		}
		
		System.out.println(cnt);
	}
}
