package algo240725;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Baek2169 {
	// 델타변수 하,좌,우
	static int[] dr = { 1, 0, 0 };
	static int[] dc = { 0, -1, 1 };

	public static void main(String[] args) throws Exception {

		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		String[] tempS = bfr.readLine().split(" ");

		int N = Integer.parseInt(tempS[0]);
		int M = Integer.parseInt(tempS[1]);

		// 할당
		int[][] map = new int[N + 1][M + 2];
		int[][][] dp = new int[N + 1][M + 2][3];
		int[][] answer = new int[N + 1][M + 2];

		// answer 최소값으로 초기화
		for (int i = 0; i < N + 1; i++) {
			Arrays.fill(answer[i], Integer.MIN_VALUE);
		}

		// dp 최소값으로 초기화
		for (int i = 0; i < N + 1; i++) {
			for (int j = 0; j < M + 2; j++) {
				Arrays.fill(dp[i][j], Integer.MIN_VALUE);
			}
		}

		// 지형정보 입력받기
		for (int i = 1; i <= N; i++) {
			tempS = bfr.readLine().split(" ");
			for (int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(tempS[j - 1]);
			}
		}

		// 0 좌에서 오는 값, 1 상에서, 2 우에서
		// dp[i][j][0] = dp[i][j-1][0],dp[i][j-1][1] 중 큰거 + 현재값
		// dp[i][j][1] = dp[i-1][j]에 들어오는 [0][1][2]세 값 중 제일 큰 거 + 현재값;
		// dp[i][j][2] = dp[i][j+1][2],dp[i][j+1][1] 중 큰거 + 현재값

		// 초기값 입력. i==1일 때
		for (int j = 1; j <= M; j++) {
			// 왼쪽칸이 경계 밖의 값이라면
			if (j == 1)
				dp[1][j][0] = map[1][j];
			else
				dp[1][j][0] = map[1][j] + answer[1][j - 1];

			answer[1][j] = Math.max(dp[1][j][0], dp[1][j][1]);

		}

		// dp 계산
		for (int i = 2; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				// 왼쪽칸이 경계 밖의 값이 아니라면
				if (j != 1) {
					dp[i][j][0] = map[i][j] + Math.max(dp[i][j - 1][0], dp[i][j - 1][1]);
				}
				// 위쪽칸이 경계 밖의 값이 아니라면
				if (i != 1)
					dp[i][j][1] = map[i][j] + Math.max(Math.max(dp[i - 1][j][0], dp[i - 1][j][1]), dp[i - 1][j][2]);

			}

			for (int j = M; j >= 1; j--) {
				// 오른칸이 경계 밖의 값이 아니라면
				if (j != M)
					dp[i][j][2] = map[i][j] + Math.max(dp[i][j + 1][1], dp[i][j + 1][2]);

				answer[i][j] = Math.max(dp[i][j][0], Math.max(dp[i][j][1], dp[i][j][2]));
			}
		}

		System.out.println(answer[N][M]);

	}
}
