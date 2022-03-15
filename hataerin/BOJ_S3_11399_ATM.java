import java.util.Arrays;
import java.util.Scanner;

public class BOJ_S3_11399_ATM {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		

	int N = sc.nextInt();
		int[] arr = new int[N+1];
		for (int n = 1; n < arr.length; n++) {
			arr[n] = sc.nextInt();
		}
		
		Arrays.sort(arr);
		
		int sum = 0;
		for (int i = 1; i < arr.length; i++) {
			for (int j = 1; j <= i; j++) {
				sum += arr[j];
			}
		}
		
		System.out.println(sum);
	}
}
