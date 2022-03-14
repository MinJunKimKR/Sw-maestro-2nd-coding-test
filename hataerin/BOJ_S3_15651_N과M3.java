
// 중복 허용 순열
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S3_15651_N과M3 {
	static int N, M, a[];
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		a = new int[N];
		solve(0);
		System.out.println(sb);
	}
	
	private static void solve(int cnt) {
		if (cnt == M) {
			for (int i = 0; i < M; i++) {
				sb.append(a[i] + " ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = 1; i <= N; i++) { 
			a[cnt] = i;  
			solve(cnt + 1);
			
		}
	}

}
