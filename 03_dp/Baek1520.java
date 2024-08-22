package algo240822;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek1520 {

	// 4방 탐색
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static int[][] board, route;
	static int M, N;

	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stz = new StringTokenizer(bfr.readLine(), " ");

		M = Integer.parseInt(stz.nextToken());
		N = Integer.parseInt(stz.nextToken());

		board = new int[M][N];
		route = new int[M][N];

		// route 초기화
		for (int i = 0; i < M; i++) {
			Arrays.fill(route[i], -1);
		}

		// 높이 정보 입력받기
		for (int i = 0; i < M; i++) {
			stz = new StringTokenizer(bfr.readLine(), " ");
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(stz.nextToken());
			}
		}

		int ans = dp(0, 0);

		System.out.println(ans);

	}

	static int dp(int r, int c) {
		// 이미 계산된 루트 반환
		if (route[r][c] != -1)
			return route[r][c];

		// 도달지점 시 1
		if (r == M - 1 && c == N - 1)
			return 1;

		int sum = 0;

		// 4방향에서 자신보다 작은 곳에서 오는 루트들의 합 저장
		for (int i = 0; i < 4; i++) {
			int nextr = r + dr[i];
			int nextc = c + dc[i];

			// 경계조건
			if (nextr < 0 || nextr >= M || nextc < 0 || nextc >= N)
				continue;

			if (board[nextr][nextc] < board[r][c])
				sum += dp(nextr, nextc);
		}
		route[r][c] = sum;
		return sum;
	}
}
