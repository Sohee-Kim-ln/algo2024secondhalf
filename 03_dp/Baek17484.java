package algo240724;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Baek17484 {
	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		String[] tempS = bfr.readLine().split(" ");
		int N = Integer.parseInt(tempS[0]);
		int M = Integer.parseInt(tempS[1]);

		int[][] map = new int[N + 1][M + 2];

		// 공간정보 입력받기
		for (int i = 1; i <= N; i++) {
			tempS = bfr.readLine().split(" ");
			for (int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(tempS[j - 1]);
			}
		}

		// 남서 0, 남 1, 남동 2
		// dp[i][j][0] = i,j에 올 때 0번 방향으로 진입한 비용
		// dp[i][j][0] = MIN(dp[i-1][j-1][2], dp[i-1][j][1])+map[i][j];
		// dp[i][j][1] = MIN(dp[i-1][j-1][2], dp[i-1][j+1][0])+map[i][j];
		// dp[i][j][2] = MIN(dp[i-1][j][1], dp[i-1][j+1][0])+map[i][j];

		int[][][] dp = new int[N + 1][M + 2][3];

		// 한줄 씩 내려가면서
		for (int i = 1; i <= N; i++) {
			// 모든 칸에 대해
			for (int j = 1; j <= M; j++) {
				// 맨 끝칸이 아니라면
				if (j != M)
					// 남서방향으로 들어온 값 계산
					dp[i][j][0] = Math.min(dp[i - 1][j + 1][2], dp[i - 1][j + 1][1]) + map[i][j];
				// 맨 끝칸이라면
				else
					// 남서방향으로 들어오기 불가능. 최대값 입력
					dp[i][j][0] = Integer.MAX_VALUE;

				// 남쪽방향으로 들어온 값 계산
				dp[i][j][1] = Math.min(dp[i - 1][j][2], dp[i - 1][j][0]) + map[i][j];

				// 맨 첫칸이 아니라면
				if (j != 1)
					// 남동방향으로 들어온 값 계산
					dp[i][j][2] = Math.min(dp[i - 1][j - 1][1], dp[i - 1][j - 1][0]) + map[i][j];
				// 맨 첫칸이라면
				else
					// 남동방향으로 들어오기 불가능. 최대값 입력
					dp[i][j][2] = Integer.MAX_VALUE;
			}
		}

		// 정답 출력용 변수
		int answer = Integer.MAX_VALUE;

		// dp 계산된 마지막 줄에서 가장 작은 수 찾기
		for (int j = 1; j <= M; j++) {
			for (int idx = 0; idx < 3; idx++)
				answer = Math.min(answer, dp[N][j][idx]);
		}

		System.out.println(answer);

	}
}
