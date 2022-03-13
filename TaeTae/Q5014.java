import java.io.*;
import java.util.*;

public class Main {

  static int f;
  static int s;
  static int g;
  static int u;
  static int d;
  static boolean[] visited;
  static int[] countAry;

  public static void main(String[] args) throws Exception {
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(bf.readLine());
    f = Integer.parseInt(st.nextToken());
    s = Integer.parseInt(st.nextToken());
    g = Integer.parseInt(st.nextToken());
    u = Integer.parseInt(st.nextToken());
    d = Integer.parseInt(st.nextToken());
    visited = new boolean[f+1];
    countAry = new int[f+1];

    solution();
  }

  private static void solution() {
    if (g == s) {
      System.out.print(0);
      return;
    }

    bfs(s);

    if (countAry[g] != 0) {
      System.out.print(countAry[g]);
    } else {
      System.out.print("use the stairs");
    }
  }

  /**
   * 경우의 수를 계산하는 BFS 메서드
   * @param start
   */
  private static void bfs(int start) {
    Deque<Integer> deque = new ArrayDeque<>(); //층 저장
    deque.addLast(start);
    visited[start] = true;

    while (!deque.isEmpty()) {
      int current = deque.pollFirst();
      List<Integer> nearList = new ArrayList<>();
      nearList.add(current - d);
      nearList.add(current + u);

      for (int near : nearList) {
        if (near < 1 || near > f) continue; //갈수없는 층이라면 중단
        if (visited[near]) continue; //방문한 층이라면 중단

        countAry[near] = countAry[current] + 1;
        visited[near] = true;
        deque.addLast(near);
      }
    }

  }
}