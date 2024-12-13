package algo241213;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek7569 {

	// 3차원 6방 탐색용 델타변수
	static int[] dr = { 1, -1, 0, 0, 0, 0 };
	static int[] dc = { 0, 0, 1, -1, 0, 0 };
	static int[] dh = { 0, 0, 0, 0, 1, -1 };

	public static void main(String[] args) throws Exception {

		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stz = new StringTokenizer(bfr.readLine());
		int M = Integer.parseInt(stz.nextToken());
		int N = Integer.parseInt(stz.nextToken());
		int H = Integer.parseInt(stz.nextToken());

		int[][][] tomato = new int[H][N][M];
		boolean[][][] visited = new boolean[H][N][M];
		int cnt = 0;
		int finished = 0;

		Queue<int[]> quu = new LinkedList<>();

		// 토마토 상자 정보 받기
		for (int h = 0; h < H; h++) {
			for (int i = 0; i < N; i++) {
				stz = new StringTokenizer(bfr.readLine());
				for (int j = 0; j < M; j++) {
					tomato[h][i][j] = Integer.parseInt(stz.nextToken());

					// 토마토가 있는 칸 카운트
					if (tomato[h][i][j] != -1)
						cnt++;

					// 익은 칸 카운트
					if (tomato[h][i][j] == 1) {
						finished++;
						quu.add(new int[] { h, i, j });
						visited[h][i][j] = true;
					}
				}
			}
		}

		// 초기값
		int day = 0;
		boolean isChanged = true;

		// 익지 않은 토마토가 있는 동안 반복
		while (cnt != finished) {

			int length = quu.size();
			isChanged = false;

			// 현재 담긴 토마토들 만큼 익힘 반복
			for (int i = 0; i < length; i++) {
				int[] now = quu.poll();

				// 6방 탐색
				for (int dir = 0; dir < 6; dir++) {
					int nh = now[0] + dh[dir];
					int nr = now[1] + dr[dir];
					int nc = now[2] + dc[dir];

					// 경계조건
					if (nh < 0 || nh >= H || nr < 0 || nr >= N || nc < 0 || nc >= M)
						continue;

					// 방문 확인
					if (visited[nh][nr][nc])
						continue;

					// 안익은 토마토 확인
					if (tomato[nh][nr][nc] != 0)
						continue;

					visited[nh][nr][nc] = true;
					quu.add(new int[] { nh, nr, nc });

					finished++;
					isChanged = true;
				}
			}
			
			// 익혀진게 있으면 day++ 후 다음날
			if (isChanged)
				day++;
			// 익혀진거 없으면
			else
				break;
		}

		// 출력
		System.out.println(cnt == finished ? day : -1);

	}
}