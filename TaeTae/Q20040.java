import java.io.*;
import java.util.*;

public class Q20040 {

  static int n;
  static int m;
  static int[] graphA;
  static int answer = 0;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());
    graphA = new int[n];

    for (int i = 0; i < n; i++) {
      graphA[i] = i;
    }

    for (int i = 0; i < m; i++) {
      st = new StringTokenizer(br.readLine());
      int dot1 = Integer.parseInt(st.nextToken());
      int dot2 = Integer.parseInt(st.nextToken());

      boolean isCycled = merge(dot1, dot2);
      if (isCycled) {
        System.out.print(i+1);
        return;
      }
    }

    System.out.print(0);
  }

  private static int find(int a) {
    if (graphA[a] == a) {
      return a;
    }

    graphA[a] = find(graphA[a]);
    return graphA[a];
  }

  private static boolean merge(int a, int b) {
    int parentA = find(a);
    int parentB = find(b);

    if (parentA < parentB) {
      graphA[parentB] = parentA;
    } else if (parentA > parentB) {
      graphA[parentA] = parentB;
    } else { //이미 두 점이 같은 그래프에 속한다면
      return true;
    }

    return false;
  }

//  private static int findGraphB(int a) {
//    if (graphB[a] == a) {
//      return a;
//    }
//
//    graphB[a] = findGraphB(a);
//    return graphB[a];
//  }
//
//  private static boolean mergeGraphB(int a, int b) {
//    int parentA = findGraphB(a);
//    int parentB = findGraphB(b);
//
//    if (parentA < parentB) {
//      graphB[parentB] = parentA;
//    } else if (parentA > parentB) {
//      graphB[parentA] = parentB;
//    } else { //이미 두 점이 같은 그래프에 속한다면
//      return true;
//    }
//
//    return false;
//  }


}