package algo241101;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Baek2661 {
	static int N;
	static int[] arr;
	static boolean isDone = false;

	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(bfr.readLine());

		arr = new int[N];

		select(0);

		// 출력
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < N; i++)
			sb.append(arr[i]);

		System.out.println(sb);
	}

	static void select(int idx) {
		// 다 골랐으면 종료
		if (idx == N) {
			isDone = true;
			return;
		}

		// 123 순서대로 넣어보면서 검사
		for (int i = 1; i <= 3; i++) {
			// 이전 숫자와 같으면 패스
			if (idx > 0 && arr[idx - 1] == i)
				continue;

			arr[idx] = i;

			boolean isGood = true;

			// 좋은 수열 검사. j는 수열길이
			for (int j = 1; j <= (idx + 1) / 2; j++) {
				int rear = idx;
				int front = idx - j;

				boolean same = true;

				// 수열 2개 비교
				for (int k = rear; k > front; k--) {
					if (arr[k] != arr[k - j])
						same = false;
				}

				if (same) {
					isGood = false;
					break;
				}
			}

			// 좋은 수열이면 다음 인덱스 선택
			if (isGood)
				select(idx + 1);

			// 최소인 좋은수 찾았다면 탐색 종료
			if (isDone)
				return;
		}

	}
}
