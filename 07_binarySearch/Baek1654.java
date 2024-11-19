package algo241119;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek1654 {
	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz = new StringTokenizer(bfr.readLine());

		int K = Integer.parseInt(stz.nextToken());
		int N = Integer.parseInt(stz.nextToken());

		int[] lan = new int[K];

		long start = 1;
		long end = Long.MIN_VALUE;
		long mid = 0;

		// 랜선 길이 정보 받기
		for (int i = 0; i < K; i++) {
			lan[i] = Integer.parseInt(bfr.readLine());
			end = Math.max(end, lan[i]);
		}

		long cnt = 0;

		// 이분탐색
		while (start <= end) {
			mid = (start + end) / 2;

			cnt = 0;
			for (int i = 0; i < K; i++)
				cnt += lan[i] / mid;

			if (cnt < N)
				end = mid - 1;
			else
				start = mid + 1;

		}

		System.out.println(end);

	}
}
