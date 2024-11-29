package algo241129;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek1103 {
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static int[][] board, depth;
	static boolean[][] visited;

	static int N, M, max = Integer.MIN_VALUE;
	static boolean isInf = false;

	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz = new StringTokenizer(bfr.readLine());

		N = Integer.parseInt(stz.nextToken());
		M = Integer.parseInt(stz.nextToken());

		board = new int[N][M];
		depth = new int[N][M];
		visited = new boolean[N][M];

		// 보드 정보 받기
		for (int i = 0; i < N; i++) {
			String tempS = bfr.readLine();

			for (int j = 0; j < M; j++) {
				board[i][j] = tempS.charAt(j) - '0';
			}
		}

		visited[0][0] = true;
		moveFrom(0, 0, 1);

		System.out.println(max);
	}

	static void moveFrom(int r, int c, int cnt) {
		for (int dir = 0; dir < 4; dir++) {
			int nr = r + board[r][c] * dr[dir];
			int nc = c + board[r][c] * dc[dir];

			// 경계조건
			if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
				max = Math.max(max, cnt);
				continue;
			}

			// 이전에 방문한 곳 무한루프
			if (visited[nr][nc]) {
				max = -1;
				isInf = true;
				return;
			}

			// 구멍에 빠졌을 때
			if (board[nr][nc] == 'H' - '0') {
				max = Math.max(max, cnt);
				continue;
			}

			// 이미 깊이 탐색한 곳 continue
			if (depth[nr][nc] > cnt)
				continue;

			// 백트래킹
			visited[nr][nc] = true;
			depth[nr][nc] = cnt + 1;
			moveFrom(nr, nc, cnt + 1);
			visited[nr][nc] = false;

			if (isInf)
				return;

		}
	}
}
