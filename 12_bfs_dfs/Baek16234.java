package algo241125;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek16234 {
	// 4방 탐색용 델타변수
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz = new StringTokenizer(bfr.readLine());

		int N = Integer.parseInt(stz.nextToken());
		int L = Integer.parseInt(stz.nextToken());
		int R = Integer.parseInt(stz.nextToken());
		int[][] nation = new int[N][N];

		// 인구 정보 받기
		for (int i = 0; i < N; i++) {
			stz = new StringTokenizer(bfr.readLine());

			for (int j = 0; j < N; j++)
				nation[i][j] = Integer.parseInt(stz.nextToken());
		}

		int day = 0;
		boolean isChanged = true;

		boolean[][] locked = new boolean[N][N];

		// 변화 없을 때까지 반복
		while (isChanged) {
			isChanged = false;

			Queue<int[]> quu = new LinkedList<>();
			Queue<int[]> quuChange = new LinkedList<>();

			int[][] groupOf = new int[N][N];

			int[] populAt = new int[N * N + 1];
			int groupNum = 1;

			// 모든 칸에 대해 연합 탐색
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					// 이전 날짜의 그룹 내부면 continue;
					if (locked[i][j] == true)
						continue;
					
					locked[i][j]=true;

					boolean[][] visited = new boolean[N][N];
					
					// 이미 그룹지어진 칸 탐색 안함
					if (groupOf[i][j] != 0)
						continue;

					quu.add(new int[] { i, j });
					groupOf[i][j] = groupNum;

					int cnt = 1;
					int sum = nation[i][j];

					// 연합 탐색 시작
					while (!quu.isEmpty()) {
						int[] now = quu.poll();
						visited[i][j] = true;

						// 4방 탐색
						for (int dir = 0; dir < 4; dir++) {
							int nr = now[0] + dr[dir];
							int nc = now[1] + dc[dir];

							// 경계조건
							if (nr < 0 || nr >= N || nc < 0 || nc >= N)
								continue;

							// 이미 다른 연합이면 종료
							if (groupOf[nr][nc] != 0)
								continue;

							// 이전 방문여부 확인
							if (visited[nr][nc])
								continue;

							// 차이 계산
							int diff = Math.abs(nation[now[0]][now[1]] - nation[nr][nc]);

							// 같은 연합이라면
							if (L <= diff && diff <= R) {
								cnt++;
								sum += nation[nr][nc];
								quu.add(new int[] { nr, nc });
								quuChange.add(new int[] { nr, nc, groupNum });
								groupOf[nr][nc] = groupNum;
							}
						}
					}

					// 탐색된 연합의 인구 산출 후 저장
					if (cnt > 1) {
						quuChange.add(new int[] { i, j, groupNum });
						populAt[groupNum] = sum / cnt;
					}

					groupNum++;
				}
			}

			// 수정할 것 있으면 트리거 변수 표시
			if (!quuChange.isEmpty()) {
				isChanged = true;
				day++;
			}

			// 연합들 인구 수정
			for (int[] now : quuChange) {
				int r = now[0];
				int c = now[1];
				int group = now[2];

				nation[r][c] = populAt[group];
				locked[r][c] = false;
			}

		}

		// 출력
		System.out.println(day);
	}
}
