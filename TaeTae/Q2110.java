import java.io.*;
import java.util.*;

public class Q2110 {

  static int n;
  static int c;
  static int[] houseAry;

  public static void main(String[] args) throws Exception {
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(bf.readLine());
    n = Integer.parseInt(st.nextToken());
    c = Integer.parseInt(st.nextToken());
    houseAry = new int[n];

    for (int i = 0; i < n; i++) {
      houseAry[i] = Integer.parseInt(bf.readLine());
    }

    solution();
  }

  private static void solution() {
    Arrays.sort(houseAry);
    int min = 1;
    int max = houseAry[houseAry.length-1] - houseAry[0];
    int mid = (max + min) / 2;

    while (min <= max) {
      int currentCount = getTotalWifiNumber(mid);
      if (currentCount < c) { //설치 가능한 공유기 수가 c보다 작으면
        //공유기 간의 최소 거리를 줄인다.
        max = mid - 1;
        mid = (max + min) / 2;

      } else if (currentCount >= c) { //설치 가능한 공유기 수가 c보다 많거나 같다면
        //공유기 간의 최소 거리를 늘린다.
        min = mid + 1;
        mid = (max + min) / 2;
      }
    }

    System.out.print(mid);
  }

  private static int getTotalWifiNumber(int mid) {
    int lastSetUpHouse = houseAry[0];
    int count = 1;

    for (int i = 1; i < houseAry.length; i++) {
      if (houseAry[i] - lastSetUpHouse >= mid) {
        lastSetUpHouse = houseAry[i];
        count++;
      }
    }

    return count;
  }
}