import java.io.*;
import java.util.*;

public class Q2470 {

  static int n;
  static long[] featureAry;

  public static void main(String[] args) throws Exception {
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    n = Integer.parseInt(bf.readLine());
    featureAry = new long[n];
    StringTokenizer st = new StringTokenizer(bf.readLine());
    for (int i = 0; i < n; i++) {
      featureAry[i] = Long.parseLong(st.nextToken());
    }

    solution();
  }

  /**
   * 투포인터 활용 메서드
   */
  private static void solution() {
    Arrays.sort(featureAry); //용액 특성 배열 오름차순 정렬

    int start = 0; //가장 작은 특성값 index
    int end = featureAry.length - 1; //가장 큰 특성값 index
    long answerResult = Long.MAX_VALUE; //0에 가장 가까운 값을 저장하는 변수
    int answerA = 0; //0에 가장 가까운 값을 만드는 start 값을 저장하는 변수
    int answerB = 0; //0에 가장 가까운 값을 만드는 end 값을 저장하는 변수

    while (start < end) {
      long result = featureAry[start] + featureAry[end];

      if (Math.abs(result) < answerResult) { //두 용액을 합친 특성값의 절대값이 기존의 값보다 작다면, 정답으로 저장
        answerResult = Math.abs(result);
        answerA = start;
        answerB = end;
      }

      /*두 용액을 합친 특성값이 음수라면, 보다 큰 값으로 만들어야 0에 가까워진다.
       * 따라서 start 값을 증가시켜 총 특성값을 더 크게 만든다.
       */
      if (result < 0) { //두 용액을 합친 특성값이 음수라면 start를 1 증가시킨다.
        start++;

      /*두 용액을 합친 특성값이 양수라면, 보다 작은 값으로 만들어야 0에 가까워진다.
       * 따라서 end 값을 감소시켜 총 특성값을 더 작게 만든다.
       */
      } else if (result > 0) { //두 용액을 합친 특성값이 음수라면 end를 1 증가시킨다.
        end--;

      /*
       * 두 용액을 합친 특성값이 정확히 0이라면, 더 이상 계산하지 않고 그대로 출력하면 된다.
       */
      } else {
        System.out.print(featureAry[start] + " " + featureAry[end]);
        return;
      }
    }

    System.out.print(featureAry[answerA] + " " + featureAry[answerB]);
  }
}