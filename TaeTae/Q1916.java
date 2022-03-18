import java.io.*;
import java.util.*;

public class Q1916 {

  static int n;
  static int m;
  static int[][] graph;
  static int start;
  static int destination;
  static long answer;
  static long[] table;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    n = Integer.parseInt(br.readLine());
    m = Integer.parseInt(br.readLine());
    graph = new int[n+1][n+1];
    table = new long[n+1];

    for (int i = 0; i < table.length; i++) {
      table[i] = Long.MAX_VALUE;
    }

    for (int i = 0; i < graph.length; i++) {
      Arrays.fill(graph[i], -1);
    }

    for (int i = 0; i < m; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      int w = Integer.parseInt(st.nextToken());

      //같은 버스가 있다면 작은 것으로 저장한다.
      if (graph[a][b] == -1) {
        graph[a][b] = w;
      } else if (graph[a][b] > w) {
        graph[a][b] = w;
      }
    }

    StringTokenizer st = new StringTokenizer(br.readLine());
    start = Integer.parseInt(st.nextToken());
    destination = Integer.parseInt(st.nextToken());

    solution();

    bw.write(String.valueOf(answer));
    bw.close();
    br.close();
  }

  private static void solution() {
    dij(start);

    answer = table[destination];
  }

  private static void dij(int start) {
    PriorityQueue<Node> queue = new PriorityQueue<>();
    queue.offer(new Node(start, 0));
    table[start] = 0;

    while (!queue.isEmpty()) {
      Node current = queue.poll();

      if (current.getDistance() > table[current.getIndex()]) continue;

      for (int i = 1; i <= n; i++) {
        if (graph[current.getIndex()][i] == -1) continue;
        if (table[i] > graph[current.getIndex()][i] + table[current.getIndex()]) {
          table[i] = graph[current.getIndex()][i] + table[current.getIndex()];
          queue.offer(new Node(i, table[i]));
        }
      }
    }
  }

  private static class Node implements Comparable<Node> {
    private int index;
    private long distance;

    public Node() {}

    public Node(int index, long distance) {
      this.index = index;
      this.distance = distance;
    }

    public int getIndex() {
      return index;
    }

    public long getDistance() {
      return distance;
    }

    @Override
    public int compareTo(Node other) {
      if (this.distance < other.distance) {
        return -1;
      } else if (this.distance > other.distance) {
        return 1;
      } else {
        return 0;
      }
    }
  }
}