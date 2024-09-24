package algo240924;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

// 최적화 덜됨
public class Baek1339_2 {
	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(bfr.readLine());
		int max = 9;
		long sum = 0;

		// 0-25까지 A-Z 매치된 수 저장
		int[] match = new int[26];
		int[] times = new int[26];

		// 등장한 자릿수 내림차순
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);

		// 단어 받기
		for (int i = 0; i < N; i++) {
			char[] word = bfr.readLine().toCharArray();
			int length = word.length;

			for (int j = 0; j < length; j++) {
				int temp = 1;

				for (int k = 0; k < length - j - 1; k++)
					temp *= 10;

				times[word[j] - 65] += temp;
			}
		}

		for (int i = 0; i < 26; i++) {
			pq.add(new int[] { i, times[i] });
		}

		// 높은 자리수부터 숫자 매칭하며 더하기
		while (!pq.isEmpty()) {
			int[] now = pq.poll();
			int code = (int) now[0];
			long mult = now[1];

			// 지정되지 않았다면 지정후 남은 숫자--
			if (match[code] == 0)
				match[code] = max--;

			// 자리수 맞춰서 sum에 더하기
			sum += match[code] * mult;

		}

		System.out.println(sum);

	}
}
