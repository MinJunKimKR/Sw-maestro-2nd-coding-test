import java.io.*;
import java.util.*;

public class Q4195 {

  static int testCase;
  static int f;
  static StringBuilder answer = new StringBuilder();
  static Map<String, String> graph;
  static Map<String, Integer> countMap;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    testCase = Integer.parseInt(br.readLine());

    while (--testCase >= 0) {
//      System.out.println("-----");
      f = Integer.parseInt(br.readLine());
      graph = new HashMap<>();
      countMap = new HashMap<>();

      for (int i = 0; i < f; i++) {
        StringTokenizer st = new StringTokenizer(br.readLine());
        String a = st.nextToken();
        String b = st.nextToken();

        if (!graph.containsKey(a)) {
          graph.put(a, a);
          countMap.put(a, 1);
        }
        if (!graph.containsKey(b)) {
          graph.put(b, b);
          countMap.put(b, 1);
        }

        answer.append(merge(a, b));
        answer.append("\n");
      }

//      Iterator<String> iter = graph.keySet().iterator();
//      while (iter.hasNext()) {
//        String key = iter.next();
//        System.out.println(key + ": " + graph.get(key));
//      }
    }

    answer.delete(answer.length()-1, answer.length());
    bw.write(answer.toString());
    bw.close();
    br.close();
  }

  private static String find(String a) {
    if (graph.get(a).equals(a)) {
      return a;
    }

    graph.put(a, find(graph.get(a)));
    return graph.get(a);
  }

  private static int merge(String a, String b) {
    String parentA = find(a);
    String parentB = find(b);

    if (parentA.compareTo(parentB) < 0) {
      graph.put(parentB, parentA);
    } else if (parentA.compareTo(parentB) > 0) {
      graph.put(parentA, parentB);
    } else {
      if (parentA.length() < parentB.length()) {
        graph.put(parentB, parentA);
      } else if (parentA.length() > parentB.length()) {
        graph.put(parentA, parentB);
      }
    }

    //-------count----------
    int count = 0;

    if (parentA.equals(parentB)) {
      count = countMap.get(parentA);
    } else {
      count = countMap.get(parentA) + countMap.get(parentB);
      countMap.put(find(parentA), count);
    }

    return count;
    //-------count----------

  }


}