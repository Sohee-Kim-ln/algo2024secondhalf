package algo240804;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Baek1743 {
	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		String[] tempS = bfr.readLine().split(" ");

		int N = Integer.parseInt(tempS[0]);
		int M = Integer.parseInt(tempS[1]);
		int K = Integer.parseInt(tempS[2]);

		boolean[][] dropped = new boolean[N][M];
		boolean[][] visited = new boolean[N][M];

		int max = Integer.MIN_VALUE;

		ArrayDeque<int[]> deq = new ArrayDeque<>();

		// 음식물 정보 입력받기
		for (int i = 0; i < K; i++) {
			tempS = bfr.readLine().split(" ");
			int r = Integer.parseInt(tempS[0]);
			int c = Integer.parseInt(tempS[1]);
			dropped[r - 1][c - 1] = true;
		}

		// 모든 필드에 대해 탐색
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// 음식물이 있고 방문하지 않았다면
				if (dropped[i][j] && !visited[i][j]) {
					int cnt = 0;
					int[] ij = { i, j };

					// 현재지점 추가
					deq.addLast(ij);
					visited[i][j] = true;
					cnt++;

					// bfs
					while (!deq.isEmpty()) {
						int[] cur = deq.pollFirst();
						int curr = cur[0];
						int curc = cur[1];

						for (int d = 0; d < 4; d++) {
							int nextr = curr + dr[d];
							int nextc = curc + dc[d];

							// 경계조건
							if (nextr < 0 || nextr >= N || nextc < 0 || nextc >= M)
								continue;

							// 다음지점이 방문하지 않았다면
							if (dropped[nextr][nextc] && !visited[nextr][nextc]) {
								int[] nextij = { nextr, nextc };

								deq.addLast(nextij);
								visited[nextr][nextc] = true;
								cnt++;
							}
						}
					}

					max = max > cnt ? max : cnt;
				}

			}
		}

		System.out.println(max);

	}
}
