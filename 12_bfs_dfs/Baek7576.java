package algo240927;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Baek7576 {
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stz = new StringTokenizer(bfr.readLine(), " ");
		int M = Integer.parseInt(stz.nextToken());
		int N = Integer.parseInt(stz.nextToken());

		int[][] box = new int[N][M];
		boolean[][] visited = new boolean[N][M];
		int day = 0;
		int cnt = 0;

		ArrayDeque<int[]> adq = new ArrayDeque<>();

		// 토마토 정보 받기
		for (int i = 0; i < N; i++) {
			stz = new StringTokenizer(bfr.readLine(), " ");

			for (int j = 0; j < M; j++) {
				box[i][j] = Integer.parseInt(stz.nextToken());

				// 안익은 토마토
				if (box[i][j] == 0)
					cnt++;

				// 익은 토마토
				if (box[i][j] == 1) {
					adq.add(new int[] { i, j });
					visited[i][j] = true;
				}
			}
		}

		boolean changed = false;
		int nextr, nextc;

		// 익힐 수 있을때까지 반복
		while (cnt != 0) {
			day++;
			changed = false;
			int size = adq.size();

			// 하루치 토마토 고려
			for (int i = 0; i < size; i++) {
				int[] now = adq.poll();

				// 4방 탐색
				for (int dir = 0; dir < 4; dir++) {
					nextr = now[0] + dr[dir];
					nextc = now[1] + dc[dir];

					// 경계조건
					if (nextr < 0 || nextr >= N || nextc < 0 || nextc >= M)
						continue;

					if (visited[nextr][nextc])
						continue;

					// 익지 않은 토마토라면
					if (box[nextr][nextc] == 0) {
						adq.add(new int[] { nextr, nextc });
						visited[nextr][nextc] = true;
						cnt--;
						changed = true;
					}

				}

			}

			if (!changed)
				break;
		}

		if (cnt != 0)
			System.out.println(-1);
		else
			System.out.println(day);
	}
}
