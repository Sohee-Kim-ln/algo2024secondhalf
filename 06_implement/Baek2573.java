package algo240928;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayDeque;

public class Baek2573 {
	// 4방 탐색용 델타변수
	static int[] dr = new int[] { -1, 1, 0, 0 };
	static int[] dc = new int[] { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stz = new StringTokenizer(bfr.readLine(), " ");
		int N = Integer.parseInt(stz.nextToken());
		int M = Integer.parseInt(stz.nextToken());

		int year = 0;
		int[][] sea = new int[N][M];
		int ice = 0;

		ArrayDeque<int[]> coor = new ArrayDeque<>();

		// 빙산 정보 받기
		for (int i = 0; i < N; i++) {
			stz = new StringTokenizer(bfr.readLine(), " ");

			for (int j = 0; j < M; j++) {
				sea[i][j] = Integer.parseInt(stz.nextToken());

				if (sea[i][j] != 0) {
					ice++;
					coor.add(new int[] { i, j });
				}
			}
		}

		int cnt;
		ArrayDeque<int[]> adq = new ArrayDeque<>();
		boolean[][] visited;

		// 빙산이 남아있는 동안 반복
		while (ice != 0) {
			// bfs용 초기화
			cnt = 0;
			adq.clear();
			visited = new boolean[N][M];

			// 시작점
			int[] now = coor.peek();
			visited[now[0]][now[1]] = true;
			adq.add(now);
			cnt++;

			// bfs
			while (!adq.isEmpty()) {
				int[] temp = adq.poll();

				// 4방 탐색
				for (int dir = 0; dir < 4; dir++) {
					int nextr = temp[0] + dr[dir];
					int nextc = temp[1] + dc[dir];

					// 경계조건
					if (nextr < 0 || nextr >= N || nextc < 0 || nextc >= M)
						continue;

					if (visited[nextr][nextc])
						continue;

					if (sea[nextr][nextc] != 0) {
						adq.add(new int[] { nextr, nextc });
						cnt++;
						visited[nextr][nextc] = true;
					}
				}
			}

			// 쪼개졌으면 break;
			if (cnt != ice)
				break;

			// 한덩어리면 year++, 녹이기
			year++;

			int[][] seaNew = new int[N][M];

			// 남은 얼음칸만큼 계산
			int size = coor.size();
			for (int i = 0; i < size; i++) {
				int[] cur = coor.poll();
				int r = cur[0];
				int c = cur[1];
				int zero = 0;

				// 4방에서 0 세기
				for (int dir = 0; dir < 4; dir++) {
					int nextr = cur[0] + dr[dir];
					int nextc = cur[1] + dc[dir];

					// 경계조건
					if (nextr < 0 || nextr >= N || nextc < 0 || nextc >= M)
						continue;
					
					if (sea[nextr][nextc] == 0)
						zero++;
				}
				seaNew[r][c] = Math.max(0, sea[r][c] - zero);

				if (seaNew[r][c] != 0)
					coor.add(cur);
				else
					ice--;
			}

			sea = seaNew;

		}

		// 출력
		if (ice == 0)
			System.out.println(0);
		else
			System.out.println(year);

	}
}
