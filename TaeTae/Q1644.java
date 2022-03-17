import java.io.*;
import java.util.*;

public class Q1644 {

  static int n;
  static int answer = 0;
  static List<Integer> primeNumList;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    n = Integer.parseInt(br.readLine());

    if (n == 1) { //n이 1인 경우, 정답은 0이다.
      System.out.print(0);
      return;
    } else if (n == 2) { //n이 2인 경우, 정답은 1이다. (예외처리함)
      System.out.print(1);
      return;
    }

    primeNumList = getPrimeNumList();
    solution();

    System.out.print(answer);
  }

  private static void solution() {
    int startIndex = 0;
    int endIndex = 0;
    int result = primeNumList.get(0);

    while (true) {
      if (result < n && endIndex < primeNumList.size() - 1) { //계산 결과가 n보다 작다면
        endIndex++;
        result += primeNumList.get(endIndex);
      } else if (result > n && startIndex < primeNumList.size() - 1) { //계산 결과가 n보다 크다면
        result -= primeNumList.get(startIndex);
        startIndex++;
      } else { //계산 결과가 n과 같다면
        answer++;

        //한칸씩 뒤로 이동시킨다.
        if (startIndex < primeNumList.size() - 1) {
          result -= primeNumList.get(startIndex);
          startIndex++;
        }
        if (endIndex < primeNumList.size() - 1) {
          endIndex++;
          result += primeNumList.get(endIndex);
        }
      }

      if (startIndex == primeNumList.size() - 1 && endIndex == primeNumList.size() - 1) break;
    }

    if (n == primeNumList.get(primeNumList.size()-1)) {
      answer++;
    }
  }

  private static List<Integer> getPrimeNumList() {
    List<Integer> result = new ArrayList<>();
    result.add(2);

    for (int primeNum = 3; primeNum <= n; primeNum++) {
      boolean isPrime = true;
      for (int i = 2; i <= Math.sqrt(primeNum); i++) {
        if (primeNum % i == 0) {
          isPrime = false;
          break;
        }
      }
      if (isPrime) {
        result.add(primeNum);
      }
    }

    return result;
  }


}