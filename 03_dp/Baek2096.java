package algo241022;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Baek2096 {
	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(bfr.readLine());
		int[][] board = new int[N][5];
		int[][][] dp = new int[N][5][2];
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;

		// 숫자 정보 받기
		for (int i = 0; i < N; i++) {
			String tempS = bfr.readLine();

			for (int j = 0; j < 3; j++) {
				board[i][j + 1] = tempS.charAt(j << 1) - '0';
			}
		}

		// 빈칸에서 최소용 dp를 int최대로 설정
		for (int i = 0; i < N; i++) {
			dp[i][0][1] = Integer.MAX_VALUE;
			dp[i][4][1] = Integer.MAX_VALUE;
		}

		// 최대 초기값 설정
		dp[0][1][0] = board[0][1];
		dp[0][2][0] = board[0][2];
		dp[0][3][0] = board[0][3];

		// 최소 초기값 설정
		dp[0][1][1] = board[0][1];
		dp[0][2][1] = board[0][2];
		dp[0][3][1] = board[0][3];

		// 점화식
		// dp[i][j] = dp[i-1][1 2 3] 중 큰거 + board[i][j];
		for (int i = 1; i < N; i++) {
			for (int j = 1; j <= 3; j++) {
				dp[i][j][0] = board[i][j]
						+ Math.max(dp[i - 1][j - 1][0], Math.max(dp[i - 1][j][0], dp[i - 1][j + 1][0]));
				
				dp[i][j][1] = board[i][j]
						+ Math.min(dp[i - 1][j - 1][1], Math.min(dp[i - 1][j][1], dp[i - 1][j + 1][1]));

			}
		}

		// 최대, 최소 계산
		for (int j = 1; j <= 3; j++) {
			max = Math.max(max, dp[N - 1][j][0]);
			min = Math.min(min, dp[N - 1][j][1]);
		}

		// 출력
		StringBuilder sb = new StringBuilder();
		sb.append(max).append(" ").append(min);
		System.out.println(sb);
	}

}
