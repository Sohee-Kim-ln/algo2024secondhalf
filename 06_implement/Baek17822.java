package algo240913;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Baek17822 {
	static int N, M, T, sum, cnt;
	static int[] start;
	static int[][] circle;

	static ArrayDeque<int[]> adq = new ArrayDeque<>();

	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stz = new StringTokenizer(bfr.readLine(), " ");

		N = Integer.parseInt(stz.nextToken());
		M = Integer.parseInt(stz.nextToken());
		T = Integer.parseInt(stz.nextToken());

		sum = 0;
		cnt = N * M;

		// 0, N+1 안씀
		circle = new int[N + 2][M];
		start = new int[N + 2]; // 회전 후 북쪽의 idx

		// 원판 정보 받기
		for (int i = 1; i <= N; i++) {
			stz = new StringTokenizer(bfr.readLine(), " ");

			for (int j = 0; j < M; j++) {
				circle[i][j] = Integer.parseInt(stz.nextToken());
				sum += circle[i][j];
			}
		}

		int x, d, k;

		// 회전 T번 실행
		for (int t = 0; t < T; t++) {
			// 원판에 수 없으면 종료
			if (cnt == 0)
				break;

			stz = new StringTokenizer(bfr.readLine(), " ");
			x = Integer.parseInt(stz.nextToken());
			d = Integer.parseInt(stz.nextToken());
			k = Integer.parseInt(stz.nextToken());

			// 회전
			rotate(x, d, k);

			// 지우기 실행 후, 결과에 따라 바꾸기 실행
			if (!erase(t, x))
				change();
		}

		System.out.println(sum);
	}

	static void rotate(int x, int d, int k) {
		for (int mult = 1; x * mult <= N; mult++) {
			// 현재 원판 번호
			int cur = x * mult;

			// 회전 방향에 따라 최종 회전상태 계산
			int added = d == 0 ? M - k : k;
			start[cur] = (start[cur] + added) % M;
		}
	}

	// 지우기. 지운거 없으면 false 반환
	static boolean erase(int t, int x) {
		boolean changed = false;

		// 모든 칸에 대해 오른쪽, 위 확인
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < M; j++) {
				int now = (start[i] + j) % M;

				// 지워진 수 continue
				if (circle[i][now] == 0)
					continue;

				int out = (start[i + 1] + j) % M;
				int next = (now + 1) % M;

				// 오른쪽 수와 같으면 지울 후보
				if (circle[i][now] == circle[i][next]) {
					adq.add(new int[] { i, now });
					adq.add(new int[] { i, next });
				}

				// 위쪽 수와 같으면 지울 후보
				if (circle[i][now] == circle[i + 1][out]) {
					adq.add(new int[] { i, now });
					adq.add(new int[] { i + 1, out });
				}
			}
		}

		// 지울 수가 없으면 flag 전환
		if (!adq.isEmpty())
			changed = true;

		// 지우기 시작
		while (!adq.isEmpty()) {
			int[] temp = adq.poll();

			// 이미 지워진 수 continue
			if (circle[temp[0]][temp[1]] == 0)
				continue;

			sum -= circle[temp[0]][temp[1]];
			cnt--;
			circle[temp[0]][temp[1]] = 0;
		}

		return changed;
	}

	// 평균과 비교 후 +1, -1씩 바꾸기
	static void change() {

		double avg = ((double) sum) / ((double) cnt);

		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < M; j++) {
				// 지워진 수 continue;
				if (circle[i][j] == 0)
					continue;

				// 평균보다 작으면 ++, 크면 --
				if (circle[i][j] < avg) {
					circle[i][j]++;
					sum++;
				} else if (circle[i][j] > avg) {
					circle[i][j]--;
					sum--;
				}
			}
		}
	}
}
