import java.io.*;
import java.util.*;

public class Main {

  static int n;
  static int m;
  static List<List<Integer>> partyList = new ArrayList<>();
  static List<Integer> knowTruthPersonList = new ArrayList<>();
  static int[] graph;
  static int answer;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());
    graph = new int[n+1];
    answer = m;

    st = new StringTokenizer(br.readLine());
    int knowTruthCount = Integer.parseInt(st.nextToken());

    for (int i = 0; i < knowTruthCount; i++) {
      knowTruthPersonList.add(Integer.parseInt(st.nextToken()));
    }

    for (int i = 0; i < m; i++) {
      st = new StringTokenizer(br.readLine());
      int memberCount = Integer.parseInt(st.nextToken());
      List<Integer> partyMemberList = new ArrayList<Integer>();

      for (int u = 0; u < memberCount; u++) {
        int member = Integer.parseInt(st.nextToken());
        partyMemberList.add(member);
      }

      partyList.add(partyMemberList);
    }

    solution();
  }

  private static void solution() {
    for (int i = 0; i <= n; i++) {
      graph[i] = i;
    }

    //각 파티의 참석자끼리 merge
    for (List<Integer> partyMemberList : partyList) {
      for (int i = 1; i < partyMemberList.size(); i++) {
        int a = partyMemberList.get(i-1);
        int b = partyMemberList.get(i);
        merge(a, b);
      }
    }

    //진실을 아는 사람과 함께한 파티인지 확인
    label: for (List<Integer> partyMemberList : partyList) {
      int parentA = find(partyMemberList.get(0));
      int parentB = 0;
      for (int knowTruthPerson : knowTruthPersonList) {
        parentB = find(knowTruthPerson);
        if (parentA == parentB) {
          answer--;
          continue label;
        }
      }
    }

    System.out.print(answer);
  }

  /**
   * find 메서드
   * @param a
   * @return
   */
  private static int find(int a) {
    if (graph[a] == a) {
      return a;
    }

    graph[a] = find(graph[a]);
    return graph[a];
  }

  /**
   * Union 메서드
   * @param a
   * @param b
   */
  private static void merge(int a, int b) {
    int parentA = find(a);
    int parentB = find(b);

    if (parentA < parentB) {
      graph[parentB] = parentA;
    } else {
      graph[parentA] = parentB;
    }
  }
}