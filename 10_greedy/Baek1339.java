package algo240924;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Baek1339 {
	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(bfr.readLine());
		int max = 9;
		int sum = 0;

		// 0-25까지 A-Z 매치된 수 저장
		long[] times = new long[26];

		// 단어 받기
		for (int i = 0; i < N; i++) {
			char[] word = bfr.readLine().toCharArray();
			int length = word.length;

			// 숫자 지정 후 곱할 수 계산
			for (int j = 0; j < length; j++) {
				int temp = 1;

				for (int k = 0; k < length - j - 1; k++)
					temp *= 10;

				times[word[j] - 65] += temp;
			}
		}

		// 오름차순 정렬
		Arrays.sort(times);

		// 곱할 게 큰 수부터 숫자 지정하며 더하기
		for (int i = 25; i >= 0; i--) {
			if (times[i] == 0)
				break;

			sum += times[i] * max--;
		}

		// 출력
		System.out.println(sum);

	}
}
