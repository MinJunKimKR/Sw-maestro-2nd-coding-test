import java.io.*;
import java.util.*;

public class Q10026 {

  static int n;
  static String[][] paint;
  static boolean[][] visited;

  public static void main(String[] args) throws Exception {
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    n = Integer.parseInt(bf.readLine());
    paint = new String[n][n];

    for (int i = 0; i < n; i++) {
      paint[i] = bf.readLine().split("");
    }

    solution();
  }

  private static void solution() {
    int countBlindArea = 0; //색맹인 사람이 세는 영역 개수 저장
    int countNormalArea = 0; //정상인 사람이 세는 영역 개수 저장

    visited = new boolean[n][n];
    for (int i = 0; i < n * n; i++) { //각 좌표마다 확인하기
      if (visited[i/n][i%n]) continue; //이미 방문한 곳이라면 bfs 수행 X
      bfs(new Position(i/n, i%n), true);
      countBlindArea++; //영역 개수 세기
    }

    visited = new boolean[n][n];
    for (int i = 0; i < n * n; i++) { //각 좌표마다 확인하기
      if (visited[i/n][i%n]) continue; //이미 방문한 곳이라면 bfs 수행 X
      bfs(new Position(i/n, i%n), false);
      countNormalArea++; //영역 개수 세기
    }

    System.out.print(countNormalArea + " " + countBlindArea);
  }

  /**
   * BFS를 통한 문제해결
   * @param start
   * @param isBlind
   */
  private static void bfs(Position start, boolean isBlind) {
    Deque<Position> deque = new ArrayDeque<>();
    String startColor = paint[start.getRow()][start.getCol()];
    deque.addLast(start);
    visited[start.getRow()][start.getCol()] = true;

    while (!deque.isEmpty()) {
      Position current = deque.pollFirst();
      List<Position> nearList = new ArrayList<>();
      nearList.add(new Position(current.getRow()-1, current.getCol()));
      nearList.add(new Position(current.getRow()+1, current.getCol()));
      nearList.add(new Position(current.getRow(), current.getCol()-1));
      nearList.add(new Position(current.getRow(), current.getCol()+1));

      nearLoop: for (Position near : nearList) {
        if (near.getRow() < 0 || near.getCol() < 0) continue;
        if (near.getRow() >= n || near.getCol() >= n) continue;
        if (visited[near.getRow()][near.getCol()]) continue;

        if (isBlind) { //색맹인 사람일 경우
          switch (startColor) {
            case "R":
              if (!paint[near.getRow()][near.getCol()].equals("R") && !paint[near.getRow()][near.getCol()].equals("G")) {
                continue nearLoop;
              }
              break;
            case "G":
              if (!paint[near.getRow()][near.getCol()].equals("R") && !paint[near.getRow()][near.getCol()].equals("G")) {
                continue nearLoop;
              }
              break;
            case "B":
              if (!paint[near.getRow()][near.getCol()].equals("B")) {
                continue nearLoop;
              }
              break;
          }
        } else { //색맹이 아닌 사람일 경우
          switch (startColor) {
            case "R":
              if (!paint[near.getRow()][near.getCol()].equals("R")) {
                continue nearLoop;
              }
              break;
            case "G":
              if (!paint[near.getRow()][near.getCol()].equals("G")) {
                continue nearLoop;
              }
              break;
            case "B":
              if (!paint[near.getRow()][near.getCol()].equals("B")) {
                continue nearLoop;
              }
              break;
          }
        }

        visited[near.getRow()][near.getCol()] = true;
        deque.addLast(near);
      }
    }
  }

  private static class Position {
    private int row;
    private int col;

    public Position() {

    }

    public Position(int row, int col) {
      this.row = row;
      this.col = col;
    }

    private int getRow() {
      return row;
    }

    private int getCol() {
      return col;
    }
  }
}