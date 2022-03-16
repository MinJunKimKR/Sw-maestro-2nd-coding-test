import java.io.*;
import java.util.*;

public class Q3055 {

  static int r;
  static int c;
  static String[][] animalMap;
  static boolean[][] visitedAnimal;
  static boolean[][] visitedWater;
  static List<Position> waterStartList = new ArrayList<>();
  static Position s;
  static Position d;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    r = Integer.parseInt(st.nextToken());
    c = Integer.parseInt(st.nextToken());

    animalMap = new String[r][c];
    visitedAnimal = new boolean[r][c];
    visitedWater = new boolean[r][c];

    for (int i = 0; i < r; i++) {
      String[] tmp = br.readLine().split("");
      for (int u = 0; u < tmp.length; u++) {
        if (tmp[u].equals("*")) {
          waterStartList.add(new Position(i, u));
          animalMap[i][u] = "*";
        } else if (tmp[u].equals("D")) {
          d = new Position(i, u);
          animalMap[i][u] = "D";
        } else if (tmp[u].equals("S")) {
          s = new Position(i, u);
          animalMap[i][u] = "0";
        } else if (tmp[u].equals("X")) {
          animalMap[i][u] = "X";
        } else if (tmp[u].equals(".")) {
          animalMap[i][u] = ".";
        }
      }
    }

    solution();
  }

  private static void solution() {
    int result = bfs(s);

    System.out.print((result == -1) ? "KAKTUS" : result);
  }

  private static int bfs(Position animalStart) {
    //1분 단위로 물을 확산시키고 고슴도치를 이동시켜야하기 때문에, 아래 두 변수를 사용한다.
    int countNowAddedWater = waterStartList.size(); //1분씩 물을 확산시키기 위한 변수
    int countNowMoveAnimal = 1; //1분씩 고슴도치를 이동시키기 위한 변수


    Deque<Position> animalDeque = new ArrayDeque<>(); //고슴도치 전용 deque
    Deque<Position> waterDeque = new ArrayDeque<>(); //물 전용 deque

    //물 시작점를 통해 초기화
    for (Position p : waterStartList) {
      waterDeque.addLast(p);
      visitedWater[p.getRow()][p.getCol()] = true;
    }

    //고슴도치 시작점을 통해 초기화
    animalDeque.addLast(animalStart);
    visitedAnimal[animalStart.getRow()][animalStart.getCol()] = true;


    while (true) {
      //--------물 이동-------------
      int waterCount = 0; //물이 1분동안 얼마나 확산되었는지 확인하기 위한 변수

      for (int i = 0; i < countNowAddedWater; i++) {
        Position currentWater = waterDeque.pollFirst();
        List<Position> nearWaterList = new ArrayList<>();
        nearWaterList.add(new Position(currentWater.getRow()-1, currentWater.getCol()));
        nearWaterList.add(new Position(currentWater.getRow()+1, currentWater.getCol()));
        nearWaterList.add(new Position(currentWater.getRow(), currentWater.getCol()-1));
        nearWaterList.add(new Position(currentWater.getRow(), currentWater.getCol()+1));

        for (Position near : nearWaterList) {
          if (near.getRow() < 0 || near.getCol() < 0) continue; //범위밖이면 이동 X
          if (near.getRow() >= r || near.getCol() >= c) continue; //범위밖이면 이동 X
          if (animalMap[near.getRow()][near.getCol()].equals("D")) continue; //동굴이면 이동 X
          if (animalMap[near.getRow()][near.getCol()].equals("X")) continue; //바위면 이동 X
          if (visitedWater[near.getRow()][near.getCol()]) continue; //이미 방문한 곳이면 이동 X

          waterDeque.addLast(near);
          visitedWater[near.getRow()][near.getCol()] = true;
          waterCount++; //확산된 횟수 세기
        }
      }
      countNowAddedWater = waterCount; //직전에 확산된 횟수만큼만 deque에서 pull해야한다. (그래야 1분 단위로 움직임을 보장할 수 있다.)

      //--------고슴도치 이동-------------
      int animalCount = 0; //1분동안 고슴도치가 얼마나 확산됐(움직였)는지 확인하기 위한 변수

      for (int i = 0; i < countNowMoveAnimal; i++) {
        Position currentAnimal = animalDeque.pollFirst();
        List<Position> nearAnimalList = new ArrayList<>();
        nearAnimalList.add(new Position(currentAnimal.getRow()-1, currentAnimal.getCol()));
        nearAnimalList.add(new Position(currentAnimal.getRow()+1, currentAnimal.getCol()));
        nearAnimalList.add(new Position(currentAnimal.getRow(), currentAnimal.getCol()-1));
        nearAnimalList.add(new Position(currentAnimal.getRow(), currentAnimal.getCol()+1));

        for (Position near : nearAnimalList) {
          if (near.getRow() < 0 || near.getCol() < 0) continue; //범위밖이면 이동 X
          if (near.getRow() >= r || near.getCol() >= c) continue; //범위밖이면 이동 X
          if (visitedWater[near.getRow()][near.getCol()]) continue; //물이 있다면 이동 X
          if (animalMap[near.getRow()][near.getCol()].equals("X")) continue; //바위면 이동 X
          if (visitedAnimal[near.getRow()][near.getCol()]) continue; //이미 방문한 곳이면 이동 X

          //동굴에 도착했다면
          if (animalMap[near.getRow()][near.getCol()].equals("D")) {
            return Integer.parseInt(animalMap[currentAnimal.getRow()][currentAnimal.getCol()]) + 1;
          }


          animalDeque.addLast(near);
          visitedAnimal[near.getRow()][near.getCol()] = true;
          int beforeCount = Integer.parseInt(animalMap[currentAnimal.getRow()][currentAnimal.getCol()]);
          animalMap[near.getRow()][near.getCol()] = String.valueOf(beforeCount + 1);
          animalCount++; //확산된 횟수 세기
        }
      }
      countNowMoveAnimal = animalCount; //직전에 확산된 횟수만큼만 deque에서 pull해야한다. (그래야 1분 단위로 움직임을 보장할 수 있다.)

      //만약 더이상 확산될 수 없다면, 고슴도치가 탈출에 실패했다.
      if (animalCount == 0 && waterCount == 0) {
        return -1;
      }
    }
  }

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