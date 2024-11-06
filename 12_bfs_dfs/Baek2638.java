package algo241106;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Baek2638 {
	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };
	static int N, M, cnt, time;
	static boolean[][] cheese, visited;
	static int[][] touched;
	static ArrayDeque<int[]> adq, adqM;

	public static void main(String[] args) throws Exception {

		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stz = new StringTokenizer(bfr.readLine());

		N = Integer.parseInt(stz.nextToken());
		M = Integer.parseInt(stz.nextToken());

		cheese = new boolean[N][M];
		visited = new boolean[N][M];
		touched = new int[N][M];

		cnt = 0;
		time = 0;

		// 치즈 정보 받기
		for (int i = 0; i < N; i++) {
			String tempS = bfr.readLine();

			for (int j = 0; j < M; j++) {
				if (tempS.charAt(2 * j) == '1') {
					cheese[i][j] = true;
					cnt++;
				}
			}
		}

		// {r c}
		adq = new ArrayDeque<>();
		adqM = new ArrayDeque<>();

		// 초기값 설정
		adq.add(new int[] { 0, 0 });
		visited[0][0] = true;

		while (cnt != 0) {

			// 현재 시간의 노출대상 찾기
			while (!adq.isEmpty()) {
				int[] now = adq.pollFirst();
				int r = now[0];
				int c = now[1];

				// 4방 탐색
				for (int dir = 0; dir < 4; dir++) {
					int nr = r + dr[dir];
					int nc = c + dc[dir];

					// 경계조건
					if (nr < 0 || nr >= N || nc < 0 || nc >= M)
						continue;

					// 방문 확인
					if (visited[nr][nc])
						continue;

					// 다음칸이 빈칸이면 앞에 추가
					if (!cheese[nr][nc]) {
						adq.addFirst(new int[] { nr, nc });
						visited[nr][nc] = true;
					}
					// 다음칸이 치즈면 뒤에 접한 횟수 표시
					else {
						touched[nr][nc]++;

						// 노출횟수 2이상이면 녹을 대상에 추가
						if (touched[nr][nc] >= 2) {
							adqM.addLast(new int[] { nr, nc });
							visited[nr][nc] = true;
						}
					}
				}
			}

			// 녹는 칸만큼 --
			cnt -= adqM.size();

			// 녹은 칸을 탐색대상으로 변경
			adq = adqM;
			adqM = new ArrayDeque<>();

			time++;

		}

		System.out.println(time);
	}
}
