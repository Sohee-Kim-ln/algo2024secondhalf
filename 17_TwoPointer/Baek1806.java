package algo241210;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek1806 {
	public static void main(String[] args) throws Exception {

		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stz = new StringTokenizer(bfr.readLine());

		int N = Integer.parseInt(stz.nextToken());
		int S = Integer.parseInt(stz.nextToken());
		int[] arr = new int[N];

		// 수열 정보 받기
		stz = new StringTokenizer(bfr.readLine());

		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(stz.nextToken());

		// 투포인터
		int sum = arr[0];
		int start = 0;
		int end = 0;
		int min = arr[0] >= S ? 1 : Integer.MAX_VALUE;

		while (end < N && start < N) {
			int len = end - start + 1;

			// 최소길이보다 크면 고려하지 않음
			if (len > min) {
				sum -= arr[start];
				start++;
				continue;
			}

			// 포인터 역전되면 끝점 재설정
			if (start > end) {
				end = start;
				len = 1;
			}

			// S보다 적으면 끝점 이동
			if (sum < S) {
				end++;

				if (end == N)
					break;

				sum += arr[end];
			}
			// S이상이면 갱신 후 시작점 이동
			else {
				min = len;
				sum -= arr[start];
				start++;
			}
		}
		
		// 출력
		System.out.println(min == Integer.MAX_VALUE ? 0 : min);
	}
}
