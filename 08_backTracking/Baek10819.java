package algo240811;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek10819 {
	static int N = Integer.MAX_VALUE;
	static int[] nums;
	static int[] ans;
	static boolean[] used;
	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(bfr.readLine());
		nums = new int[N];
		ans = new int[N];
		used = new boolean[N];

		// 숫자들 입력받기
		StringTokenizer stz = new StringTokenizer(bfr.readLine(), " ");

		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(stz.nextToken());
		}

		// 백트래킹 시작
		backtracking(0);

		System.out.println(max);

	}

	static void backtracking(int pointer) {
		// 모든 수 다 고려했으면
		if (pointer >= N) {
			int sum = 0;

			// 합 계산
			for (int i = 0; i < N - 1; i++) {
				sum += Math.abs(ans[i + 1] - ans[i]);
			}

			// 최대값 갱신
			if (max < sum)
				max = sum;
			return;
		}

		// 안쓴 숫자 골라서 배열에 넣기
		for (int i = 0; i < N; i++) {
			if (!used[i]) {
				used[i] = true;
				ans[pointer] = nums[i];
				backtracking(pointer + 1);
				used[i] = false;
			}
		}

	}
}
