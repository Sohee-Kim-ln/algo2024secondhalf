package algo240806;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Baek14716 {
	// 8방탐색용 델타변수
	static int[] dr = { 0, 0, 1, -1, 1, 1, -1, -1 };
	static int[] dc = { 1, -1, 0, 0, 1, -1, 1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		// 현수막 크기
		String[] tempS = bfr.readLine().split(" ");
		int N = Integer.parseInt(tempS[0]);
		int M = Integer.parseInt(tempS[1]);

		boolean[][] board = new boolean[N][M];
		boolean[][] visited = new boolean[N][M];

		// 글자 갯수
		int cnt = 0;

		// 현수막 입력받기
		for (int i = 0; i < N; i++) {
			String tempLine = bfr.readLine().replace(" ", "");
			char[] arr = tempLine.toCharArray();
			for (int j = 0; j < M; j++) {
				if (arr[j] == '1')
					board[i][j] = true;
			}
		}

		// dfs 스택 대용으로 쓸 ad
		ArrayDeque<int[]> ad = new ArrayDeque<>();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// 방문하지 않은 1이면 8방탐색 시작
				if (board[i][j] && !visited[i][j]) {
					// 시작점 스택에 넣기
					cnt++;
					visited[i][j] = true;
					int[] temp = { i, j };
					ad.add(temp);

					// 연결된 픽셀에 true 표시 시작
					while (!ad.isEmpty()) {
						int[] cur = ad.pop();

						// 8방탐색 시작
						for (int dir = 0; dir < 8; dir++) {
							int nextr = cur[0] + dr[dir];
							int nextc = cur[1] + dc[dir];

							// 경계조건
							if (nextr < 0 || nextr >= N || nextc < 0 || nextc >= M)
								continue;

							// 방문하지 않은 픽셀 발견하면
							if (!visited[nextr][nextc]) {
								// 중복탐색 방지를 위해 0 포함 방문처리
								visited[nextr][nextc] = true;

								// 표시된 픽셀이라면 다음 탐색처리
								if (board[nextr][nextc]) {

									int[] next = { nextr, nextc };
									ad.add(next);
								}
							}
						}
					}
				}

			}
		}
		System.out.println(cnt);

	}
}
