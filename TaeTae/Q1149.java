import java.io.*;
import java.util.*;

/**
 * [점화식 정의]
 * d[i][0] = i번째 집을 Red로 칠했을 때, (1~i)번째 집까지의 총 최소 비용
 * d[i][1] = i번째 집을 Green로 칠했을 때, (1~i)번째 집까지의 총 최소 비용
 * d[i][2] = i번째 집을 Blue로 칠했을 때, (1~i)번째 집까지의 총 최소 비용
 *
 * [점화식]
 * d[i][0] = min(d[i-1][1], d[i-1][2])
 * d[i][1] = min(d[i-1][0], d[i-1][2])
 * d[i][2] = min(d[i-1][0], d[i-1][1])
 */
public class Q1149 {

  static int n; //집의 개수
  static int[][] colorAry; //색상비용

  public static void main(String[] args) throws Exception {
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    n = Integer.parseInt(bf.readLine());
    colorAry = new int[n+1][3];

    for (int i = 1; i <= n; i++) {
      StringTokenizer st = new StringTokenizer(bf.readLine());
      colorAry[i][0] = Integer.parseInt(st.nextToken()); //i번째 집을 Red로 칠하는 비용
      colorAry[i][1] = Integer.parseInt(st.nextToken()); //i번째 집을 Green로 칠하는 비용
      colorAry[i][2] = Integer.parseInt(st.nextToken()); //i번째 집을 Blue로 칠하는 비용
    }

    solution();
  }

  private static void solution() {
    int[][] d = new int[n+1][3];

    //초기화
    d[1][0] = colorAry[1][0];
    d[1][1] = colorAry[1][1];
    d[1][2] = colorAry[1][2];

    for (int i = 2; i <= n; i++) {
      d[i][0] = Math.min(d[i-1][1], d[i-1][2]) + colorAry[i][0];
      d[i][1] = Math.min(d[i-1][0], d[i-1][2]) + colorAry[i][1];
      d[i][2] = Math.min(d[i-1][0], d[i-1][1]) + colorAry[i][2];
    }

    System.out.print(Math.min(d[n][0], Math.min(d[n][1], d[n][2])));
  }


}