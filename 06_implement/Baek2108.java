package algo240801;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Baek2108 {
	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		// 수열 길이
		int N = Integer.parseInt(bfr.readLine());
		int[] arr = new int[N];
		int[] count = new int[8002];

		int sum = 0;
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;

		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(bfr.readLine());
			// 산술평균용 합
			sum += num;

			// 최대최소 저장
			max = max > num ? max : num;
			min = min < num ? min : num;

			// 등장수 저장
			count[num + 4000]++;
		}

		// 산술평균 구하기
		int avg = Math.round((float) sum / (float) N);

		// 중앙값, 최빈값 구하기
		int skip = 0;
		int most = 5000;
		int second = 5000;
		int mostCnt = 0;
		int middle = 5000;
		boolean saved = false;

		for (int i = 8001; i >= 0; i--) {
			// 처음으로 중앙값을 넘은 상태일 때
			if (skip > N / 2 && !saved) {
				middle = i + 1 - 4000;
				saved = true;
			} else
				skip += count[i];

			// 최빈값과 지난 최빈값 저장
			if (mostCnt <= count[i]) {
				mostCnt = count[i];
				if (mostCnt == count[i])
					second = most;
				most = i;

			}

		}
		// 산술평균
		System.out.println(avg);
		
		// 중앙값
		System.out.println(middle);
		
		// 최빈값
		if (count[most] != count[second])
			System.out.println(most-4000);
		else
			System.out.println(second-4000);
		
		// 범위
		System.out.println(max - min);
	}
}
