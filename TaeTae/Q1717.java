import java.io.*;
import java.util.*;

public class Q1717 {

  static int n;
  static int m;
  static int[] graph;
  static StringBuilder answer = new StringBuilder();

  public static void main(String[] args) throws Exception {
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(bf.readLine());
    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());
    graph = new int[n+1];

    for (int i = 0; i <= n; i++) {
      graph[i] = i;
    }

    for (int i = 0; i < m; i++) {
      st = new StringTokenizer(bf.readLine());
      int order = Integer.parseInt(st.nextToken());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());

      if (order == 0) {
        merge(a, b);
      } else {
        int parentA = find(a);
        int parentB = find(b);
        if (parentA == parentB) {
          answer.append("YES");
          answer.append("\n");
        } else {
          answer.append("NO");
          answer.append("\n");
        }
      }
    }

    answer.delete(answer.length() - 1, answer.length());

    bw.write(answer.toString());
    bw.close();
    bf.close();
  }

  private static int find(int a) {
    if (graph[a] == a) {
      return a;
    } else {
      graph[a] = find(graph[a]);
      return graph[a];
    }
  }

  private static void merge(int a, int b) {
    int parentA = find(a);
    int parentB = find(b);

    if (parentA < parentB) {
      graph[parentB] = parentA;
    } else if (parentA > parentB) {
      graph[parentA] = parentB;
    }
  }


}