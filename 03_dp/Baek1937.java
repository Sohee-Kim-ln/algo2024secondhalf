package algo240904;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek1937 {
	static int max = Integer.MIN_VALUE;
	static int N;
	static int[][] forest, move;

	static int[] dr = { 0, 0, 1, -1 };
	static int[] dc = { 1, -1, 0, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(bfr.readLine());

		forest = new int[N][N];
		move = new int[N][N];

		// 대나무숲 정보 받기
		StringTokenizer stz;
		for (int i = 0; i < N; i++) {
			stz = new StringTokenizer(bfr.readLine(), " ");

			for (int j = 0; j < N; j++) {
				forest[i][j] = Integer.parseInt(stz.nextToken());
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (move[i][j] == 0)
					dp(i, j);

				max = Math.max(max, move[i][j]);
			}
		}
		
		System.out.println(max);

	}

	static void dp(int r, int c) {
		int res = 0;

		// 4방탐색
		for (int dir = 0; dir < 4; dir++) {
			int nextr = r + dr[dir];
			int nextc = c + dc[dir];

			// 경계조건
			if (nextr < 0 || nextr >= N || nextc < 0 || nextc >= N)
				continue;

			// 현재 칸으로 올 수 없는 대나무면 continue
			if (forest[r][c] <= forest[nextr][nextc])
				continue;

			if (move[nextr][nextc] == 0)
				dp(nextr, nextc);

			res = Math.max(res, move[nextr][nextc]);
		}

		move[r][c] = res + 1;
	}
}
