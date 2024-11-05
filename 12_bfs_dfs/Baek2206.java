package algo241105;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek2206 {
	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };

	public static void main(String[] args) throws Exception {

		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stz = new StringTokenizer(bfr.readLine());

		int N = Integer.parseInt(stz.nextToken());
		int M = Integer.parseInt(stz.nextToken());
		int max = Integer.MAX_VALUE;

		boolean[][] isWall = new boolean[N][M];

		// n,m에서 벽 안부순 루트, 벽 부순 루트
		int[][][] min = new int[N][M][2];
		boolean[][][] visited = new boolean[N][M][2];

		ArrayDeque<int[]> adq = new ArrayDeque<>();

		// 맵 정보 받기
		for (int i = 0; i < N; i++) {
			String tempS = bfr.readLine();

			for (int j = 0; j < M; j++) {
				isWall[i][j] = tempS.charAt(j) == '1';
			}
		}

		// 초기값 넣기 {r, c, 0안부숨 1부숨}
		adq.add(new int[] { 0, 0, 0 });
		visited[0][0][0] = true;

		min[0][0][0] = 1;
		min[0][0][1] = 0;
		boolean success = false;

		while (!adq.isEmpty() && !success) {
			int[] now = adq.pop();
			int r = now[0];
			int c = now[1];
			int broken = now[2];

			for (int dir = 0; dir < 4; dir++) {
				int nr = r + dr[dir];
				int nc = c + dc[dir];

				// 경계조건
				if (nr < 0 || nr >= N || nc < 0 || nc >= M)
					continue;

				// 벽아니고
				if (!isWall[nr][nc]) {
					// 루트별로 방문하지 않았다면
					if (min[nr][nc][broken] == 0) {
						min[nr][nc][broken] = min[r][c][broken] + 1;
						
						if (nr == N - 1 && nc == M - 1) {
							success = true;
							break;
						}
						
						adq.add(new int[] { nr, nc, broken });
					}
				}

				// 벽이라면
				else {
					// 현재 안 부순 루트고, 부서진채로 방문된 적 없으면
					if (broken == 0 && min[nr][nc][1] == 0) {
						min[nr][nc][1] = min[r][c][0] + 1;
						
						if (nr == N - 1 && nc == M - 1) {
							success = true;
							break;
						}
						
						adq.add(new int[] { nr, nc, 1 });
					}
				}
			}
		}

		// 결과 도출
		int res = min[N - 1][M - 1][0];

		// 안부수고 방문 못했으면
		if (res == 0)
			res = min[N - 1][M - 1][1];

		// 부숴도 방문하지 못했다면 -1
		if (res == 0)
			res = -1;

		System.out.println(res);
//
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++) {
//				System.out.print(Arrays.toString(min[i][j]) + " ");
//			}
//			System.out.println();
//		}

	}
}
