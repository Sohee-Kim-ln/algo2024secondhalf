package algo240721;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Baek15989 {
	static int[][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(bfr.readLine());

		for (int tc = 0; tc < T; tc++) {
			int n = Integer.parseInt(bfr.readLine());

			// dp[n][i] n을 쪼갤 때 i가 최대조각인 경우의 수
			// dp[n][3] = dp[n-3][3] + dp[n-3][2] + dp[n-3][1];
			// dp[n][2] = dp[n-2][2] + dp[n-2][1];
			dp = new int[n + 1][4];// [n][0]안씀

			cal(n, 3);
			cal(n, 2);
			cal(n, 1);

			System.out.println(dp[n][3] + dp[n][2] + dp[n][1]);
		}
	}

	static void cal(int input, int max) {
		// 0이하 입력시 종료
		if (input < 1)
			return;

		// 쪼개는 최대 값이 1이면 무조건 1
		if (max == 1) {
			dp[input][max] = 1;
			return;
		}

		// 1,2,3 1개로만 나누어지는 케이스
		if (input == max) {
			dp[input][max] = 1;
			return;
		}

		// 남은 수 자를 때 큰 수부터 자르기
		for (int i = max; i > 0; i--) {
			if (input < max)
				continue;
			// 계산 되지 않았다면 계산
			if (dp[input - max][i] == 0)
				cal(input - max, i);

			dp[input][max] += dp[input - max][i];
		}
	}
}
