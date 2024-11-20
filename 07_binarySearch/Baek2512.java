package algo241120;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek2512 {
	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(bfr.readLine());
		int[] budget = new int[N];
		int sum = 0;
		int maxBudget = Integer.MIN_VALUE;

		// 예산 정보 받기
		StringTokenizer stz = new StringTokenizer(bfr.readLine());

		for (int i = 0; i < N; i++) {
			budget[i] = Integer.parseInt(stz.nextToken());
			maxBudget = Math.max(maxBudget, budget[i]);
			sum += budget[i];
		}

		// 총액 정보 받기
		int limit = Integer.parseInt(bfr.readLine());

		// 전부 배정 불가능 시 이분탐색
		if (sum > limit) {

			int start = 0;
			int end = maxBudget;
			int mid;

			maxBudget = Integer.MIN_VALUE;

			// 이분탐색
			while (start <= end) {
				mid = (start + end) >> 1;
				sum = 0;

				// 모든 국가에 대해 상한액으로 계산
				for (int i = 0; i < N; i++) {
					if (budget[i] < mid)
						sum += budget[i];
					else
						sum += mid;
				}

				// 분배 가능하면
				if (sum <= limit) {
					maxBudget = Math.max(maxBudget, mid);
					start = mid + 1;
				} else {
					end = mid - 1;
				}
			}

		}
		
		System.out.println(maxBudget);

	}
}
