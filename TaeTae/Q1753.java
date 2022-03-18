import java.io.*;
import java.util.*;

public class Q1753 {

  static int v;
  static int e;
  static int start;
  static List<List<Node>> graph = new ArrayList<>();
  static int[] table;
  static StringBuilder answer = new StringBuilder();

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    v = Integer.parseInt(st.nextToken());
    e = Integer.parseInt(st.nextToken());
    start = Integer.parseInt(br.readLine());
    table = new int[v+1];

    for (int i = 0; i <= v; i++) {
      graph.add(new ArrayList<>());
    }

    for (int i = 0; i < e; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      int w = Integer.parseInt(st.nextToken());

      graph.get(a).add(new Node(b, w));
    }

    solution();

    bw.write(answer.toString());
    bw.close();
    br.close();
  }

  public static void solution() {
    Arrays.fill(table, (int) 1e9);
    table[start] = 0;

    dij(start);

    for (int i = 1; i < table.length; i++) {
      if (table[i] == (int) 1e9) {
        answer.append("INF");
      } else {
        answer.append(table[i]);
      }
      answer.append("\n");
    }

    answer.delete(answer.length()-1, answer.length());
  }

  /**
   * 다익스트라 알고리즘
   * @param start
   */
  private static void dij(int start) {
    PriorityQueue<Node> queue = new PriorityQueue<>();
    queue.offer(new Node(start, 0));

    while (!queue.isEmpty()) {
      Node current = queue.poll();

      //필요없는 데이터라면 생략
      if (current.getDistance() > table[current.getIndex()]) continue;

      //현재 데이터의 index의 인접 노드 구하기
      List<Node> nearList = graph.get(current.getIndex());
      for (Node near : nearList) {

        //만약 '시작노드~near노드'까지의 거리가 현재 table에 저장된 값보다 작다면 갱신
        if (table[near.getIndex()] > table[current.getIndex()] + near.getDistance()) {
          table[near.getIndex()] = table[current.getIndex()] + near.getDistance();
          queue.offer(new Node(near.getIndex(), table[near.getIndex()]));
        }
      }
    }
  }

  private static class Node implements Comparable<Node> {
    private int index;
    private int distance;

    public Node() {

    }

    public Node(int index, int distance) {
      this.index = index;
      this.distance = distance;
    }

    public int getIndex() {
      return index;
    }

    public int getDistance() {
      return distance;
    }

    @Override
    public int compareTo(Node other) {
      if (this.distance < other.distance) {
        return -1;
      } else if (this.distance > other.distance) {
        return 1;
      } else {
        if (this.index < other.index) {
          return -1;
        } else if (this.index > other.index) {
          return 1;
        } else {
          return 0;
        }
      }
    }
  }
}