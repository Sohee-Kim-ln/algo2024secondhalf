package algo240821;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek17135 {
	static int N, M, D;
	static boolean[][] board;
	static boolean[] hasArcher;
	static int[] archerc = new int[3];
	static int max = Integer.MIN_VALUE;

	// 좌,상,우
	static int[] dr = { 0, -1, 0 };
	static int[] dc = { -1, 0, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stz = new StringTokenizer(bfr.readLine(), " ");

		N = Integer.parseInt(stz.nextToken());
		M = Integer.parseInt(stz.nextToken());
		D = Integer.parseInt(stz.nextToken());

		board = new boolean[N][M];
		hasArcher = new boolean[M];

		// 게임 적 정보 입력받기
		for (int i = 0; i < N; i++) {
			stz = new StringTokenizer(bfr.readLine(), " ");
			for (int j = 0; j < M; j++) {
				if (stz.nextToken().equals("1"))
					board[i][j] = true;
			}
		}

		// 궁수자리 3개 선택해서 전투 진행
		comb(0, 0);

		System.out.println(max);
	}

	static void comb(int idx, int cnt) {

		// 전부 다 골랐으면 전투 시작
		if (cnt == 3) {
			fight();
			return;
		}

		// 경계조건
		if (idx >= M)
			return;

		// choosed[idx]=false; 일 때
		comb(idx + 1, cnt);

		hasArcher[idx] = true;
		archerc[cnt] = idx;
		comb(idx + 1, cnt + 1);

	}

	// 궁수 배치 후 전투 진행
	static void fight() {
		int kill = 0;
		boolean[][] isKilled = new boolean[N][M];

		// 한줄씩 궁수들이 앞으로 가면서 적 처리
		for (int i = N; i > 0; i--) {
			Queue<int[]> quu;
			Queue<int[]> target = new LinkedList<>();

			// 선택된 3명의 궁수에 대해 각각의 목표 찾기. BFS
			for (int j = 0; j < 3; j++) {
				quu = new LinkedList<>();

				boolean[][] visited = new boolean[N][M];

				quu.add(new int[] { i - 1, archerc[j], 1 });
				visited[i - 1][archerc[j]] = true;

				while (!quu.isEmpty()) {
					int[] pos = quu.poll();
					int r = pos[0];
					int c = pos[1];

					if (board[r][c] && !isKilled[r][c]) {
						target.add(new int[] { r, c });
						break;
					}

					// 좌 상 우 탐색
					for (int k = 0; k < 3; k++) {
						int nextr = r + dr[k];
						int nextc = c + dc[k];
						int nextdist = pos[2] + 1;

						// 거리조건
						if (nextdist > D)
							continue;

						// 경계조건
						if (nextr < 0 || nextr >= N || nextc < 0 || nextc >= M)
							continue;

						// 기존 방문여부
						if (!visited[nextr][nextc]) {
							visited[nextr][nextc] = true;
							quu.add(new int[] { nextr, nextc, nextdist });
						}

					}

				}
			}

			// 궁수들의 이번 목표 제거
			while (!target.isEmpty()) {
				int[] temp = target.poll();

				if (!isKilled[temp[0]][temp[1]]) {
					kill++;
					isKilled[temp[0]][temp[1]] = true;
				}
			}

		}

		// 적처리 끝난 후 제거 최대치 갱신
		max = Math.max(max, kill);

	}
}
