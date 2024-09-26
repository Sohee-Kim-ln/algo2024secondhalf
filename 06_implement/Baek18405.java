package algo240926;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Baek18405 {
	// 4방 탐색용 델타변수
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stz = new StringTokenizer(bfr.readLine(), " ");

		// 맵크기, 바이러스 종류 수 정보 받기
		int N = Integer.parseInt(stz.nextToken());
		int K = Integer.parseInt(stz.nextToken());

		int[][] board = new int[N][N];

		ArrayDeque<int[]>[] virus = new ArrayDeque[K + 1];
		for (int i = 1; i <= K; i++)
			virus[i] = new ArrayDeque<>();

		// 맵 정보 받기
		for (int i = 0; i < N; i++) {
			stz = new StringTokenizer(bfr.readLine(), " ");
			for (int j = 0; j < N; j++) {
				int num = Integer.parseInt(stz.nextToken());
				board[i][j] = num;
				if (num != 0) {
					virus[num].add(new int[] { i, j });
				}
			}
		}

		// 시간, 좌표 정보 받기
		stz = new StringTokenizer(bfr.readLine(), " ");

		int S = Integer.parseInt(stz.nextToken());
		int X = Integer.parseInt(stz.nextToken()) - 1;
		int Y = Integer.parseInt(stz.nextToken()) - 1;

		// S초 진행
		for (int time = 0; time < S; time++) {
			// 적은 번호 바이러스 부터
			for (int idx = 1; idx <= K; idx++) {
				int size = virus[idx].size();

				// 현재 있는 바이러스 만큼 반복
				for (int i = 0; i < size; i++) {
					int[] now = virus[idx].poll();

					int nextr, nextc;
					// 4방탐색
					for (int dir = 0; dir < 4; dir++) {
						nextr = now[0] + dr[dir];
						nextc = now[1] + dc[dir];

						// 경계조건
						if (nextr < 0 || nextr >= N || nextc < 0 || nextc >= N)
							continue;

						// 비어있는 칸이라면
						if (board[nextr][nextc] == 0) {
							board[nextr][nextc] = idx;
							virus[idx].add(new int[] { nextr, nextc });
						}
					}
				}

			}
		}

		// 출력
		System.out.println(board[X][Y]);

	}
}
