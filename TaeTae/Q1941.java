import java.io.*;
import java.util.*;

public class Q1941 {

  static String[][] map = new String[5][5];
  static Student[] studentAry = new Student[25];
  static boolean[] visited = new boolean[25];
  static Student[] result = new Student[7];
  static int answer = 0;

  public static void main(String[] args) throws Exception {
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    for (int i = 0; i < 5; i++) {
      map[i] = bf.readLine().split("");
    }

    solution();
  }

  private static void solution() {
    int tmp = 0;
    for (int i = 0; i < 5; i++) {
      for (int u = 0; u < 5; u++) {
        studentAry[tmp++] = new Student(i, u, map[i][u]);
      }
    }

    combination(0, studentAry.length, 7, 0);

    System.out.print(answer);
  }

  /**
   * 7명을 뽑는 조합 메서드
   */
  private static void combination(int depth, int n, int r, int start) {
    if (depth == r) {

      if (canBe7Princess(result[0])) {
        answer++;
      }
      return;
    }

    for (int i = start; i < n; i++) {
      if (visited[i]) continue;

      visited[i] = true;
      result[depth] = studentAry[i];
      combination(depth+1, n, r, i + 1);

      visited[i] = false;
    }
  }

  /**
   * 칠공주가 될 수 있는지 확인하는 BFS 메서드
   */
  private static boolean canBe7Princess(Student start) {
    int countNearBy = 1; //7명 모두 인접했는지 (탐색됐는지)
    int countSGroup = 0; //이다솜파인 사람이 4명 이상인지
    Deque<Student> deque = new ArrayDeque<>();
    boolean[][] visited = new boolean[5][5];
    deque.addLast(start);
    visited[start.getRow()][start.getCol()] = true;

    if (start.getGroup().equals("S")) countSGroup++;

    while (!deque.isEmpty()) {
      Student current = deque.pollFirst();
      List<Student> nearList = new ArrayList<>();
      nearList.add(new Student(current.getRow()-1, current.getCol(), ""));
      nearList.add(new Student(current.getRow()+1, current.getCol(), ""));
      nearList.add(new Student(current.getRow(), current.getCol()-1, ""));
      nearList.add(new Student(current.getRow(), current.getCol()+1, ""));

      for (Student near : nearList) {
        boolean flag = false;
        if (near.getRow() < 0 || near.getCol() < 0) continue;
        if (near.getRow() >= 5 || near.getCol() >= 5) continue;
        if (visited[near.getRow()][near.getCol()]) continue;
        for (Student tmp : result) {
          if (tmp.getRow() == near.getRow() && tmp.getCol() == near.getCol()) {
            flag = true;
            break;
          }
        }
        if (!flag) continue;

        visited[near.getRow()][near.getCol()] = true;
        deque.addLast(near);
        countNearBy++;
        if (map[near.getRow()][near.getCol()].equals("S")) countSGroup++;
      }
    }

    if (countNearBy != 7) {
      return false;
    }
    if (countSGroup < 4) {
      return false;
    }
    return true;
  }

  /**
   * 각 학생을 나타내는 클래스
   */
  private static class Student {
    private int row;
    private int col;
    private String group;

    public Student() {

    }

    public Student(int row, int col, String group) {
      this.row = row;
      this.col = col;
      this.group = group;
    }

    public int getRow() {
      return row;
    }

    public int getCol() {
      return col;
    }

    public String getGroup() {
      return group;
    }
  }

}