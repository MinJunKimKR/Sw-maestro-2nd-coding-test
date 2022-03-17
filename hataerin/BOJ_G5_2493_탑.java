
import java.io.*;
import java.util.*;

public class BOJ_G5_2493_탑 {

	public static void main(String[] args) throws Exception {

		// Scanner 사용할 경우 메모리 초과 또는 시간 초과 발생
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s = br.readLine();
		StringTokenizer st = new StringTokenizer(s);
		int n = Integer.parseInt(st.nextToken()); // n 은 최대 50만

		st = new StringTokenizer(br.readLine());
		Stack<int[]> stack = new Stack<>();
		// input
		for (int i = 1; i <= n; i++) {
			int num = Integer.parseInt(st.nextToken());

			while (!stack.empty()) {
				if (stack.peek()[1] > num) {
					System.out.print(stack.peek()[0] + " ");
					break;
				}
				stack.pop();
			}

			if (stack.empty()) {
				System.out.print("0 ");
			}
			stack.push(new int[] { i, num });
		}
	}
}