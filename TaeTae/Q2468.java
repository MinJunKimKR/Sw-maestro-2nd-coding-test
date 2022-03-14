import java.io.*;
import java.util.*;

public class Q2468 {

  static int n;
  static int[][] map;
  static boolean[][] visited;
  static int answer = 0;

  public static void main(String[] args) throws Exception {
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    n = Integer.parseInt(bf.readLine());
    map = new int[n][n];
    visited = new boolean[n][n];

    for (int i = 0; i < n; i++) {
      StringTokenizer st = new StringTokenizer(bf.readLine());
      for (int u = 0; u < n; u++) {
        map[i][u] = Integer.parseInt(st.nextToken());
      }
    }

    solution();
  }

  private static void solution() {
    int maxHeight = 0;

    //map 상의 최대높이 구하기
    for (int i = 0; i < n; i++) {
      int tmp = Arrays.stream(map[i]).max().getAsInt();
      if (maxHeight < tmp) {
        maxHeight = tmp;
      }
    }

    //각 비의 높이마다 안전지대 개수 계산
    //고려해야하는 비의 높이는 0(비가 전혀 오지 않는 경우)부터 map 상의 최대값까지이다.
    for (int waterHeight = 0; waterHeight <= maxHeight; waterHeight++) {
      int countSafeArea = 0; //안전지대 개수
      visited = new boolean[n][n];

      //각 칸마다 dfs의 시작점으로 지정
      for (int i = 0; i < n * n; i++) {
        Position current = new Position(i/n, i%n);
        if (map[current.getRow()][current.getCol()] <= waterHeight) continue;
        if (visited[current.getRow()][current.getCol()]) continue;
        dfs(current, waterHeight);
        countSafeArea++;
      }

      if (answer < countSafeArea) {
        answer = countSafeArea;
      }
    }

    System.out.print(answer);
  }

  /**
   * 문제 해결을 위한 dfs
   * @param current
   * @param waterHeight
   */
  private static void dfs(Position current, int waterHeight) {
    if (visited[current.getRow()][current.getCol()]) return;

    visited[current.getRow()][current.getCol()] = true;

    List<Position> nearList = new ArrayList<>();
    nearList.add(new Position(current.getRow()-1, current.getCol()));
    nearList.add(new Position(current.getRow()+1, current.getCol()));
    nearList.add(new Position(current.getRow(), current.getCol()-1));
    nearList.add(new Position(current.getRow(), current.getCol()+1));

    for (Position near : nearList) {
      if (near.getRow() < 0 || near.getCol() < 0) continue;
      if (near.getRow() >= n || near.getCol() >= n) continue;
      if (map[near.getRow()][near.getCol()] <= waterHeight) continue;
      if (visited[near.getRow()][near.getCol()]) continue;

      dfs(near, waterHeight);
    }
  }

  /**
   * 각 위치(map상의)를 나타내는 클래스
   */
  private static class Position {
    private int row;
    private int col;

    public Position() {}

    public Position(int row, int col) {
      this.row = row;
      this.col = col;
    }

    public int getRow() {
      return row;
    }

    public int getCol() {
      return col;
    }
  }
}