import java.util.Scanner;

public class BOJ_10157_자리배정 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int R = sc.nextInt();
		int C = sc.nextInt();
		int map[][] = new int[R+2][C+2]; //범위체크 안하도록
		int K = sc.nextInt(); //대기번호
		
		if(K > R*C) { // 반례 : 만일 모든 좌석이 배정되어 해당 대기번호의 관객에게 좌석을 배정할 수 없는 경우
			System.out.println("0");
			System.exit(0);
		}
		
		//벽치기
		for(int i = 0; i < R+2; i++) // 행 개수만큼
			map[i][C+1] = map[i][0] = -1; //세로 양끝을 -1로 변경
		for(int i = 0; i < C+2; i++) // 열 개수만큼
			map[R+1][i] = map[0][i] = -1; //가로 양끝을 -1로 변경
		
//					우   하    좌    상
		int dx[] = {0, 1, 0, -1};
		int dy[] = {1, 0, -1, 0};
		int d=0; // 인덱스
		int k=1; //손님 숫자
		int r=1, c=1;
		
		while(true) {
			if(k == K) { // 손님이 대기번호랑 같아지면
				System.out.println(r + " " + c); //출력
				break;
			}
			if(map[r][c] == 0)map[r][c] = k++; // 현재위치가 빈칸이면 손님을 채우고
			
			if(map[r+dx[d]][c+dy[d]] != 0) { // 다음칸이 벽 또는 사람이라면
				d = (d+1) % 4; // 방향을 바꾸고
			}
			
			r += dx[d]; // 다음칸으로 이동
			c += dy[d];
		}
	}
}
