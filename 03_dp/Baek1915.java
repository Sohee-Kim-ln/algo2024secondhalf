package algo241014;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek1915 {
	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stz = new StringTokenizer(bfr.readLine(), " ");
		int N = Integer.parseInt(stz.nextToken());
		int M = Integer.parseInt(stz.nextToken());

		// 0안씀
		boolean[][] board = new boolean[N + 1][M + 1];

		// 최대 사각형 길이, 왼쪽으로 1 연속 수, 위쪽으로 1 연속 수
		int[][] dp = new int[N + 1][M + 1];

		int max = Integer.MIN_VALUE;

		// 점화식 dp[i][j] = 현재칸을 꼭지점으로 하는 가장 큰 사각형의 변 길이
		// dp[i][j] = dp[i-1][j-1] dp[i][j-1] dp[i-1][j] 중 최소 +1;

		// 2차원 배열 정보 받으면서 dp 계산
		for (int i = 1; i <= N; i++) {
			String temp = bfr.readLine();

			for (int j = 1; j <= M; j++) {
				// 1이 있는 칸이면
				if (temp.charAt(j - 1) == '1') {
					board[i][j] = true;
					dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j])) + 1;
					max = Math.max(max, dp[i][j]);
				}
			}
		}

		System.out.println(max * max);

	}
}
