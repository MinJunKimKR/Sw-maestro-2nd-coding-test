import java.io.*;
import java.util.*;

public class Q1759 {

  static int l;
  static int c;
  static char[] charAry;
  static boolean[] visited;
  static char[] result;
  static List<String> answerList = new ArrayList<>();
  static StringBuilder answer = new StringBuilder();

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    l = Integer.parseInt(st.nextToken());
    c = Integer.parseInt(st.nextToken());

    st = new StringTokenizer(br.readLine());
    charAry = new char[c];
    visited = new boolean[c];
    result = new char[l];
    for (int i = 0; i < c; i++) {
      charAry[i] = st.nextToken().charAt(0);
    }

    solution();

    bw.write(answer.toString());
    bw.close();
    br.close();
  }

  private static void solution() {
    permutation(0, charAry.length, l);

    Collections.sort(answerList);

    for (String s : answerList) {
      answer.append(s);
      answer.append("\n");
    }

    answer.delete(answer.length()-1, answer.length());
  }

  /**
   * 순열 메서드
   * @param depth
   * @param n
   * @param r
   */
  private static void permutation(int depth, int n, int r) {
    if (depth == r) {
      calculateResult();
      return;
    }

    for (int i = 0; i < n; i++) {
      if (visited[i]) continue;

      //오름차순이 아니라면 생략
      if (depth > 0) {
        if (result[depth-1] > charAry[i]) continue;
      }

      visited[i] = true;
      result[depth] = charAry[i];
      permutation(depth+1, n, r);

      visited[i] = false;
    }
  }

  /**
   * 가능성 있는 비밀번호인지 확인하는 메서드
   */
  private static void calculateResult() {
    int countA = 0;
    int countB = 0;

    for (char c : result) {
      if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
        countA++;
      } else {
        countB++;
      }
    }

    if (countA >= 1 && countB >= 2) {
      answerList.add(String.valueOf(result));
    }
  }
}