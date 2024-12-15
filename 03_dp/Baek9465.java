package algo241215;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek9465 {
	public static void main(String[] args) throws Exception {

		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(bfr.readLine());

		for (int tc = 0; tc < T; tc++) {
			int N = Integer.parseInt(bfr.readLine());

			int[][] score = new int[2][N];

			// 각 칸별 {0행 뗐을 때, 1행 뗐을 때, 둘다 안뗐을 때};
			int[][] dp = new int[3][N];

			// 스티커 점수 받기
			for (int i = 0; i < 2; i++) {

				stz = new StringTokenizer(bfr.readLine());

				for (int j = 0; j < N; j++) {
					score[i][j] = Integer.parseInt(stz.nextToken());
				}
			}

			// 초기값
			dp[0][0] = score[0][0];
			dp[1][0] = score[1][0];
			dp[2][0] = 0;

			// 점화식
			// dp 현재칸 땠을 떄 = 이전칸 다른줄 뗀거, 이전칸둘다 안뗀거 중 큰거 + 현재 점수
			// dp 계산
			for (int c = 1; c < N; c++) {
				dp[0][c] = Math.max(dp[1][c - 1], dp[2][c - 1]) + score[0][c];
				dp[1][c] = Math.max(dp[0][c - 1], dp[2][c - 1]) + score[1][c];
				dp[2][c] = Math.max(dp[0][c - 1], Math.max(dp[1][c - 1], dp[2][c - 1]));

			}

			sb.append(Math.max(dp[0][N - 1], dp[1][N - 1])).append('\n');

		}
		
		System.out.print(sb);
	}
}
