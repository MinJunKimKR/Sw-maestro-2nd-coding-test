import java.io.*;
import java.util.*;

/**
 * [점화식 정의]
 * d[i][u] = 수용 가능한 최대 무게가 u일 때, "첫번째 물건부터 i번째 물건"까지 고려하여 만든 최대 가치값
 * · "첫번째 물건부터 i번째 물건까지 고려한다는 것"은 필요에 따라 넣지 않는 물건이 있을 수 있다는 것임.
 * · 현재 물건을 넣거나, 넣지 않거나 중 택해야한다.
 * [점화식]
 * d[i][u] = max(d[i-1][u], d[i-1][u-weight] + value)
 * · d[i-1][u] : 현재 물건(u)를 넣지 않는 경우. (이전 물건까지 고려해서 무게 u 이하의 최대 가치)
 * · d[i-1][u-weight] + value : 현재 물건(u)를 넣는 경우. (이전 물건까지 고려해서, 무게 u - weight 이하의 최대 가치)
 */
public class Q12865 {

  static int n;
  static int k;
  static int[][] itemAry;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    k = Integer.parseInt(st.nextToken());
    itemAry = new int[n+1][2];

    for (int i = 1; i <= n; i++) {
      st = new StringTokenizer(br.readLine());
      itemAry[i][0] = Integer.parseInt(st.nextToken());
      itemAry[i][1] = Integer.parseInt(st.nextToken());
    }

    solution();
  }

  /**
   * 냅색 DP
   */
  private static void solution() {
    int[][] d = new int[n+1][k+1];

    for (int i = 0; i < k + 1; i++) {
      if (i >= itemAry[1][0]) {
        d[1][i] = itemAry[1][1];
      }
    }

    for (int itemLimit = 2; itemLimit < n + 1; itemLimit++) {
      for (int maxWeight = 0; maxWeight < k+1; maxWeight++) {
        d[itemLimit][maxWeight] = d[itemLimit-1][maxWeight]; //현재 item을 선택하지 않은 경우

        if (maxWeight - itemAry[itemLimit][0] >= 0) { //현재 item을 선택할 수 있는 경우
          d[itemLimit][maxWeight] = Math.max(d[itemLimit][maxWeight]
                                      , d[itemLimit-1][maxWeight - itemAry[itemLimit][0]] + itemAry[itemLimit][1]);
        }
      }
    }

    System.out.print(d[n][k]);
  }


}