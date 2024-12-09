package algo241209;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek1940 {
	public static void main(String[] args) throws Exception {

		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bfr.readLine());
		int M = Integer.parseInt(bfr.readLine());
		int[] arr = new int[N];
		int cnt = 0;

		// 재료 정보 받기
		StringTokenizer stz = new StringTokenizer(bfr.readLine());

		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(stz.nextToken());

		// 오름차순 정렬
		Arrays.sort(arr);

		int left = 0;
		int right = N - 1;

		while (left < right) {
			// 합이 M이라 만들 수 있으면
			if (arr[left] + arr[right] == M) {
				cnt++;
				left++;
				right--;
			} else if (arr[left] + arr[right] < M)
				left++;
			else
				right--;

		}
		
		// 출력 
		System.out.println(cnt);
	}
}
