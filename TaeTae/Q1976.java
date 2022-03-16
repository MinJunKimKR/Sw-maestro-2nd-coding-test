import java.io.*;
import java.util.*;

public class Q1976 {

  static int n;
  static int m;
  static int[] graph;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    n = Integer.parseInt(br.readLine());
    m = Integer.parseInt(br.readLine());
    graph = new int[n+1];

    for (int i = 1; i < n + 1; i++) {
      graph[i] = i;
    }

    for (int i = 1; i < n+1; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int relationNum = 1;
      while (st.hasMoreTokens()) {
        int value = Integer.parseInt(st.nextToken());
        if (value == 1) {
          merge(i, relationNum); //value가 1이라면 Union 연산 수행
        }
        relationNum++;
      }
    }

    solution(br.readLine());
  }

  //'여행경로의 각 도시'의 부모노드가 같지 않다면, 하나의 트리안에 있는 것이 아니므로, NO를 출력한다.
  private static void solution(String travelRoute) {
    String[] travelRouteAry = travelRoute.split(" ");
    int parent = find(Integer.parseInt(travelRouteAry[0]));

    for (int i = 1; i < travelRouteAry.length; i++) {
      if (parent != find(Integer.parseInt(travelRouteAry[i]))) {
        System.out.print("NO");
        return;
      }
    }

    System.out.print("YES");
  }

  private static int find(int a) {
    if (graph[a] == a) {
      return a;
    }

    graph[a] = find(graph[a]);
    return graph[a];
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