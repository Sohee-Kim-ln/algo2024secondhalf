package algo241121;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek17406_3 {
	static int N, M, K;
	static int[][] arrOrigin, order;
	static int[] sequence;
	static int masked = 0;
	static int min = Integer.MAX_VALUE;
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };
	static int[] mr = { -1, -1, 1, 1 };
	static int[] mc = { -1, 1, 1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz = new StringTokenizer(bfr.readLine());

		N = Integer.parseInt(stz.nextToken());
		M = Integer.parseInt(stz.nextToken());
		K = Integer.parseInt(stz.nextToken());
		arrOrigin = new int[N][M];
		order = new int[K][3];
		sequence = new int[K];

		// 배열 정보 받기
		for (int i = 0; i < N; i++) {
			stz = new StringTokenizer(bfr.readLine());

			for (int j = 0; j < M; j++)
				arrOrigin[i][j] = Integer.parseInt(stz.nextToken());

		}

		// 회전 정보 받기
		for (int i = 0; i < K; i++) {
			stz = new StringTokenizer(bfr.readLine());

			order[i][0] = Integer.parseInt(stz.nextToken()) - 1;
			order[i][1] = Integer.parseInt(stz.nextToken()) - 1;
			order[i][2] = Integer.parseInt(stz.nextToken());
		}

		// 회전 백트래킹 수행
		backtracking(0);

		// 출력
		System.out.println(min);

	}

	static void rotate() {

		int[][] arrNew = new int[N][M];

		// 깊은 복사
		for (int i = 0; i < N; i++) {
			arrNew[i] = arrOrigin[i].clone();
		}

		// 골라진 순서에 따라 회전 실행
		for (int idx = 0; idx < K; idx++) {
			// 회전 명령
			int r = order[sequence[idx]][0];
			int c = order[sequence[idx]][1];
			int s = order[sequence[idx]][2];

			// 각 반지름 가지는 원에 대해
			for (int rad = 1; rad <= s; rad++) {
				int temp = arrNew[r - rad][c - rad];

				// 각 4변에서 이동
				for (int dir = 3; dir >= 0; dir--) {
					// 해당변 시작 꼭짓점
					int nr = r + rad * mr[dir];
					int nc = c + rad * mc[dir];

					// 꼭지점부터 해당 변 길이-1만큼 이동수행
					for (int i = (rad << 1) - 1; i >= 0; i--) {
						arrNew[nr + (i + 1) * dr[dir]][nc + (i + 1) * dc[dir]] = arrNew[nr + i * dr[dir]][nc
								+ i * dc[dir]];
					}
				}

				arrNew[r - rad][c - rad + 1] = temp;
			}
		}

		// 각 행의 최소값 계산
		for (int i = 0; i < N; i++) {
			int sum = 0;

			for (int j = 0; j < M; j++)
				sum += arrNew[i][j];

			min = Math.min(min, sum);
		}

		return;
	}

	static void backtracking(int depth) {

		// 명령 순서 배열 완료시 회전 계산 후 종료
		if (depth == K) {
			rotate();
			return;
		}

		// 아직 사용하지 않은 명령 수행
		for (int i = 0; i < K; i++) {
			// 사용여부 확인
			if ((masked & 1 << i) != 0)
				continue;

			masked |= 1 << i;
			sequence[depth] = i;
			backtracking(depth + 1);
			masked ^= 1 << i;
		}
	}
}
